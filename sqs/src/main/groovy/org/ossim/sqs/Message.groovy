package org.ossim.sqs

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.model.CreateQueueRequest;
import com.amazonaws.services.sqs.model.DeleteMessageRequest;
import com.amazonaws.services.sqs.model.DeleteQueueRequest;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.services.sqs.model.ChangeMessageVisibilityRequest
import org.apache.commons.codec.digest.DigestUtils
import groovy.util.logging.Slf4j

@Slf4j
class Message
{
   AmazonSQS sqs = new AmazonSQSClient()

   def listQueues()
   {
     sqs.listQueues()
   }
   static Boolean checkMd5(String messageBodyMd5, String message)
   {
     String md5Check =  DigestUtils.md5Hex (message);
        
     md5Check == messageBodyMd5

   }
   void send(String queue, String messageBody)
   {
      try{
         sqs.sendMessage(new SendMessageRequest()
            .withQueueUrl(queue)
            .withMessageBody(messageBody)
          )
      }
      catch(e)
      {
         log.error("ERROR: Unable to send to queue ${queue}")
         log.error("ERROR: ${e.toString()}")
      }
   }

   def receive(String queue)
   {
      log.trace ("receive(String queue) entered......")
      def result = []
      def messages
      try{
         ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest(queue);

         messages = sqs.receiveMessage(receiveMessageRequest).messages
      }
      catch(e)
      {
         log.error("ERROR: Unable to receive message for queue: ${queue}")
         log.error("ERROR: ${e.toString()}")
      }
      messages?.each{message->
         try{
            if(checkMd5(message.mD5OfBody, message.body))
            {
               result << message.body
            }
            else
            {
               log.error("ERROR: BAD MD5 Checksum For Message: ${messageBody}")
            }
            sqs.deleteMessage(new DeleteMessageRequest(queue, message.receiptHandle));
         }
         catch(e)
         {
            log.error("ERROR: ${e.toString()}")
         }
      }
      log.trace ("receive(String queue) leaving......")
      result
   }

}