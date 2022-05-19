const {AdminClient} = require('../dist/index');

(async () => {
  const client = new AdminClient('localhost', 50053);
  const paused = await client.checkPaused();

  console.log(paused);
})();
