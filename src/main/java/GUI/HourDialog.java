package GUI;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;

import java.util.Optional;

public class HourDialog extends Dialog<String> {
    public HourDialog(){
        build();
    }

    private void build(){
        VBox content = new VBox();

        Slider hour = new Slider();
        hour.setMax(24);
        hour.setMin(0);
        hour.setBlockIncrement(1);

        Slider minutes = new Slider();
        minutes.setMin(0);
        minutes.setMax(60);
        minutes.setBlockIncrement(1);

        Slider seconds = new Slider();
        seconds.setMin(0);
        seconds.setMax(60);
        seconds.setBlockIncrement(1);

        content.getChildren().addAll(hour,minutes,seconds);
        getDialogPane().setContent(content);
        setOnCloseRequest(actionEvent ->{
            setResult(hour.getValue() +":"+ minutes.getValue() +":"+ seconds.getValue());
        });
    }
}
