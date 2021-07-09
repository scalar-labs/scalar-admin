package com.scalar.admin;

import java.io.StringWriter;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
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
      required = true,
      paramLabel = "SRV_SERVICE_URL",
      description = "A service URL of SRV record.")
  private String srvServiceUrl;

  @CommandLine.Option(
      names = {"--wait-outstanding", "-w"},
      defaultValue = "false",
      description =
          "A flag to specify if PAUSE command waits for termination of outstanding requests.")
  private boolean waitOutstanding;

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
    RequestCoordinator coordinator = new RequestCoordinator(srvServiceUrl);
    switch (command) {
      case PAUSE:
        coordinator.pause(waitOutstanding);
        System.out.println("Pause completed at " + getCurrentTimeWithFormat());
        break;
      case UNPAUSE:
        System.out.println("Unpause started at " + getCurrentTimeWithFormat());
        coordinator.unpause();
        break;
      case STATS:
        printJson(coordinator.stats());
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
}
