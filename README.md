Coverage: 83.1% SonarQube, 93.5% JUnit 

# Inventory Management System - QA Consultancy Individual Project

This project links a Google Cloud Platform MySQL instance to a Java exetuble via JDBC, from which an inventory of items may be managed.
The project is built on Maven using the Eclipse IDE, using JUnit & Mockito for testing purposes. 
Version control was done using Git, with GitHub as host. 
A CI Server was set up, managed by Jenkins, to analyse code pushed to GitHub using SonarQube. The artifacts were stored on Nexus.

## Deploying the program

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
5) Development environmentshould be separate from actual database, having its own MySQL instance, since
	1) the database is reset every time tests are run
	2) MySQL login details are hard coded into the test files.
6) you should delete/modify the Tests in order to prevent this.
	
### Prerequisites

In order to run the program from the command line, Maven must be used to create a jar containing the dependencies.
Additionaly, a MySQL instance must be set up (either locally or on the cloud), and the IP addresses, which are hardcoded in the IMS and DAO classes, must be changed to refer to said MySQL instance.

### creating a development environment

You will need to install
Maven, Java, Git & Jenkins software and ensure they are up to date with the project.

1) Set up GCP MySQL instance. 
2) Whitelist your public IP address for this instance.
3) Clone this GitHub repository to your client.
4) Set up a GitHub repository of your own
5) Set the Origin to for the project to your GitHub repository
6) Open the program as a Maven Project
7) Change the hard-coded IP addresses in the src/main/java/com/qa/ims class, as well as the com/qa/ims/persistence/dao classes.
8) Run the project, either from your IDE or as a fat .jar as mentioned above (mvn clean install, java -jar <name>.jar)
9) Log in using your MySQL log in details.

Linking it to a CI Pipeline:
1) Set up SonarQube. You may want to do this in a GCP virtual machine.
2) For ubuntu, this may be done using following commands in a ubuntu VM. Close the VM after entering the first command:
```
curl https://gist.githubusercontent.com/christophperrins/760262e7308ceb8d9c51b4b984792a43/raw/00970ff2aa1857ab54f573f750c9f4f23d6c9578/installDocker.sh | sh
```
exit, then open again
```
curl https://gist.githubusercontent.com/christophperrins/fa5155359f8808a83fee7e34abb21769/raw/10f8cee4968fe76510b9e6a04cb6c679be92b466/installSonaqubeWithDocker.sh | sh
```
3) Whitelist your IP address and Port in the VM's network settings.
4) open jenkins (localhost)
5) log in with the password provided int he jenkins settings folder. You may want to change this later.
6) create a new freestyle project
7) click configure
8) link it to your GitHub repository in the source management section
9) in the scm section, check the poll scm box
10) switch on options to abort build when stuck
11) build your program with Maven by adding following batch commands:
```
mvn clean package test
```
12) upload it to SonarQube (replace IP with your SonarQube VM instance or the like) do NOT use new lines as in example
```
	mvn sonar:sonar
		--define sonar.host.url=http://<yourSonarqubeVirtualMachineIPAddress>:9000
		--define sonar.login.admin=admin
		--define sonar.password=admin
```
13) to deploy an artifact to nexus
```
	mvn deploy:deploy-file
		--define generatePom=false 
		--define pomFile=pom.xml 
		--define url=<yourSonarqubeVirtualMachineIPAddress>:8081/repository/maven-snapshots 
		--define file=target/<artifactId>-<descriptorRef>.jar (the name of the fat jar in /target following build step)
		--define repositoryId=nexus
```
14) You may now run the program using a compatible command line interface or your IDE. You can test it using JUnit by creating a new package, or do it automatically by creating a new Jenkins build. Review your code, which is uploaded to SonarQube for static analysis afterwards.

## Running the tests

SonarQube was used for static analysis, whereas JUnit, together with Mockito, were used to test the functionality of the program.
Controller classes, Service classes, DAO classes and the Domains themselves all have their own tests. 
Testing will drop the database and all information containing it! Be aware of this before running a test.

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management
* [Jenkins](https://maven.apache.org/) - CI Pipeline scripter
* [Eclipse](https://maven.apache.org/) - IDE
* [SonarQube](https://maven.apache.org/) - static analysis
* [Nexus](https://maven.apache.org/) - artifact repository
* [Git](https://maven.apache.org/) - version control system
* [GitHub](https://maven.apache.org/) - host of version control system
* [Trello](https://maven.apache.org/) - planning, kanban
* [MySQL](https://maven.apache.org/) - database language
* [GCP](https://maven.apache.org/) - database host


## Authors

* **Chris Perrins** - *Initial work* - [christophperrins](https://github.com/christophperrins)
* **Korbinian Ring** - *all but first commit * - [KMRRingQA](https://github.com/KMRRingQA)

## License

This project is licensed under the MIT license - see the [LICENSE.md](LICENSE.md) file for details 

# IMSProject
