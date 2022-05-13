package com.scalar.admin;

import com.google.protobuf.Empty;
import com.scalar.admin.exception.AdminException;
import com.scalar.admin.rpc.AdminGrpc;
import com.scalar.admin.rpc.PauseRequest;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import java.io.Closeable;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;

@Immutable
public class AdminClient implements Closeable {
  private final ManagedChannel channel;
  private final AdminGrpc.AdminBlockingStub blockingStub;

  public AdminClient(String host, int port) {
    this.channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext().build();
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

  public Optional<String> stats() {
    try {
      return Optional.of(blockingStub.stats(Empty.newBuilder().build()).getStats());
    } catch (Exception e) {
      throw new AdminException("stats failed.", e);
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
