package calculator;

import java.util.List;

public class And extends BooleanOperation{

    public And(List<Expression> elist) throws IllegalConstruction {
        super(elist);
        symbol = "and";
    }

    @Override
    public int op(int b) {
        return b;
    }


    public void op(int l, int r) {
        return (l==0 && r==0?0:1);
    }
}
