package com.jwilliams.machinistmate.app.AppContent;

/**
 * Created by john.williams on 3/27/2014.
 * This class is used for the conversion of various values within the app.
 */
public class Calculations {

    private final static double INCHES_TO_FEET = 0.0833333333333333;
    private final static double INCHES_TO_YARD = 0.0277778;
    private final static double INCHES_TO_MM = 25.4;
    private final static double INCHES_TO_CM = 2.54;
    private final static double INCHES_TO_METER = 0.0254;


    private final static double FEET_TO_YARD = 0.333333;
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

    public static double calcAnswerReturned(int inputSpinner, int outputSpinner, double input) {
        double answer = 0.0;

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
                            answer = input / INCHES_TO_FEET;
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
        return answer;
    }
}
