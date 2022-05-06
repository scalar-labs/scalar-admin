const {AdminClient} = require('../dist/index');

(async () => {
  const client = new AdminClient('localhost', 50053);
  await client.pause(true);
})();
