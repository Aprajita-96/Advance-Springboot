package com.basic.springboot.awsSqs;

import com.amazonaws.AmazonClientException;
import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagingTemplate {

    @Value("${spring.profile.active}")
    private String profile;

    @Bean
    public AmazonSQS getAwsSqs(){
        if(null!=profile && "local".equalsIgnoreCase(profile)){
            return getSQSTemplateLocal();
        }
        else{
        return getSQSTemplate();
        }
    }
    public AmazonSQS getSQSTemplate(){
        return AmazonSQSClientBuilder.
                standard().
                withRegion(Regions.US_EAST_1).build();
    }

    public AmazonSQS getSQSTemplateLocal() {
        AWSCredentials credentials = null;
        ProfileCredentialsProvider credentialsProvider = new ProfileCredentialsProvider();
        try {
            credentials = credentialsProvider.getCredentials();

        } catch (Exception e) {
            throw new AmazonClientException("Cannot load credntials");
        }
        return AmazonSQSClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.US_EAST_1).withClientConfiguration(clientConfiguration()).build();
    }
    @Bean
    public ClientConfiguration clientConfiguration(){
        ClientConfiguration clientConfiguration=new ClientConfiguration();
        clientConfiguration.setProtocol(Protocol.HTTPS);
        clientConfiguration.setProxyHost("proxy.appu.com");
        clientConfiguration.setProxyPort(8080);
        clientConfiguration.setProxyUsername("abcd2345");
        clientConfiguration.setProxyPassword("0000000");
        return clientConfiguration;
    }

}
