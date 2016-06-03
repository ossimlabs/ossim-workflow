# Welcome to the ossim-workflow Repository

This repository will have libraries for workflow management as well as the worklow plugins.

## Dependencies

* Java 7 preferrably 8.
* Installed version of grails/groovy.  You can use the [sdkman](http://sdkman.io). Instructions repeated to install the sdkman package manager and instructions for the versions we currently have installed:

 ```
 $ curl -s "https://get.sdkman.io" | bash
 source "$HOME/.sdkman/bin/sdkman-init.sh"
 sdk version
 ``` 
* Install libraries used for building:

 ```
 sdk install grails 3.0.17
 sdk install groovy
 sdk install gradle
```

## Build

Please see the individual README files for building each library.  Build the libraries in the following order

* see [sqs](sqs/README.md) for full build
 
 ```
 cd sqs
 gradle clean build install
 ``` 

* See [ossim-aws](ossim-aws/README.md) for full build and running

 ```
cd ossim-aws
./gradlew assemble
```
