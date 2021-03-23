package calculator;

import java.util.List;

public class Implies extends BooleanOperation{

    public Implies(List<Expression> elist) throws IllegalConstruction {
        super(elist);
        symbol = "implies";
    }

    @Override
    public int op(int b) {
        return b;
    }

    @Override
    public int op(int l, int r) {
        switch (l) {
            case 0:
                return (r+1<=1?0:1);
            case 1:
                return 1;
        }
        return -1;
    }
}
