package calculator;

import visitor.Counter;
import visitor.Evaluator;
import visitor.Printer;

import java.util.ArrayList;

public class Calculator {
    /*
     For the moment the calculator only contains a print method and an eval method
     It would be useful to complete this with a read method, so that we would be able
     to implement a full REPL cycle (Read-Eval-Print loop) such as in Scheme, Python, R and other languages.
     To do so would require to implement a method with the following signature, converting an input string
     into an arithmetic expression:
     public Expression read(String s)
    */

    public static Expression getOp(String s, ArrayList<Expression> total) {
        try {
            Expression exp = null;
            switch (s) {
                case "+":
                    exp = new Plus(total);
                    break;
                case "-":
                    exp = new Minus(total);
                    break;
                case "/":
                    exp = new Divides(total);
                    break;
                case "*":
                    exp = new Times(total);
                    break;
                case "xor":
                    exp = new Xor(total);
                    break;
                case "not":
                    exp = new Not(total);
                    break;
                case "and":
                    exp = new And(total);
                    break;
                case "or":
                    exp = new Or(total);
                    break;
                case "implies":
                    exp = new Implies(total);
                    break;
            }
            return exp;
        }catch (Exception e){
            System.err.println(e.getMessage());
            return null;
        }
    }

    public static boolean isAlphaNum(String s){
        try{
            Integer.parseInt(s);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public void print(Expression e) {
        System.out.println("The result of evaluating expression " + e);
        System.out.println("is: " + eval(e) + ".");
        System.out.println();
    }

    public void printExpressionDetails(Expression e) {
        print(e);
        System.out.print("It contains " + e.countDepth() + " levels of nested expressions, ");
        System.out.print(e.countOps() + " operations");
        System.out.println(" and " + e.countNbs() + " numbers.");
        System.out.println();
    }

    public int count(Expression e, int type){
        Counter c = new Counter(type);
        try{
            e.accept(c);
        }catch (ArithmeticException ignored){

        }
        return c.getCounter();
    }

    public int eval(Expression e) {
        // create a new visitor to evaluate expressions
        Evaluator v = new Evaluator();
        // and ask the expression to accept this visitor to start the evaluation process
        try {
            e.accept(v);
        }catch (ArithmeticException ex){
            System.out.println("Divided by Zero");
            return Integer.MIN_VALUE;
        }
        // and return the result of the evaluation at the end of the process
        return v.getResult();
    }

    public String formatPrint(Expression e, Notation n){
        // create a new visitor to evaluate expressions
        Printer v = new Printer(n);
        // and ask the expression to accept this visitor to start the evaluation process
        try {
            e.accept(v);
        }catch (ArithmeticException ignored){

        }
        // and return the result of the evaluation at the end of the process
        return v.getStr();
    }

    /*
     We could also have other methods, e.g. to verify whether an expression is syntactically correct
     public Boolean validate(Expression e)
     or to simplify some expression
     public Expression simplify(Expression e)
    */
}
