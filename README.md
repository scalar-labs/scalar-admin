# scalar-admin

scalar-admin is an admin interface and client tool for Scalar services such as [Scalar DL](https://github.com/scalar-labs/scalardl) and [Scalar DB](https://github.com/scalar-labs/scalardb) applications. 
It will help you to `PAUSE` and `UNPAUSE` the ledger to create point-in-time recovery (PITR). 

## Client-side tool

The scalar-admin client tool is used to manage the admin interface implemented applications.
It will help to `PAUSE` and `UNPAUSE` the application without losing the transaction.

### Installation

You can create a build for the scalar-admin client tool using the following methods

#### Jar

If you want to create a client tool fat jar, you can do it as follows.

```console
$ ./gradlew shadowJar
```

#### Container

If you want to create a scalar-admin container, you can do it as follows.

```console
$ ./gradlew docker
```

### Usage

You can manage the scalar-admin integrated applications in the following ways

#### Jar

To access scalar-admin integrated applications, you can use the fat jar `scalar-admin-<version>-all.jar` found in [releases](https://github.com/scalar-labs/scalar-admin/releases) as follows

```console
$ java -jar scalar-admin-<version>-all.jar -c <Command> -s <SRV_Service_URL>
```

#### Container

If you want to use the scalar-admin container, you can do it as follows.

```console
$ docker run -it --rm ghcr.io/scalar-labs/scalar-admin:<version> -c <Command> -s <SRV_Service_URL>
```

If you want to use the scalar-admin container on kubernetes, you can do it as follows.

```console
$ kubectl run --image=ghcr.io/scalar-labs/scalar-admin:<version> --restart=Never --rm  -i scalarAdmin -- -c <Command> -s <SRV_Service_URL>
```

Note:- Commands are `PAUSE`, `UNPAUSE` and `STATS`.

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
