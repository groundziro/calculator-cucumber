package GUI;

import calculator.Calculator;
import calculator.Expression;
import calculator.Memory;
import calculator.Notation;
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

public abstract class CalculatorScreen extends VBox {
    protected final MenuBar bar = new MenuBar();
    protected final GridPane grid = new GridPane();
    protected final Stage stage;
    protected final Memory mem;
    protected final Calculator c = new Calculator();
    protected final Text current = new Text();
    protected final Text before = new Text();
    protected String currentOp = "";
    protected Expression currentExpr;
    protected Expression oldExpr;
    protected final ArrayList<Expression> currentNum = new ArrayList<>();
    protected boolean equalized = false;

    public CalculatorScreen(Memory m, Stage stage, int mode){
        mem = m;
        this.stage = stage;
        build(mode);
    }

    public void back(int size){
        String cur = current.getText();
        current.setText(cur.substring(0,cur.length()-size));
    }

    private void build(int mode){

        Menu modes = new Menu("Modes");

        MenuItem mode0 = new MenuItem("Classic Calculator");
        Menu mode1 = new Menu("Converter");
        String[] types = {"Length", "Area", "Currency", "Power", "Pressure", "Speed", "Time", "Volume",
                          "Weight and mass", "Temperature"};
        for (String type:types) {
            MenuItem t = new MenuItem(type);
            t.setOnAction(actionEvent -> {
                System.out.println("Goes to the Converter");
                ConverterScreen conv = new ConverterScreen(mem, stage, t.getText());
                stage.getScene().setRoot(conv);
            });
            mode1.getItems().add(t);
        }
//        MenuItem mode2 = new MenuItem("Time Computation");

        // TODO when selecting Elapsed since, should 
        Menu mode2 = new Menu("Time computation");
        String[] types_time_computation = {"Elapsed since"};
        for (String type:types_time_computation) {
            MenuItem t = new MenuItem(type);
            t.setOnAction(actionEvent -> {
                System.out.println("Goes to time computation");
                ConverterScreen conv = new ConverterScreen(mem, stage, t.getText());
                stage.getScene().setRoot(conv);
            });
            mode2.getItems().add(t);
        }

        MenuItem mode3 = new MenuItem("Boolean Operations");


        mode0.setOnAction(actionEvent -> {
            System.out.println("Goes to the Classic Mode");
            if (mode!=0) {
                MainScreen main = new MainScreen(mem, stage);
                stage.getScene().setRoot(main);
            }

        });

//        mode2.setOnAction(actionEvent -> {
//            System.out.println("Goes to time Computation");
//            if (mode!=2) {
//                TimeComputationScreen time = new TimeComputationScreen(mem, stage);
//                stage.getScene().setRoot(time);
//            }
//        });

        mode3.setOnAction(actionEvent -> {
            System.out.println("Goes to Boolean Operation");
            if (mode!=3) {
                BooleanOpScreen bool = new BooleanOpScreen(mem, stage);
                stage.getScene().setRoot(bool);
            }
        });
        modes.getItems().addAll(mode0,mode1,mode2,mode3);
        bar.getMenus().add(modes);
    }

    protected abstract void buildGrid();
}
