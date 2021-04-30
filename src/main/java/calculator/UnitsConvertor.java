package calculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * The UnitsConvertor class contains all what is needed to perform units specific actions.
 *
 * @author Laurent Bossart
 */
public class UnitsConvertor {
    private final HashMap<String, ArrayList<String>> map = new HashMap<>();

    /**
     * This method create the mapping for the units.
     */
    public void buildMap(){
        map.put("length", new ArrayList<>(Arrays.asList("Millimetres", "Centimetres", "Metres", "Kilometres", "Inches",
                                                        "Feet", "Yards", "Miles", "Nautical Miles")));
        map.put("area", new ArrayList<>(Arrays.asList("Square millimetres", "Square centimetres", "Square metres",
                                                      "Hectares", "Square kilometres", "Square inches", "Square feet",
                                                      "Square yards", "Acres", "Square miles")));
        map.put("currency", new ArrayList<>(Arrays.asList("Europe - Euro", "United States - Dollar",
                                                          "United Kingdom - Pound", "China - Yuan", "Japan - Yen")));
        map.put("power", new ArrayList<>(Arrays.asList("Watts", "Kilowatts", "Horsepower (US)", "Foot-pounds/minute",
                                                           "BTUs/minute")));
        map.put("pressure", new ArrayList<>(Arrays.asList("Atmospheres", "Bars", "Kilopascals",
                                                          "Millimetres of mercury", "Pascals",
                                                          "Pounds per square inch")));
        map.put("speed", new ArrayList<>(Arrays.asList("Centimetres per second", "Metres per second",
                                                       "Kilometres per hour", "Feet per second", "Miles per hour",
                                                       "Knots", "Mach")));
        map.put("time", new ArrayList<>(Arrays.asList("Microseconds", "Milliseconds", "Seconds", "Hours", "Days",
                                                      "Weeks", "Years")));
        map.put("volume", new ArrayList<>(Arrays.asList("Millilitres", "Cubic centimetres", "Litres", "Cubic metres",
                                                        "Teaspoons (US)", "Tablespoons (US)", "Fluid ounces (US)",
                                                        "Cups (US)", "Pints (US)", "Quarts (US)", "Gallons (US)",
                                                        "Cubic inches", "Cubic feet", "Cubic yards", "Teaspoons (UK)",
                                                        "Tablespoons (UK)", "Fluid ounces (UK)", "Pints (UK)",
                                                        "Quarts (UK)", "Gallons (UK)")));
        map.put("weight and mass", new ArrayList<>(Arrays.asList("Carats", "Milligrams", "Centigrams", "Decigrams",
                                                                 "Grams", "Decagrams", "Hectograms", "Kilograms",
                                                                 "Metric tonnes", "Ounces", "Pounds", "Stone",
                                                                 "Short tons (US)", "Long tons (UK)")));
        map.put("temperature",new ArrayList<>(Arrays.asList("Celsius","Fahrenheit","Kelvin")));
    }

    /**
     * This method is used the get an entry in the map.
     *
     * @param type The type the user want to get in the map.
     * @return The entry in the map.
     */
    public ArrayList<String> getMap(String type){
        return map.get(type);
    }

