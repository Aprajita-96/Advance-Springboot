package com.basic.springboot.service;


import com.basic.springboot.Configuration.LoggerService;
import com.basic.springboot.modelClasses.RequestObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class CacheMethodsService extends BaseService {

    @Value("${esb.api.entitlements}")
    private String entitleUrl;
    @Value("${esb.api.posts}")
    private String postUrl;
    @Value("${esb.api.basicAuth}")
    private String basicAuth;


    @Cacheable("entilements")
    public String getentitlemnts(){
        LoggerService.debug("in side the entitlements");
        ResponseEntity<String> response=null;
        RequestObject req=new RequestObject();
        try{
            HttpHeaders header=getHttpHeaders();
            header.set(HttpHeaders.AUTHORIZATION,basicAuth);
            HttpEntity<RequestObject> entity=new HttpEntity<>(req,header);
            response=getRestTemplate().exchange(entitleUrl, HttpMethod.POST,entity,String.class);
            return response.getBody();
        }
        catch (Exception e){
            LoggerService.debug("exception occured in the external service");
        }
        return null;
    }
    @CacheEvict(value="entitlements",allEntries = true,beforeInvocation = true)
    @Cacheable("entitlements")
    public String clearCache(){
        LoggerService.debug("In refreshing cache");
        return getentitlemnts();
    }

    //below is the function to call a esb with query param
    public String getresponse(){
        ResponseEntity<String> response=null;
        try{
            HttpHeaders header=getHttpHeaders();
            header.set(HttpHeaders.AUTHORIZATION,basicAuth);
            HttpEntity<String> entity=new HttpEntity<>(header);
            UriComponents builder= UriComponentsBuilder.fromHttpUrl(postUrl)
                    .queryParam("userId","234567").build();
            long startTime=System.currentTimeMillis();
            response=getRestTemplate().exchange(builder.toUriString(),HttpMethod.POST,entity,String.class);
            return response.getBody();
        }
        catch (Exception e){
            LoggerService.debug("Exception"+e);
        }
        return null;
    }



}
