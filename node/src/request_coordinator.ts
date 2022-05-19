import {promises as DNS, SrvRecord} from 'dns';
import {AdminClient} from './admin_client';

export type CheckPausedResopnse = {
  host: string;
  paused: boolean;
};

/**
 * Supports to send pause/unpause requests to all hosts of a service records
 */
export class RequestCoordinator {
  resolver: DNS.Resolver;

  constructor(private srvServiceUrl: string) {
    this.resolver = new DNS.Resolver();
  }

  async resolveClients(): Promise<AdminClient[]> {
    const clients: AdminClient[] = [];
    const resolved: SrvRecord[] = await this.resolver.resolveSrv(
      this.srvServiceUrl
    );

    for (const record of resolved) {
      const ips = await this.resolver.resolve4(record.name);
      for (const ip of ips) {
        clients.push(new AdminClient(ip, record.port));
      }
    }

    return clients;
  }

  /**
   * Send pause request to all hosts of a service records.
   * If one of the hosts cannot be paused, this function unpause (rollback) all hosts.
   * @param {boolean} waitOutstanding
   * @param {number} maxPauseWaitTime
   */
  async pause(
    waitOutstanding: boolean,
    maxPauseWaitTime: number | null = null
  ): Promise<void> {
    const clients: AdminClient[] = await this.resolveClients();

    try {
      await Promise.all(
        clients.map(client => client.pause(waitOutstanding, maxPauseWaitTime))
      );
    } catch (_) {
      await Promise.all(clients.map(client => client.unpause()));
    }
  }

  /**
   * Send unpause request to all hosts of a service records.
   */
  async unpause(): Promise<void> {
    const clients: AdminClient[] = await this.resolveClients();

    await Promise.all(clients.map(client => client.unpause()));
  }

  /**
   *
   * @returns {Array} return an array of {ip: string, paused: boolean}
   */
  async checkPaused(): Promise<CheckPausedResopnse[]> {
    const clients: AdminClient[] = await this.resolveClients();

    const promises: Promise<CheckPausedResopnse>[] = [];
    for (const client of clients) {
      promises.push(
        client.checkPaused().then(r => ({
          host: client.getHost(),
          paused: r,
        }))
      );
    }

    return Promise.all(promises);
  }
}
