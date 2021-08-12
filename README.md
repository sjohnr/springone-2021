Spring Security 5.5 From Taxi to Takeoff
==

This repository is for the SpringOne 2021 presentation titled "Spring Security 5.5 From Taxi to Takeoff".
It contains the following four applications:

* [spa](/spa) - An Angular-based Single Page Application
* [flights-web](/flights-web) - A Spring-powered OAuth 2.0 client application
* [flights-api](/flights-api) - A REST API secured with Spring Security OAuth 2.0 Resource Server
* [sso](/sso) - A Spring-powered OAuth 2.0 Authorization Server

The final state is a single-page application that authenticates the user with OpenID Connect 1.0 and collaborates with a REST API using OAuth 2.0 bearer tokens.

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

This will safely attempt to switch to a particular commit, but you will be in 'detached HEAD' state. To reset to a particular point such as *Step 11 - Secure BFF application* ,`git checkout main` again, and do the following:

```shell
./jump-to 'Step 11'
```

This will hard-reset to the specified commit and discard changes in your working directory.
