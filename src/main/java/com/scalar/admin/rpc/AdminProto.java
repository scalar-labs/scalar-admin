// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: scalar/protobuf/admin.proto

package com.scalar.admin.rpc;

public final class AdminProto {
  private AdminProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_rpc_PauseRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_rpc_PauseRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_rpc_StatsResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_rpc_StatsResponse_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\033scalar/protobuf/admin.proto\022\003rpc\032\033goog" +
      "le/protobuf/empty.proto\"b\n\014PauseRequest\022" +
      "\030\n\020wait_outstanding\030\001 \001(\010\022 \n\023max_pause_w" +
      "ait_time\030\002 \001(\003H\000\210\001\001B\026\n\024_max_pause_wait_t" +
      "ime\"\036\n\rStatsResponse\022\r\n\005stats\030\001 \001(\t2\261\001\n\005" +
      "Admin\0224\n\005Pause\022\021.rpc.PauseRequest\032\026.goog" +
      "le.protobuf.Empty\"\000\022;\n\007Unpause\022\026.google." +
      "protobuf.Empty\032\026.google.protobuf.Empty\"\000" +
      "\0225\n\005Stats\022\026.google.protobuf.Empty\032\022.rpc." +
      "StatsResponse\"\000B$\n\024com.scalar.admin.rpcB" +
      "\nAdminProtoP\001b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          com.google.protobuf.EmptyProto.getDescriptor(),
        });
    internal_static_rpc_PauseRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_rpc_PauseRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_rpc_PauseRequest_descriptor,
        new java.lang.String[] { "WaitOutstanding", "MaxPauseWaitTime", "MaxPauseWaitTime", });
    internal_static_rpc_StatsResponse_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_rpc_StatsResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_rpc_StatsResponse_descriptor,
        new java.lang.String[] { "Stats", });
    com.google.protobuf.EmptyProto.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
