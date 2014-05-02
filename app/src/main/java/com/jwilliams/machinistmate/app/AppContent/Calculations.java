package com.jwilliams.machinistmate.app.AppContent;

import java.text.DecimalFormat;

/**
 * Created by john.williams on 3/27/2014.
 * This class is used for the conversion of various values within the app.
 */
public class Calculations {
    //Conversion Activity Variables
    private final static double INCHES_TO_FEET = 0.0833;
    private final static double INCHES_TO_YARD = 0.0277;
    private final static double INCHES_TO_MM = 25.4;
    private final static double INCHES_TO_CM = 2.54;
    private final static double INCHES_TO_METER = 0.0254;
    private final static double FEET_TO_YARD = 0.3333;
    private final static double FEET_TO_MM = 304.8;
    private final static double FEET_TO_CM = 30.48;
    private final static double FEET_TO_METER = 0.3048;
    private final static double YARD_TO_MM = 914.4;
    private final static double YARD_TO_CM = 91.44;
    private final static double YARD_TO_METER = 0.9144;
    private final static double MM_TO_CM = 0.1;
    private final static double MM_TO_METER = 0.001;
    private final static double CM_TO_METER = 0.01;

    public Calculations() {

    }

    //Conversion Activity Calculations
    public static double conversionCalc(int inputSpinner, int outputSpinner, double input, int precSpinner) {
        double answer = 0.0;
        DecimalFormat df = new DecimalFormat("###.###");

        if (inputSpinner == outputSpinner) {
            answer = input;
        } else {
            switch (inputSpinner) {
                case 0:
                    switch (outputSpinner) {
                        case 1:
                            answer = input * INCHES_TO_FEET;
                            break;
                        case 2:
                            answer = input * INCHES_TO_YARD;
                            break;
                        case 3:
                            answer = input * INCHES_TO_MM;
                            break;
                        case 4:
                            answer = input * INCHES_TO_CM;
                            break;
                        case 5:
                            answer = input * INCHES_TO_METER;
                            break;
                    }
                    break;
                case 1:
                    switch (outputSpinner) {
                        case 0:
                            answer = Double.parseDouble(df.format(input / INCHES_TO_FEET));
                            break;
                        case 2:
                            answer = input * FEET_TO_YARD;
                            break;
                        case 3:
                            answer = input * FEET_TO_MM;
                            break;
                        case 4:
                            answer = input * FEET_TO_CM;
                            break;
                        case 5:
                            answer = input * FEET_TO_METER;
                            break;
                    }
                    break;
                case 2:
                    switch (outputSpinner) {
                        case 0:
                            answer = input / INCHES_TO_YARD;
                            break;
                        case 1:
                            answer = input / FEET_TO_YARD;
                            break;
                        case 3:
                            answer = input * YARD_TO_MM;
                            break;
                        case 4:
                            answer = input * YARD_TO_CM;
                            break;
                        case 5:
                            answer = input * YARD_TO_METER;
                            break;
                    }
                    break;
                case 3:
                    switch (outputSpinner) {
                        case 0:
                            answer = input / INCHES_TO_MM;
                            break;
                        case 1:
                            answer = input / FEET_TO_MM;
                            break;
                        case 2:
                            answer = input / YARD_TO_MM;
                            break;
                        case 4:
                            answer = input * MM_TO_CM;
                            break;
                        case 5:
                            answer = input * MM_TO_METER;
                            break;
                    }
                    break;
                case 4:
                    switch (outputSpinner) {
                        case 0:
                            answer = input / INCHES_TO_CM;
                            break;
                        case 1:
                            answer = input / FEET_TO_CM;
                            break;
                        case 2:
                            answer = input / YARD_TO_CM;
                            break;
                        case 3:
                            answer = input / MM_TO_CM;
                            break;
                        case 5:
                            answer = input * CM_TO_METER;
                            break;
                    }
                    break;
                case 5:
                    switch (outputSpinner) {
                        case 0:
                            answer = input / INCHES_TO_METER;
                            break;
                        case 1:
                            answer = input / FEET_TO_METER;
                            break;
                        case 2:
                            answer = input / YARD_TO_METER;
                            break;
                        case 3:
                            answer = input / MM_TO_METER;
                            break;
                        case 4:
                            answer = input / CM_TO_METER;
                            break;
                    }
                    break;
            }
        }
        return formatter(answer, precSpinner);
    }

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
