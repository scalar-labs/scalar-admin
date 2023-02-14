import {promises as DNS} from 'dns';
import {AdminClient} from './admin_client';

export type CheckPausedResopnse = {
  host: string;
  paused: boolean;
};

export type Address = {
  ip: string;
  port: number;
};

/**
 * Supports to send pause/unpause requests to all hosts of a service records
 */
export class RequestCoordinator {
  resolver: DNS.Resolver;

  constructor(private targets: string | Address[]) {
    this.resolver = new DNS.Resolver();
  }

  async resolveClients(): Promise<AdminClient[]> {
    let addresses: Address[] = [];

    if (typeof this.targets === 'string') {
      for (const r of await this.resolver.resolveSrv(this.targets)) {
        const ips = await this.resolver.resolve4(r.name);
        for (const ip of ips) {
          addresses.push({ip, port: r.port});
        }
      }
    } else if (Array.isArray(this.targets)) {
      addresses = this.targets;
    }

    return addresses.map(a => new AdminClient(a.ip, a.port));
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
