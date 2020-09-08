package com.basic.springboot.service;


import com.basic.springboot.awsSqs.MessagingUtility;
import com.basic.springboot.modelClasses.RequestObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ServiceUsingSQS {

    @Autowired
    private MessagingUtility messagingUtility;

    //below is the method showing how to send the payload to database as well as sqs

    public String postTransaction(RequestObject payload) throws JsonProcessingException {
        ObjectMapper mapper=new ObjectMapper();
        String jsonPayload=mapper.writeValueAsString(payload);
        //add this payload to db
        //2.send to sqs
        String msgId=messagingUtility.send(jsonPayload,"Trans_type");
        //transactionlog.setMessageId(msgId);
        return "sucess";
    }
}
