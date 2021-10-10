FROM openjdk:8-jre-alpine

WORKDIR /scalar

# The path should be relative from build/docker. Running `gradle build`
# (provided by com.palantir.docker plugin) will copy this Dockerfile and
# scalar-admin.tar to build/docker.
ADD ./scalar-admin.tar  .

WORKDIR /scalar/scalar-admin

ENTRYPOINT ["./bin/scalar-admin"]
