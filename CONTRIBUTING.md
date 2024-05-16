# How to contribute

The golden sample app provides a opportunity to show samples, examples and best practices internally and externally of Backbase methods, processes and way of working. The `main` branch contains a minimalistic working application which can be basis for new applications and POCs. 

This guideline summarizes the development process with the Golden sample and how you can create your own samples.

## Structure

The repository's main branch contains the main sample and CATALOG.md file which contains a list of all the available samples. A new sample can be based on the main branch or on another sample, but if you choose another sample it's possible that it's not maintained. After finishing a sample the CATALOG.md file needs to be updated with the details. 

### Steps to create a new sample
1. Create a new branch from main according to the branch naming guide described in this guide
2. Add your sample code to the branch.
3. On the `main` branch update catalog.yaml file according to this guide and create a PR for just this change.

## Roles and responsibilities

The repository is owned by the DEVS team. It's the DEVS team responsibility to update the `main` branch and to handle PRs to the `main` branch and to keep the repository clean. Every sample has a maintainer which is the team that created the sample and it is that team responsibility to maintain it and keep the sample up to date and runnable if needed (some samples show an example or practice that doesn't need this). 

## Branches

### Main branch
The `main` branch is owned by the DEVS team and is continuously updated to the latest LTS version. The `main` branch is the repository's primary and protected branch. The `main` branch should be the basis for a new sample.

### Sample and POC branches

A custom branch on the Golden sample app has an maintainer, the type of the branch can be sample or proof of concept. The name of the branch reflects the type, the value stream and what the sample does.

The name of a custom branch should follow the convention of `type/valuestream/sample` scheme. The last part of the name should describe the sample. 

Type can be:
- `poc`
- `sample`

Value stream can be:
- `retail`
- `business`
- `wealth`
- `flow`
- `devs`
- `cs`

Example branch names:
 - `poc/devs/O11Y`
 - `poc/retail/payments-journey`

 We encourage the usage of demo data instead of relying on backend data to ensure that a sample can run independently of the backend and requires less maintenance.

## Sample catalog

The sample catalog file (CATALOG.md) contains all the samples and POCs. It serves as guide for the users to help to find samples and their maintainers. It's also a guide to the DEVS team to see which branches are samples and which can be deleted. 

### Catalog entry
A catalog entry must contain the following information:
- Name to the branch
- Short description
- Maintainer team
- Maintenance status
- LTS version when the sample was working (check the latest main tag!)
- Link to the branch

## Issues
If there is an issue with a sample check the catalog file and contact the responsible team if it's maintained. For login issues contact the API sandbox team at their support Slack channel: `#s-ebp-api-sandbox`. For issues with the main branch and other problems reach out to the DEVS team on `#s-golden-sample-mobile` Slack channel. You can also use the Discussion tab to report problems.


