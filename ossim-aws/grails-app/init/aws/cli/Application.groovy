package aws.cli

import grails.boot.GrailsApp
import grails.boot.config.GrailsAutoConfiguration
import grails.util.Holders

class Application extends GrailsAutoConfiguration {
    static void main(String[] args) {
        def ctx = GrailsApp.run(Application, args)
        AwsUtils.resetAwsConfig()

        new AwsApp().run(args)
   }
}