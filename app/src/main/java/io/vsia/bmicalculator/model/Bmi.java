package io.vsia.bmicalculator.model;

public class Bmi {

    public String name;
    public double gewicht;
    public double groesse;
    public double bmi;

    public Bmi(String name, double gewicht, double groesse, double bmi) {
        this.name = name;
        this.gewicht = gewicht;
        this.groesse = groesse;
        this.bmi = bmi;
    }

    public Bmi() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getGewicht() {
        return gewicht;
    }

    public void setGewicht(double gewicht) {
        this.gewicht = gewicht;
    }

    public double getGroesse() {
        return groesse;
    }

    public void setGroesse(double groesse) {
        this.groesse = groesse;
    }

    public double getBmi() {
        return bmi;
    }

    public void setBmi(double bmi) {
        this.bmi = bmi;
    }

    @Override
    public String toString() {
        StringBuilder response = new StringBuilder();
        response.append("Name: ").append(name)
                .append(" (").append(gewicht)
                .append(" kg, ").append(groesse)
                .append(" cm)").append("\nBMI: ").append(bmi);
      return response.toString();
    }
}
