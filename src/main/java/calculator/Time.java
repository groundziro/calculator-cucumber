package calculator;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Time {
    public final String time;
    public final String format;

    public Time (String time, String format){
        this.time = time;
        this.format = format;
    }

    public static String now(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    public String since(){
        String format = this.format;
        String current = Time.now();
        int timeInMinutes = toMinutes(this.time);
        int currentInMinutes = toMinutes(current);
        if (timeInMinutes > currentInMinutes){
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
