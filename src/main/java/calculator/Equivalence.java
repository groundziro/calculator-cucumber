package calculator;

import java.util.List;

public class Equivalence extends BooleanOperation{
    public Equivalence(List<Expression> elist) throws IllegalConstruction {
        super(elist);
        symbol="==";
    }

    @Override
    public int op(int b) {
        return 0;
    }

    @Override
    public int op(int l, int r) {
        return (l==r?0:1);
    }
}
