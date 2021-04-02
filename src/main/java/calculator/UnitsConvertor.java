package calculator;

import com.sun.source.doctree.UnknownInlineTagTree;

import java.util.Arrays;
import java.util.List;

public class UnitsConvertor {

    public Unit convert(Unit input, String goal){
        Double operation;
        List<String> types = Arrays.asList("length", "area", "currency", "power", "pressure", "speed", "time");
        if (types.contains(input.type)){
            operation = (1 / input.strength) * Unit.getStrength(goal);
            return new Unit(input.type, goal, input.value * operation);
        }
        else if (input.type.equals("temperature")){
            if (input.kind.equals("celsius")){
                if (goal.equals("fahrenheit")){
                    return new Unit(input.type, goal, (input.value * 1.8) + 32.0);
                }
                else if (goal.equals("kelvin")){
                    return new Unit(input.type, goal, input.value + 273.15);
                }
            }
            else if (input.kind.equals("fahrenheit")){
                if (goal.equals("celsius")){
                    return new Unit(input.type, goal, (input.value - 32.0) / 1.8);
                }
                else if (goal.equals("kelvin")){
                    return new Unit(input.type, goal, (input.value - 32.0) / 1.8 + 273.15);
                }
            }
            else if (input.kind.equals("kelvin")){
                if (goal.equals("celsius")){
                    return new Unit(input.type, goal, (input.value - 273.15));
                }
                else if (goal.equals("fahrenheit")){
                    return new Unit(input.type, goal, 1.8 * (input.value - 273.15) + 32.0);
                }
            }
        }
        return input;
    }
}
