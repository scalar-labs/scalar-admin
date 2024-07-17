# scalar-admin

scalar-admin is an admin interface and client tool for Scalar services such as [ScalarDL](https://github.com/scalar-labs/scalardl) and [ScalarDB](https://github.com/scalar-labs/scalardb) applications.
It will help you to pause ScalarDL Ledger cluster or ScalarDB server cluster to create a transactionally-consistent backup.

## scalar-admin client tool

As of the current implementation, the scalar-admin client tool is mainly used to pause (and unpause) the cluster of applications that implement the admin interface.

### Install

#### Fat jar

The release versions of `scalar-admin` fat jar can be downloaded from [releases](https://github.com/scalar-labs/scalar-admin/releases) page of Scalar Admin.

#### Docker container

You can pull the docker image from [Scalar's container registry](https://github.com/orgs/scalar-labs/packages/container/package/scalar-admin).

#### Build from sources

In case you want to build `scalar-admin` fat jar from the source:

```console
$ ./gradlew shadowJar
```

* The built fat jar file is `build/libs/scalar-admin-<version>-all.jar`

In case you want to build the docker image from the source:

```console
$ ./gradlew docker
```

### Usage

#### Fat jar

You can run the fat jar as follows.


```console
$ java -jar scalar-admin-<version>-all.jar -c <COMMAND> -a <IP1:Port1,IP2:Port2,...>
```

The command above can take effect on multiple ScalarDB or ScalarDL instances in one shot.

**NOTE** If you previously used the following command to pause and unpause Scalar pods in Kubernetes clusters,
we recommend switching to [Scalar Admin for Kubernetes](https://github.com/scalar-labs/scalar-admin-for-kubernetes).

```console
$ java -jar scalar-admin-<version>-all.jar -c <COMMAND> -s <SRV_SERVICE_URL>
```

Using the `-s` option to pause and unpause Scalar pods in a Kubernetes cluster is error-prone.
Scalar Admin doesn't check for orchestration changes during the pause/unpause period, while Scalar Admin for Kubernetes is tolerant of Kubernetes orchestration changes.

This command is an FYI and we do not recommend using it.
Please either use the `-a` option to explicitly pause or unpause Scalar instances, or consider using Scalar Admin for Kubernetes to pause and then unpause Scalar pods in a tolerant manner.

We will deprecate this option in the next major release.

#### Docker container

If you want to use the `scalar-admin` container, you can do it as follows.

```console
$ docker run -it --rm ghcr.io/scalar-labs/scalar-admin:<version> -c <COMMAND> -s <SRV_SERVICE_URL>
```

If you want to use the `scalar-admin` container on kubernetes, you can do it as follows.

```console
$ kubectl run <NAME> -it --image=ghcr.io/scalar-labs/scalar-admin:<version> --restart=Never --rm -- -c <COMMAND> -s <SRV_SERVICE_URL>
```

## Implement server-side code

To make your services accessible from scalar-admin tool, you need to implement server-side code based on the admin interface defined in [admin.proto](src/main/proto/scalar/protobuf/admin.proto).
The required library is available on [Maven Central](https://search.maven.org/search?q=a:scalar-admin). You can install it in your application using your build tool such as Gradle. For example in Gradle, you can add the following dependency to your build.gradle. Please replace the `<version>` with the version you want to use.

```
dependencies {
    compile group: 'com.scalar-labs', name: 'scalar-admin', version: '<version>'
}
```

## License
scalar-admin is licensed under the Apache 2.0 License (found in the LICENSE file in the root directory).
