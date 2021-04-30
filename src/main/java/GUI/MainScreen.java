package GUI;

import calculator.*;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import visitor.Printer;

import java.io.File;
import java.util.ArrayList;

public class MainScreen extends CalculatorScreen{

    public MainScreen(Memory m, Stage stage) {
        super(m, stage,0);
        buildGrid();
        this.getChildren().addAll(bar,grid);
    }

    protected void buildGrid() {
        FileChooser fc = new FileChooser();
        Menu memory = new Menu("Memory");
        MenuItem load = new MenuItem("Load...");
        MenuItem size = new MenuItem("Memory Size");
        MenuItem get = new MenuItem("Get Computation");
        MenuItem undo = new MenuItem("Undo last operation");
        MenuItem save = new MenuItem("Save Memory");
        MenuItem print = new MenuItem("Show Memory");
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
                case ENTER:
                case C:
                    eq.fire();break;
                case PLUS: ops.get(0).fire();break;
                case MINUS: ops.get(1).fire();break;
                case MULTIPLY: ops.get(2).fire();break;
                case DIVIDE: ops.get(3).fire();break;
                case BACK_SPACE:
                case DELETE:back(1);break;
                case ESCAPE:save.fire();break;
            }
        });
        load.setOnAction(actionEvent -> {
            fc.setTitle("Choose the Memory to Load");
            fc.setInitialDirectory(new File("."));
            fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Memory Files","*.mem"),
                    new FileChooser.ExtensionFilter("All Files","*"));
            File res = fc.showOpenDialog(stage);
            if (res!=null) {
                mem.load(res.getPath());
            }
        });
        get.setOnAction(actionEvent -> {
            if (mem.isEmpty())
                load.fire();
            if (mem.isEmpty())
                return;
            GetDialog dial = new GetDialog(mem);
            dial.showAndWait();
            Expression e = dial.getResult();
            if(e!=null) {
                Printer p = new Printer(Notation.INFIX);
                e.accept(p);
                before.setText(p.getStr());
                currentNum.clear();
                currentNum.add(e);
                oldExpr = e;
                currentExpr = e;
            }
            currentOp=((Operation) currentExpr).getSymbol();
            equalized=false;
        });

        size.setOnAction(actionEvent -> {
            SizeDialog dial = new SizeDialog();
            dial.showAndWait();
            Integer res = dial.getResult();
            mem.setMax(res);
        });
        save.setOnAction(actionEvent -> {
            fc.setTitle("Saving Memory");
            fc.setInitialDirectory(new File("."));
            fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Memory Files","*.mem"),
                    new FileChooser.ExtensionFilter("All Files","*"));
            File file = fc.showSaveDialog(stage);
            if (file!=null)
                mem.save(file.getAbsolutePath());
        });
        print.setOnAction(actionEvent -> {
            Dialog<String> dialog = new Dialog<>();
            dialog.setContentText(mem.getLog());
            dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
            dialog.showAndWait();
        });
        undo.setOnAction(actionEvent -> {
            currentExpr = mem.undo(currentExpr);
            Printer p = new Printer(Notation.INFIX);
            currentExpr.accept(p);
            before.setText(p.getStr()+ " = " + c.eval(currentExpr));
            currentNum.clear();
            currentNum.add(currentExpr);
            oldExpr = currentExpr;
            currentOp=((Operation) currentExpr).getSymbol();
            equalized = false;
        });
        memory.getItems().addAll(load,size,get,undo,save,print);
        bar.getMenus().add(memory);
    }

}
