# Golden Sample Android App
This golden sample provides examples of the code structure, configuration, and best practices for using the Backbase Android tools.

## Getting started

### Gradle setup

This project depends on various artifacts published on [Backbase Repo](https://repo.backbase.com). You
must have read access to build this project. To ensure the build system can access these
artifacts on your machine, follow these steps:
1. Log in to [Repo](https://repo.backbase.com) and click your name in the top right.
2. Type in your password to get access to your encrypted password. Copy your encrypted password.
3. Open/create the `gradle.properties` file at `/Users/<username>/.gradle/` (Mac) /
   `C:\Users\<username>\.gradle\` (Windows) / `/home/<username>/.gradle/` (Linux).
4. Add `backbaseRepoUsername=<your username>` to the file.
5. Add `backbaseRepoPassword=<your encrypted password>` to the file.

### Register facet ID

Since the Golden Sample App makes use of the Retail Sandbox Environment, follow [this guide](https://backbase.io/developers/documentation/api-sandbox/retail-banking-usa/android-guide/) to quickly set-up your facet-id.

### Log in with the app

To log in and test the app, user credentials can be found [here](https://backbase.io/developers/documentation/api-sandbox/retail-banking-usa/retail-user-credentials/).