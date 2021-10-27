FROM openjdk:8u292-jre-slim

WORKDIR /scalar

# Run `./gradlew build` command for creating the scalar-admin build.
ADD ./build/distributions/scalar-admin.tar  .

WORKDIR /scalar/scalar-admin

ENTRYPOINT ["./bin/scalar-admin"]
