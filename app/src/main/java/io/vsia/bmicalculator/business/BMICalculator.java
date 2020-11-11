package io.vsia.bmicalculator.business;

import android.util.Log;

public class BMICalculator {

    public double calculateBMI(double gewicht, double groesse) {
        double BMI;

        //cm in m umwandeln
        groesse = groesse / 100;

        //BMI berechnen
        BMI = gewicht / (groesse * groesse);

        //auf ganze Zahl runden
        BMI = Math.round(BMI);

        return BMI;
    }
}
