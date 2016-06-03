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
        _ longOpt: 'queue', args:1, argName:'message', 'Specify the queue to send the message to'
      }

      def options = cli.parse(args)
      if (!options) {
         println cli.usage()
         return
      }
      if(options.help)
      {
        log.info cli.usage()
        return
      }
      if(options."send-message")
      {
        String queue = options."queue"
        if ((!queue)&&(config.sqsUrl))
        {
          queue = config.sqsUrl.toString()
        }
        if(queue)
        {
          Message msg = new Message()
          String sendMessageArg = options."send-message"

          URL url = new URL(sendMessageArg)
          String messageString = url.text
          if(messageString)
          {
            log.info "SENDING MESSAGE ${messageString}".toString()
            msg.send(queue, messageString)
          }
        }
        else
        {
          log.error "--queue option needs to be specified"
        }
      }
      else
      {
        log.error "--send-message with the argument needs to be specified"
      }
   }
}