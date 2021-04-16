package calculator;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

public class Time{

    public static String now(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    public static boolean hours_well_formatted(String checking) {
        return checking.matches("^(0?[1-9]|1[0-9]|2[0-3]):[0-5]?[0-9](:[0-5]?[0-9])?$");
    }

    private String check_seconds(String hour){
        boolean format = hour.matches("^(0?[1-9]|1[0-9]|2[0-3]):[0-5]?[0-9]:[0-5]?[0-9]$");
        String output;
        if (! format){
            output = hour + ":00";
        }
        else{
            output = hour;
        }
        return output;
    }

    public String minus(LocalDate first_date, LocalDate second_date, String first_hour, String second_hour){
        LocalTime first_time = LocalTime.parse(first_hour,DateTimeFormatter.ofPattern("HH:mm:ss"));
        LocalTime second_time = LocalTime.parse(second_hour,DateTimeFormatter.ofPattern("HH:mm:ss"));
        LocalDateTime first_time_updated = first_date.atTime(first_time);
        LocalDateTime second_time_updated = second_date.atTime(second_time);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String time1 = formatter.format(first_time_updated);
        String time2 = formatter.format(second_time_updated);
//        String time1 = first_time_updated.toString().replace('T', ' ');
//        String time2 = second_time_updated.toString().replace('T', ' ');
        String[] date_and_hours1 = time1.split(" ");
        String[] date_and_hours2 = time2.split(" ");
        String[] date1 = date_and_hours1[0].split("-");
        String[] date2 = date_and_hours2[0].split("-");
        String[] hours1 = check_seconds(date_and_hours1[1]).split(":");
        String[] hours2 = check_seconds(date_and_hours2[1]).split(":");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Integer.parseInt(date1[0]), Integer.parseInt(date1[1]), Integer.parseInt(date1[2]),
                     Integer.parseInt(hours1[0]), Integer.parseInt(hours1[1]), Integer.parseInt(hours1[2]));
        calendar.add(Calendar.YEAR, -Integer.parseInt(date2[0]));
        calendar.add(Calendar.MONTH, -Integer.parseInt(date2[1]));
        calendar.add(Calendar.DATE, -Integer.parseInt(date2[2]));
        calendar.add(Calendar.HOUR, -Integer.parseInt(hours2[0]));
        calendar.add(Calendar.MINUTE, -Integer.parseInt(hours2[1]));
        calendar.add(Calendar.SECOND, -Integer.parseInt(hours2[2]));
        return calendar.getTime().toString();
    }

    public void plus(LocalDate l, LocalDate r, String hourL, String hourR){
        LocalTime timeL = LocalTime.parse(hourL,DateTimeFormatter.ofPattern("HH:mm:ss"));
        LocalTime timeR = LocalTime.parse(hourR,DateTimeFormatter.ofPattern("HH:mm:ss"));
        LocalDateTime updatedL = l.atTime(timeL);
        LocalDateTime updatedR = r.atTime(timeR);
    }

    public String elapsed_since(LocalDate user_date, String user_hours, String user_result){
        user_hours = check_seconds(user_hours);
        LocalTime hours = LocalTime.parse(user_hours, DateTimeFormatter.ofPattern("HH:mm:ss"));
        LocalDateTime user_date_and_hours = user_date.atTime(hours);
        LocalDateTime current_date_and_hours = LocalDateTime.now();
        if (current_date_and_hours.isBefore(user_date_and_hours) || current_date_and_hours.isEqual(user_date_and_hours)){
            return "No time elapsed since this time.";
        }
        switch (user_result){
            case "Centuries":
                return String.valueOf(ChronoUnit.CENTURIES.between(user_date_and_hours, current_date_and_hours));
            case "Decades":
                return String.valueOf(ChronoUnit.DECADES.between(user_date_and_hours, current_date_and_hours));
            case "Years":
                return String.valueOf(ChronoUnit.YEARS.between(user_date_and_hours, current_date_and_hours));
            case "Months":
                return String.valueOf(ChronoUnit.MONTHS.between(user_date_and_hours, current_date_and_hours));
            case "Days":
                return String.valueOf(ChronoUnit.DAYS.between(user_date_and_hours, current_date_and_hours));
            case "Hours":
                return String.valueOf(ChronoUnit.HOURS.between(user_date_and_hours, current_date_and_hours));
            case "Minutes":
                return String.valueOf(ChronoUnit.MINUTES.between(user_date_and_hours, current_date_and_hours));
            case "Seconds":
                return String.valueOf(ChronoUnit.SECONDS.between(user_date_and_hours, current_date_and_hours));
            default:
                return "Invalid output type";
        }
    }

    public Duration elapsed(LocalDate l, LocalDate r, String hourL, String hourR){
        LocalTime timeL = LocalTime.parse(hourL,DateTimeFormatter.ofPattern("HH:mm:ss"));
        LocalTime timeR = LocalTime.parse(hourR,DateTimeFormatter.ofPattern("HH:mm:ss"));
        LocalDateTime updatedL = l.atTime(timeL);
        LocalDateTime updatedR = r.atTime(timeR);
        return Duration.between(updatedL,updatedR);
    }
}

/*
public class Time{

    public Time (String time, String format){
        this.time = time;
        this.format = format;
    }

    public String minus(Time t){
        int hours1;
        int minutes1;
        int hours2;
        int minutes2;
        if (this.format.equals("24")) {
            hours1 = Integer.parseInt(this.time.split(":")[0]);
            minutes1 = Integer.parseInt(this.time.split(":")[1]);
        }
        else{
            String hour = to24(this.time);
            hours1 = Integer.parseInt(hour.split(":")[0]);
            minutes1 = Integer.parseInt(hour.split(":")[1]);
        }
        if (t.format.equals("24")){
            hours2 = Integer.parseInt(t.time.split(":")[0]);
            minutes2 = Integer.parseInt(t.time.split(":")[1]);
        }
        else{
            String hour = to24(t.time);
            hours2 = Integer.parseInt(hour.split(":")[0]);
            minutes2 = Integer.parseInt(hour.split(":")[1]);
        }
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
*/