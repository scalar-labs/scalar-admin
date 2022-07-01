/* eslint-disable node/no-unpublished-import */

const originalDns = jest.requireActual('dns');

const markedResolver = {
  resolveSrv: jest.fn(() => Promise.resolve([{name: 'host', port: 0}])),
  resolve4: jest.fn(() => Promise.resolve(['ip1', 'ip2'])),
};

jest.mock('dns', () => ({
  ...originalDns,
  promises: {
    Resolver: jest.fn(() => markedResolver),
  },
}));

const markedAdminClient = {
  pause: jest.fn(() => Promise.resolve()),
  unpause: jest.fn(() => Promise.resolve()),
  checkPaused: jest.fn(() => Promise.resolve(false)),
  getHost: jest.fn(),
};

jest.mock('../src/admin_client', () => ({
  AdminClient: jest.fn(() => markedAdminClient),
}));

import {RequestCoordinator} from '../src/request_coordinator';

beforeEach(() => {
  markedAdminClient.pause.mockRestore();
  markedAdminClient.unpause.mockRestore();
});

test('pause successfully', async () => {
  const coordinator = new RequestCoordinator('srv');

  await coordinator.pause(false);

  expect(markedAdminClient.pause).toBeCalledWith(false, null);
  expect(markedAdminClient.pause).toBeCalledTimes(2);
});

test('pause unsuccessfully', async () => {
  markedAdminClient.pause = jest.fn(() => Promise.reject(new Error()));

  const coordinator = new RequestCoordinator('srv');

  await coordinator.pause(false);

  expect(markedAdminClient.pause).toBeCalledWith(false, null);
  expect(markedAdminClient.unpause).toBeCalledTimes(2);
});

test('unpause successfully', async () => {
  const coordinator = new RequestCoordinator('srv');

  await coordinator.unpause();

  expect(markedAdminClient.unpause).toBeCalledTimes(2);
});

test('unpause unsuccessfully', async () => {
  markedAdminClient.unpause = jest.fn(() =>
    Promise.reject(new Error('unpause error'))
  );

  const coordinator = new RequestCoordinator('srv');

  await expect(coordinator.unpause()).rejects.toThrow('unpause error');
});

test('checkPaused successfully', async () => {
  const coordinator = new RequestCoordinator('srv');

  markedAdminClient.getHost
    .mockReturnValueOnce('ip1')
    .mockReturnValueOnce('ip2');

  const paused = await coordinator.checkPaused();

  expect(paused).toEqual([
    {host: 'ip1', paused: false},
    {host: 'ip2', paused: false},
  ]);
});

test('checkPaused unsuccessfully', async () => {
  markedAdminClient.checkPaused = jest.fn(() =>
    Promise.reject('checkPaused error')
  );

  const coordinator = new RequestCoordinator('srv');

  try {
    await coordinator.checkPaused();
    expect.assertions(1); // this line should not be run
  } catch (e) {
    expect(e).toMatch('checkPaused error');
  }
});
