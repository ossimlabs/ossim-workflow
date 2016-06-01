package aws.cli

import grails.boot.GrailsApp
import grails.boot.config.GrailsAutoConfiguration
import grails.util.Holders

class Application extends GrailsAutoConfiguration {
    static void main(String[] args) {
        def ctx = GrailsApp.run(Application, args)
        AwsUtils.resetAwsConfig()

        new AwsApp().run(args)

        //def sqsMessageDriver = new SqsMessageDriver(grailsApplication:)

 //       System.out.println("Let's inspect the beans provided by Spring Boot:");

 //       String[] beanNames = ctx.getBeanDefinitionNames();
 //       Arrays.sort(beanNames);
 //       for (String beanName : beanNames) {
 //           System.out.println(beanName);
  //      }
 //       println Holders.grailsApplication.config

        //def awsApp = ctx.getBean("awsApp")
        //def awsConfig = ctx.getBean("AwsConfig")

      //  println "MAIN: ${awsApp.awsConfig}"
       // println "MAIN: ${awsConfig.grailsApplication}"

//        println grailsApplication.config
//        println ctx.getBean("sqsMessageDriver")//?.grailsApplication?.config
//
//        SqsMessageDriver driver = new SqsMessageDriver()

//        println driver.grailsApplication.config

   }
}