package com.jwilliams.machinistmate.app.AppContent;


import java.text.DecimalFormat;

/**
 * Created by john.williams on 3/27/2014.
 * This class is used for the conversion of various values within the app.
 */
public class Utility {

    public Utility() {
    }

    // Decimal Formatter usable by all Activities
    public static double formatter(double answer, int precision){
        DecimalFormat df;
        String formats= "##.";
        Double returnAnswer;

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