    /**
     * This method is used to convert one unit to another.
     *
     * @param input The value associated the the first unit.
     * @param goal The goal unit.
     * @return The new unit with the new value.
     */
    public Unit convert(Unit input, String goal){
        double operation;
        List<String> types = Arrays.asList("length", "area", "currency", "power", "pressure", "speed", "time", "volume",
                                           "weight and mass");
        List<String> length_types_si = Arrays.asList("Millimetres", "Centimetres", "Metres", "Kilometres");
        List<String> length_types_nosi = Arrays.asList("Inches", "Feet", "Yards", "Miles", "Nautical Miles");

        if (input.type.equals("length") && length_types_si.contains(input.kind) && length_types_nosi.contains((goal))) {
            if (input.kind.equals("Millimetres") && goal.equals("Inches")) {
                return new Unit(input.type, goal, input.value / 25.4);
            }
            else if (input.kind.equals("Millimetres") && goal.equals("Feet")) {
                return new Unit(input.type, goal, input.value / 304.8);
            }
            else if (input.kind.equals("Millimetres") && goal.equals("Yards")) {
                return new Unit(input.type, goal, input.value / 914.4);
            }
            else if (input.kind.equals("Millimetres") && goal.equals("Miles")) {
                return new Unit(input.type, goal, input.value / 1609344);
            }
            else if (input.kind.equals("Millimetres") && goal.equals("Nautical Miles")) {
                return new Unit(input.type, goal, input.value / 1852000);
            }
            else if (input.kind.equals("Centimetres") && goal.equals("Inches")) {
                return new Unit(input.type, goal, input.value / 2.54);
            }
            else if (input.kind.equals("Centimetres") && goal.equals("Feet")) {
                return new Unit(input.type, goal, input.value / 30.48);
            }
            else if (input.kind.equals("Centimetres") && goal.equals("Yards")) {
                return new Unit(input.type, goal, input.value / 91.44);
            }
            else if (input.kind.equals("Centimetres") && goal.equals("Miles")) {
                return new Unit(input.type, goal, input.value / 160934.4);
            }
            else if (input.kind.equals("Centimetres") && goal.equals("Nautical Miles")) {
                return new Unit(input.type, goal, input.value / 185200);
            }
            else if (input.kind.equals("Metres") && goal.equals("Inches")) {
                return new Unit(input.type, goal, input.value / 0.0254);
            }
            else if (input.kind.equals("Metres") && goal.equals("Feet")) {
                return new Unit(input.type, goal, input.value / 0.3048);
            }
            else if (input.kind.equals("Metres") && goal.equals("Yards")) {
                return new Unit(input.type, goal, input.value / 0.9144);
            }
            else if (input.kind.equals("Metres") && goal.equals("Miles")) {
                return new Unit(input.type, goal, input.value / 1609.344);
            }
            else if (input.kind.equals("Metres") && goal.equals("Nautical Miles")) {
                return new Unit(input.type, goal, input.value / 1852);
            }
            else if (input.kind.equals("Kilometres") && goal.equals("Inches")) {
                return new Unit(input.type, goal, input.value / 0.0000254);
            }
            else if (input.kind.equals("Kilometres") && goal.equals("Feet")) {
                return new Unit(input.type, goal, input.value / 0.0003048);
            }
            else if (input.kind.equals("Kilometres") && goal.equals("Yards")) {
                return new Unit(input.type, goal, input.value / 0.0009144);
            }
            else if (input.kind.equals("Kilometres") && goal.equals("Miles")) {
                return new Unit(input.type, goal, input.value / 1.609344);
            }
            else if (input.kind.equals("Kilometres") && goal.equals("Nautical Miles")) {
                return new Unit(input.type, goal, input.value / 1.852);
            }
        }
        else if (input.type.equals("length") && length_types_nosi.contains(input.kind) && length_types_si.contains((goal))) {
            if (input.kind.equals("Inches") && goal.equals("Millimetres")) {
                return new Unit(input.type, goal, input.value * 25.4);
            }
            else if (input.kind.equals("Inches") && goal.equals("Centimetres")) {
                return new Unit(input.type, goal, input.value * 2.54);
            }
            else if (input.kind.equals("Inches") && goal.equals("Metres")) {
                return new Unit(input.type, goal, input.value * 0.0254);
            }
            else if (input.kind.equals("Inches") && goal.equals("Kilometres")) {
                return new Unit(input.type, goal, input.value * 0.0000254);
            }
            else if (input.kind.equals("Feet") && goal.equals("Millimetres")) {
                return new Unit(input.type, goal, input.value * 304.8);
            }
            else if (input.kind.equals("Feet") && goal.equals("Centimetres")) {
                return new Unit(input.type, goal, input.value * 30.48);
            }
            else if (input.kind.equals("Feet") && goal.equals("Metres")) {
                return new Unit(input.type, goal, input.value * 0.3048);
            }
            else if (input.kind.equals("Feet") && goal.equals("Kilometres")) {
                return new Unit(input.type, goal, input.value * 0.0003048);
            }
            else if (input.kind.equals("Yards") && goal.equals("Millimetres")) {
                return new Unit(input.type, goal, input.value * 914.4);
            }
            else if (input.kind.equals("Yards") && goal.equals("Centimetres")) {
                return new Unit(input.type, goal, input.value * 91.44);
            }
            else if (input.kind.equals("Yards") && goal.equals("Metres")) {
                return new Unit(input.type, goal, input.value * 0.9144);
            }
            else if (input.kind.equals("Yards") && goal.equals("Kilometres")) {
                return new Unit(input.type, goal, input.value * 0.0009144);
            }
            else if (input.kind.equals("Miles") && goal.equals("Millimetres")) {
                return new Unit(input.type, goal, input.value * 1609344);
            }
            else if (input.kind.equals("Miles") && goal.equals("Centimetres")) {
                return new Unit(input.type, goal, input.value * 160934.4);
            }
            else if (input.kind.equals("Miles") && goal.equals("Metres")) {
                return new Unit(input.type, goal, input.value * 1609.344);
            }
            else if (input.kind.equals("Miles") && goal.equals("Kilometres")) {
                return new Unit(input.type, goal, input.value * 1.609344);
            }
            else if (input.kind.equals("Nautical Miles") && goal.equals("Millimetres")) {
                return new Unit(input.type, goal, input.value * 1852000);
            }
            else if (input.kind.equals("Nautical Miles") && goal.equals("Centimetres")) {
                return new Unit(input.type, goal, input.value * 185200);
            }
            else if (input.kind.equals("Nautical Miles") && goal.equals("Metres")) {
                return new Unit(input.type, goal, input.value * 1852);
            }
            else if (input.kind.equals("Nautical Miles") && goal.equals("Kilometres")) {
                return new Unit(input.type, goal, input.value * 1.852);
            }
        }
        else if (input.type.equals("length") && length_types_nosi.contains(input.kind) && length_types_nosi.contains((goal))) {
            if (input.kind.equals("Inches") && goal.equals("Inches")) {
                return new Unit(input.type, goal, input.value);
            }
            else if (input.kind.equals("Inches") && goal.equals("Feet")) {
                return new Unit(input.type, goal, input.value / 12);
            }
            else if (input.kind.equals("Inches") && goal.equals("Yards")) {
                return new Unit(input.type, goal, input.value / 36);
            }
            else if (input.kind.equals("Inches") && goal.equals("Miles")) {
                return new Unit(input.type, goal, input.value / 63360);
            }
            else if (input.kind.equals("Inches") && goal.equals("Nautical Miles")) {
                return new Unit(input.type, goal, input.value / 72913.3858);
            }
            else if (input.kind.equals("Feet") && goal.equals("Inches")) {
                return new Unit(input.type, goal, input.value * 12);
            }
            else if (input.kind.equals("Feet") && goal.equals("Feet")) {
                return new Unit(input.type, goal, input.value);
            }
            else if (input.kind.equals("Feet") && goal.equals("Yards")) {
                return new Unit(input.type, goal, input.value / 3);
            }
            else if (input.kind.equals("Feet") && goal.equals("Miles")) {
                return new Unit(input.type, goal, input.value / 5280);
            }
            else if (input.kind.equals("Feet") && goal.equals("Nautical Miles")) {
                return new Unit(input.type, goal, input.value / 6076.11549);
            }
            else if (input.kind.equals("Yards") && goal.equals("Inches")) {
                return new Unit(input.type, goal, input.value * 36);
            }
            else if (input.kind.equals("Yards") && goal.equals("Feet")) {
                return new Unit(input.type, goal, input.value * 3);
            }
            else if (input.kind.equals("Yards") && goal.equals("Yards")) {
                return new Unit(input.type, goal, input.value);
            }
            else if (input.kind.equals("Yards") && goal.equals("Miles")) {
                return new Unit(input.type, goal, input.value / 1760);
            }
            else if (input.kind.equals("Yards") && goal.equals("Nautical Miles")) {
                return new Unit(input.type, goal, input.value / 2025.37183);
            }
            else if (input.kind.equals("Miles") && goal.equals("Inches")) {
                return new Unit(input.type, goal, input.value * 63360);
            }
            else if (input.kind.equals("Miles") && goal.equals("Feet")) {
                return new Unit(input.type, goal, input.value * 5280);
            }
            else if (input.kind.equals("Miles") && goal.equals("Yards")) {
                return new Unit(input.type, goal, input.value * 1760);
            }
            else if (input.kind.equals("Miles") && goal.equals("Miles")) {
                return new Unit(input.type, goal, input.value);
            }
            else if (input.kind.equals("Miles") && goal.equals("Nautical Miles")) {
                return new Unit(input.type, goal, input.value / 1.15078);
            }
            else if (input.kind.equals("Nautical Miles") && goal.equals("Inches")) {
                return new Unit(input.type, goal, input.value * 72913.3858);
            }
            else if (input.kind.equals("Nautical Miles") && goal.equals("Feet")) {
                return new Unit(input.type, goal, input.value * 6076.11549);
            }
            else if (input.kind.equals("Nautical Miles") && goal.equals("Yards")) {
                return new Unit(input.type, goal, input.value * 2025.37183);
            }
            else if (input.kind.equals("Nautical Miles") && goal.equals("Miles")) {
                return new Unit(input.type, goal, input.value * 1.15078);
            }
            else if (input.kind.equals("Nautical Miles") && goal.equals("Nautical Miles")) {
                return new Unit(input.type, goal, input.value);
            }
        }
        else if (types.contains(input.type)) {
            operation = (1 / input.strength) * Unit.get_strength(goal);
            return new Unit(input.type, goal, input.value * operation);
        }
        else if (input.type.equals("temperature")) {
            switch (input.kind) {
                case "Celsius":
                    if (goal.equals("Fahrenheit")) {
                        return new Unit(input.type, goal, (input.value * 1.8) + 32.0);
                    } else if (goal.equals("Kelvin")) {
                        return new Unit(input.type, goal, input.value + 273.15);
                    }
                    break;
                case "Fahrenheit":
                    if (goal.equals("Celsius")) {
                        return new Unit(input.type, goal, (input.value - 32.0) / 1.8);
                    } else if (goal.equals("Kelvin")) {
                        return new Unit(input.type, goal, (input.value - 32.0) / 1.8 + 273.15);
                    }
                    break;
                case "Kelvin":
                    if (goal.equals("Celsius")) {
                        return new Unit(input.type, goal, (input.value - 273.15));
                    } else if (goal.equals("Fahrenheit")) {
                        return new Unit(input.type, goal, 1.8 * (input.value - 273.15) + 32.0);
                    }
                    break;
            }
        }
        return input;
    }
}
