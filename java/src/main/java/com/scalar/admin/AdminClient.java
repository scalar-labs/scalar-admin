package com.scalar.admin;

import com.google.protobuf.Empty;
import com.scalar.admin.exception.AdminException;
import com.scalar.admin.rpc.AdminGrpc;
import com.scalar.admin.rpc.PauseRequest;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.netty.shaded.io.grpc.netty.GrpcSslContexts;
import io.grpc.netty.shaded.io.grpc.netty.NettyChannelBuilder;
import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import javax.net.ssl.SSLException;

@Immutable
public class AdminClient implements Closeable {
  private final ManagedChannel channel;
  private final AdminGrpc.AdminBlockingStub blockingStub;

  public AdminClient(String host, int port) {
    this.channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
    this.blockingStub = AdminGrpc.newBlockingStub(channel);
  }

  public AdminClient(
      String host, int port, @Nullable String caRootCert, @Nullable String overrideAuthority) {

    NettyChannelBuilder builder = NettyChannelBuilder.forAddress(host, port).useTransportSecurity();

    if (caRootCert != null) {
      try {
        builder.sslContext(
            GrpcSslContexts.forClient()
                .trustManager(new ByteArrayInputStream(caRootCert.getBytes(StandardCharsets.UTF_8)))
                .build());
      } catch (SSLException e) {
        throw new AdminException("Couldn't configure TLS.", e);
      }
    }

    if (overrideAuthority != null) {
      builder.overrideAuthority(overrideAuthority);
    }

    this.channel = builder.build();
    this.blockingStub = AdminGrpc.newBlockingStub(channel);
  }

  public Optional<String> pause(boolean waitOutstanding, @Nullable Long maxPauseWaitTime) {
    PauseRequest.Builder builder = PauseRequest.newBuilder().setWaitOutstanding(waitOutstanding);
    if (maxPauseWaitTime != null) {
      builder.setMaxPauseWaitTime(maxPauseWaitTime);
    }
    try {
      blockingStub.pause(builder.build());
      return Optional.empty();
    } catch (Exception e) {
      throw new AdminException("pause failed.", e);
    }
  }

  public Optional<String> unpause() {
    try {
      blockingStub.unpause(Empty.newBuilder().build());
      return Optional.empty();
    } catch (Exception e) {
      throw new AdminException("unpause failed.", e);
    }
  }

  public Optional<String> checkPaused() {
    try {
      return Optional.of(
          String.valueOf(blockingStub.checkPaused(Empty.newBuilder().build()).getPaused()));
    } catch (Exception e) {
      throw new AdminException("failed to check the paused status.", e);
    }
  }

  @Override
  public void close() {
    try {
      channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    } catch (InterruptedException e) {
      throw new AdminException("close failed.", e);
    }
  }
}
