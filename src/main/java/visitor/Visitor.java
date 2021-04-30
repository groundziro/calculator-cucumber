package visitor;

import calculator.BooleanOperation;
import calculator.MyNumber;
import calculator.Operation;
import calculator.Time;

import java.io.Serializable;

/* Visitor design pattern
 */
public abstract class Visitor implements Serializable {

    public abstract void visit(MyNumber n);
    public abstract void visit(Operation o);
}
