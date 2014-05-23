package com.jwilliams.machinistmate.app.AppContent;

import java.text.DecimalFormat;

/**
 * Created by John on 5/22/2014.
 */
public class Calculations {

        // Decimal Formatter usable by all Activities
                public static double formatter(double answer, int precision){
                DecimalFormat df;
                String formats= "##.";
                Double returnAnswer = 0.0;
                if(precision>0) {
                        for (int x = 0; x < precision; x++) {
                                formats = formats + "#";
                            }
                        df = new DecimalFormat(formats);
                       returnAnswer = Double.valueOf(df.format(answer));
                    }else{
                        returnAnswer = answer;
                   }
                        return returnAnswer;
    }
}
