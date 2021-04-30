package calculator;

import java.util.List;

/**
 * This is an abstract class that descends from the Operation in order to add an 'op' function with only
 * 1 parameter.
 */
public abstract class BooleanOperation extends Operation{

    public BooleanOperation(List<Expression> elist) throws IllegalConstruction {
        super(elist);
    }

    /**
     * Function used in the Boolean Operations when only 1 parameter is needed.
     * Especially for the Not operation.
     * @param b the boolean that we want to apply the operation to
     * @return result of the operation on b.
     */
    abstract public int op(int b);

}
