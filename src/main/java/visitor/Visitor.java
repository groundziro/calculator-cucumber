package visitor;

import calculator.MyNumber;
import calculator.Operation;
import calculator.Time;

/* Visitor design pattern
 */
public abstract class Visitor {

    public abstract void visit(MyNumber n);
    public abstract void visit(Operation o);
}
