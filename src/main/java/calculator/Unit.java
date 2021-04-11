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
            case "ml":
            case "cubic cm":
            case "millisec":
            case "mg":
                output = 1000.0;
                break;
            case "hm":
            case "cg":
                output = 100.0;
                break;
            case "dam":
            case "decigrams":
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
            case "l":
            case "g":
                output = 1.0;
                break;
            case "dm":
            case "decagrams":
                output = 0.1;
                break;
            case "cm":
            case "m per sec":
            case "hectograms":
                output = 0.01;
                break;
            case "mm":
            case "kilowatts":
            case "cubic m":
            case "kilograms":
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
            case "metric tonnes":
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
            case "teaspoons US":
                output = 202.8841;
                break;
            case "tablespoons US":
                output = 67.62805;
                break;
            case "fluid ounces US":
                output = 33.81402;
                break;
            case "cups US":
                output = 4.226753;
                break;
            case "pints US":
                output = 2.113376;
                break;
            case "quarts US":
                output = 1.056688;
                break;
            case "gallons US":
                output = 0.264172;
                break;
            case "cubic in":
                output = 61.02374;
                break;
            case "cubic ft":
                output = 0.035315;
                break;
            case "cubic yd":
                output = 0.001308;
                break;
            case "teaspoons UK":
                output = 168.9364;
                break;
            case "tablespoons UK":
                output = 56.31213;
                break;
            case "fluid ounces UK":
                output = 35.19508;
                break;
            case "pints UK":
                output = 1.759754;
                break;
            case "quarts UK":
                output = 0.879877;
                break;
            case "gallons UK":
                output = 0.219969;
                break;
            case "carats":
                output = 5.0;
                break;
            case "ounces":
                output = 0.035274;
                break;
            case "pounds":
                output = 0.002205;
                break;
            case "stone":
                output = 0.000157;
                break;
            case "short tons US":
                output = 0.000001;
                break;
            case "long tons UK":
                output = 0.000000984206528;
                break;
            default:
                System.out.println("Invalid kind");
                output = 0.0;
                break;
        }
        return output;
    }
}
