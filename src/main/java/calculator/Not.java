package calculator;

import java.util.List;

public class Not extends BooleanOperation{

    public Not(List<Expression> elist) throws IllegalConstruction {
        super(elist);
        symbol="not";
    }

    @Override
    public int op(int l, int r) {
        return (l==1 || r == 1?0:1);
    }

    @Override
    public int op(int b){
        return (b==1?0:1);
    }

    @Override
    public String toString() {
        return String.valueOf(this.getClass());
    }
}
