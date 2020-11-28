Pre-Requisites:
1. Make sure Java jdk 1.7 version is installed.
2. Make sure maven with version 3.0 or above is installed.
3. Make sure that no other process is listening on the port 8080.

To build & run the application:

1. Download or clone the repository from git.
2. Open terminal and move to project folder(nerworking) where pom.xml file can be found.
3. To build the artifact jar, run the below command.
      mvn clean install
4. The executable jar file can be found under networking/target/ folder.
5. Move to target folder and run the below command to start the application.
      java -jar networking-0.0.1-SNAPSHOT.jar