package com.scalar.admin;

import com.google.common.annotations.VisibleForTesting;
import com.scalar.admin.exception.AdminException;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xbill.DNS.Lookup;
import org.xbill.DNS.Record;
import org.xbill.DNS.SRVRecord;
import org.xbill.DNS.TextParseException;
import org.xbill.DNS.Type;

@Immutable
public class RequestCoordinator {
  private static final Logger logger = LoggerFactory.getLogger(RequestCoordinator.class);
  private String srvServiceUrl;
  private List<InetSocketAddress> addresses;

  public RequestCoordinator(String srvServiceUrl) {
    this.srvServiceUrl = srvServiceUrl;
  }

  public RequestCoordinator(List<InetSocketAddress> addresses) {
    this.addresses = addresses;
  }

  public void pause(boolean waitOutstanding, @Nullable Long maxPauseWaitTime) {
    run(client -> client.pause(waitOutstanding, maxPauseWaitTime));
  }

  public void unpause() {
    run(AdminClient::unpause);
  }

  public JsonObject checkPaused() {
    JsonObjectBuilder builder = Json.createObjectBuilder();
    run(AdminClient::checkPaused).forEach((k, v) -> builder.add(k, v));
    return builder.build();
  }

  private Map<String, String> run(Function<AdminClient, Optional<String>> function) {
    // Assume that the list of addresses for unpause is the same as the one for pause.
    if (addresses == null) {
      addresses =
          getApplicationIps(srvServiceUrl).stream()
              .map(r -> new InetSocketAddress(r.getTarget().toString(true), r.getPort()))
              .collect(Collectors.toList());
    }

    ExecutorService executor = Executors.newCachedThreadPool();
    Map<String, Future<Optional<String>>> futures = new HashMap<>();
    addresses.forEach(
        address -> {
          String host = address.getHostString();
          int port = address.getPort();

          // use Callable to propagate exceptions
          Callable<Optional<String>> task =
              () -> {
                try (AdminClient client = getClient(host, port)) {
                  return function.apply(client);
                } catch (Exception e) {
                  logger.error(function.toString() + " failed.", e);
                  throw new AdminException(e);
                }
              };
          futures.put(host + ":" + port, executor.submit(task));
        });
    executor.shutdown();

    Map<String, String> results = new HashMap<>();
    try {
      for (Map.Entry<String, Future<Optional<String>>> entry : futures.entrySet()) {
        entry.getValue().get().ifPresent(v -> results.put(entry.getKey(), v));
      }
      boolean terminated = executor.awaitTermination(Long.MAX_VALUE, TimeUnit.HOURS);
      if (!terminated) {
        throw new TimeoutException();
      }
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
      throw new AdminException(e);
    }
    return results;
  }

  @VisibleForTesting
  List<SRVRecord> getApplicationIps(String srvServiceUrl) {
    Record[] records;
    try {
      records = new Lookup(srvServiceUrl, Type.SRV).run();
      if (records == null) {
        throw new AdminException("Can't get SRV records from " + srvServiceUrl);
      }
    } catch (TextParseException e) {
      logger.error(e.getMessage(), e);
      throw new AdminException(e);
    }

    List<SRVRecord> srvRecords = new ArrayList<>();
    for (int i = 0; i < records.length; i++) {
      srvRecords.add((SRVRecord) records[i]);
    }
    return srvRecords;
  }

  @VisibleForTesting
  AdminClient getClient(String host, int port) {
    return new AdminClient(host, port);
  }
}
