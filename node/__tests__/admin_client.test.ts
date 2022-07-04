const mockedPauseRequest = {
  setWaitOutstanding: jest.fn(() => {}),
  setMaxPauseWaitTime: jest.fn(() => {}),
};

const mockedCheckPausedResponse = {
  getPaused: jest.fn(() => {}),
};

const mockedGrpcAdminClient = {
  pause: jest.fn((_, cb) => {
    cb(null);
  }),
  unpause: jest.fn((_, cb) => {
    cb(null);
  }),
  checkPaused: jest.fn((_, cb) => {
    cb(null, mockedCheckPausedResponse);
  }),
};

jest.mock('../src/admin_pb.js', () => ({
  PauseRequest: jest.fn(() => mockedPauseRequest),
}));

jest.mock('../src/admin_grpc_pb.js', () => ({
  AdminClient: jest.fn(() => mockedGrpcAdminClient),
}));

import {AdminClient} from '../src/admin_client';

test('pause without maxTime', async () => {
  const client = new AdminClient('host', 0);
  await client.pause(false);

  expect(client.getHost()).toStrictEqual('host');
  expect(mockedGrpcAdminClient.pause).toBeCalled();
  expect(mockedPauseRequest.setWaitOutstanding).toBeCalledWith(false);
  expect(mockedPauseRequest.setMaxPauseWaitTime).not.toBeCalled();
});

test('pause with maxTime', async () => {
  const client = new AdminClient('host', 0);
  await client.pause(true, 10);

  expect(client.getHost()).toStrictEqual('host');
  expect(mockedGrpcAdminClient.pause).toBeCalled();
  expect(mockedPauseRequest.setWaitOutstanding).toBeCalledWith(true);
  expect(mockedPauseRequest.setMaxPauseWaitTime).toBeCalledWith(10);
});

test('unpause', async () => {
  const client = new AdminClient('host', 0);
  await client.unpause();

  expect(mockedGrpcAdminClient.unpause).toBeCalled();
});

test('checkPaused', async () => {
  const client = new AdminClient('host', 0);
  await client.checkPaused();

  expect(mockedGrpcAdminClient.checkPaused).toBeCalled();
  expect(mockedCheckPausedResponse.getPaused).toBeCalled();
});

test('pause but gRPC error', async () => {
  mockedGrpcAdminClient.pause = jest.fn((req, cb) => {
    cb(new Error('pause error'));
  });

  const client = new AdminClient('host', 0);

  await expect(client.pause(false)).rejects.toThrowError('pause error');
});

test('unpause but gRPC error', async () => {
  mockedGrpcAdminClient.unpause = jest.fn((req, cb) => {
    cb(new Error('unpause error'));
  });

  const client = new AdminClient('host', 0);

  await expect(client.unpause()).rejects.toThrowError('unpause error');
});

test('checkPaused but gRPC error', async () => {
  mockedGrpcAdminClient.checkPaused = jest.fn((req, cb) => {
    cb(new Error('checkPaused error'), null);
  });

  const client = new AdminClient('host', 0);

  await expect(client.checkPaused()).rejects.toThrowError('checkPaused error');
});
