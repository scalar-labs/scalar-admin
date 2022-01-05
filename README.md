# scalar-admin

scalar-admin is an admin interface and client tool for Scalar services such as [Scalar DL](https://github.com/scalar-labs/scalardl) and [Scalar DB](https://github.com/scalar-labs/scalardb) applications. 
It will help you to `PAUSE` and `UNPAUSE` the ledger to create point-in-time recovery (PITR). 

## scalar-admin client tool

The scalar-admin client tool is used to manage the admin interface implemented applications.
It will help to `PAUSE` and `UNPAUSE` the application without losing the transaction.

### Usage

You can manage the scalar-admin integrated applications in the following ways

#### Install

The release versions of `scalar-admin` fat jar can be downloaded from [releases](https://github.com/scalar-labs/scalar-admin/releases) page of Scalar Admin.

#### Build

In case you want to build `scalar-admin` fat jar from the source:

```console
$ ./gradlew shadowJar
```

* The built fat jar file is `build/libs/scalar-admin-<version>-all.jar`

#### Container

You can pull the docker image from [Scalar's container registry](https://github.com/orgs/scalar-labs/packages/container/package/scalar-admin).

If you want to use the `scalar-admin` container, you can do it as follows.

```console
$ docker run -it --rm ghcr.io/scalar-labs/scalar-admin:<version> <command_arguments>
```

If you want to use the `scalar-admin` container on kubernetes, you can do it as follows.

```console
$ kubectl run <NAME> -i --image=ghcr.io/scalar-labs/scalar-admin:<version> --restart=Never --rm -- <command_arguments>
```

* Note that you can specify the same command arguments even if you use the fat jar or the container.
The example commands in the next section are shown with a jar, but you can run the commands with the container in the same way by replacing
`java -jar scalar-admin-<version>-all.jar` with `docker run -it --rm ghcr.io/scalar-labs/scalar-admin:<version>` or `kubectl run <NAME> -i --image=ghcr.io/scalar-labs/scalar-admin:<version> --restart=Never --rm --`

In case you want to build the docker image from the source:

```console
$ ./gradlew docker
```

#### Run

##### Available commands

```
Usage: scalar-admin [-hn] -c=COMMAND [-m=<maxPauseWaitTime>] -s=SRV_SERVICE_URL
Execute an admin command for applications that implement scalar admin interface.
  -c, --command=COMMAND   A command to execute.
  -h, --help              display the help message.
  -m, --max-pause-wait-time=<maxPauseWaitTime>
                          A max pause wait time in milliseconds.
  -n, --no-wait           A flag to specify if PAUSE command waits for
                            termination of outstanding requests.
  -s, --srv-service-url=SRV_SERVICE_URL
                          A service URL of SRV record.
```

##### Execute commands

```
$ java -jar scalar-admin-<version>-all.jar -c <COMMAND> -s <SRV_SERVICE_URL>
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
