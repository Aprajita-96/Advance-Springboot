package com.basic.springboot.awsSqs;


import lombok.Data;

@Data
public class SqsMessage {

    private String messageId;
    private String receiptHandle;
}
