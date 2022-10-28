# EcoFirstShopping
For the IBM Call for Code 2022 Edition
[Dataset.xlsx](https://github.com/jonbrunette/EcoFirstShopping/files/9538905/Dataset.xlsx)

Rest APIs

1. List All products
   http://localhost:8081/products
2. Search data
   http://localhost:8081/search?inField=name&keyword=Eco



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
Tech waste is a big form of waste that we don’t really talk about or see . 

An undetermined amount of used electronics is shipped from the United States and other developed countries to developing countries that lack the capacity to reject imports or to handle these materials appropriately. Without proper standards and enforcement, improper practices may result in public health and environmental concerns, even in countries where processing facilities exist.We have serious concerns about unsafe handling of used electronics and e-waste, in developing countries, that results in harm to human health and the environment. Some of the hazards of electronics waste, include harmful chemicals such as lead, cadmium, and other known carcinogens, which can leach into the soil and contaminate water supplies.

As a result various channels are opening up to reduce carbon footprint, one of which is by manufacturing eco-friendly products that are bio-degradable. 

Our aim is to promote these products, bringing these in fornt of the buyer and showcasing how they can make a difference by making better choices.

### What's the problem?
- Did you know the impact your lifestyle is having on the environment?
- Did you know about the term "Technology Waste " and how we have been contributing to this effect ?
- Did you know more than 50 million metric tons of E-waste is generated globally every year ?
- Do you know what happens to the phone, earphones, tech accessories you just broke and threw out ?

The lack of awareness of what happens to these devices is a really big problem. 

**Once we all become aware it’s a problem, solutions can start flowing in.**

### How can technology help?
Our goal is to help the buyer find the product of their choice with the best recommendation for better products which reduce environmental impacts. For every product category our algorithm looks for the company that uses the most eco friendly products and procedures to build it, which we then propose to the buyers via chrome extension.

### The idea

We aim to show that enduring the planet  doesn’t mean letting go of one's wants and needs. We want to promote a greener and healthier environment by use of eco-friendly tech products that can positively affect our planet ensuringwe fulfill the requirements without hurting Mother earth in the process.

## Demo video

https://user-images.githubusercontent.com/30491170/198356591-d17e44b3-43ec-46fe-9042-47d1c0b052ef.mp4


## The architecture



![image](https://user-images.githubusercontent.com/95766933/198687575-b8a328fe-a780-47ac-98fc-4af4da8e0e1d.png)

1. The buyer logs in to online shopping site and searches for product.
2. Our chrome extension then invokes the API .
3. API performs a lookup. 
4. Based on the pre-existing products and based on the ML used to add newer products matching eco-friendly filter, results are returned back.



## Project roadmap

The project currently does the following things.

- Using the exsiting database of eco-friendly products, recomendataions are made to the buyer for the respective product category
- Using ML we are identifying similar products and expanding our 
- For future we will integrate the extension with sites like FB Marketplace etc. to reccomend products in the same category available for resale

![image](https://user-images.githubusercontent.com/95766933/198691871-9dae9a20-9603-42b9-873c-471ee1135666.png)



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
