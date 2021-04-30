package calculator;

/**
 * The Unit class contains the values associates with a unit.
 *
 * @author Laurent Bossart
 */
public class Unit {
    public final String type;
    public final String kind;
    public final double value;
    public final double strength;

    public Unit(String type, String kind, double value){
        this.type = type;
        this.kind = kind;
        this.value = value;
        this.strength = get_strength(this.kind);
    }

    /**
     * This method is used to get the value associated to a unit.
     *
     * @param value The unit we wants to get the value associated with.
     * @return The value associated to the unit.
     */
    public static double get_strength(String value){
        double output;
        switch (value){
            case "Millilitres":
            case "Cubic centimetres":
            case "Milliseconds":
            case "Milligrams":
            case "Millimetres":
                output = 1000.0;
                break;
            case "hm":
            case "Centigrams":
            case "Centimetres":
                output = 100.0;
                break;
            case "dam":
            case "Decigrams":
                output = 10.0;
                break;
            case "Metres":
            case "Square metres":
            case "Europe - Euro":
            case "Watts":
            case "Atmospheres":
            case "Centimetres per second":
            case "Celsius":
            case "Seconds":
            case "Litres":
            case "Grams":
            case "Inches":
                output = 1.0;
                break;
            case "dm":
            case "Decagrams":
                output = 0.1;
                break;
            case "Metres per second":
            case "Hectograms":
                output = 0.01;
                break;
            case "Kilowatts":
            case "Cubic metres":
            case "Kilograms":
            case "Kilometres":
                output = 0.001;
                break;
            case "Feet":
                output = 0.083333;
                break;
            case "Yards":
                output = 0.027778;
                break;
            case "Miles":
                output = 0.000016;
                break;
            case "Nautical Miles":
                output = 0.000014;
                break;
            case "Square millimetres":
                output = 1000000;
                break;
            case "Square centimetres":
                output = 10000;
                break;
            case "Hectares":
                output = 0.0001;
                break;
            case "Square kilometres":
            case "Metric tonnes":
                output = 0.000001;
                break;
            case "Square inches":
                output = 1550.003;
                break;
            case "Square feet":
                output = 10.76391;
                break;
            case "Square yards":
                output = 1.19599;
                break;
            case "Acres":
                output = 0.000247;
                break;
            case "Square miles":
                output = 0.000000386102159;
                break;
            case "United States - Dollar":
                output = 1.18;
                break;
            case "United Kingdom - Pound":
                output = 0.85;
                break;
            case "China - Yuan":
                output = 7.73;
                break;
            case "Japan - Yen":
                output = 130.0;
                break;
            case "Horsepower (US)":
                output = 0.001341;
                break;
            case "Foot-pounds/minute":
                output = 44.25373;
                break;
            case "BTUs/minute":
                output = 0.056869;
                break;
            case "Bars":
                output = 1.01325;
                break;
            case "Kilopascals":
                output = 101.325;
                break;
            case "Millimetres of mercury":
                output = 760.1275;
                break;
            case "Pascals":
                output = 101325.0;
                break;
            case "Pounds per square inch":
                output = 14.69595;
                break;
            case "Kilometres per hour":
                output = 0.036;
                break;
            case "Feet per second":
                output = 0.032808;
                break;
            case "Miles per hour":
                output = 0.022371;
                break;
            case "Knots":
                output = 0.01944;
                break;
            case "Mach":
                output = 0.000029;
                break;
            case "Fahrenheit":
                output = 33.8;
                break;
            case "Kelvin":
                output = 274.15;
                break;
            case "Microseconds":
                output = 1000000.0;
                break;
            case "min":
                output = 0.016667;
                break;
            case "Hours":
                output = 0.000278;
                break;
            case "Days":
                output = 0.000012;
                break;
            case "Weeks":
                output = 0.000002;
                break;
            case "Years":
                output = 0.000000031688088;
                break;
            case "Teaspoons (US)":
                output = 202.8841;
                break;
            case "Tablespoons (US)":
                output = 67.62805;
                break;
            case "Fluid ounces (US)":
                output = 33.81402;
                break;
            case "Cups (US)":
                output = 4.226753;
                break;
            case "Pints (US)":
                output = 2.113376;
                break;
            case "Quarts (US)":
                output = 1.056688;
                break;
            case "Gallons (US)":
                output = 0.264172;
                break;
            case "Cubic inches":
                output = 61.02374;
                break;
            case "Cubic feet":
                output = 0.035315;
                break;
            case "Cubic yards":
                output = 0.001308;
                break;
            case "Teaspoons (UK)":
                output = 168.9364;
                break;
            case "Tablespoons (UK)":
                output = 56.31213;
                break;
            case "Fluid ounces (UK)":
                output = 35.19508;
                break;
            case "Pints (UK)":
                output = 1.759754;
                break;
            case "Quarts (UK)":
                output = 0.879877;
                break;
            case "Gallons (UK)":
                output = 0.219969;
                break;
            case "Carats":
                output = 5.0;
                break;
            case "Ounces":
                output = 0.035274;
                break;
            case "Pounds":
                output = 0.002205;
                break;
            case "Stone":
                output = 0.000157;
                break;
            case "Short tons (US)":
                output = 0.000001;
                break;
            case "Long tons (UK)":
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
