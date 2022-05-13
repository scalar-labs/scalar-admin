# Scalar Admin Library for Node.js

This library provides the following classes to se pause and unpause requests to Scalar Admin gRPC servers.

- AdminClient
- RequsetCoordinator

### AdminClient

AdminClient is used to send pause/unpause requests to a single server.

```javascript
const client = new AdminClient(ip, port);
const waitOutstanding = true;

try {
	await client.pause(waitOutstanding);
	await client.unpause();
} catch (e) {
	// ...
}
```

### RequestCoordinator

RequsetCoordinator can be used to send pause/unpause requests to all servers behine the given service record.
When it fails to pause any of the servers, it tries to unpause (rollback) all of them.

```javascript
const coordinator = new RequestCoordinator(srv);
const waitOutstanding = true;

try {
	await coordinator.pause(waitOutstanding);
	await coordinator.unpause();
} catch (e) {
	// ...
}
```

## Build JavaScript bundle and TypeScript declaration

This is a TypeScript project. You can use
```
npm run build
```
to generate the JavaScript bundle file and the TypeScript declaration file.

## (Re)generate protobuf static files

```
grpc_tools_node_protoc --js_out=import_style=commonjs,binary:. --grpc_out=grpc_js:src --plugin=protoc-gen-grpc=`which grpc_tools_node_protoc_plugin` [the admin Proto Buffer file]
```
