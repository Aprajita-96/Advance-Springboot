package com.basic.springboot.awsSqs;


import lombok.Data;

import java.util.List;

@Data
public class SqsMessagesHolder {

    private List<SqsMessage> failureMessages;
}
