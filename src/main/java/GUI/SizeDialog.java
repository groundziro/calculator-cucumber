package GUI;

import calculator.Calculator;
import calculator.Memory;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

public class SizeDialog extends Dialog<Integer> {
    private final Memory m;

    public SizeDialog(Memory memory){
        m = memory;
        build();
    }

    private void build(){
        DialogPane dp = getDialogPane();
        TextField tf = new TextField();
        tf.setPromptText("Insert the size that you want for your Memory");
        tf.setEditable(true);
        Button ok = new Button("Choose");
        ok.setOnAction(actionEvent -> {
            if (Calculator.isAlphaNum(tf.getText())) {
                m.setMax(Integer.parseInt(tf.getText()));
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
