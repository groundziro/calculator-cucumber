package calculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


public class UnitsConvertor {
    private HashMap<String, ArrayList<String>> map = new HashMap<>();

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
//        map.put("speed", new ArrayList<>(Arrays.asList("Centimetres per second", "Metres per second", "Kilometres per hour", "Feet per second", "Miles per hour", "Knots", "mach")));
//        map.put("time", new ArrayList<>(Arrays.asList()));
//        map.put("volume", new ArrayList<>(Arrays.asList()));
//        map.put("weight and mass", new ArrayList<>(Arrays.asList()));
//        map.put("temperature",new ArrayList<>(Arrays.asList("celsius","fahrenheit","kelvin")));
    }

    public ArrayList<String> getMap(String type){
        return map.get(type);
    }

    public Unit convert(Unit input, String goal){
        Double operation;
        List<String> types = Arrays.asList("length", "area", "currency", "power", "pressure", "speed", "time", "volume",
                                           "weight and mass");
        if (types.contains(input.type)){
            operation = (1 / input.strength) * Unit.get_strength(goal);
            return new Unit(input.type, goal, input.value * operation);
        }
        else if (input.type.equals("temperature")){
            switch (input.kind) {
                case "celsius":
                    if (goal.equals("fahrenheit")) {
                        return new Unit(input.type, goal, (input.value * 1.8) + 32.0);
                    } else if (goal.equals("kelvin")) {
                        return new Unit(input.type, goal, input.value + 273.15);
                    }
                    break;
                case "fahrenheit":
                    if (goal.equals("celsius")) {
                        return new Unit(input.type, goal, (input.value - 32.0) / 1.8);
                    } else if (goal.equals("kelvin")) {
                        return new Unit(input.type, goal, (input.value - 32.0) / 1.8 + 273.15);
                    }
                    break;
                case "kelvin":
                    if (goal.equals("celsius")) {
                        return new Unit(input.type, goal, (input.value - 273.15));
                    } else if (goal.equals("fahrenheit")) {
                        return new Unit(input.type, goal, 1.8 * (input.value - 273.15) + 32.0);
                    }
                    break;
            }
        }
        return input;
    }
}
