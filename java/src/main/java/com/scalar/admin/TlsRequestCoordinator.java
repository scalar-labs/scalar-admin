package com.scalar.admin;

import java.net.InetSocketAddress;
import java.util.List;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

@Immutable
public class TlsRequestCoordinator extends RequestCoordinator {

  private final String caRootCert;
  private final String overrideAuthority;

  public TlsRequestCoordinator(
      String srvServiceUrl, @Nullable String caRootCert, @Nullable String overrideAuthority) {
    super(srvServiceUrl);

    this.caRootCert = caRootCert;
    this.overrideAuthority = overrideAuthority;
  }

  public TlsRequestCoordinator(
      List<InetSocketAddress> addresses,
      @Nullable String caRootCert,
      @Nullable String overrideAuthority) {
    super(addresses);

    this.caRootCert = caRootCert;
    this.overrideAuthority = overrideAuthority;
  }

  @Override
  AdminClient getClient(String host, int port) {
    return new AdminClient(host, port, caRootCert, overrideAuthority);
  }
}
