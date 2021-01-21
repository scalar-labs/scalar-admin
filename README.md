# scalar-admin

scalar-admin is an admin interface and client tool for Scalar services such as [Scalar DL](https://github.com/scalar-labs/scalardl) and [Scalar DB](https://github.com/scalar-labs/scalardb) applications.

## Use the client-side tool

To access scalar-admin integrated applications, you can use the fat jar `scalar-admin-<version>-all.jar` found in [releases](https://github.com/scalar-labs/scalar-admin/releases) as follows.
```console
$ java -jar scalar-admin-<version>-all.jar
```

You can also build the tool as follows.

```console
$ ./gradlew installDist
$ build/install/admin/bin/scalar-admin
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
