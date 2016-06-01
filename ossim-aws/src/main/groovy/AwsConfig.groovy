package aws.cli
import grails.core.GrailsApplication
import grails.util.Holders

class AwsConfig
{
   static GrailsApplication application

   private static GrailsApplication getApplication() {
      if (!application) {
         application = Holders.grailsApplication
      }
      application
   }
   static ConfigObject getAwsConfig() {
      def config = getApplication().config

      config.aws
   }
   static void setAwsConfig(ConfigObject c) {
      ConfigObject config = new ConfigObject()
      config.aws = c
   }
}