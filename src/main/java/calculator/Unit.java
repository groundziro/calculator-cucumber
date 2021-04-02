package calculator;

import java.util.Arrays;
import java.util.List;

public class Unit {
    public final String type; // support: length
    public final String kind; // support: km, m
    public final Double value;
    public final Double strength;

    private static final List<String> lengthSI = Arrays.asList("km", "hm", "dam", "m", "dm", "cm", "mm");
    private static final List<String> lengthUS = Arrays.asList("nm", "mi", "yd", "ft", "in");
    private static final List<String> areaSI = Arrays.asList("sqrt_mm", "sqrt_cm", "sqrt_m", "ha", "sqrt_km");
    private static final List<String> areaUS = Arrays.asList("sqrt_in", "sqrt_ft", "sqrt_yd", "ac", "sqrt_mi");

    public Unit(String type, String kind, Double value){
        this.type = type;
        this.kind = kind;
        this.value = value;
        this.strength = getStrength(this.kind);
    }

    public static boolean isLengthSI(String value){
        return lengthSI.contains(value);
    }

    public static boolean isLengthUS(String value){
        return lengthUS.contains(value);
    }

    public static boolean isAreaSI(String value){
        return areaSI.contains(value);
    }

    public static boolean isAreaUS(String value){
        return areaUS.contains(value);
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
            case "in":
            case "sqrt_m":
            case "sqrt_in":
                output = 1.0;
                break;
            case "dm":
                output = 0.1;
                break;
            case "cm":
                output = 0.01;
                break;
            case "mm":
                output = 0.001;
                break;
            case "ft":
                output = 0.083333;
                break;
            case "yd":
                output = 0.027778;
                break;
            case "mi":
                output = 0.000016;
                break;
            case "nm":
                output = 0.000014;
                break;
            case "sqrt_mm":
                output = 0.000001;
                break;
            case "sqrt_cm":
                output = 0.0001;
                break;
            case "ha":
                output = 10000;
                break;
            case "sqrt_km":
                output = 1000000;
                break;
            case "sqrt_ft":
                output = 0.006944;
                break;
            case "sqrt_yd":
                output = 0.000772;
                break;
            case "ac":
                output = 0.000000159422508;
                break;
            case "sqrt_mi":
                output = 0.000000000249098;
                break;
            default:
                System.out.println("Invalid kind");
                output = 0.0;
                break;
        }
        return output;
    }
}
