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

const markedAdminClientForIp1 = {
  pause: jest.fn(() => Promise.resolve()),
  unpause: jest.fn(() => Promise.resolve()),
  checkPaused: jest.fn(() => Promise.resolve(false)),
  getHost: jest.fn(() => 'ip1'),
};

const markedAdminClientForIp2 = {
  pause: jest.fn(() => Promise.resolve()),
  unpause: jest.fn(() => Promise.resolve()),
  checkPaused: jest.fn(() => Promise.resolve(false)),
  getHost: jest.fn(() => 'ip2'),
};

const markedAdminClientConstructor = jest.fn();

jest.mock('../src/admin_client', () => ({
  AdminClient: markedAdminClientConstructor,
}));

import {RequestCoordinator} from '../src/request_coordinator';

beforeEach(() => {
  markedAdminClientConstructor.mockClear();
  markedAdminClientConstructor
    .mockReturnValueOnce(markedAdminClientForIp1)
    .mockReturnValueOnce(markedAdminClientForIp2);
});

test('pause successfully', async () => {
  const coordinator = new RequestCoordinator('srv');

  await coordinator.pause(false);

  expect(markedAdminClientForIp1.pause).toBeCalledWith(false, null);
  expect(markedAdminClientForIp2.pause).toBeCalledWith(false, null);
  expect(markedAdminClientForIp1.pause).toBeCalled();
  expect(markedAdminClientForIp2.pause).toBeCalled();
});

test('pause with ip/port successfully', async () => {
  const coordinator = new RequestCoordinator([
    {ip: 'ip1', port: 0},
    {ip: 'ip2', port: 0},
  ]);

  await coordinator.pause(false);

  expect(markedAdminClientForIp1.pause).toBeCalledWith(false, null);
  expect(markedAdminClientForIp2.pause).toBeCalledWith(false, null);
  expect(markedAdminClientForIp1.pause).toBeCalled();
  expect(markedAdminClientForIp2.pause).toBeCalled();
});

test('pause unsuccessfully', async () => {
  markedAdminClientForIp1.pause = jest.fn(() => Promise.reject(new Error()));

  const coordinator = new RequestCoordinator('srv');

  await coordinator.pause(false);

  expect(markedAdminClientForIp1.pause).toBeCalledWith(false, null);
  expect(markedAdminClientForIp1.unpause).toBeCalled();
  expect(markedAdminClientForIp2.unpause).toBeCalled();
});

test('unpause successfully', async () => {
  const coordinator = new RequestCoordinator('srv');

  await coordinator.unpause();

  expect(markedAdminClientForIp1.unpause).toBeCalled();
  expect(markedAdminClientForIp2.unpause).toBeCalled();
});

test('unpause with ip/port successfully', async () => {
  const coordinator = new RequestCoordinator([
    {ip: 'ip1', port: 0},
    {ip: 'ip2', port: 0},
  ]);

  await coordinator.unpause();

  expect(markedAdminClientForIp1.unpause).toBeCalled();
  expect(markedAdminClientForIp2.unpause).toBeCalled();
});

test('unpause unsuccessfully', async () => {
  markedAdminClientForIp1.unpause = jest.fn(() =>
    Promise.reject(new Error('unpause error'))
  );

  const coordinator = new RequestCoordinator('srv');

  await expect(coordinator.unpause()).rejects.toThrow('unpause error');
});

test('checkPaused successfully', async () => {
  const coordinator = new RequestCoordinator('srv');

  const paused = await coordinator.checkPaused();

  expect(paused).toEqual([
    {host: 'ip1', paused: false},
    {host: 'ip2', paused: false},
  ]);
});

test('checkPaused with ip/port successfully', async () => {
  const coordinator = new RequestCoordinator([
    {ip: 'ip3', port: 1},
    {ip: 'ip4', port: 2},
  ]);

  const paused = await coordinator.checkPaused();

  expect(markedAdminClientConstructor).toBeCalledWith('ip3', 1);
  expect(markedAdminClientConstructor).toBeCalledWith('ip4', 2);
  expect(paused).toEqual([
    {host: 'ip1', paused: false},
    {host: 'ip2', paused: false},
  ]);
});

test('checkPaused unsuccessfully', async () => {
  markedAdminClientForIp1.checkPaused = jest.fn(() =>
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
