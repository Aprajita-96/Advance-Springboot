package com.basic.springboot.controller;

import com.basic.springboot.service.CacheMethodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1")
public class CacheCalling {

    @Autowired
    private CacheMethodsService service;

    @PostMapping("/posts")
    public ResponseEntity<?> getentitlments(){
        return new ResponseEntity<>(service.getentitlemnts(), HttpStatus.OK);
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
