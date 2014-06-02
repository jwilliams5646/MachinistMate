package com.jwilliams.machinistmate.app.AppContent;

import java.text.DecimalFormat;

/**
 * Created by John on 5/22/2014.
 */
public class Calculations {

        // Decimal Formatter usable by all Activities
                public static String formatter(double answer, int precision){
                DecimalFormat df;
                String formats= "##.";
                String returnAnswer = "";
                if(precision>0) {
                        for (int x = 0; x < precision; x++) {
                                formats = formats + "#";
                            }
                        df = new DecimalFormat(formats);
                       returnAnswer = df.format(answer);
                    }else{
                        formats= "##";
                        df = new DecimalFormat(formats);
                        returnAnswer = df.format(answer);
                   }
                        return returnAnswer;
    }
}
