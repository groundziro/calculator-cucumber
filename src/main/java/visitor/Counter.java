package visitor;

import calculator.BooleanOperation;
import calculator.Expression;
import calculator.MyNumber;
import calculator.Operation;
import calculator.Time;

public class Counter extends Visitor{
    private final int type;
    private int counter;

    public Counter(int t){
        type = t;
        if (t==0)
            counter=-1;
    }

    public int getCounter() {
        return counter;
    }

    @Override
    public void visit(MyNumber n) {
        if (type==2)
            counter+=1;
    }

    @Override
    public void visit(Operation o) {
        switch (type) {
            case 1:
                counter += 1;
                break;
            case 0:
                int max = -1;
                counter += 1;
                for (Expression ignored : o.args) {
                    if (counter > max)
                        max = counter;
                    counter = 0;
                }
                counter = max;
                break;
        }
    }


    public void visit(BooleanOperation b) {

    }
}
