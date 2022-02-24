// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: scalar/protobuf/admin.proto

package com.scalar.admin.rpc;

/**
 * Protobuf type {@code rpc.StatsResponse}
 */
public final class StatsResponse extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:rpc.StatsResponse)
    StatsResponseOrBuilder {
private static final long serialVersionUID = 0L;
  // Use StatsResponse.newBuilder() to construct.
  private StatsResponse(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private StatsResponse() {
    stats_ = "";
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new StatsResponse();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private StatsResponse(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          case 10: {
            java.lang.String s = input.readStringRequireUtf8();

            stats_ = s;
            break;
          }
          default: {
            if (!parseUnknownField(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.scalar.admin.rpc.AdminProto.internal_static_rpc_StatsResponse_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.scalar.admin.rpc.AdminProto.internal_static_rpc_StatsResponse_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.scalar.admin.rpc.StatsResponse.class, com.scalar.admin.rpc.StatsResponse.Builder.class);
  }

  public static final int STATS_FIELD_NUMBER = 1;
  private volatile java.lang.Object stats_;
  /**
   * <pre>
   * json-formatted
   * </pre>
   *
   * <code>string stats = 1;</code>
   * @return The stats.
   */
  @java.lang.Override
  public java.lang.String getStats() {
    java.lang.Object ref = stats_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      stats_ = s;
      return s;
    }
  }
  /**
   * <pre>
   * json-formatted
   * </pre>
   *
   * <code>string stats = 1;</code>
   * @return The bytes for stats.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getStatsBytes() {
    java.lang.Object ref = stats_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      stats_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(stats_)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, stats_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(stats_)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, stats_);
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof com.scalar.admin.rpc.StatsResponse)) {
      return super.equals(obj);
    }
    com.scalar.admin.rpc.StatsResponse other = (com.scalar.admin.rpc.StatsResponse) obj;

    if (!getStats()
        .equals(other.getStats())) return false;
    if (!unknownFields.equals(other.unknownFields)) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + STATS_FIELD_NUMBER;
    hash = (53 * hash) + getStats().hashCode();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.scalar.admin.rpc.StatsResponse parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.scalar.admin.rpc.StatsResponse parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.scalar.admin.rpc.StatsResponse parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.scalar.admin.rpc.StatsResponse parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.scalar.admin.rpc.StatsResponse parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.scalar.admin.rpc.StatsResponse parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.scalar.admin.rpc.StatsResponse parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.scalar.admin.rpc.StatsResponse parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.scalar.admin.rpc.StatsResponse parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.scalar.admin.rpc.StatsResponse parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.scalar.admin.rpc.StatsResponse parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.scalar.admin.rpc.StatsResponse parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(com.scalar.admin.rpc.StatsResponse prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code rpc.StatsResponse}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:rpc.StatsResponse)
      com.scalar.admin.rpc.StatsResponseOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.scalar.admin.rpc.AdminProto.internal_static_rpc_StatsResponse_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.scalar.admin.rpc.AdminProto.internal_static_rpc_StatsResponse_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.scalar.admin.rpc.StatsResponse.class, com.scalar.admin.rpc.StatsResponse.Builder.class);
    }

    // Construct using com.scalar.admin.rpc.StatsResponse.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      stats_ = "";

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.scalar.admin.rpc.AdminProto.internal_static_rpc_StatsResponse_descriptor;
    }

    @java.lang.Override
    public com.scalar.admin.rpc.StatsResponse getDefaultInstanceForType() {
      return com.scalar.admin.rpc.StatsResponse.getDefaultInstance();
    }

    @java.lang.Override
    public com.scalar.admin.rpc.StatsResponse build() {
      com.scalar.admin.rpc.StatsResponse result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.scalar.admin.rpc.StatsResponse buildPartial() {
      com.scalar.admin.rpc.StatsResponse result = new com.scalar.admin.rpc.StatsResponse(this);
      result.stats_ = stats_;
      onBuilt();
      return result;
    }

    @java.lang.Override
    public Builder clone() {
      return super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.scalar.admin.rpc.StatsResponse) {
        return mergeFrom((com.scalar.admin.rpc.StatsResponse)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.scalar.admin.rpc.StatsResponse other) {
      if (other == com.scalar.admin.rpc.StatsResponse.getDefaultInstance()) return this;
      if (!other.getStats().isEmpty()) {
        stats_ = other.stats_;
        onChanged();
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      com.scalar.admin.rpc.StatsResponse parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.scalar.admin.rpc.StatsResponse) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private java.lang.Object stats_ = "";
    /**
     * <pre>
     * json-formatted
     * </pre>
     *
     * <code>string stats = 1;</code>
     * @return The stats.
     */
    public java.lang.String getStats() {
      java.lang.Object ref = stats_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        stats_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <pre>
     * json-formatted
     * </pre>
     *
     * <code>string stats = 1;</code>
     * @return The bytes for stats.
     */
    public com.google.protobuf.ByteString
        getStatsBytes() {
      java.lang.Object ref = stats_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        stats_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <pre>
     * json-formatted
     * </pre>
     *
     * <code>string stats = 1;</code>
     * @param value The stats to set.
     * @return This builder for chaining.
     */
    public Builder setStats(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      stats_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * json-formatted
     * </pre>
     *
     * <code>string stats = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearStats() {
      
      stats_ = getDefaultInstance().getStats();
      onChanged();
      return this;
    }
    /**
     * <pre>
     * json-formatted
     * </pre>
     *
     * <code>string stats = 1;</code>
     * @param value The bytes for stats to set.
     * @return This builder for chaining.
     */
    public Builder setStatsBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      stats_ = value;
      onChanged();
      return this;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:rpc.StatsResponse)
  }

  // @@protoc_insertion_point(class_scope:rpc.StatsResponse)
  private static final com.scalar.admin.rpc.StatsResponse DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.scalar.admin.rpc.StatsResponse();
  }

  public static com.scalar.admin.rpc.StatsResponse getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<StatsResponse>
      PARSER = new com.google.protobuf.AbstractParser<StatsResponse>() {
    @java.lang.Override
    public StatsResponse parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new StatsResponse(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<StatsResponse> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<StatsResponse> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.scalar.admin.rpc.StatsResponse getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

