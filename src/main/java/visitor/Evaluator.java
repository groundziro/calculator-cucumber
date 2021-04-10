package visitor;


import calculator.*;


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
        if (max == 1 && !classicOp(o.getSymbol())){
            BooleanOperation b = (BooleanOperation) o;
            temp = b.op(temp);
        }
        // store the accumulated result
        computedValue = temp;
    }

    private boolean classicOp(String symbol) {
        return symbol.equals("+")||symbol.equals("-")||symbol.equals("/")||symbol.equals("*");
    }

    private void getArgs(ArrayList<Integer> evaluatedArgs, Operation o){
        for(Expression a: o.args){
            a.accept(this);
            evaluatedArgs.add(computedValue);
        }
    }
}
