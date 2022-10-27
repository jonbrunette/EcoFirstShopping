# EcoFirstShopping
For the IBM Call for Code 2022 Edition
[Dataset.xlsx](https://github.com/jonbrunette/EcoFirstShopping/files/9538905/Dataset.xlsx)

Rest APIs

1. List All products
   http://localhost:8081/products
2. Search data
   http://localhost:8081/search?inField=name&keyword=Eco

**From sample project :** 

# Submission or project name

[![License](https://img.shields.io/badge/License-Apache2-blue.svg)](https://www.apache.org/licenses/LICENSE-2.0) [![Community](https://img.shields.io/badge/Join-Community-blue)](https://developer.ibm.com/callforcode/solutions/projects/get-started/) [![Website](https://img.shields.io/badge/View-Website-blue)](https://sample-project.s3-web.us-east.cloud-object-storage.appdomain.cloud/)

A basic GitHub repository example for new [Call for Code](https://developer.ibm.com/callforcode/) projects and those that join the Call for Code with The Linux Foundation deployment initiative. Not all sections or files are required. You can make this as simple or as in-depth as you need. And don't forget to [join the Call for Code community](https://developer.ibm.com/callforcode/solutions/projects/get-started/).

> If you're new to open source, please consider taking the [free "Introduction to Open Source" class](https://cognitiveclass.ai/courses/introduction-to-open-source).
>
> [![Open Source Foundations](images/img.png)](https://cognitiveclass.ai/courses/introduction-to-open-source)

## Contents

- [Submission or project name](#submission-or-project-name)
   - [Contents](#contents)
   - [Short description](#short-description)
      - [What's the problem?](#whats-the-problem)
      - [How can technology help?](#how-can-technology-help)
      - [The idea](#the-idea)
   - [Demo video](#demo-video)
   - [The architecture](#the-architecture)
   - [Long description](#long-description)
   - [Project roadmap](#project-roadmap)
   - [Getting started](#getting-started)
   - [Live demo](#live-demo)
   - [Built with](#built-with)
   - [Contributing](#contributing)
   - [Versioning](#versioning)
   - [Authors](#authors)
   - [License](#license)
   - [Acknowledgments](#acknowledgments)
## Short description

### What's the problem?

Part of the World Health Organization's guidance on limiting further spread of COVID-19 is to practice social distancing. As a result, schools in most affected areas are taking precautionary measures by closing their facilities. With school-aged children at home for an indeterminate amount of time, keeping them engaged, entertained, and on top of their education is important.

### How can technology help?

Schools and teachers can continue to engage with their students through virtual classrooms, and even create interactive spaces for classes. As parents face a new situation where they may need to homeschool their children, finding appropriate online resources is important as well.

### The idea

It's imperative that learning and creating can continue when educational institutions have to shift the way they teach in times of crises, such as the COVID-19 pandemic. Providing a set of open source tools, backed by IBM Cloud and Watson Services, will enable educators to more easily make content available for their students.

## Demo video

https://user-images.githubusercontent.com/30491170/198356591-d17e44b3-43ec-46fe-9042-47d1c0b052ef.mp4


## The architecture

![Video transcription/translation app](images/EcoFirstArchitecture.png)

1. The user navigates to the site and uploads a video file.
2. Watson Speech to Text processes the audio and extracts the text.
3. Watson Translation (optionally) can translate the text to the desired language.
4. The app stores the translated text as a document within Object Storage.

## Long description

[More detail is available here](./docs/DESCRIPTION.md)

## Project roadmap

The project currently does the following things.

- Feature 1
- Feature 2
- Feature 3

It's in a free tier IBM Cloud Kubernetes cluster. In the future we plan to run on Red Hat OpenShift, for example.

See below for our proposed schedule on next steps after Call for Code 2021 submission.

[//]: # (![Roadmap]&#40;./images/roadmap.jpg&#41;)

## Getting started

## Live demo

You can find a running system to test at [callforcode.mybluemix.net](http://callforcode.mybluemix.net/).

See the "long description" field in our submission (not in this repo) for the log-in credentials.

## Built with

- [IBM Cloudant](https://cloud.ibm.com/catalog?search=cloudant#search_results) - The NoSQL database used
- [IBM Cloud Functions](https://cloud.ibm.com/catalog?search=cloud%20functions#search_results) - The compute platform for handing logic
- [IBM API Connect](https://cloud.ibm.com/catalog?search=api%20connect#search_results) - The web framework used
- [Dropwizard](http://www.dropwizard.io/1.0.2/docs/) - The web framework used
- [Maven](https://maven.apache.org/) - Dependency management
- [ROME](https://rometools.github.io/rome/) - Used to generate RSS Feeds

## Contributing

## Versioning

We use [SemVer](http://semver.org/) for versioning. For the versions available, see the [tags on this repository](https://github.com/your/project/tags).

## Authors

<a href="https://github.com/jonbrunette/EcoFirstShopping/graphs/contributors">

[//]: # (  <img src="https://contributors-img.web.app/image?repo=Call-for-Code/Project-Sample" />)
</a>

- **Billie Thompson** - _Initial work_ - [PurpleBooth](https://github.com/PurpleBooth)

## License

This project is licensed under the Apache 2 License - see the [LICENSE](LICENSE) file for details.

## Acknowledgments

- Based on [Billie Thompson's README template](https://gist.github.com/PurpleBooth/109311bb0361f32d87a2).
