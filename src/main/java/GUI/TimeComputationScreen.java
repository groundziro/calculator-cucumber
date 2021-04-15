package GUI;

import calculator.Memory;
import calculator.Time;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeComputationScreen extends CalculatorScreen{
    Duration between;
    public TimeComputationScreen(Memory m, Stage stage) {
        super(m, stage,2);
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
        Button elapsedS = new Button("elapsed since");
        Button elapsedB = new Button("elapsed between");
        LocalDateTime dateNow = LocalDateTime.now();
        String hourNow = Time.now();

        elapsedS.setOnAction(actionEvent -> {
            // juste les heures
            String hourLTfS = hourLTf.getText();
            if (!Time.hoursWellFormated(hourLTfS)) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Hours aren't well formatted.", ButtonType.OK);
                alert.showAndWait();
                return;
            }

            // juste la date
            LocalDate localDateL = (dateL.getValue()!=null?dateL.getValue():dateNow.toLocalDate());
            LocalDate localDateNow = dateNow.toLocalDate();
            t.elapsed(localDateNow,localDateL,hourNow,hourLTfS);
            current.setText("");
        });
        elapsedB.setOnAction(actionEvent -> {
            // juste les heures
            String hourLTfS = hourLTf.getText();
            String hourRTfS = hourRTf.getText();
            if (!Time.hoursWellFormated(hourLTfS)||!Time.hoursWellFormated(hourRTfS)) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Hours aren't well formatted.", ButtonType.OK);
                alert.showAndWait();
                return;
            }

            // juste la date
            LocalDate localDateL = (dateL.getValue()!=null?dateL.getValue():dateNow.toLocalDate());
            LocalDate localDateR = (dateR.getValue()!=null?dateR.getValue():dateNow.toLocalDate());
            t.elapsed(localDateL,localDateR,hourLTfS,hourRTfS);
            current.setText("");// Mettre la réponse
        });
        minus.setOnAction(actionEvent -> {
            String hourLTfS = hourLTf.getText();
            String hourRTfS = hourRTf.getText();
            if (!Time.hoursWellFormated(hourLTfS)||!Time.hoursWellFormated(hourRTfS)) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Hours aren't well formatted.", ButtonType.OK);
                alert.showAndWait();
                return;
            }

            // juste la date
            LocalDate localDateL = (dateL.getValue()!=null?dateL.getValue():dateNow.toLocalDate());
            LocalDate localDateR = (dateR.getValue()!=null?dateR.getValue():dateNow.toLocalDate());

            current.setText(t.minus(localDateL,localDateR,hourLTfS,hourRTfS));// Mettre la réponse
        });
        plus.setOnAction(actionEvent -> {
            String hourLTfS = hourLTf.getText();
            String hourRTfS = hourRTf.getText();
            if (!Time.hoursWellFormated(hourLTfS)||!Time.hoursWellFormated(hourRTfS)) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Hours aren't well formatted.", ButtonType.OK);
                alert.showAndWait();
                return;
            }

            // juste la date
            LocalDate localDateL = (dateL.getValue()!=null?dateL.getValue():dateNow.toLocalDate());
            LocalDate localDateR = (dateR.getValue()!=null?dateR.getValue():dateNow.toLocalDate());
            t.plus(localDateL,localDateR,hourLTfS,hourRTfS);
            current.setText(""); //Mettre la réponse
        });
        grid.addRow(0,dateL,dateR);
        grid.addRow(1,hourLTf,hourRTf);
        grid.addRow(2,plus,minus);
        grid.addRow(3,elapsedS,elapsedB);
        grid.addRow(4,current);
    }

}
