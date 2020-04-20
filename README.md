Coverage: 83.1% SonarQube, 

# Inventory Management System - QA Consultancy Individual Project

This project links a Google Cloud Platform MySQL instance to a Java exetuble via JDBC, from which an inventory of items may be managed.
The project is built on Maven using the Eclipse IDE, using JUnit & Mockito for testing purposes. 
Version control was done using Git, with GitHub as host. 
A CI Server was set up, managed by Jenkins, to analyse code pushed to GitHub using SonarQube. The artifacts were stored on Nexus.

## Getting Started

1) Clone the repository to the desired client
2) Open the project as a Maven project
3) Link it to your MySQL instance by replacing the hard-coded IP-addresses
4) You may now run it from your IDE as a Maven project, or use 

```
mvn clean install
```
inside of the project folder, followed by
```
java -jar target/KorbinianRing-SoftwareMarch16-jar-with-dependencies.jar
```
### Prerequisites

In order to run the program from the command line, Maven must be used to create a jar containing the dependencies.
Additionaly, a MySQL instance must be set up (either locally or on the cloud), and the IP addresses, which are hardcoded in the IMS and DAO classes, must be changed to refer to said MySQL instance.

### Installing

A step by step series of examples that tell you how to get a development env running

Say what the step will be

```
Give the example
```

And repeat

```
until finished
```

End with an example of getting some data out of the system or using it for a little demo



## Running the tests

Explain how to run the automated tests for this system. Break down into which tests and what they do

### Unit Tests 

Explain what these tests test, why and how to run them

```
Give an example
```

### Integration Tests 
Explain what these tests test, why and how to run them

```
Give an example
```

### And coding style tests

Explain what these tests test and why

```
Give an example
```

## Deployment

Add additional notes about how to deploy this on a live system

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Versioning

We use [SemVer](http://semver.org/) for versioning.

## Authors

* **Chris Perrins** - *Initial work* - [christophperrins](https://github.com/christophperrins)
* **Korbinian Ring** - *all but first commit * - [KMRRingQA](https://github.com/KMRRingQA)

## License

This project is licensed under the MIT license - see the [LICENSE.md](LICENSE.md) file for details 

## Acknowledgments

* Caroline Strasenburgh, helping me out with documentation

# IMSProject
