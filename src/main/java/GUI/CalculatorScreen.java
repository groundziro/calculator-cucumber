package GUI;

import calculator.*;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;
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
    private String currentOp;
    private Expression currentExpr;
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
            mem.load(res.getPath());
            mem.print();
        });
        get.setOnAction(actionEvent -> {
            if (mem.isEmpty())
                load.fire();
            GetDialog dial = new GetDialog(mem);
            dial.showAndWait();
            Operation e = dial.getResult();
            Printer p = new Printer(Notation.INFIX);
            e.accept(p);
            before.setText(p.getStr());
            currentExpr = e;
            currentOp = e.getSymbol();
        });
        size.setOnAction(actionEvent -> {
            SizeDialog dial = new SizeDialog(mem);
            dial.showAndWait();
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
                if (current.getText().isEmpty())
                    return;
                if (before.getText().isEmpty()) {
                    before.setText(before.getText() + Integer.parseInt(current.getText()) + operations[finalI]);
                    currentNum.add(new MyNumber(Integer.parseInt(current.getText())));
                    currentOp=op.getText();
                }else {
                    if (op.getText().equals(currentOp)) {
                        currentNum.add(new MyNumber(Integer.parseInt(current.getText())));
                    }else{
                        Expression oldExp = Calculator.getOp(currentOp, currentNum);
                        currentNum.add(oldExp);

                        if (currentExpr!=null)
                            currentNum.add(0,currentExpr);
                        currentExpr = Calculator.getOp(op.getText(),currentNum);
                        currentNum.clear();
                        currentNum.add(new MyNumber(Integer.parseInt(current.getText())));
                        currentOp = op.getText();
                    }
                    before.setText(before.getText() + Integer.parseInt(current.getText())+operations[finalI] );
                }
                current.setText("");
            });
            ops.add(op);
        }
        grid.setGridLinesVisible(true);
        setOnKeyTyped(keyEvent -> {
            switch(keyEvent.getCode()){
                case NUMPAD0: ops.get(0).fire();break;
                case NUMPAD1: ops.get(1).fire();break;
                case NUMPAD2: ops.get(2).fire();break;
                case NUMPAD3: ops.get(3).fire();break;
                case NUMPAD4: ops.get(4).fire();break;
                case NUMPAD5: ops.get(5).fire();break;
                case NUMPAD6: ops.get(6).fire();break;
                case NUMPAD7: ops.get(7).fire();break;
                case NUMPAD8: ops.get(8).fire();break;
                case NUMPAD9: ops.get(9).fire();break;
            }
        });
        Button eq = new Button("=");
        eq.setOnAction(actionEvent -> {
            if (!current.getText().isEmpty()) {
                currentNum.add(new MyNumber(Integer.parseInt(current.getText())));
            }
            assert !currentNum.isEmpty();
            currentExpr = Calculator.getOp(currentOp,currentNum);
            if (currentExpr == null)
                return;
            System.out.println(currentExpr);
            before.setText(c.eval(currentExpr) + currentOp);
            mem.add(currentExpr);
            equalized = true;
        });
        ops.add(eq);
        grid.addColumn(0,numbers.toArray(new Button[0]));
        grid.addColumn(1,ops.toArray(new Button[0]));
        grid.addColumn(2,before,current);
    }

}
