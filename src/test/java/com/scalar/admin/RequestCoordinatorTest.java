package com.scalar.admin;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.scalar.admin.exception.AdminException;
import java.io.StringReader;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javax.json.Json;
import javax.json.JsonObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.xbill.DNS.DClass;
import org.xbill.DNS.Name;
import org.xbill.DNS.SRVRecord;
import org.xbill.DNS.TextParseException;

public class RequestCoordinatorTest {
  private final String SRV_SERVICE_URL = "srv_service_url.";
  private final String APP_IP1 = "ip1";
  private final String APP_IP2 = "ip2";
  private final String APP_IP3 = "ip3";
  private final String JSON_RESULT = "{\"total_succeeded\": 10}";
  private final int PORT = 50051;
  private RequestCoordinator coordinator;

  @Before
  public void setUp() {
    coordinator = Mockito.spy(new RequestCoordinator(SRV_SERVICE_URL));
  }

  public List<SRVRecord> prepareSrvRecords() throws TextParseException {
    return Arrays.asList(
        new SRVRecord(
            new Name(SRV_SERVICE_URL), DClass.IN, 60, 0, 0, PORT, new Name(APP_IP1 + ".")),
        new SRVRecord(
            new Name(SRV_SERVICE_URL), DClass.IN, 60, 0, 0, PORT, new Name(APP_IP2 + ".")),
        new SRVRecord(
            new Name(SRV_SERVICE_URL), DClass.IN, 60, 0, 0, PORT, new Name(APP_IP3 + ".")));
  }

  @Test
  public void pause_SrvServiceUrlGiven_ShouldPauseAll() throws TextParseException {
    // Arrange
    List<SRVRecord> records = prepareSrvRecords();
    doReturn(records).when(coordinator).getApplicationIps(SRV_SERVICE_URL);
    AdminClient client1 = mock(AdminClient.class);
    when(coordinator.getClient(APP_IP1, PORT)).thenReturn(client1);
    AdminClient client2 = mock(AdminClient.class);
    when(coordinator.getClient(APP_IP2, PORT)).thenReturn(client2);
    AdminClient client3 = mock(AdminClient.class);
    when(coordinator.getClient(APP_IP3, PORT)).thenReturn(client3);

    // Act
    coordinator.pause(true);

    // Assert
    verify(coordinator).getClient(APP_IP1, PORT);
    verify(client1).pause(true);
    verify(coordinator).getClient(APP_IP2, PORT);
    verify(client2).pause(true);
    verify(coordinator).getClient(APP_IP3, PORT);
    verify(client3).pause(true);
  }

  @Test
  public void pause_SomeExceptionThrown_ShouldThrowAdminException() throws TextParseException {
    // Arrange
    List<SRVRecord> records = prepareSrvRecords();
    doReturn(records).when(coordinator).getApplicationIps(SRV_SERVICE_URL);
    AdminClient client1 = mock(AdminClient.class);
    when(coordinator.getClient(APP_IP1, PORT)).thenReturn(client1);
    RuntimeException toThrow = mock(RuntimeException.class);
    doThrow(toThrow).when(client1).pause(true);
    AdminClient client2 = mock(AdminClient.class);
    when(coordinator.getClient(APP_IP2, PORT)).thenReturn(client2);
    AdminClient client3 = mock(AdminClient.class);
    when(coordinator.getClient(APP_IP3, PORT)).thenReturn(client3);

    // Act Assert
    assertThatThrownBy(() -> coordinator.pause(true)).isInstanceOf(AdminException.class);
  }

  @Test
  public void unpause_SrvServiceUrlGiven_ShouldUnpauseAll() throws TextParseException {
    // Arrange
    List<SRVRecord> records = prepareSrvRecords();
    doReturn(records).when(coordinator).getApplicationIps(SRV_SERVICE_URL);
    AdminClient client1 = mock(AdminClient.class);
    when(coordinator.getClient(APP_IP1, PORT)).thenReturn(client1);
    AdminClient client2 = mock(AdminClient.class);
    when(coordinator.getClient(APP_IP2, PORT)).thenReturn(client2);
    AdminClient client3 = mock(AdminClient.class);
    when(coordinator.getClient(APP_IP3, PORT)).thenReturn(client3);

    // Act
    coordinator.unpause();

    // Assert
    verify(coordinator).getClient(APP_IP1, PORT);
    verify(client1).unpause();
    verify(coordinator).getClient(APP_IP2, PORT);
    verify(client2).unpause();
    verify(coordinator).getClient(APP_IP3, PORT);
    verify(client3).unpause();
  }

  @Test
  public void unpause_SomeExceptionThrown_ShouldThrowAdminException() throws TextParseException {
    // Arrange
    List<SRVRecord> records = prepareSrvRecords();
    doReturn(records).when(coordinator).getApplicationIps(SRV_SERVICE_URL);
    AdminClient client1 = mock(AdminClient.class);
    when(coordinator.getClient(APP_IP1, PORT)).thenReturn(client1);
    RuntimeException toThrow = mock(RuntimeException.class);
    doThrow(toThrow).when(client1).unpause();
    AdminClient client2 = mock(AdminClient.class);
    when(coordinator.getClient(APP_IP2, PORT)).thenReturn(client2);
    AdminClient client3 = mock(AdminClient.class);
    when(coordinator.getClient(APP_IP3, PORT)).thenReturn(client3);

    // Act Assert
    assertThatThrownBy(() -> coordinator.unpause()).isInstanceOf(AdminException.class);
  }

  @Test
  public void stats_SrvServiceUrlGiven_ShouldUnpauseAll() throws TextParseException {
    // Arrange
    List<SRVRecord> records = prepareSrvRecords();
    doReturn(records).when(coordinator).getApplicationIps(SRV_SERVICE_URL);
    AdminClient client1 = mock(AdminClient.class);
    when(coordinator.getClient(APP_IP1, PORT)).thenReturn(client1);
    when(client1.stats()).thenReturn(Optional.of(JSON_RESULT));
    AdminClient client2 = mock(AdminClient.class);
    when(coordinator.getClient(APP_IP2, PORT)).thenReturn(client2);
    when(client2.stats()).thenReturn(Optional.of(JSON_RESULT));
    AdminClient client3 = mock(AdminClient.class);
    when(coordinator.getClient(APP_IP3, PORT)).thenReturn(client3);
    when(client3.stats()).thenReturn(Optional.of(JSON_RESULT));

    // Act
    JsonObject actual = coordinator.stats();

    // Assert
    verify(coordinator).getClient(APP_IP1, PORT);
    verify(client1).stats();
    verify(coordinator).getClient(APP_IP2, PORT);
    verify(client2).stats();
    verify(coordinator).getClient(APP_IP3, PORT);
    verify(client3).stats();
    JsonObject result = Json.createReader(new StringReader(JSON_RESULT)).readObject();
    JsonObject expected =
        Json.createObjectBuilder()
            .add(APP_IP1, result)
            .add(APP_IP2, result)
            .add(APP_IP3, result)
            .build();
    assertThat(actual).isEqualTo(expected);
  }
}
