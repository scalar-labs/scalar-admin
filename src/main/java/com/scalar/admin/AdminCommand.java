package com.scalar.admin;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.UncheckedIOException;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonWriter;
import javax.json.JsonWriterFactory;
import javax.json.stream.JsonGenerator;
import picocli.CommandLine;
import picocli.CommandLine.Command;

@Command(
    name = "scalar-admin",
    description =
        "Execute an admin command for applications that implement scalar admin interface.")
public class AdminCommand implements Callable<Integer> {

  @CommandLine.Option(
      names = {"--command", "-c"},
      required = true,
      paramLabel = "COMMAND",
      description = "A command to execute.")
  private com.scalar.admin.Command command;

  @CommandLine.Option(
      names = {"--srv-service-url", "-s"},
      paramLabel = "SRV_SERVICE_URL",
      description = "A service URL of SRV record. (deprecated)")
  private String srvServiceUrl;

  @CommandLine.Option(
      names = {"--addresses", "-a"},
      split = ",",
      paramLabel = "ADDRESSES",
      description = "A list of {ip}:{port} separated by comma.")
  private List<String> addresses;

  @CommandLine.Option(
      names = {"--no-wait", "-n"},
      defaultValue = "false",
      description =
          "A flag to specify if PAUSE command waits for termination of outstanding requests.")
  private boolean noWait;

  @CommandLine.Option(
      names = {"--max-pause-wait-time", "-m"},
      description = "A max pause wait time in milliseconds.")
  private Long maxPauseWaitTime;

  @CommandLine.Option(
      names = {"--tls"},
      description = "Whether wire encryption (TLS) between scalar-admin and the target is enabled.")
  private boolean tlsEnabled;

  @CommandLine.Option(
      names = {"--ca-root-cert-path"},
      description =
          "A path to a root certificate file for verifying the server's certificate when wire"
              + " encryption is enabled.")
  private String caRootCertPath;

  @CommandLine.Option(
      names = {"--ca-root-cert-pem"},
      description =
          "A PEM format string of a root certificate for verifying the server's certificate when"
              + " wire encryption is enabled. This option is prioritized when --ca-root-cert-path"
              + " is specified.")
  private String caRootCertPem;

  @CommandLine.Option(
      names = {"--override-authority"},
      description =
          "The value to be used as the expected authority in the server's certificate when wire"
              + " encryption is enabled.")
  private String overrideAuthority;

  @CommandLine.Option(
      names = {"-h", "--help"},
      usageHelp = true,
      description = "display the help message.")
  boolean helpRequested;

  public static void main(String[] args) {
    int exitCode =
        new CommandLine(new AdminCommand()).setCaseInsensitiveEnumValuesAllowed(true).execute(args);
    System.exit(exitCode);
  }

  @Override
  public Integer call() {
    RequestCoordinator coordinator = null;

    if ((srvServiceUrl != null && addresses != null)
        || (srvServiceUrl == null && addresses == null)) {
      throw new IllegalArgumentException(
          "It's required to specify only either [--srv-service-url, -s] or [--addresses, -a].");
    } else if (srvServiceUrl != null) {
      coordinator = createCoordinator(srvServiceUrl);
    } else { // addresses != null
      coordinator =
          createCoordinator(
              addresses.stream()
                  .map(
                      a -> {
                        String[] hostAndPort = a.split(":");
                        if (hostAndPort.length != 2) {
                          throw new IllegalArgumentException(
                              "Invalid address format. Expected {ip}:{port} but got " + a);
                        }

                        return new InetSocketAddress(
                            hostAndPort[0], Integer.parseInt(hostAndPort[1]));
                      })
                  .collect(Collectors.toList()));
    }

    switch (command) {
      case PAUSE:
        coordinator.pause(!noWait, maxPauseWaitTime);
        System.out.println("Pause completed at " + getCurrentTimeWithFormat());
        break;
      case UNPAUSE:
        System.out.println("Unpause started at " + getCurrentTimeWithFormat());
        coordinator.unpause();
        break;
      case CHECK_PAUSED:
        printJson(coordinator.checkPaused());
        break;
    }
    return 0;
  }

  static void printJson(JsonObject json) {
    Map<String, Object> properties = new HashMap<>();
    properties.put(JsonGenerator.PRETTY_PRINTING, true);
    JsonWriterFactory writerFactory = Json.createWriterFactory(properties);
    StringWriter stringWriter = new StringWriter();
    JsonWriter jsonWriter = writerFactory.createWriter(stringWriter);
    jsonWriter.writeObject(json);
    System.out.println(stringWriter.toString().trim());
  }

  static String getCurrentTimeWithFormat() {
    Instant instant = Instant.now();
    return instant + " UTC (" + instant.toEpochMilli() + ")";
  }

  private RequestCoordinator createCoordinator(String srvServiceUrl) {
    return tlsEnabled
        ? new TlsRequestCoordinator(srvServiceUrl, getCaRootCert(), overrideAuthority)
        : new RequestCoordinator(srvServiceUrl);
  }

  private RequestCoordinator createCoordinator(List<InetSocketAddress> addresses) {
    return tlsEnabled
        ? new TlsRequestCoordinator(addresses, getCaRootCert(), overrideAuthority)
        : new RequestCoordinator(addresses);
  }

  private String getCaRootCert() {
    String caRootCert = null;

    if (caRootCertPem != null) {
      caRootCert = caRootCertPem.replace("\\n", System.lineSeparator());
    } else if (caRootCertPath != null) {
      try {
        caRootCert =
            new String(
                Files.readAllBytes(new File(caRootCertPath).toPath()), StandardCharsets.UTF_8);
      } catch (IOException e) {
        throw new UncheckedIOException("Couldn't read the file: " + caRootCertPath, e);
      }
    }

    return caRootCert;
  }
}
