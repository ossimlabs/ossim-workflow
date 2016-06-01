package org.ossim.sqs

import groovy.util.logging.Slf4j
//import groovy.util.ConfigSlurper

@Slf4j
class MessageDriver
{
   MessageDriverProperties configSettings = new MessageDriverProperties()
   static void main(String []args)
   {
//      def config = new ConfigSlurper('prod').parse(new URL("file:application.yml"))

//      println config
     // MessageDriver messageDriver = new MessageDriver()
     // println "configSettings.sqsQueue = ${messageDriver.configSettings.sqsQueue}"
   }
}