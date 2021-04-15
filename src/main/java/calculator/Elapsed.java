package calculator;

import java.util.List;
import java.time.LocalTime;


final public class Elapsed extends Operation{

    public Elapsed(List<Expression> elist) throws IllegalConstruction{
        super(elist);
        symbol = "-";
        neutral = LocalTime.now().toSecondOfDay();
    }

    public Elapsed(List<Expression> elist, Notation n) throws IllegalConstruction{
        super(elist,n);
        symbol = "-";
        neutral = LocalTime.now().toSecondOfDay();
    }

    public int op(int current_seconds, int since_seconds){
        return Math.abs(current_seconds - since_seconds);
    }
}
