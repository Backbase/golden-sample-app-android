# Golden Sample Android App
The golden sample is a public repository that contains samples, examples and best practices. This repository should be used internally and externally to show the Backbase approach, architecture and way of working to our developers and customers.

## Samples
Every sample lives on its own branch. The [CATALOG.md](CATALOG.md) file lists all the samples that the repo contains. The repository is updated with every LTS releases and these updates are tagged, you can easily compare a sample to the tag to see the code changes. 

### Contribution
If you want to create your own samples check the [CONTRIBUTING.md](CONTRIBUTING.md) for the guidelines.

## Discussion
You can discuss samples, request new examples and report problems on Github Discussions. 

## 
## 💻 Getting started
This project depends on various artifacts published to repositories on [Backbase Repo](https://repo.backbase.com). You must have read access to these repositories to build this project. The project also requires a specific keystore in order for the app to communicate with the backend.
In order to configure the project, please follow this [guide on Backbase.io](https://backbase.io/developers/documentation/mobile-devkit/getting-started/set-up-android-development/).

Note that this project connects to the EBP Sandbox Environment, for that you need to request an API key as [mentioned here](https://backbase.io/developers/documentation/api-sandbox/retail-banking-usa/android-guide/). Add the key to the [config.json](app%2Fsrc%2Fmain%2Fassets%2Fbackbase%2Fconfig.json) file in the assets folder.

### 📱 Login
In order to login you can find user credentials at [user-credentials page](https://backbase.io/ebp-sandbox/user-credentials?experience=retail)

## The Journey Architecture
Backbase mobile is built with the journey architecture, where a journey is an independent set of screens that form a typical user journey. To learn more about the Backbase journey architecture read this [article](https://backbase.io/developers/documentation/retail-banking-universal/latest/system-wide/architecture/mobile-journey-architecture-understand/).