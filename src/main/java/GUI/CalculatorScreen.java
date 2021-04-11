package GUI;

import calculator.*;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import visitor.Printer;

import java.io.File;
import java.util.ArrayList;

public class CalculatorScreen extends VBox {
    private final MenuBar bar = new MenuBar();
    private final GridPane grid = new GridPane();
    private final Stage stage;
    private final Memory mem;
    private final Calculator c = new Calculator();
    private final Text current = new Text();
    private final Text before = new Text();
    private String currentOp = "";
    private Expression currentExpr;
    private Expression oldExpr;
    private final ArrayList<Expression> currentNum = new ArrayList<>();
    private boolean equalized = false;

    public CalculatorScreen(Memory m, Stage stage){
        mem = m;
        this.stage = stage;
        build();
    }

    private void build(){
        FileChooser fc = new FileChooser();
        Menu modes = new Menu("Modes");
        Menu memory = new Menu("Memory");
        MenuItem mode1 = new MenuItem("Converter");
        MenuItem mode2 = new MenuItem("Time Computation");
        MenuItem mode3 = new MenuItem("Boolean Operations");
        MenuItem load = new MenuItem("Load...");
        MenuItem size = new MenuItem("Memory Size");
        MenuItem get = new MenuItem("Get Computation");
        mode1.setOnAction(actionEvent -> {
            //TODO: Change Group to Converter
            System.out.println("Goes to the Converter");
            ConverterScreen conv = new ConverterScreen(mem,stage);
        });
        mode2.setOnAction(actionEvent -> {
            //TODO: Change Group to Time Computation
            System.out.println("Goes to time Computation");
            TimeComputationScreen time = new TimeComputationScreen(mem,stage);
        });
        mode3.setOnAction(actionEvent -> {
            //TODO: Change Group to Boolean Operations
            System.out.println("Goes to Boolean Operation");
            BooleanOpScreen bool = new BooleanOpScreen(mem,stage);
        });
        load.setOnAction(actionEvent -> {
            fc.setTitle("Choose the Memory to Load");
            fc.setInitialDirectory(new File("."));
            File res = fc.showOpenDialog(stage);
            if (res!=null) {
                mem.load(res.getPath());
                mem.print();
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
            Printer p = new Printer(Notation.INFIX);
            e.accept(p);
            before.setText(p.getStr());
            currentNum.add(e);
            equalized=true;
        });
        size.setOnAction(actionEvent -> {
            SizeDialog dial = new SizeDialog();
            dial.showAndWait();
            Integer res = dial.getResult();
            mem.setMax(res);
        });
        memory.getItems().addAll(load,size,get);
        modes.getItems().addAll(mode1,mode2,mode3);
        bar.getMenus().addAll(modes,memory);
        buildGrid();
        this.getChildren().addAll(bar,grid);
    }

    private void buildGrid() {
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
