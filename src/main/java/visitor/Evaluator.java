package visitor;

import calculator.BooleanOperation;
import calculator.Expression;
import calculator.MyNumber;
import calculator.Operation;

import java.util.ArrayList;

public class Evaluator extends Visitor {

    private int computedValue;

    public Integer getResult() { return computedValue; }

    public void visit(MyNumber n) {
        computedValue = n.getValue();
    }


    public void visit(Operation o){
        ArrayList<Integer> evaluatedArgs = new ArrayList<>();
        //first loop to recursively evaluate each subexpression
        getArgs(evaluatedArgs,o);
        //second loop to accumulate all the evaluated sub-results
        int temp = evaluatedArgs.get(0);
        int max = evaluatedArgs.size();
        for (int counter = 1; counter < max; counter++) {
            temp = o.op(temp, evaluatedArgs.get(counter));
        }
        if (max == 1){
            BooleanOperation b = (BooleanOperation) o;
            temp = b.op(temp);
        }
        // store the accumulated result
        System.out.println("Sol = "+temp);
        computedValue = temp;
    }

    private void getArgs(ArrayList<Integer> evaluatedArgs, Operation o){
        for(Expression a: o.args){
            System.out.println("Operation "+a);
            a.accept(this);
            evaluatedArgs.add(computedValue);
        }
    }

    /*public void visit(BooleanOperation b) {
        ArrayList<Integer> evaluatedArgs = new ArrayList<>();
        getArgs(evaluatedArgs,b);

        int temp = evaluatedArgs.get(0);
        int max = evaluatedArgs.size();
        System.out.println(max);
        if (max == 1)
            computedValue = b.op(temp);
        else{
            for (int i = 1; i < max; i++) {
                temp = b.op(temp,evaluatedArgs.get(i));
            }
            computedValue = temp;
        }
    }*/


}
