package GUI;

import calculator.Calculator;
import calculator.Memory;
import calculator.MyBoolean;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.util.ArrayList;

public class BooleanOpScreen extends CalculatorScreen {
    private boolean first = true;
    private boolean notOp = false;
    private boolean equalized = false;
    private final ArrayList<String> lastInput = new ArrayList<>();

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
                if (!current.getText().isEmpty() && goodString(current.getText())==0){
                    Alert alert = new Alert(Alert.AlertType.WARNING,"You finished typing your operation, you can" +
                            " compute it or go back in order to add other operations.",ButtonType.OK);
                    alert.showAndWait();
                    return;
                }
                if (equalized) {
                    current.setText("");
                    equalized = false;
                }
                notOp = operation.getText().equals(ops[2]);
                String s = (!first?" , ":"") +op +" ( ";
                lastInput.add(s);
                current.setText(current.getText() + s);
                first = true;
            });
            operations.add(operation);
        }
        Button parenthesis = new Button(")");
        parenthesis.setOnAction(actionEvent -> {
            String s = " )";
            current.setText(current.getText()+s);
            lastInput.add(s);
        });
        operations.add(parenthesis);
        setOnKeyPressed(keyEvent->  {
            if (keyEvent.getCode()==KeyCode.R) {
                before.setText("");
                current.setText("");
                lastInput.clear();
            }
            if (keyEvent.getCode()==KeyCode.NUMPAD0)
                t.fire();
            if (keyEvent.getCode() == KeyCode.NUMPAD1)
                f.fire();
            if (keyEvent.getCode() == KeyCode.BACK_SPACE)
                remove();
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
                    } catch (Exception ex) {
                        Alert alert = new Alert(Alert.AlertType.ERROR,ex.getMessage(), ButtonType.OK);
                        alert.showAndWait();
                        return;
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
            if (goodString(current.getText())==0){
                Alert alert = new Alert(Alert.AlertType.ERROR,"There is no current operation," +
                        " add at least an operation",ButtonType.OK);
                alert.showAndWait();
                return;
            }
            if (equalized) {
                current.setText("");
                lastInput.clear();
                equalized = false;
            }
            String s;
            if (first) {
                s = f.getText();
                current.setText(current.getText() + s);
                first = false;
            }
            else {
                s = " , " + f.getText() + (notOp ? " ) " : "");
                current.setText(current.getText() + s);
            }
            lastInput.add(s);
        });
    }

    private void remove(){
        String lastIn = lastInput.remove(lastInput.size()-1);
        int lengthToRemove = lastIn.length();
        first = lastIn.contains("(") || lastIn.length() == 1;
        back(lengthToRemove);
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
