package GUI;

import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
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
        Text nameTuto = new Text();
        Menu modes = new Menu("Tutorials");
        MenuItem memory = new MenuItem("Memory");
        MenuItem classic = new MenuItem("Classic");
        MenuItem converter = new MenuItem("Converter");
        MenuItem time = new MenuItem("Time Computation");
        MenuItem bool = new MenuItem("Boolean Operations");
        memory.setOnAction(actionEvent -> {tuto.setText("There are 5 options in the 'Memory' menu: Load..., Memory Size" +
                ", Get Computation, Save Memory and Print Memory\n\n" +
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
                "When you press 'Enter' the memory will save itself.\n\n" +
                "When you select 'Print Memory' a new dialog will open showing you the current values stored\n" +
                "in the memory.\n\n" +
                "When you exit the calculator the memory is stored inside the 'tmp.mem' file and is loaded everytime" +
                "\n you launch it."
        );
        nameTuto.setText(memory.getText());});
        classic.setOnAction(actionEvent -> {tuto.setText("That is the main screen that you start on.\n" +
                "The calculator works like the standard mode of the Windows calculator,\n" +
                "meaning that you enter a number then the operation in order to register it.\n" +
                "You can enter numbers via the numpad or the buttons, but the operations must be \n" +
                "entered via the buttons.\n\n" +
                "If you press 'Enter' you will obtain the response in the form 'x {op} y = z' and if you\n" +
                "continue to press 'Enter' you will {op} 'y', the last number you entered, to the answer 'z'.\n" +
                "Just like with the Windows calculator.\n\n" +
                "The memory implemented is only used in this mode, it can be used for boolean operations\n" +
                "but it wasn't implemented to be used in the GUI.");
            nameTuto.setText(classic.getText());
        });
        converter.setOnAction(actionEvent -> {tuto.setText("Converter Tuto");
        nameTuto.setText(converter.getText());}); // TODO
        time.setOnAction(actionEvent -> {tuto.setText("Time Tuto");
        nameTuto.setText(time.getText());}); // TODO
        bool.setOnAction(actionEvent -> {
            tuto.setText("When you select the 'Boolean Computation' mode, you are faced" +
                    " with all of the different possible operations:\n" +
                    "And, Or, Not, Xor, Implication, Equivalence.\n" +
                    "IMPORTANT: we are using the PREFIX notation so when you want to write the boolean operation you " +
                    "\nneed to take that into consideration.\n\n" +
                    "1 represents the False boolean and 0 represents the True boolean.\n" +
                    "When you are finished writing an operation you need to close the parenthesis of each operation.\n" +
                    "If you think that you are finished you can press the 'C' key on your keyboard to compute\n" +
                    "the operations. A dialog will open if you didn't close all your operations and \n" +
                    "telling you how many parenthesis that you need to add.\n\n" +
                    "If you made a mistake you can press 'R' to reset the computation.");
            nameTuto.setText(bool.getText());
        });
        modes.getItems().addAll(classic,converter,time,bool,memory);
        bar.getMenus().add(modes);
        nameTuto.setUnderline(true);
        nameTuto.setFont(new Font(15));
        content.getChildren().addAll(bar,nameTuto,tuto);
        getDialogPane().setContent(content);
        classic.fire();
        getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
        getDialogPane().setMinWidth(600);
        getDialogPane().setMinHeight(600);
    }
}
