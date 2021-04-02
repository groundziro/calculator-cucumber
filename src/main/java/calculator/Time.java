package calculator;

import visitor.Visitor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Time {
    public final String time;
    public final String format;

    public Time (String time, String format){
        this.time = time;
        this.format = format;
    }

    public String minus(Time t){
        int hours1 = Integer.parseInt(this.time.split(":")[0]);
        int minutes1 = Integer.parseInt(this.time.split(":")[1]);
        int hours2 = Integer.parseInt(t.time.split(":")[0]);
        int minutes2 = Integer.parseInt(t.time.split(":")[1]);
        int hours = hours1-hours2;
        int minutes = minutes1 - minutes2;
        if (hours <= 0){
            hours = 24 + hours;
        }
        if (minutes < 0){
            hours -= 1;
            minutes = 60 + minutes;
        }
        return hours + ":" + minutes;
    }

    public static String now(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    public static String to24(String hour){
        String[] hourMinFormat = hour.split(" ");
        String[] hourMin = hourMinFormat[0].split(":");
        int hours = Integer.parseInt(hourMin[0]);
        int min = Integer.parseInt(hourMin[1]);
        String format = hourMinFormat[1];
        if (format.equals("PM")){
            hours += 12;
        }
        return hours + ":" + min;
    }

    public String since(){
        String format = this.format;
        int currentInMinutes;
        int timeInMinutes;
        if (format.equals("12")){
            timeInMinutes = toMinutes(to24(this.time));
        }
        else {
            timeInMinutes = toMinutes(this.time);
        }
        String current = Time.now();
        currentInMinutes = toMinutes(current);
        if (timeInMinutes > currentInMinutes) {
            currentInMinutes += 1440;
        }
        return toHours(String.valueOf(currentInMinutes - timeInMinutes));
    }

    public static int toMinutes(String hour){
        String[] hourMin = hour.split(":");
        int hours = Integer.parseInt(hourMin[0]);
        int minutes = Integer.parseInt(hourMin[1]);
        int hoursInMin = hours * 60;
        return hoursInMin + minutes;
    }

    public static String toHours(String hour){
        int time = Integer.parseInt(hour);
        int hours = time / 60;
        int minutes = time % 60;
        String min = String.valueOf(minutes);
        if (minutes < 10){
            min = "0" + minutes;
        }
        return hours + ":" + min;
    }
}
