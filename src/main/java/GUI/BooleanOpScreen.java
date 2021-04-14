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
                    before.setText("");
                    equalized = false;
                }
                before.setText(before.getText() + (!first?" , ":"") +op +" ( ");
                first = true;
            });
            operations.add(operation);
        }
        Button parenthesis = new Button(")");
        parenthesis.setOnAction(actionEvent -> before.setText(before.getText()+" )"));
        operations.add(parenthesis);
        setOnKeyPressed(keyEvent-> {
            if (keyEvent.getCode()== KeyCode.ENTER || keyEvent.getCode() == KeyCode.C){
                int depth = goodString(before.getText());
                if (depth==0) {
                    try {
                        String s = before.getText().substring(0,1);
                        if (Calculator.isAlphaNum(s))
                            currentExpr = new MyBoolean((Integer.parseInt(s) != 1));
                        else
                            currentExpr = mem.parse(before.getText());
                        current.setText(String.valueOf((c.eval(currentExpr) == 0)));
                    } catch (IllegalConstruction illegalConstruction) {
                        illegalConstruction.printStackTrace();
                    }
                    first = true;
                    equalized = true;

                }else {
                    Alert alert = new Alert(Alert.AlertType.WARNING,"There are missing "+ depth +" parenthesis in your operation,"
                                            + "\n please verify your operations");
                    alert.showAndWait();
                }
            }
        });
        grid.addColumn(0,t,f);
        grid.addColumn(1,operations.toArray(new Button[0]));
        grid.addColumn(2,before,current);
    }

    private void action(Button f) {
        f.setOnAction(actionEvent -> {
            if (equalized) {
                before.setText("");
                equalized = false;
            }
            if (first) {
                before.setText(before.getText() + f.getText());
                first = false;
            }
            else
                before.setText(before.getText() + " , " + f.getText());
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
