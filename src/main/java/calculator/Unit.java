package calculator;


public class Unit {
    public final String type;
    public final String kind;
    public final Double value;
    public final Double strength;

    public Unit(String type, String kind, Double value){
        this.type = type;
        this.kind = kind;
        this.value = value;
        this.strength = get_strength(this.kind);
    }

    public static Double get_strength(String value){
        double output;
        switch (value){
            case "Kilometres":
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
            case "Metres":
            case "Square metres":
            case "Europe - Euro":
            case "Watts":
            case "Atmospheres":
            case "Centimetres per second":
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
            case "Centimetres":
            case "Metres per second":
            case "hectograms":
                output = 0.01;
                break;
            case "Millimetres":
            case "Kilowatts":
            case "cubic m":
            case "kilograms":
                output = 0.001;
                break;
            case "Inches":
                output = 39.37008;
                break;
            case "Feet":
                output = 3.28084;
                break;
            case "Yards":
                output = 1.093613;
                break;
            case "Miles":
                output = 0.000621;
                break;
            case "Nautical Miles":
                output = 0.00054;
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
            case "metric tonnes":
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
