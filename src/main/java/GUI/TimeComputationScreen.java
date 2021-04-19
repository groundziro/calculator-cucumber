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

public class TimeComputationScreen extends CalculatorScreen {
    private final Text conversionCurrent = new Text("Complete");
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
        dateR.setConverter(converter);

        TextField hourLTf = new TextField();
        TextField hourRTf = new TextField();

        Button minus = new Button("-");
        Button plus = new Button("+");
        Button elapsedS = new Button("Elapsed since");
        Button elapsedB = new Button("Elapsed between");
        LocalDateTime dateNow = LocalDateTime.now();

        elapsedS.setOnAction(actionEvent -> {
            String hourLTfS = hourLTf.getText().equals("") ? Time.current_time() : hourLTf.getText();
            if (!Time.hours_well_formatted(hourLTfS)) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Hours aren't well formatted.\n Use format HH:mm[:ss] [AM|PM] [TimeZone ID+X]\nList of time zones can be find in the help section.", ButtonType.OK);
                alert.showAndWait();
                return;
            }
            LocalDate localDateL = dateL.getValue() != null ? dateL.getValue() : dateNow.toLocalDate();
            current.setText(t.elapsed_since(localDateL,hourLTfS,conversionCurrent.getText()));
        });

        elapsedB.setOnAction(actionEvent -> {
            String hourLTfS = hourLTf.getText().equals("") ? Time.current_time() : hourLTf.getText();
            String hourRTfS = hourRTf.getText().equals("") ? Time.current_time() : hourRTf.getText();
            if (!Time.hours_well_formatted(hourLTfS) || !Time.hours_well_formatted(hourRTfS)) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Hours aren't well formatted.\nUse format HH:mm[:ss] [AM|PM] [TimeZone ID+X]\nList of time zones can be find in the help section.", ButtonType.OK);
                alert.showAndWait();
                return;
            }
            LocalDate localDateL = dateL.getValue() !=null ? dateL.getValue() : dateNow.toLocalDate();
            LocalDate localDateR = dateR.getValue() != null ? dateR.getValue() : dateNow.toLocalDate();
            current.setText(t.elapsed_between(localDateL, hourLTfS, conversionCurrent.getText(), localDateR, hourRTfS));
        });

        minus.setOnAction(actionEvent -> {
            String hourLTfS = hourLTf.getText();
            String hourRTfS = hourRTf.getText();
            if (!Time.hours_well_formatted(hourLTfS) || !Time.hours_well_formatted(hourRTfS)) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Hours aren't well formatted.\nUse format HH:mm[:ss] [AM|PM] [TimeZone ID+X]\nList of time zones can be find in the help section.", ButtonType.OK);
                alert.showAndWait();
                return;
            }
            LocalDate localDateL = (dateL.getValue()!=null?dateL.getValue():dateNow.toLocalDate());
            LocalDate localDateR = (dateR.getValue()!=null?dateR.getValue():dateNow.toLocalDate());
            current.setText(t.minus(localDateL,localDateR,hourLTfS,hourRTfS));
        });

        plus.setOnAction(actionEvent -> {
            String hourLTfS = hourLTf.getText();
            String hourRTfS = hourRTf.getText();
            if (!Time.hours_well_formatted(hourLTfS) || !Time.hours_well_formatted(hourRTfS)) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Hours aren't well formatted.\nUse format HH:mm[:ss] [AM|PM] [TimeZone ID+X]\nList of time zones can be find in the help section.", ButtonType.OK);
                alert.showAndWait();
                return;
            }
            LocalDate localDateL = (dateL.getValue()!=null?dateL.getValue():dateNow.toLocalDate());
            LocalDate localDateR = (dateR.getValue()!=null?dateR.getValue():dateNow.toLocalDate());
            t.plus(localDateL,localDateR,hourLTfS,hourRTfS);
            current.setText("");
        });

        Menu conversion = new Menu("Conversion");
        String[] conversions = {"Complete","Centuries","Decades","Years","Months","Days","Hours","Minutes","Seconds"};
        for (String s:conversions) {
            MenuItem conversionCur = new MenuItem(s);
            conversionCur.setOnAction(actionEvent -> conversionCurrent.setText(conversionCur.getText()));
            conversion.getItems().add(conversionCur);
        }
        bar.getMenus().add(conversion);
        grid.addRow(0,conversionCurrent);
        if (!isElapsedSince && !isElapsedBetween) {
            grid.addRow(1, dateL, dateR);
            grid.addRow(2,hourLTf,hourRTf);
            grid.addRow(3, plus, minus);
            grid.addRow(4, current);
        }
        else if (isElapsedBetween) {
            grid.addRow(1, dateL, dateR);
            grid.addRow(2, hourLTf, hourRTf);
            grid.addRow(3, elapsedB);
            grid.addRow(4, current);
        }
        else{
            grid.addRow(1,dateL);
            grid.addRow(2,hourLTf);
            grid.addRow(3,elapsedS);
            grid.addRow(4,current);
        }
    }

}
