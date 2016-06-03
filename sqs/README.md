# Welcome to the SQS jar library

This library dumbs down the aws client interface to the SQS queue. The library only sends and receives messages and is used mainly for iterating a receive or sending individual or batch of messages.

##Dependencies

* Java 7 preferrably 8.
* AWS credentials stored in the your home location: Unix is **~/.aws/credentials** with contents defining your access keys

 ```
[default]
aws_access_key_id=
aws_secret_access_key= 
```

* Installed version of grails/groovy.  You can use the [sdkman](http://sdkman.io). Instructions repeated to install the sdkman package manager and instructions for the versions we currently have installed:

 ```
 $ curl -s "https://get.sdkman.io" | bash
 source "$HOME/.sdkman/bin/sdkman-init.sh"
 sdk version
 ``` 
 * Install the libraries used for building.

 ```
 sdk install grails 3.0.17
 sdk install groovy
 sdk install gradle
```

## Build

Build the library by using the build command and then install.  The install will put the library into the local maven.

```
gradle clean build install
``` 