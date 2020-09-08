package com.basic.springboot.Configuration;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MaskData implements Cloneable {

    Set<String> feildSet;
    protected String[] maskingFeilds;

    public Object clone(){ return this;}

    @Override
    public String toString() {
        feildSet =new HashSet<String>();
        StringBuffer buffer=new StringBuffer();
        try{
            Object object=super.clone();
            maskObjectFeilds(buffer,object);

        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return buffer.toString();
    }

    private void maskObjectFeilds(StringBuffer buffer, Object object){
        try{
            buffer.append(object.getClass().getCanonicalName()).append("{");
            Object value=null;
            for(Field field:object.getClass().getDeclaredFields()){
                if(feildSet.add(field.getName())){
                    field.setAccessible(true);
                    buffer.append(field.getName()+"=");
                    value=field.get(object);
                    if(value!=null){
                        if(field.getType().isArray())
                        {
                            buffer.append("[");
                            maskObjectFeilds(buffer,value);
                            buffer.append("]");
                        }
                        else if(Arrays.asList(maskingFeilds).contains(field.getName())){
                            buffer.append(Utils.fullMask((String)value));
                        }
                        else{
                            buffer.append(value);
                        }
                    }
                }
                buffer.append(",");
            }
            buffer.append("}");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
