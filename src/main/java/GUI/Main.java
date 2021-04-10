package GUI;

import calculator.Calculator;
import calculator.Memory;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    private Calculator c = new Calculator();
    private Memory m = new Memory();
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(new CalculatorScreen(m,primaryStage));
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
