salesforce-package-generator
============================

Salesforce ANT tool to generate package.xml file. Follow the steps to build the tool.

1. Install JDK 8 (8 or later is required). Download from here http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
2. Install ANT (download and unzip somewhere). Download from here http://ant.apache.org/
3. On command prompt run javac -version and java -version. Both should show java 8, if not try removing old version.
4. Add ANT_HOME variable to the environment with pointing to where you installed the ANT
5. Add ANT_HOME/bin to the PATH and verify by running 'ant' on command prompt.
6. Download ant-salesforce.jar from your salesforce sandbox and copy it to ANT_HOME/lib.
7. Clone this repo somewhere, update build.xml and put the ANT home folder for ant.home.dir property.
8. On command prompt, while in the folder where you cloned this repo, issue command ant.
9. You should see packagegenerator.jar generated and copied to ANT_HOME/lib.
10. Enjoy.
