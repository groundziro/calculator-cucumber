package calculator;

import java.util.Arrays;
import java.util.List;

public class Unit {
    public final String type; // support: length
    public final String kind; // support: km, m
    public final Double value;
    public final Double strength;

    public Unit(String type, String kind, Double value){
        this.type = type;
        this.kind = kind;
        this.value = value;
        this.strength = getStrength(this.kind);
    }

    public static Double getStrength(String value){
        double output;
        switch (value){
            case "km":
                output = 1000.0;
                break;
            case "hm":
                output = 100.0;
                break;
            case "dam":
                output = 10.0;
                break;
            case "m":
            case "sqrt_m":
            case "eur":
            case "watts":
            case "atmos":
            case "cm per sec":
            case "celsius":
            case "sec":
                output = 1.0;
                break;
            case "dm":
                output = 0.1;
                break;
            case "cm":
            case "m per sec":
                output = 0.01;
                break;
            case "mm":
            case "kilowatts":
                output = 0.001;
                break;
            case "in":
                output = 39.37008;
                break;
            case "ft":
                output = 3.28084;
                break;
            case "yd":
                output = 1.093613;
                break;
            case "mi":
                output = 0.000621;
                break;
            case "nm":
                output = 0.00054;
                break;
            case "sqrt_mm":
                output = 1000000;
                break;
            case "sqrt_cm":
                output = 10000;
                break;
            case "ha":
                output = 0.0001;
                break;
            case "sqrt_km":
                output = 0.000001;
                break;
            case "sqrt_in":
                output = 1550.003;
                break;
            case "sqrt_ft":
                output = 10.76391;
                break;
            case "sqrt_yd":
                output = 1.19599;
                break;
            case "ac":
                output = 0.000247;
                break;
            case "sqrt_mi":
                output = 0.000000386102159;
                break;
            case "usd":
                output = 1.18;
                break;
            case "pound":
                output = 0.85;
                break;
            case "china_yuan":
                output = 7.73;
                break;
            case "japan_yen":
                output = 130.0;
                break;
            case "horsepower":
                output = 0.001341;
                break;
            case "foot-pounds/minute":
                output = 44.25373;
                break;
            case "BTUs/minute":
                output = 0.056869;
                break;
            case "bars":
                output = 1.01325;
                break;
            case "kilopascals":
                output = 101.325;
                break;
            case "millimetres of mercury":
                output = 760.1275;
                break;
            case "pascals":
                output = 101325.0;
                break;
            case "pounds per square inch":
                output = 14.69595;
                break;
            case "km per hour":
                output = 0.036;
                break;
            case "ft per sec":
                output = 0.032808;
                break;
            case "mi per hour":
                output = 0.022371;
                break;
            case "knots":
                output = 0.01944;
                break;
            case "mach":
                output = 0.000029;
                break;
            case "fahrenheit":
                output = 33.8;
                break;
            case "kelvin":
                output = 274.15;
                break;
            case "microsec":
                output = 1000000.0;
                break;
            case "millisec":
                output = 1000.0;
                break;
            case "min":
                output = 0.016667;
                break;
            case "hours":
                output = 0.000278;
                break;
            case "days":
                output = 0.000012;
                break;
            case "weeks":
                output = 0.000002;
                break;
            case "years":
                output = 0.000000031688088;
                break;
            default:
                System.out.println("Invalid kind");
                output = 0.0;
                break;
        }
        return output;
    }
}
