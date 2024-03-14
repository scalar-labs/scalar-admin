package com.scalar.admin.rpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.60.2)",
    comments = "Source: scalar/protobuf/admin.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class AdminGrpc {

  private AdminGrpc() {}

  public static final java.lang.String SERVICE_NAME = "rpc.Admin";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.scalar.admin.rpc.PauseRequest,
      com.google.protobuf.Empty> getPauseMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Pause",
      requestType = com.scalar.admin.rpc.PauseRequest.class,
      responseType = com.google.protobuf.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.scalar.admin.rpc.PauseRequest,
      com.google.protobuf.Empty> getPauseMethod() {
    io.grpc.MethodDescriptor<com.scalar.admin.rpc.PauseRequest, com.google.protobuf.Empty> getPauseMethod;
    if ((getPauseMethod = AdminGrpc.getPauseMethod) == null) {
      synchronized (AdminGrpc.class) {
        if ((getPauseMethod = AdminGrpc.getPauseMethod) == null) {
          AdminGrpc.getPauseMethod = getPauseMethod =
              io.grpc.MethodDescriptor.<com.scalar.admin.rpc.PauseRequest, com.google.protobuf.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Pause"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.scalar.admin.rpc.PauseRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setSchemaDescriptor(new AdminMethodDescriptorSupplier("Pause"))
              .build();
        }
      }
    }
    return getPauseMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      com.google.protobuf.Empty> getUnpauseMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Unpause",
      requestType = com.google.protobuf.Empty.class,
      responseType = com.google.protobuf.Empty.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      com.google.protobuf.Empty> getUnpauseMethod() {
    io.grpc.MethodDescriptor<com.google.protobuf.Empty, com.google.protobuf.Empty> getUnpauseMethod;
    if ((getUnpauseMethod = AdminGrpc.getUnpauseMethod) == null) {
      synchronized (AdminGrpc.class) {
        if ((getUnpauseMethod = AdminGrpc.getUnpauseMethod) == null) {
          AdminGrpc.getUnpauseMethod = getUnpauseMethod =
              io.grpc.MethodDescriptor.<com.google.protobuf.Empty, com.google.protobuf.Empty>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Unpause"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setSchemaDescriptor(new AdminMethodDescriptorSupplier("Unpause"))
              .build();
        }
      }
    }
    return getUnpauseMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      com.scalar.admin.rpc.CheckPausedResponse> getCheckPausedMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CheckPaused",
      requestType = com.google.protobuf.Empty.class,
      responseType = com.scalar.admin.rpc.CheckPausedResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      com.scalar.admin.rpc.CheckPausedResponse> getCheckPausedMethod() {
    io.grpc.MethodDescriptor<com.google.protobuf.Empty, com.scalar.admin.rpc.CheckPausedResponse> getCheckPausedMethod;
    if ((getCheckPausedMethod = AdminGrpc.getCheckPausedMethod) == null) {
      synchronized (AdminGrpc.class) {
        if ((getCheckPausedMethod = AdminGrpc.getCheckPausedMethod) == null) {
          AdminGrpc.getCheckPausedMethod = getCheckPausedMethod =
              io.grpc.MethodDescriptor.<com.google.protobuf.Empty, com.scalar.admin.rpc.CheckPausedResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CheckPaused"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.scalar.admin.rpc.CheckPausedResponse.getDefaultInstance()))
              .setSchemaDescriptor(new AdminMethodDescriptorSupplier("CheckPaused"))
              .build();
        }
      }
    }
    return getCheckPausedMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static AdminStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AdminStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AdminStub>() {
        @java.lang.Override
        public AdminStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AdminStub(channel, callOptions);
        }
      };
    return AdminStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static AdminBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AdminBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AdminBlockingStub>() {
        @java.lang.Override
        public AdminBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AdminBlockingStub(channel, callOptions);
        }
      };
    return AdminBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static AdminFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AdminFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AdminFutureStub>() {
        @java.lang.Override
        public AdminFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AdminFutureStub(channel, callOptions);
        }
      };
    return AdminFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void pause(com.scalar.admin.rpc.PauseRequest request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getPauseMethod(), responseObserver);
    }

    /**
     */
    default void unpause(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUnpauseMethod(), responseObserver);
    }

    /**
     */
    default void checkPaused(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<com.scalar.admin.rpc.CheckPausedResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCheckPausedMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service Admin.
   */
  public static abstract class AdminImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return AdminGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service Admin.
   */
  public static final class AdminStub
      extends io.grpc.stub.AbstractAsyncStub<AdminStub> {
    private AdminStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AdminStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AdminStub(channel, callOptions);
    }

    /**
     */
    public void pause(com.scalar.admin.rpc.PauseRequest request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getPauseMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void unpause(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<com.google.protobuf.Empty> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUnpauseMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void checkPaused(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<com.scalar.admin.rpc.CheckPausedResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCheckPausedMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service Admin.
   */
  public static final class AdminBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<AdminBlockingStub> {
    private AdminBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AdminBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AdminBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.google.protobuf.Empty pause(com.scalar.admin.rpc.PauseRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getPauseMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.google.protobuf.Empty unpause(com.google.protobuf.Empty request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUnpauseMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.scalar.admin.rpc.CheckPausedResponse checkPaused(com.google.protobuf.Empty request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCheckPausedMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service Admin.
   */
  public static final class AdminFutureStub
      extends io.grpc.stub.AbstractFutureStub<AdminFutureStub> {
    private AdminFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AdminFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AdminFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.google.protobuf.Empty> pause(
        com.scalar.admin.rpc.PauseRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getPauseMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.google.protobuf.Empty> unpause(
        com.google.protobuf.Empty request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUnpauseMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.scalar.admin.rpc.CheckPausedResponse> checkPaused(
        com.google.protobuf.Empty request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCheckPausedMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_PAUSE = 0;
  private static final int METHODID_UNPAUSE = 1;
  private static final int METHODID_CHECK_PAUSED = 2;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_PAUSE:
          serviceImpl.pause((com.scalar.admin.rpc.PauseRequest) request,
              (io.grpc.stub.StreamObserver<com.google.protobuf.Empty>) responseObserver);
          break;
        case METHODID_UNPAUSE:
          serviceImpl.unpause((com.google.protobuf.Empty) request,
              (io.grpc.stub.StreamObserver<com.google.protobuf.Empty>) responseObserver);
          break;
        case METHODID_CHECK_PAUSED:
          serviceImpl.checkPaused((com.google.protobuf.Empty) request,
              (io.grpc.stub.StreamObserver<com.scalar.admin.rpc.CheckPausedResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getPauseMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.scalar.admin.rpc.PauseRequest,
              com.google.protobuf.Empty>(
                service, METHODID_PAUSE)))
        .addMethod(
          getUnpauseMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.google.protobuf.Empty,
              com.google.protobuf.Empty>(
                service, METHODID_UNPAUSE)))
        .addMethod(
          getCheckPausedMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.google.protobuf.Empty,
              com.scalar.admin.rpc.CheckPausedResponse>(
                service, METHODID_CHECK_PAUSED)))
        .build();
  }

  private static abstract class AdminBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    AdminBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.scalar.admin.rpc.AdminProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Admin");
    }
  }

  private static final class AdminFileDescriptorSupplier
      extends AdminBaseDescriptorSupplier {
    AdminFileDescriptorSupplier() {}
  }

  private static final class AdminMethodDescriptorSupplier
      extends AdminBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    AdminMethodDescriptorSupplier(java.lang.String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (AdminGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new AdminFileDescriptorSupplier())
              .addMethod(getPauseMethod())
              .addMethod(getUnpauseMethod())
              .addMethod(getCheckPausedMethod())
              .build();
        }
      }
    }
    return result;
  }
}
