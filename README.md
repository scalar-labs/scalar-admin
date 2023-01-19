The Protocol Buffers for Scalar Admin is defined in [admin.proto](./admin.proto).

This repository maintains
- the codes generated by the Protocol Buffers compiler
- the gRPC client libraries used to send requests to the Scalar Admin gRPC servers

## Versioning
The Java and Node client libraries are released with the same version.
Please make sure the versions are the same in [build.gradle](./java/build.gradle) and [package.json](./node/package.json) before you push `v{major}.{minor}.{bugfix}` tags to trigger related GitHub Actions workflows to release them.
