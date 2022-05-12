// GENERATED CODE -- DO NOT EDIT!

'use strict';
const grpc = require('@grpc/grpc-js');
const admin_pb = require('./admin_pb.js');
const google_protobuf_empty_pb = require('google-protobuf/google/protobuf/empty_pb.js');

function serialize_google_protobuf_Empty(arg) {
  if (!(arg instanceof google_protobuf_empty_pb.Empty)) {
    throw new Error('Expected argument of type google.protobuf.Empty');
  }
  return Buffer.from(arg.serializeBinary());
}

function deserialize_google_protobuf_Empty(buffer_arg) {
  return google_protobuf_empty_pb.Empty.deserializeBinary(
    new Uint8Array(buffer_arg)
  );
}

function serialize_rpc_PauseRequest(arg) {
  if (!(arg instanceof admin_pb.PauseRequest)) {
    throw new Error('Expected argument of type rpc.PauseRequest');
  }
  return Buffer.from(arg.serializeBinary());
}

function deserialize_rpc_PauseRequest(buffer_arg) {
  return admin_pb.PauseRequest.deserializeBinary(new Uint8Array(buffer_arg));
}

function serialize_rpc_StatsResponse(arg) {
  if (!(arg instanceof admin_pb.StatsResponse)) {
    throw new Error('Expected argument of type rpc.StatsResponse');
  }
  return Buffer.from(arg.serializeBinary());
}

function deserialize_rpc_StatsResponse(buffer_arg) {
  return admin_pb.StatsResponse.deserializeBinary(new Uint8Array(buffer_arg));
}

const AdminService = (exports.AdminService = {
  pause: {
    path: '/rpc.Admin/Pause',
    requestStream: false,
    responseStream: false,
    requestType: admin_pb.PauseRequest,
    responseType: google_protobuf_empty_pb.Empty,
    requestSerialize: serialize_rpc_PauseRequest,
    requestDeserialize: deserialize_rpc_PauseRequest,
    responseSerialize: serialize_google_protobuf_Empty,
    responseDeserialize: deserialize_google_protobuf_Empty,
  },
  unpause: {
    path: '/rpc.Admin/Unpause',
    requestStream: false,
    responseStream: false,
    requestType: google_protobuf_empty_pb.Empty,
    responseType: google_protobuf_empty_pb.Empty,
    requestSerialize: serialize_google_protobuf_Empty,
    requestDeserialize: deserialize_google_protobuf_Empty,
    responseSerialize: serialize_google_protobuf_Empty,
    responseDeserialize: deserialize_google_protobuf_Empty,
  },
  stats: {
    path: '/rpc.Admin/Stats',
    requestStream: false,
    responseStream: false,
    requestType: google_protobuf_empty_pb.Empty,
    responseType: admin_pb.StatsResponse,
    requestSerialize: serialize_google_protobuf_Empty,
    requestDeserialize: deserialize_google_protobuf_Empty,
    responseSerialize: serialize_rpc_StatsResponse,
    responseDeserialize: deserialize_rpc_StatsResponse,
  },
});

exports.AdminClient = grpc.makeGenericClientConstructor(AdminService);
