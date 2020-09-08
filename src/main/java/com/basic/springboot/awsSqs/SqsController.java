package com.basic.springboot.awsSqs;


import com.basic.springboot.service.ServiceUsingSQS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/sqs")
public class SqsController {

    @Autowired
    private ServiceUsingSQS serviceUsingSQS;

    @Autowired
    MessagingUtility utility;

    @PostMapping("/processfailedMessages")
    public ResponseEntity<?> processfail(@RequestBody SqsMessagesHolder messagesHolder) {

        StringBuilder res = new StringBuilder("{\"processed\":[");
        if (null != messagesHolder && null != messagesHolder.getFailureMessages()) {

            for (SqsMessage sqsMessage : messagesHolder.getFailureMessages()) {
                utility.deleteMsgFromFailureQueue(sqsMessage.getReceiptHandle());
                // do the required steps like
                //TransactionLog transactionLog=new TransactionLog();
                //transactionlog.setMessageId(sqsMessage.getMessageId());
                //transactionLog.setStatus("Failure")
                //service.updateTransactionLog(transactionlog); //this is simple update method of repo
                res.append("/");
                res.append(sqsMessage.getMessageId());
                res.append("/");


            }

        }
        res.append("]}");
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
