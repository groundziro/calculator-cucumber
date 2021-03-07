package calculator;

import java.util.List;

public class Xor extends BooleanOperation{
    public Xor(List<Expression> elist) throws IllegalConstruction {
        super(elist);
    }

    @Override
    public int op(int b) {
        return b;
    }

    @Override
    public int op(int l, int r) {
        return (l+r==1?0:1);
    }
}
