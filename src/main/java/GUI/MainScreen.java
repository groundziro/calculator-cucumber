package GUI;

import calculator.Calculator;
import calculator.Memory;
import calculator.MyNumber;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.util.ArrayList;

public class MainScreen extends CalculatorScreen{
    public MainScreen(Memory m, Stage stage) {
        super(m, stage,0);
    }

    protected void buildGrid() {
        ArrayList<Button> numbers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Button number = new Button(Integer.toString(i));
            int finalI = i;
            number.setOnAction(actionEvent -> {
                if (equalized){
                    equalized = false;
                    current.setText("");
                    before.setText("");
                    currentExpr = null;
                    oldExpr = null;
                    currentOp = "";
                    currentNum.clear();
                }
                current.setText(current.getText()+ finalI);
            });
            numbers.add(number);
        }
        String[] operations = {"+","-","*","/"};
        ArrayList<Button> ops = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Button op = new Button(operations[i]);
            int finalI = i;
            op.setOnAction(actionEvent -> {
                if (current.getText().isEmpty()&&!equalized) {
                    before.setText(c.eval(currentExpr)+operations[finalI]);
                    currentOp = op.getText();
                    return;
                }
                MyNumber x = new MyNumber((equalized?0:Integer.parseInt(current.getText())));
                if (before.getText().isEmpty() && !equalized) {
                    before.setText(before.getText() + x.getValue() + operations[finalI]);
                    currentNum.add(x);
                    currentExpr = x;
                    currentOp=op.getText();
                }else {
                    if (equalized){
                        equalized = false;
                        currentOp = op.getText();
                        before.setText(c.eval(currentExpr) + operations[finalI]);
                        current.setText("");
                        return;
                    }
                    if (op.getText().equals(currentOp)) {
                        oldExpr = currentExpr;
                        currentNum.add(x);
                        currentExpr = Calculator.getOp(currentOp,currentNum);
                    }else{
                        oldExpr = Calculator.getOp(currentOp,currentNum);
                        currentNum.clear();
                        currentNum.add(oldExpr);
                        currentNum.add(x);
                        currentExpr = Calculator.getOp(currentOp,currentNum);
                        currentOp = op.getText();
                    }
                    before.setText(c.eval(currentExpr)+operations[finalI]);
                    currentNum.clear();
                    currentNum.add(currentExpr);
                    mem.add(currentExpr);
                }
                current.setText("");
            });
            ops.add(op);
        }

        Button eq = new Button("=");
        eq.setOnAction(actionEvent -> {
            oldExpr = currentExpr;
            if (!current.getText().isEmpty()) {
                if (!equalized || !currentOp.isEmpty())
                    currentNum.add(new MyNumber(Integer.parseInt(current.getText())));
            }
            assert !currentNum.isEmpty();
            if (!currentOp.isEmpty()) {
                currentExpr = Calculator.getOp(currentOp, currentNum);
                currentNum.clear();
                currentNum.add(currentExpr);
            }
            else {
                currentExpr = currentNum.get(0);
                oldExpr = currentExpr;
            }
            if (currentExpr == null)
                return;
            before.setText(c.eval(oldExpr) + (current.getText().isEmpty()?"":currentOp) + (currentOp.isEmpty()?"":current.getText())+ " = "+ c.eval(currentExpr));
            mem.add(currentExpr);
            equalized = true;
        });
        eq.setDefaultButton(true);

        ArrayList<Node> row1 = new ArrayList<>(numbers.subList(7,10));
        row1.add(ops.get(0));
        ArrayList<Node> row2 = new ArrayList<>(numbers.subList(4,7));
        row2.add(ops.get(1));
        ArrayList<Node> row3 = new ArrayList<>(numbers.subList(1,4));
        row3.add(ops.get(2));
        grid.addRow(0,row1.toArray(new Node[0]));
        grid.addRow(1,row2.toArray(new Node[0]));
        grid.addRow(2,row3.toArray(new Node[0]));
        grid.addRow(3,numbers.get(0),new Region(),eq,ops.get(3));
        grid.addColumn(5,before,current);
        setOnKeyPressed(keyEvent -> {
            switch(keyEvent.getCode()){
                case NUMPAD0: numbers.get(0).fire();break;
                case NUMPAD1: numbers.get(1).fire();break;
                case NUMPAD2: numbers.get(2).fire();break;
                case NUMPAD3: numbers.get(3).fire();break;
                case NUMPAD4: numbers.get(4).fire();break;
                case NUMPAD5: numbers.get(5).fire();break;
                case NUMPAD6: numbers.get(6).fire();break;
                case NUMPAD7: numbers.get(7).fire();break;
                case NUMPAD8: numbers.get(8).fire();break;
                case NUMPAD9: numbers.get(9).fire();break;
                case ENTER: eq.fire();break;
                case PLUS: ops.get(0).fire();break;
                case MINUS: ops.get(1).fire();break;
                case MULTIPLY: ops.get(2).fire();break;
                case DIVIDE: ops.get(3).fire();break;
            }
        });
    }
}
