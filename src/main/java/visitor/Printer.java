package visitor;

import calculator.*;

import java.util.ArrayList;

public class Printer extends Visitor{
    private String str;
    private Notation n;

    public Printer(Notation notation){
        n = notation;
    }

    public String getStr(){
        return str;
    }

    public void setN(Notation notation){
        n = notation;
    }

    @Override
    public void visit(MyNumber n) {
        str = n.getValue().toString();
    }

    @Override
    public void visit(Operation o) {
        ArrayList<String> numbers = new ArrayList<>();
        for (Expression e: o.args){
            e.accept(this);
            numbers.add(str);
        }
        StringBuilder temp = new StringBuilder();
        if (n == Notation.INFIX) {
            temp.append("( ");
            temp.append(numbers.get(0));
            for (int i = 1; i < numbers.size(); i++) {
                temp.append(" ");
                temp.append(o.getSymbol());
                temp.append(" ");
                temp.append(numbers.get(i));
            }
            temp.append(" )");
        }else{
            temp = prePost(numbers,o);
        }
        str = temp.toString();
    }

    private StringBuilder prePost(ArrayList<String> numbers, Operation o) {
        StringBuilder sol = new StringBuilder();
        if (n == Notation.PREFIX){
            sol.append(o.getSymbol()).append(" ");
        }
        sol.append("( ");
        sol.append(numbers.get(0));
        for (int i = 1; i < numbers.size(); i++) {
            sol.append(" , ");
            sol.append(numbers.get(i));
        }
        sol.append(" )");
        if (n == Notation.POSTFIX){
            sol.append(" ").append(o.getSymbol());
        }
        return sol;
    }


}
