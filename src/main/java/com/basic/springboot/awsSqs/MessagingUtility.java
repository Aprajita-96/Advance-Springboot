package com.basic.springboot.awsSqs;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.*;
import com.basic.springboot.Configuration.LoggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MessagingUtility {

    @Autowired
    AmazonSQS amazonSQS;

    @Value("${aws.auth.sqs.queue.delaySeconds}")
    private int delay;
    @Value("${aws.auth.sqs.queue.url}")
    private String sqsUrl;
    @Value("${aws.auth.sqs.queue.failure.delaySeconds}")
    private int failureDelay;
    @Value("{aqs.auth.sqs.queue.failure.url}")
    private String failureSqsUrl;
    @Value("{aws.auth.sqs.queue.failure.max-message-read}")
    private int failureSqsMaxMsgRead;


    public String send(String payload , String trnstype){
        LoggerService.debug("Posting message to sqs");
        Map<String, MessageAttributeValue> messageAttributeValueMap=new HashMap<>();
        messageAttributeValueMap.put("APPNAME",new MessageAttributeValue()
                                            .withStringValue(trnstype).
                                            withDataType("String"));
        SendMessageRequest sendMessageRequest=new SendMessageRequest()
                .withQueueUrl(sqsUrl)
                .withMessageBody(payload)
                .withMessageAttributes(messageAttributeValueMap);
        SendMessageResult result=null;
        try{
            result=amazonSQS.sendMessage(sendMessageRequest);
        }
        catch(Exception e){
            LoggerService.debug(e);
        }
        return result.getMessageId();
    }


    public List<Message> recieveMessageFromfAilureQueue(){
        LoggerService.debug("Recieving messages from "+failureSqsUrl);
        ReceiveMessageRequest receiveMessageRequest=new ReceiveMessageRequest()
                    .withMaxNumberOfMessages(failureSqsMaxMsgRead);
        List<Message> sqsMessage=amazonSQS.receiveMessage(receiveMessageRequest).getMessages();
        return sqsMessage;
    }

    public boolean deleteMsgFromFailureQueue(String messageHandle){
        amazonSQS.deleteMessage(new DeleteMessageRequest()
                .withQueueUrl(failureSqsUrl)
        .withReceiptHandle(messageHandle));
        return true;
    }
}
