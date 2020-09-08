package com.basic.springboot.Configuration;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerService {

    private static final Logger logger= LogManager.getLogger("APRAJITA'S APPLICATION");


    public static void debug(String obj,Object message){
        message=obj+":"+message;
        logger.info(message);
    }
    public static void debug(Object message){
        logger.info(message.toString());
    }

}
