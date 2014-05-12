package com.jwilliams.machinistmate.app.AppContent;

/**
 * John Williams
 * Calculation class handles all calculations for the app,
 * which aren't directly tied to the view controller
 */
public class Calculations {
    public Calculations(){

    }

    public static double calcSinByDegree(double degreeRad) {
        return Math.sin(Math.toRadians(degreeRad));
    }

    public static double calcSinByRadian(double degreeRad) {
        return Math.sin(degreeRad);
    }

    public static double calcSinByValues(double opposite, double hypotenuse) {
        return opposite/hypotenuse;
    }

    public static double calcInvSinToDegree(double degreeRad) {
        return Math.toDegrees(Math.asin(degreeRad));
    }

    public static double calcInvSinToRadian(double degreeRad) {
        return Math.asin(degreeRad);
    }

    public static double calcCosByDegree(double degreeRad) {
        return Math.cos(Math.toRadians(degreeRad));
    }

    public static double calcCosByRadian(double degreeRad) {
        return Math.cos(degreeRad);
    }

    public static double calcCosByValues(double adjacent, double hypotenuse) {
        return adjacent/hypotenuse;
    }

    public static double calcInvCosToDegree(double degreeRad) {
        return Math.toDegrees(Math.acos(degreeRad));
    }

    public static double calcInvCosToRadian(double degreeRad) {
        return Math.acos(degreeRad);
    }

    public static double calcTanByValues(double opposite, double adjacent) {
        return opposite/adjacent;
    }

    public static double calcTanByRadian(double degreeRad) {
        return Math.tan(degreeRad);
    }

    public static double calcTanByDegree(double degreeRad) {
        return Math.tan(Math.toRadians(degreeRad));
    }

    public static double calcInvTanToDegree(double degreeRad) {
        return Math.toDegrees(Math.atan(degreeRad));
    }

    public static double calcInvTanToRadian(double degreeRad) {
        return Math.atan(degreeRad);
    }

    public static double calcArea(double base, double height) {
        return (base*height)/2;
    }

    public static double calcSideH(double sideO, double sideA) {
        return Math.sqrt(Math.pow(sideO,2)+Math.pow(sideA,2));
    }

    public static double calcSideO(double sideH, double sideA) {
        return Math.sqrt(Math.pow(sideH,2)-Math.pow(sideA,2));
    }

    public static double calcSideA(double sideH, double sideO) {
        return Math.sqrt(Math.pow(sideH,2)-Math.pow(sideO,2));
    }

    public static double calcPerimeter(double sideH, double sideO, double sideA) {
        return sideH+sideO+sideA;
    }
}
