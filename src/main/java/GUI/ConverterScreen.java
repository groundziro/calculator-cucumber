package GUI;

import calculator.Calculator;
import calculator.Memory;
import calculator.Unit;
import calculator.UnitsConvertor;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;


public class ConverterScreen extends CalculatorScreen{
    String type;
    UnitsConvertor convertor = new UnitsConvertor();
    ChoiceBox<String> input = new ChoiceBox<>();
    ChoiceBox<String> output = new ChoiceBox<>();

    public ConverterScreen(Memory m, Stage stage, String type){
        super(m, stage,1);
        this.type = type.toLowerCase();
        convertor.buildMap();
        buildGrid();
        this.getChildren().addAll(bar,grid);
    }

    @Override
    protected void buildGrid() {
        input.getItems().addAll(convertor.getMap(type).toArray(new String[0]));
        output.getItems().addAll(convertor.getMap(type).toArray(new String[0]));
        input.setValue(convertor.getMap(type).get(0));
        output.setValue(convertor.getMap(type).get(1));
        TextField tf = new TextField();
        tf.setPromptText("Enter your number");
        tf.setEditable(true);
        setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ENTER){
                if (Calculator.isAlphaNum(tf.getText())){
                    double x = Double.parseDouble(tf.getText());
                    Unit current_unit = new Unit(type, input.getValue(),x);
                    Unit goal = convertor.convert(current_unit,output.getValue());
                    current.setText(String.valueOf(goal.value));
                }
                else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Please, check the help section to know how to use this function. [RTFM]", ButtonType.OK);
                    alert.showAndWait();
                }
            }
        });
        grid.addRow(0,input,tf);
        grid.addRow(1,output,current);
    }
}
