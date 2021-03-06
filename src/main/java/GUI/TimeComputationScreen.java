package GUI;

import calculator.Memory;
import calculator.Time;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The TimeComputationScreen class is the screen associated to the time computation.
 */
public class TimeComputationScreen extends CalculatorScreen {
    private final Text conversionCurrent = new Text("Mixe");
    private final boolean isElapsedSince;
    private final boolean isElapsedBetween;

    public TimeComputationScreen(Memory m, Stage stage, boolean isElapsedSince, boolean isElapsedBetween) {
        super(m, stage,2);
        this.isElapsedSince = isElapsedSince;
        this.isElapsedBetween = isElapsedBetween;
        buildGrid();
        getChildren().addAll(bar,grid);
    }

    @Override
    protected void buildGrid() {
        Time t = new Time();
        DatePicker dateL = new DatePicker();
        ChoiceBox<String> mode = new ChoiceBox<>();
        mode.getItems().addAll("Mixe", "Centuries", "Decades", "Years", "Months", "Days", "Hours", "Minutes", "Seconds");
        mode.setValue("Mixe");
        ChoiceBox<String> input = new ChoiceBox<>();
        input.getItems().addAll("Years", "Months", "Weeks", "Days", "Hours", "Minutes", "Seconds", "Mixe");
        input.setValue("Mixe");
        DatePicker dateR = new DatePicker();

        StringConverter<LocalDate> converter = new StringConverter<>() {
            final DateTimeFormatter dateFormatter =
                    DateTimeFormatter.ofPattern("yyyy-MM-dd");

            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }

            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        };
        dateL.setConverter(converter);

        TextField hourLTf = new TextField();
        hourLTf.setPromptText("Enter your time.");
        TextField hourRTf = new TextField();
        hourRTf.setPromptText("Enter your time.");

        Button minus = new Button("-");
        Button plus = new Button("+");
        Button elapsedS = new Button("Elapsed since");
        Button elapsedB = new Button("Elapsed between");
        LocalDateTime dateNow = LocalDateTime.now();

        elapsedS.setOnAction(actionEvent -> {
            String hourLTfS = hourLTf.getText().equals("") ? Time.current_time() : hourLTf.getText();
            String how_to_show = mode.getValue();
            if (!Time.hours_well_formatted(hourLTfS)) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Please, check the help section to know how to use this function. [RTFM]", ButtonType.OK);
                alert.showAndWait();
                return;
            }
            LocalDate localDateL = dateL.getValue() != null ? dateL.getValue() : dateNow.toLocalDate();
            current.setText(t.elapsed_since(localDateL, hourLTfS, how_to_show));
        });

        elapsedB.setOnAction(actionEvent -> {
            String hourLTfS = hourLTf.getText().equals("") ? Time.current_time() : hourLTf.getText();
            String hourRTfS = hourRTf.getText().equals("") ? Time.current_time() : hourRTf.getText();
            String how_to_show = mode.getValue();
            if (!Time.hours_well_formatted(hourLTfS) || !Time.hours_well_formatted(hourRTfS)) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Please, check the help section to know how to use this function. [RTFM]", ButtonType.OK);
                alert.showAndWait();
                return;
            }
            LocalDate localDateL = dateL.getValue() !=null ? dateL.getValue() : dateNow.toLocalDate();
            LocalDate localDateR = dateR.getValue() != null ? dateR.getValue() : dateNow.toLocalDate();
            current.setText(t.elapsed_between(localDateL, hourLTfS, how_to_show, localDateR, hourRTfS));
        });

        minus.setOnAction(actionEvent -> {
            String hourLTfS = hourLTf.getText();
            String what_to_add_remove = input.getValue();
            if (!Time.add_remove_well_formatted(hourLTfS, what_to_add_remove)) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Please, check the help section to know how to use this function. [RTFM]", ButtonType.OK);
                alert.showAndWait();
                return;
            }
            current.setText(t.minus(hourLTfS, what_to_add_remove));
        });

        plus.setOnAction(actionEvent -> {
            String hourLTfS = hourLTf.getText();
            String what_to_add_remove = input.getValue();
            if (!Time.add_remove_well_formatted(hourLTfS, what_to_add_remove)) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Please, check the help section to know how to use this function. [RTFM]", ButtonType.OK);
                alert.showAndWait();
                return;
            }
            current.setText(t.plus(hourLTfS, what_to_add_remove));
        });

        Menu conversion = new Menu("Conversion");
        String[] conversions = {"Mixe","Centuries","Decades","Years","Months","Days","Hours","Minutes","Seconds"};
        for (String s:conversions) {
            MenuItem conversionCur = new MenuItem(s);
            conversionCur.setOnAction(actionEvent -> conversionCurrent.setText(conversionCur.getText()));
            conversion.getItems().add(conversionCur);
        }
        if (!isElapsedSince && !isElapsedBetween) {
            grid.addRow(1, input);
            grid.addRow(2, hourLTf, plus, minus);
            grid.addRow(3, current);
        }
        else if (isElapsedBetween) {
            grid.addRow(1, mode);
            grid.addRow(2, dateL, dateR);
            grid.addRow(3, hourLTf, hourRTf);
            grid.addRow(4, elapsedB);
            grid.addRow(5, current);
        }
        else{
            grid.addRow(1, mode);
            grid.addRow(2,dateL);
            grid.addRow(3,hourLTf);
            grid.addRow(4,elapsedS);
            grid.addRow(5,current);
        }
    }

}
