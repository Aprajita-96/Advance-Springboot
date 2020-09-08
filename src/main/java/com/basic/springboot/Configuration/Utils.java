package com.basic.springboot.Configuration;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Utils {

    public static String fullMask(String value){
        int len=value==null?0:value.length();
        if(len==0)
            return "";
        return value.replaceAll(".","*");
    }


    public static String maskSSN(String ssn){
        int len= ssn==null?0:ssn.length();
        if(len==0)
            return "";
        String stars=len>0 ? len>1 ? len>2 ? len>3? len>4? "***-**-":"***-*":"***-":"**":"*":"";
        String res=stars+ssn.substring(5);
        return res;
    }

    public static String getESTDateTimeString(){
        DateTimeFormatter eformat= DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss.SSS");

        ZoneId etzoneId= ZoneId.of("America/New_York");
        LocalDateTime currentDateTime=LocalDateTime.now(etzoneId);
        return eformat.format(currentDateTime);
    }

    public static Date getESTDateTime(){
        DateFormat st=new SimpleDateFormat("MM/dd/yyy HH:mm:ss.SSS");
        Date dt=null;
        try{
            dt=st.parse(getESTDateTimeString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dt;
    }
}
