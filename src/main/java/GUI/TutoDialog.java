package GUI;

import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class TutoDialog extends Dialog<String> {
    private final MenuBar bar = new MenuBar();

    public TutoDialog(boolean tuto){
        if (!tuto){
            Alert alert = new Alert(Alert.AlertType.WARNING,"If you have a doubt you can press the H key at any time " +
                    "to show you the tutorials");
            alert.showAndWait();
            return;
        }
        build();
    }

    private void build() {
        VBox content = new VBox();
        Text tuto = new Text();
        Menu modes = new Menu("Tutorials");
        MenuItem memory = new MenuItem("Memory");
        MenuItem classic = new MenuItem("Classic");
        MenuItem converter = new MenuItem("Converter");
        MenuItem time = new MenuItem("Time Computation");
        MenuItem bool = new MenuItem("Boolean Operations");
        memory.setOnAction(actionEvent -> tuto.setText("There are 4 options in the 'Memory' menu: Load..., Memory Size" +
                ", Get Computation and Save Memory\n\n" +
                "When you select 'Load' a dialog opens and you can search for the memory that you created.\n" +
                "The file extension is normally '*.mem' but you can select another type of files with the filters.\n" +
                "A little remark, the file MUST use the prefix notation if you create the file yourself.\n" +
                "If you saved it via the 'Save' button then you don't have to worry.\n\n" +
                "When you select 'Memory Size' you can enter the size that you want for the memory.\n" +
                "If you let the Field empty then the memory size will be 1 by default.\n\n" +
                "When you select 'Get Computation', you can see a dialog in which you can select the computations\n" +
                "that you made or loaded.\n" +
                "Everytime that you press an operator or the '=' button the current expression is added to the memory.\n\n" +
                "When you select 'Save Memory', a file dialog opens and you can type the file that you want to\n" +
                "create or overwrite.\n" +
                "When you press 'Enter' the memory will save itself."));
        classic.setOnAction(actionEvent -> tuto.setText("Classic Tuto"));
        converter.setOnAction(actionEvent -> tuto.setText("Converter Tuto")); // TODO
        time.setOnAction(actionEvent -> tuto.setText("Time Tuto")); // TODO
        bool.setOnAction(actionEvent -> tuto.setText("When you select the 'Boolean Computation' mode, you are faced" +
                " with all of the different possible operations:\n" +
                "And, Or, Not, Xor, Implication, Equivalence.\n" +
                "IMPORTANT: we are using the PREFIX notation so when you want to write the boolean operation you " +
                "\nneed to take that into consideration.\n\n" +
                "1 represents the False boolean and 0 represents the True boolean.\n" +
                "When you are finished writing an operation you need to close the parenthesis of each operation.\n" +
                "If you think that you are finished you can press the 'C' key on your keyboard to compute\n" +
                "the operations. A dialog will open if you didn't close all your operations and \n" +
                "telling you how many parenthesis that you need to add."));
        modes.getItems().addAll(classic,converter,time,bool,memory);
        bar.getMenus().add(modes);
        content.getChildren().addAll(bar,tuto);
        getDialogPane().setContent(content);
        getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
        getDialogPane().setMinWidth(600);
        getDialogPane().setMinHeight(600);
    }
}
