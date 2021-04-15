package GUI;

import calculator.Calculator;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

public class SizeDialog extends Dialog<Integer> {

    public SizeDialog(){
        build();
    }

    private void build(){
        DialogPane dp = getDialogPane();
        TextField tf = new TextField();
        tf.setPromptText("Insert the size that you want for your Memory");
        tf.setEditable(true);
        Button ok = new Button("Choose");
        ok.setOnAction(actionEvent -> {
            if (tf.getText().isEmpty()) {
                setResult(1);
                Alert alert = new Alert(Alert.AlertType.WARNING,"Since you didin't enter value, the memory has " +
                        "a default size of 1");
                alert.showAndWait();
                close();
            }
            else if (Calculator.isAlphaNum(tf.getText())) {
                setResult(Integer.parseInt(tf.getText()));
                close();
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR,"The text you entered is not a number, please enter a Number !",ButtonType.OK);
                alert.showAndWait();
            }
        });
        HBox hbox = new HBox();
        hbox.getChildren().addAll(tf,ok);
        dp.setContent(hbox);
    }


}
