/* eslint-disable @typescript-eslint/no-explicit-any */
import * as grpc from '@grpc/grpc-js';
import {Empty} from 'google-protobuf/google/protobuf/empty_pb';

const messages = require('./admin_pb.js');
const services = require('./admin_grpc_pb.js');

/**
 * Supports to send pause/unpause requests to a host by its ip and port.
 */
export class AdminClient {
  _client: any;

  constructor(
    private host: string,
    port: number,
    credentials: grpc.ChannelCredentials | null = null
  ) {
    this._client = new services.AdminClient(
      `${host}:${port}`,
      credentials || grpc.credentials.createInsecure()
    );
  }

  getHost(): string {
    return this.host;
  }

  /**
   * Send pause request to a host.
   * @param waitOutstanding
   * @param maxPauseWaitTime
   */
  async pause(
    waitOutstanding: boolean,
    maxPauseWaitTime: number | null = null
  ): Promise<void> {
    const client = this._client;
    const request = new messages.PauseRequest();

    request.setWaitOutstanding(waitOutstanding);

    if (maxPauseWaitTime !== null) {
      request.setMaxPauseWaitTime(maxPauseWaitTime);
    }

    return new Promise((resolve, reject) => {
      client.pause(request, (e: unknown) => {
        if (e) reject(e);
        resolve();
      });
    });
  }

  /**
   * Send unpause request to a host.
   */
  async unpause(): Promise<void> {
    const client = this._client;

    return new Promise((resolve, reject) => {
      client.unpause(new Empty(), (e: unknown) => {
        if (e) reject(e);
        resolve();
      });
    });
  }

  /**
   * Send check paused request to a host.
   * @returns {boolean} paused or not
   */
  async checkPaused(): Promise<boolean> {
    const client = this._client;

    return new Promise((resolve, reject) => {
      client.checkPaused(new Empty(), (e: unknown, response: any) => {
        if (e) reject(e);
        resolve(response.getPaused());
      });
    });
  }
}
