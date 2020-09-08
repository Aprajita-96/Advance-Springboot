package com.basic.springboot.service;

import lombok.Data;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Data
public class BaseService {

    RestTemplate restTemplate;
    HttpHeaders httpHeaders;

    public BaseService() {
        httpHeaders=new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        restTemplate=new RestTemplate();
    }
    public HttpHeaders getHttpHeaders(){
        return this.httpHeaders;
    }
}
