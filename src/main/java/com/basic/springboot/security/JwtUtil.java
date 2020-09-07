package com.basic.springboot.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

@Component
public class JwtUtil {
    @Value("${security.jwt.token.secret-key}")
    private String secretKey;

    @Value("${security.jwt.token.token.expire-length}")
    private long validityInMiliSeconds; //1hr

    public String getJwtToken(String userId){
        if(null==userId || userId.trim().length()==0){
            return "";
        }
        String token =null;
        String roles=null;


        if(null!=userId && "admin".equalsIgnoreCase(userId))
        {
            roles="admin";
        }
        else if(null!=userId && "ams".equalsIgnoreCase(userId)){
            roles="suppport";

        }
        else{
            roles="user";
        }
        Calendar now=Calendar.getInstance();
        now.setTimeInMillis(now.getTimeInMillis() + validityInMiliSeconds);
        token="Bearer" + Jwts.builder().
                setSubject(userId).
                claim("roles",roles).
                setIssuedAt(new Date()).
                setExpiration(now.getTime()).
                signWith(SignatureAlgorithm.HS256,secretKey).
                compact();
        return token;


    }

    public boolean validateToken(String token){
        try{
            Jws<Claims> claims=Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            if(claims.getBody().getExpiration().before(new Date())){
                return false;
            }
            return true;
        }
        catch(Exception e){
            return false;
        }
    }


}
