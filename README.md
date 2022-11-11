# EcoFirstShopping
For the IBM Call for Code 2022 Edition
[Dataset.xlsx](https://github.com/jonbrunette/EcoFirstShopping/files/9538905/Dataset.xlsx)

Rest APIs

1. List All products
   http://localhost:8081/products
2. Search data
   http://localhost:8081/search?inField=name&keyword=Eco
3. Swagger API 
   http://localhost:8081/swagger-ui/index.html



## Contents

- [Eco First Shopping](#submission-or-project-name)
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

Online shopping prioritizes profits over environmentally sustainable options. In most cases the cheapest or least eco-friendly option is shown first to consumer and shoppers. The online shopping experience is clouded in overwhelming product suggestions and "buy it now" attitude. Making smart environmentally motivated choices is difficult. 


### How can technology help?
We want to positively influence more people into making eco-friendly and zero-waste lifestyle decisions through products that are reusable, biodegradable, sustainable, all-natural, non-toxic and made from recycled materials.

Our goal is to help the buyer find the product of their choice with the best recommendation for better products which reduce environmental impacts. For every product category our algorithm looks for the company that uses the most eco friendly products and processes to manufacture it, which we then propose to the buyers via our browser extension.

### The idea

We aim to show that enduring the planet  doesn’t mean letting go of one's wants and needs. We want to promote a greener and healthier environment by use of eco-friendly tech products that can positively affect our planet ensuringwe fulfill the requirements without hurting Mother earth in the process.

Through advanced filtering with the help of Machine learning we aim to show that enduring the planet doesn’t mean letting go of one's wants and needs. We want to promote a greener and healthier environment by use of eco-friendly tech products that can positively affect our planet ensuring we fulfill the requirements without hurting Mother earth in the process. This lets the consumer vote with their dollars and select products they support rather than an algorithm used to generate the most profit

## Demo video

[![Watch the Eco First Shopping video](https://img.youtube.com/vi/dmpVdeNjFYg/default.jpg)](https://youtu.be/dmpVdeNjFYg)

## The architecture



![image](https://user-images.githubusercontent.com/95766933/198687575-b8a328fe-a780-47ac-98fc-4af4da8e0e1d.png)
Steps to follow to install the extension:

1. Install Browser Extension - _Using the associated browser extension store locate and install the Eco First Shopping extension_
2. Shop online as usual - _The buyer logs in to online shopping site and searches for product.The extension will re-rank search results and associated product recommendations to prioritize eco-friendly options_
3. Review Options- _Review the options and note why they have been boosted in search results and make an informed purchase__.(Our chrome extension then invokes the API which performs a lookup based on the pre-existing products and based on the ML used to add newer products matching eco-friendly filter, results are returned back.)_

## Visuals demonstraing the chrome extension
The user (who has installed our browser extenstion) is looking to buy straws on amazon
![unnamed](https://user-images.githubusercontent.com/95766933/201418049-b2f221fe-7b8b-4a06-b0e6-11e43a159e14.png)

The chrome extension ranks the suggested products based on their eco-friendy features
![unnamed](https://user-images.githubusercontent.com/95766933/201418172-058448ff-92a3-4ca4-8812-c70057e170e3.png)

The reason for suggetsing these products is displayed to the user
![unnamed](https://user-images.githubusercontent.com/95766933/201418223-be93f14a-4838-449f-b03e-10153f26ae86.png)

A user without this extension will have the below view
![unnamed](https://user-images.githubusercontent.com/95766933/201418389-a9d7809a-3143-4b8e-8834-fd54df2d3ecd.png)

## Project roadmap

The project currently does the following things.

- Using the exsiting database of eco-friendly products, recomendataions are made to the buyer for the respective product category
- Using ML we are identifying similar products and expanding our 
- For future we will integrate the extension with sites like FB Marketplace etc. to reccomend products in the same category available for resale

![image](https://user-images.githubusercontent.com/95766933/198691871-9dae9a20-9603-42b9-873c-471ee1135666.png)

## Getting Started

Pre-requites : 
   1. Oracle Java 15 version
   2. MVN 3.6 version

3  commands to be exeucted from CLI from the workspace src folder of the project : 

mvn compile
mvn package
mvn spring-boot:run

The localhost urls :

1. http://localhost:8081/search?inField=name&keyword=Eco
2. http://localhost:8081/products


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

## Team

We are group of enthusiasts who want to help support and encourage environmental awareness using our technical competancies . Our passion is Earth friendliness, green living, and the conservation of critical resources like water and energy. We are geographically dispersed, met each other (via zoom) during project initiation and have ever since been dedicated to this project. All of our interactions were via zoom calls and over the course of the past three months we have shared and learnt alot from each other and are looking forward to buildiong onto this solution and enhancing it in future.

Jonathan Brunette 

![image](https://user-images.githubusercontent.com/95766933/198690273-9a667dc9-3772-443d-ae26-0b2e73e2ba75.png)

Madhavi Polepeddi 

![image](https://user-images.githubusercontent.com/95766933/198690169-579317f9-bb1f-4909-8eb3-e50c160b2b2e.png)

Sangeeta Singh 

![image](https://user-images.githubusercontent.com/95766933/198690101-e2d94455-ea1b-4b34-8a68-3c58fe105474.png)

Di Pei

![image](https://user-images.githubusercontent.com/95766933/198690002-a797d316-09c2-4dd5-863d-d322b8f73dba.png)


## License

This project is licensed under the Apache 2 License - see the [LICENSE](LICENSE) file for details.

## Acknowledgments

- Based on [Billie Thompson's README template](https://gist.github.com/PurpleBooth/109311bb0361f32d87a2).
