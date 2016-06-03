# Welcome to the Test AWS Command line application

The command line application is used as a way to test the AWS interface.  Currently we only support posting messages to a SQS Queue.

##Dependencies

* Java 7 preferrably 8.
* Build of the [sqs](../sqs) library
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
 * Install libraries used for building:

 ```
 sdk install grails 3.0.17
 sdk install groovy
 sdk install gradle
```

##Build

First build the [sqs](../sqs) library.  Once you have installed the sqs library into your local maven repository you can build the command line application by issuing the following command:

```
./gradlew assemble
```

And to get the current help:

```
java -jar build/libs/ossim-aws-1.0.0.jar --help

OUTPUT:

usage: ossim-aws [options]
 -h,--help                     Show usage information
    --queue <message>          Specify the queue to send the message to
    --send-message <message>   Send a message to the SQS queue.  Argument
                               is a URL
                               
```

##Run

To run with live data you will need access to an SQS queue.  The source message currently must a valid URL and accepts local file protocol using the **file:///my/directory/file.txt** or you can even use an http protocol to get the message to post.  For example: **https://my.domain.com/path/to/file.txt**.  The only caveat so far is that we assume that the entire file is the payload to upload to the SQS queue.

```
java -jar build/libs/ossim-aws-1.0.0.jar --queue https://sqs.us-east-1.amazonaws.com/path/to/queue --send-message https://my.domain.com/path/to/file.txt
```

