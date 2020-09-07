package com.basic.springboot.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins="*",maxAge =3600)
@RestController
@RequestMapping("/jwt")
public class JwtController {

    @Autowired
    JwtUtil jwtUtil;

    @PostMapping(path="/gettoken")
    public ResponseEntity<?> gettoken(@RequestParam String userId){
        return new ResponseEntity<>("{\"token\":\""+jwtUtil.getJwtToken(userId)+ "\"}", HttpStatus.OK);
    }
}
