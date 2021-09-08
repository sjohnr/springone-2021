Spring Security 5.5 From Taxi to Takeoff
==

This repository is for the SpringOne 2021 presentation titled "Spring Security 5.5 From Taxi to Takeoff".
It contains the following four applications:

* [spa](/spa) - An Angular-based Single Page Application
* [flights-web](/flights-web) - A Spring-powered OAuth 2.0 client application
* [flights-api](/flights-api) - A REST API secured with Spring Security OAuth 2.0 Resource Server
* [sso](/sso) - A Spring-powered OAuth 2.0 Authorization Server

The final state is a single-page application that authenticates the user with OpenID Connect 1.0 and collaborates with a REST API using OAuth 2.0 bearer tokens. It brings together the following concepts:

* The `spa` is served as static content from the `/static` directory of `flights-web`
* The `sso` application is configured as an OpenID Connect 1.0 provider that mints signed JWTs for an OAuth 2.0 client
* The `flights-api` application is simplified to act as a resource server that verifies signed JWTs for authentication
* The `flights-web` application acts as an OAuth 2.0 client, performs token relay with Spring Cloud Gateway, and implements the [backend for frontend (bff)](https://www.ietf.org/id/draft-bertocci-oauth2-tmi-bff-01.html) pattern to store access tokens on the server
* The `spa` authenticates with `flights-web` using a standard session cookie (`SESSIONID`), and additionally uses a cookie/header pair for csrf protection (`XSRF-TOKEN`, `X-XSRF-TOKEN`)

Getting Started
--

First, start the authorization server, with the following command:

```shell
./gradlew :sso:bootRun
```

Next, start the REST API like so:

```shell
./gradlew :flights-api:bootRun
```

You will need the [Angular CLI](https://angular.io/cli) installed.
Then, start the SPA and OAuth 2.0 Client application using the following command:

```shell
./gradlew :flights-web:bootRun
```

Finally, navigate to http://127.0.0.1:8000

Running Natively
--

To run the application's natively, you can use [spring-native](https://docs.spring.io/spring-native/docs/current/reference/htmlsingle/#getting-started-native-image) to build the images locally, or pull the pre-built images from Docker Hub. A docker-compose.yml file is provided to run using the pre-built images.

```shell
docker-compose up
```

Following Along
--

To follow along with the presentation, start with the `main` branch:

```shell
git checkout main
```

Each checkpoint along the way contains a specific commit message you can use to quickly hop around in the presentation. For example, to switch to *Step 1 - Secure by default*, do the following:

```shell
./look-at 'Step 1'
```

This will safely attempt to switch to a particular commit, but you will be in 'detached HEAD' state. To reset to a particular point such as *Step 12 - Secure BFF application* ,`git checkout main` again, and do the following:

```shell
./jump-to 'Step 12'
```

This will hard-reset to the specified commit and discard changes in your working directory.