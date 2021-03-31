package calculator;

import java.util.List;

public class Or extends BooleanOperation{
    public Or(List<Expression> elist) throws IllegalConstruction {
        super(elist);
        symbol="or";
    }

    @Override
    public int op(int b) {
        return b;
    }

    @Override
    public int op(int l, int r) {
        return (l+r<=1?0:1);
    }
}
