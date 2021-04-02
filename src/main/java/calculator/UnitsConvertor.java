package calculator;

import java.util.Arrays;
import java.util.List;

public class UnitsConvertor {

    public Unit convert(Unit input, String goal) {
        Double operation;
        if (input.type.equals("length")) {
            if (Unit.isLengthSI(input.kind) && Unit.isLengthSI(goal)) {
                operation = input.strength / Unit.getStrength(goal);
                return new Unit(input.type, goal, input.value * operation);
            }
            else if (Unit.isLengthUS(input.kind) && Unit.isLengthUS(goal)) {
                operation = Unit.getStrength(goal) / input.strength;
                return new Unit(input.type, goal, input.value * operation);
            }
            else{
                if (Unit.isLengthSI(input.kind)) {
                    operation = input.strength / Unit.getStrength("cm") / 2.54;
                    operation /= 1.0 / Unit.getStrength(goal);
                    return new Unit(input.type, goal, input.value * operation);
                } else {
                    operation = Unit.getStrength("in") / input.strength;
                    operation *= 2.54 / (Unit.getStrength(goal) / Unit.getStrength("cm"));
                    return new Unit(input.type, goal, input.value * operation);
                }
            }
        }
        return input;
    }
}
