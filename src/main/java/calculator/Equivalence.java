package calculator;

import java.util.List;

public class Equivalence extends BooleanOperation{
    public Equivalence(List<Expression> elist) throws IllegalConstruction {
        super(elist);
    }

    @Override
    public int op(int b) {
        return b;
    }

    @Override
    public int op(int l, int r) {
        return (l==r?0:1);
    }
}
