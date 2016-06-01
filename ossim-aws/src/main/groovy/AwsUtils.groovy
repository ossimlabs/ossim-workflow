package aws.cli
import groovy.util.logging.Slf4j
import grails.util.Environment

@Slf4j
class AwsUtils
{
   private static ConfigObject awsConfig

   /**
    * Parse and load the aws configuration.
    * @return the configuration
    */
   static synchronized ConfigObject getAwsConfig() {
      if (awsConfig == null) {
         log.trace 'Building aws config since there is no cached config'
         reloadAwsConfig()
      }
      awsConfig
   }


   /** Force a reload of the aws  configuration. */
   static void reloadAwsConfig() {
      mergeConfig AwsConfig.awsConfig, 'DefaultAwsConfig'

      mergeConfigToGlobalConfig()

      log.trace 'reloaded aws config'
   }
   /** Reset the config for testing or after a dev mode Config.groovy change. */
   static synchronized void resetAwsConfig() {
      awsConfig = null
      log.trace 'reset aws config'
   }

   static void mergeConfigToGlobalConfig(){

      log.trace "mergeConfigToGlobalConfig(): Entered..............."

      log.trace "mergeConfigToGlobalConfig(): Leaving..............."
   }
   /**
    * Merge in a secondary config (provided by a plugin as defaults) into the main config.
    * @param currentConfig the current configuration
    * @param className the name of the config class to load
    */
   private static void mergeConfig(ConfigObject currentConfig, String className) {
      ConfigObject secondary = new ConfigSlurper(Environment.current.name).parse(
              new GroovyClassLoader(this.classLoader).loadClass(className))
      awsConfig = AwsConfig.awsConfig = mergeConfig(currentConfig, secondary.aws as ConfigObject)
   }

   /**
    * Merge two configs together. The order is important if <code>secondary</code> is not null then
    * start with that and merge the main config on top of that. This lets the <code>secondary</code>
    * config act as default values but let user-supplied values in the main config override them.
    *
    * @param currentConfig the main config, starting from Config.groovy
    * @param secondary new default values
    * @return the merged configs
    */
   private static ConfigObject mergeConfig(ConfigObject currentConfig, ConfigObject secondary) {
      (secondary ?: new ConfigObject()).merge(currentConfig ?: new ConfigObject()) as ConfigObject
   }

   private static def mergeConfig(java.util.Map currentConfig, def grailsConfig)
   {
      currentConfig.keySet().each{key->
         grailsConfig."${key}" = currentConfig."${key}"
      }
      grailsConfig
   }
}
