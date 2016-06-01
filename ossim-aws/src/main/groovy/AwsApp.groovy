package aws.cli
import groovy.util.logging.Slf4j
import groovy.util.CliBuilder
import java.net.URL
import org.ossim.sqs.Message

@Slf4j
class AwsApp
{
   void run(String []args)
   {
      def config = AwsUtils.awsConfig
      def cli = new CliBuilder(usage: 'ossim-aws [options]')

      cli.with {
        h longOpt: 'help', 'Show usage information'
        _ longOpt: 'send-message', args:1, argName:'message', 'Send a message to the SQS queue.  Argument is a URL'
      }

      def options = cli.parse(args)
      if (!options) {
         println cli.usage()
         return
      }
      if(options.help)
      {
        println cli.usage()
        return
      }
      if(options."send-message")
      {
         if(config.sqsUrl)
         {
            Message msg = new Message()
            String sendMessageArg = options."send-message"
            
            URL url = new URL(sendMessageArg)
            println "PROTOCOL: ${url.protocol}"
            String messageString = url.text
            if(messageString)
            {
              msg.send(config.sqsUrl, messageString)
            }
         }
      }
   }
}