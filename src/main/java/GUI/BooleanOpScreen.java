package GUI;

import calculator.Calculator;
import calculator.Memory;
import calculator.MyBoolean;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;

public class BooleanOpScreen extends CalculatorScreen {

    public BooleanOpScreen(Memory m, Stage stage) {
        super(m, stage, 3);
        buildGrid();
        getChildren().addAll(bar,grid);
    }

    @Override
    protected void buildGrid() {
        Button t = new Button("True");
        Button f = new Button("False");
        t.setOnAction(actionEvent -> current.setText("true"));
        f.setOnAction(actionEvent -> current.setText("false"));
        ArrayList<Button> operations = new ArrayList<>();
        String[] ops = {"and","or","not","xor","implies","=="};
        for (String op : ops) {
            Button operation = new Button(op);
            operation.setOnAction(actionEvent -> {
                if (current.getText().isEmpty())
                    return;
                boolean b = Boolean.parseBoolean(current.getText());
                MyBoolean bool = new MyBoolean(b);
                currentExpr = Calculator.getOp(operation.getText(), new ArrayList<>(Collections.singleton(bool)));

            });
            operations.add(operation);
        }
        grid.addColumn(1,operations.toArray(new Button[0]));
    }
}
