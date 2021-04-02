package calculator;

import java.util.Arrays;
import java.util.List;

public class UnitsConvertor {

    public Unit convert(Unit input, String goal){
        Double operation;
        if (input.type.equals("length")){
            if (Unit.isLengthSI(input.kind) && Unit.isLengthSI(goal)){
                operation = input.strength / Unit.getStrength(goal);
            }
            else if (Unit.isLengthUS(input.kind) && Unit.isLengthUS(goal)){
                operation = Unit.getStrength(goal) / input.strength;
            }
            else{
                if (Unit.isLengthSI(input.kind)){
                    operation = input.strength / Unit.getStrength("cm") / 2.54;
                    operation /= 1.0 / Unit.getStrength(goal);
                }
                else{
                    operation = Unit.getStrength("in") / input.strength;
                    operation *= 2.54 / (Unit.getStrength(goal) / Unit.getStrength("cm"));
                }
            }
            return new Unit(input.type, goal, input.value * operation);
        }

        else if (input.type.equals("area")){
            if (Unit.isAreaSI(input.kind) && Unit.isAreaSI(goal)){
                operation = input.strength / Unit.getStrength(goal);
            }
            else if (Unit.isAreaUS(input.kind) && Unit.isAreaUS(goal)){
                operation = Unit.getStrength(goal) / input.strength;
            }
            else{
                if (Unit.isAreaSI(input.kind)){
                    operation = input.strength / Unit.getStrength("sqrt_cm") / 6.4516;
                    operation /= 1.0 / Unit.getStrength(goal);
                }
                else{
                    operation = Unit.getStrength("sqrt_in") / input.strength;
                    operation *= 6.4516 / (Unit.getStrength(goal) / Unit.getStrength("sqrt_cm"));
                }
            }
            return new Unit(input.type, goal, input.value * operation);
        }

        else if (input.type.equals("currency")){
            operation = 1 / input.strength * Unit.getStrength(goal);
            return new Unit(input.type, goal, input.value * operation);
        }
        return input;
    }
}
