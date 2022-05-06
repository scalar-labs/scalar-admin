import {promises as DNS, SrvRecord} from 'dns';
import {AdminClient} from './admin_client';

/**
 * Supports to send pause/unpause requests to all hosts of a service records
 */
export class RequestCoordinator {
  srvServiceUrl: string;
  resolver: DNS.Resolver;

  constructor(srvServiceUrl: string) {
    this.srvServiceUrl = srvServiceUrl;
    this.resolver = new DNS.Resolver();
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

    await Promise.all(clients.map(client => client.unpause()));
  }
}
