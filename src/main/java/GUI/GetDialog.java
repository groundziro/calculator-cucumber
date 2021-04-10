package GUI;

import calculator.Expression;
import calculator.Memory;
import calculator.Notation;
import calculator.Operation;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.layout.HBox;
import javafx.util.Pair;
import visitor.Printer;

public class GetDialog extends Dialog<Operation> {
    private final ChoiceBox<Pair<String,Operation>> choiceBox = new ChoiceBox<>();
    private final Button ok = new Button("Choose");
    private final Memory m;
    private final Printer p = new Printer(Notation.INFIX);
    public GetDialog(Memory m){
        this.m = m;
        build();
    }
    private void build(){
        DialogPane dp = getDialogPane();
        for (int i = 0; i < m.size(); i++) {
            Expression e = m.get(i);
            e.accept(p);
            choiceBox.getItems().add(new Pair<>(p.getStr(),(Operation) e));
        }
        ok.setDefaultButton(true);
        ok.setOnAction(actionEvent -> {
            if (!choiceBox.getItems().isEmpty()) {
                setResult(choiceBox.getValue().getValue());
            }
            close();
        });
        HBox hBox = new HBox();
        hBox.getChildren().addAll(choiceBox,ok);
        dp.setHeaderText("Choice of Expression");
        dp.setContent(hBox);
    }
}