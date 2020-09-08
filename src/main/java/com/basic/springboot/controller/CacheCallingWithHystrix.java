package com.basic.springboot.controller;

import com.basic.springboot.modelClasses.RequestObject;
import com.basic.springboot.service.CacheMethodsService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CacheCallingWithHystrix {

    @Autowired
    private CacheMethodsService service;
    //below is the exmaple of hystrix
    @HystrixCommand(commandKey = "getentitlments" , fallbackMethod = "getentitlementsDefault")
    @PostMapping("/posts")
    public ResponseEntity<?> getentitlments(@RequestBody RequestObject requestObject){
        return new ResponseEntity<>(service.getentitlemnts(), HttpStatus.OK);
    }

    public ResponseEntity<?> getentitlementsDefault(RequestObject requestObject){
        List<RequestObject> response=new ArrayList<>();
        return new ResponseEntity<>(response,HttpStatus.OK);

    }



    @GetMapping("/clearcache")
    public ResponseEntity<?> claearCache(){
        String res=service.clearCache();
        String status="success";
        if(res==null){
            status="fail";
        }
        return new ResponseEntity<>("{\"status\":\""+status+"\"}",HttpStatus.OK);

    }

    @PostMapping("/getPost")
    public ResponseEntity<?> getProfilefromheader(HttpServletRequest req){
    String userId=req.getHeader("USER_ID");
    String res=service.getresponse();
    return new ResponseEntity<>(res,HttpStatus.OK);
    }
}
