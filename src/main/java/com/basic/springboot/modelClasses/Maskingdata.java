package com.basic.springboot.modelClasses;

import com.basic.springboot.Configuration.MaskData;
import com.basic.springboot.Configuration.Utils;
import lombok.Data;
import org.hibernate.procedure.internal.Util;

@Data
public class Maskingdata extends MaskData {

    private String ssn;
    private String date;

    //this is to mask the ssn while returing to frontend


    public String getSsn() {
        return Utils.maskSSN(ssn);
    }

    // this is for printing in the logs becuse it is masked in toString()
    @Override
    public String toString() {
        
        maskingFeilds=new String[]("ssn");
        return super.toString();
    }
}
