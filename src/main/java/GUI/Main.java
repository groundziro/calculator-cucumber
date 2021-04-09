package GUI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        MenuBar menuBar = new MenuBar();
        Menu modes = new Menu("menu1");
        MenuItem mode1 = new MenuItem("Converter");
        modes.getItems().add(mode1);
        menuBar.getMenus().add(modes);
        VBox vbox = new VBox();
        vbox.getChildren().add(menuBar);
        primaryStage.setScene(new Scene(vbox,256,180));
        primaryStage.sizeToScene();
        primaryStage.show();
    }
}
