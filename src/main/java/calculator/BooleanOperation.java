package calculator;

import java.util.List;

public abstract class BooleanOperation extends Operation{

    public BooleanOperation(List<Expression> elist) throws IllegalConstruction {
        super(elist);
    }

    abstract public int op(int b);

}
