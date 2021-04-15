package GUI;

import calculator.Calculator;
import calculator.IllegalConstruction;
import calculator.Memory;
import calculator.MyBoolean;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.util.ArrayList;

public class BooleanOpScreen extends CalculatorScreen {
    boolean first = true;
    boolean notOp = false;
    boolean equalized = false;

    public BooleanOpScreen(Memory m, Stage stage) {
        super(m, stage, 3);
        buildGrid();
        getChildren().addAll(bar,grid);
    }

    @Override
    protected void buildGrid() {
        Button f = new Button("1"); // false
        Button t = new Button("0");
        action(t);
        action(f);
        ArrayList<Button> operations = new ArrayList<>();
        String[] ops = {"and","or","not","xor","implies","equi"};
        for (String op : ops) {
            Button operation = new Button(op);
            operation.setOnAction(actionEvent -> {
                if (equalized) {
                    current.setText("");
                    equalized = false;
                }
                notOp = operation.getText().equals(ops[2]);
                current.setText(current.getText() + (!first?" , ":"") +op +" ( ");
                first = true;
            });
            operations.add(operation);
        }
        Button parenthesis = new Button(")");
        parenthesis.setOnAction(actionEvent -> current.setText(current.getText()+" )"));
        operations.add(parenthesis);
        setOnKeyPressed(keyEvent-> {
            if (keyEvent.getCode()==KeyCode.NUMPAD0)
                t.fire();
            if (keyEvent.getCode() == KeyCode.NUMPAD1)
                f.fire();
            if (keyEvent.getCode()== KeyCode.ENTER || keyEvent.getCode() == KeyCode.C){
                int depth = goodString(current.getText());
                if (depth==0) {
                    try {
                        String s = current.getText().substring(0,1);
                        if (Calculator.isAlphaNum(s))
                            currentExpr = new MyBoolean((Integer.parseInt(s) != 1));
                        else
                            currentExpr = mem.parse(current.getText());
                        before.setText(String.valueOf((c.eval(currentExpr) == 0)));
                    } catch (IllegalConstruction illegalConstruction) {
                        illegalConstruction.printStackTrace();
                    }
                    first = true;
                    equalized = true;

                }else {
                    Alert alert = new Alert(Alert.AlertType.WARNING,"There are " + depth+" missing parenthesis in your operation,"
                                            + "\n please verify your operations");
                    alert.showAndWait();
                }
            }
        });
        grid.addColumn(0,t,f);
        grid.addColumn(1,operations.subList(0,2).toArray(new Button[0]));
        grid.addColumn(2,operations.subList(2,4).toArray(new Button[0]));
        grid.addColumn(3,operations.subList(4,6).toArray(new Button[0]));
        grid.addColumn(4,before,current);
        grid.addRow(3,parenthesis);
    }

    private void action(Button f) {
        f.setOnAction(actionEvent -> {
            if (equalized) {
                current.setText("");
                equalized = false;
            }
            if (first) {
                current.setText(current.getText() + f.getText());
                first = false;
            }
            else
                current.setText(current.getText() + " , " + f.getText() + (notOp?" ) ":""));
        });
    }

    private int goodString(String text) {
        int depth = 0;
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) == '('){
                depth+=1;
            }else if (text.charAt(i) == ')'){
                depth-=1;
            }
        }
        return depth;
    }
}
