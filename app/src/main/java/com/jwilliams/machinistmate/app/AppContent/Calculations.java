package com.jwilliams.machinistmate.app.AppContent;

/**
 * Created by John on 5/10/2014.
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

    public static double calcInvSinByDegree(double degreeRad) {
        return Math.asin(Math.toRadians(degreeRad));
    }

    public static double calcInvSinByRadian(double degreeRad) {
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

    public static double calcInvCosByDegree(double degreeRad) {
        return Math.acos(Math.toRadians(degreeRad));
    }

    public static double calcInvCosByRadian(double degreeRad) {
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

    public static double calcInvTanByDegree(double degreeRad) {
        return Math.atan(Math.toRadians(degreeRad));
    }

    public static double calcInvTanByRadian(double degreeRad) {
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
