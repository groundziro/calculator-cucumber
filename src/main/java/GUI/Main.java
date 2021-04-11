package GUI;

import calculator.Calculator;
import calculator.Memory;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.Window;

public class Main extends Application {
    private Calculator c = new Calculator();
    private Memory m = new Memory();
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(new CalculatorScreen(m,primaryStage));
        scene.addEventHandler(KeyEvent.KEY_PRESSED, keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ESCAPE){
                Stage sb = (Stage)scene.getWindow();
                sb.close();
            }
        });
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
