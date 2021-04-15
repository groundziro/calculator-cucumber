package GUI;

import calculator.Memory;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.util.Optional;

public class Main extends Application {
    private final Memory m = new Memory();
    TutoDialog tutoDialog;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Calculator");
        Scene scene = new Scene(new MainScreen(m,primaryStage));
        scene.addEventHandler(KeyEvent.KEY_PRESSED, keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ESCAPE){
                Stage sb = (Stage)scene.getWindow();
                sb.close();
            }
            if (keyEvent.getCode()==KeyCode.H) {
                tutoDialog = new TutoDialog(true);
                tutoDialog.showAndWait();
            }
        });
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Is this your first time using our calculator ?",ButtonType.YES,ButtonType.NO);
        Optional<ButtonType> opt = alert.showAndWait();
        boolean tuto = false;
        if (opt.isPresent()) {
            if (opt.get().getText().equals(ButtonType.YES.getText())){
                tuto = true;
            }
        }
        tutoDialog = new TutoDialog(tuto);
        if (tuto)
            tutoDialog.showAndWait();
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
