FROM eclipse-temurin:8-jre-jammy

RUN apt-get update && apt-get upgrade -y && rm -rf /var/lib/apt/lists/*

WORKDIR /scalar

# The path should be relative from build/docker. Running `gradle build`
# (provided by com.palantir.docker plugin) will copy this Dockerfile and
# scalar-admin.tar to build/docker.
ADD ./scalar-admin.tar  .

WORKDIR /scalar/scalar-admin

ENTRYPOINT ["./bin/scalar-admin"]
