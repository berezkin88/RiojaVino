"Riojavino" is a test project.

It's simulating a wine e-shop.

Environment requirements: Tomcat as a servlet container, Maven as an assembling tool.

To run the project the git repository should be cloned to a local machine. The building could be accomplished via CLI started in the Riojavino local repo, with "mvn install" command or build via any of Java editors with Maven plugin (IntelliJ IDEA, Eclipse). 

As a result riojavino.war package should appear. This package should be relocated to Tomcat/webapps repository.

After starting Tomcat server the webapp would be reachable by "localhost:port/riojavino" URL

For correct webapp working "data" folder with test wine csv database should be placed in Tomcat/ folder as well.

The "data" folder is provided in this git repository