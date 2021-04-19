package calculator;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.logging.Logger;

public class Time {

    private final Logger logger = Logger.getLogger(Time.class.getName());

    public static String current_time() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return formatter.format(LocalTime.now());
    }

    public static boolean hours_well_formatted(String checking) {
        return checking.matches("^(0?[0-9]|1[0-9]|2[0-3]):[0-5][0-9](:[0-5][0-9])?$") ||
               checking.matches("^([1-9]|1[0-2])(:[0-5][0-9])?(:[0-5][0-9])? (AM|PM)$") ||
               (checking.matches("^(((0?[0-9]|1[0-9]|2[0-3]):[0-5][0-9](:[0-5][0-9])?)|(([1-9]|1[0-2])(:[0-5][0-9])?(:[0-5][0-9])? (AM|PM))) [A-Z]{1,4}((\\+([0-9]|1[1-4]))|(-([1-9]|1[1-2])))$") &&
                       checking.contains("+") ? checking.split("\\+")[0].split(" ").length == 3 ? TimeZones.getTimeZones().contains(checking.split("\\+")[0].split(" ")[2])
                       : checking.split("\\+")[0].split(" ").length == 2 && TimeZones.getTimeZones().contains(checking.split("\\+")[0].split(" ")[1])
                       : checking.split("-")[0].split(" ").length == 3 ? TimeZones.getTimeZones().contains(checking.split("-")[0].split(" ")[2])
                       : checking.split("-")[0].split(" ").length == 2 && TimeZones.getTimeZones().contains(checking.split("-")[0].split(" ")[1]));

    }

    //  midnight as 12 am and noon as 12 pm
    private String check_format(String p_hour) {
        boolean format = p_hour.matches("^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]$");
        boolean format_AM_PM = p_hour.matches("^([1-9]|1[0-2])(:[0-5][0-9])?(:[0-5][0-9])? (AM|PM)$");
        boolean format_time_zone = p_hour.matches("^(((0?[0-9]|1[0-9]|2[0-3]):[0-5][0-9](:[0-5][0-9])?)|(([1-9]|1[0-2])(:[0-5][0-9])?(:[0-5][0-9])? (AM|PM))) [A-Z]{1,4}((\\+([0-9]|1[1-4]))|(-([0-9]|1[1-2])))$");
        String output1;
        String output2;
        if (format_AM_PM) {
            output2 = check_format_AM_PM(p_hour);
        }
        else if (format_time_zone){
            output2 = check_format_time_zone(p_hour);
        }
        else if (!format) {
            boolean test_hours = p_hour.matches("^[0-9]:[0-5][0-9](:[0-5][0-9])?$");
            if (test_hours) {
                output1 = "0" + p_hour;
            }
            else {
                output1 = p_hour;
            }
            boolean test_seconds = output1.matches("^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$");
            if (test_seconds) {
                    output2 = output1 + ":00";
            }
            else {
                output2 =output1;
            }
        }
        else {
            output2 = p_hour;
        }
        return output2;
    }

    private String check_format_time_zone(String p_hour) {
        boolean is_AM_PM = p_hour.matches("^([1-9]|1[0-2])(:[0-5][0-9])?(:[0-5][0-9])? (AM|PM) [A-Z]{1,4}((\\+([0-9]|1[1-4]))|(-([0-9]|1[1-2])))$");
        String[] p_hour_list = p_hour.split(" ");
        String hours;
        int hours_int;
        if (is_AM_PM) {
            hours = check_format_AM_PM(p_hour_list[0] + " " + p_hour_list[1]);
            hours_int = (Integer.parseInt(hours.split(":")[0]) + Integer.parseInt(p_hour_list[2].contains("+") ? p_hour_list[2].split("\\+")[1] : p_hour_list[2].split("-")[1])) % 24;
        }
        else {
            hours = p_hour;
            hours_int = (Integer.parseInt(hours.split(":")[0]) + Integer.parseInt(p_hour_list[2].contains("+") ? p_hour_list[1].split("\\+")[1] : p_hour_list[1].split("-")[1])) % 24;
        }
        hours = hours_int + ":" + hours.split(":")[1] + ":" + hours.split(":")[2];
        return check_format(hours);
    }

    private String check_format_AM_PM(String p_hour) {
        String time_format = p_hour.split(" ")[1];
        String time = p_hour.split(" ")[0];
        String[] time_list = time.split(":");
        int hour = Integer.parseInt(time_list[0]);
        StringBuilder output;
        if (time_format.equals("PM")) {
            if (hour != 12) {
                hour += 12;
            }
            time_list[0] = String.valueOf(hour);
            output = new StringBuilder(time_list[0]);
            for (int i = 1; i < time_list.length; i++) {
                output.append(":").append(time_list[i]);
            }
        }
        else {
            if (hour == 12) {
                hour -= 12;
            }
            time_list[0] = String.valueOf(hour);
            output = new StringBuilder(time_list[0]);
            for (int i = 1; i < time_list.length; i++) {
                output.append(":").append(time_list[i]);
            }
        }
        String[] test_format = output.toString().split(":");
        if (test_format.length == 1) {
            output.append(":00");
        }
        return check_format(output.toString());
    }

    public String minus(LocalDate first_date, LocalDate second_date, String first_hour, String second_hour) {
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
        String[] hours1 = check_format(date_and_hours1[1]).split(":");
        String[] hours2 = check_format(date_and_hours2[1]).split(":");
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

    public void plus(LocalDate l, LocalDate r, String hourL, String hourR) {
        LocalTime timeL = LocalTime.parse(hourL,DateTimeFormatter.ofPattern("HH:mm:ss"));
        LocalTime timeR = LocalTime.parse(hourR,DateTimeFormatter.ofPattern("HH:mm:ss"));
        LocalDateTime updatedL = l.atTime(timeL);
        LocalDateTime updatedR = r.atTime(timeR);
    }

    public String choices(ZonedDateTime first_date_time, ZonedDateTime second_date_time, String user_result) {
        switch (user_result) {
            case "Complete":
                long days = ChronoUnit.DAYS.between(first_date_time, second_date_time);
                long hours = ChronoUnit.HOURS.between(first_date_time, second_date_time);
                long minutes = ChronoUnit.MINUTES.between(first_date_time, second_date_time);
                long seconds = ChronoUnit.SECONDS.between(first_date_time, second_date_time);

                return days + " days, " + (hours % 24)  + " hours, " + (minutes % 60) + " minutes and " +
                       (seconds % 60) + " seconds.";
            case "Centuries":
                return ChronoUnit.CENTURIES.between(first_date_time, second_date_time) + " centuries";
            case "Decades":
                return ChronoUnit.DECADES.between(first_date_time, second_date_time) + " decades";
            case "Years":
                return ChronoUnit.YEARS.between(first_date_time, second_date_time) + " years";
            case "Months":
                return ChronoUnit.MONTHS.between(first_date_time, second_date_time) + " months";
            case "Days":
                return ChronoUnit.DAYS.between(first_date_time, second_date_time) + " days";
            case "Hours":
                return ChronoUnit.HOURS.between(first_date_time, second_date_time) + " hours";
            case "Minutes":
                return ChronoUnit.MINUTES.between(first_date_time, second_date_time) + " minutes";
            case "Seconds":
                return ChronoUnit.SECONDS.between(first_date_time, second_date_time) + " seconds";
            default:
                return "Invalid output type";
        }
    }

    public String elapsed_since(LocalDate user_date, String user_hours, String user_result) {
        boolean using_time_zone = user_hours.split(" ").length == 3;
        String time_zone;
        if (using_time_zone) {
            time_zone = user_hours.contains("+") ? user_hours.split(" ")[2].split("\\+")[0] : user_hours.split(" ")[2].split("-")[0];
            // =========================================================================================================
            logger.info("user time zone: " + time_zone);
            // =========================================================================================================
        }
        else {
            time_zone = "CET";
        }
        String new_user_hours = check_format(user_hours);
        LocalTime hours = LocalTime.parse(new_user_hours, DateTimeFormatter.ofPattern("HH:mm:ss"));
        LocalDateTime user_date_and_hours = user_date.atTime(hours);
        LocalDateTime current_date_and_hours = LocalDateTime.now().withNano(0);

        ZonedDateTime current_zoned_date_and_hours;
        ZonedDateTime user_zoned_date_and_hours;
        current_zoned_date_and_hours = current_date_and_hours.atZone(ZoneId.of(TimeZones.valueOf("CET").label));
        user_zoned_date_and_hours = user_date_and_hours.atZone(ZoneId.of(TimeZones.valueOf(time_zone).label));

        // =============================================================================================================
        logger.info("european time zone: " + current_zoned_date_and_hours);
        logger.info("user time zone : " + user_zoned_date_and_hours);
        // =============================================================================================================

        // return choices(user_zoned_date_and_hours, current_zoned_date_and_hours, user_result);

        if (current_zoned_date_and_hours.isBefore(user_zoned_date_and_hours) || current_zoned_date_and_hours.isEqual(user_zoned_date_and_hours)) {
            return "No time elapsed since this time.";
        }
        return choices(user_zoned_date_and_hours, current_zoned_date_and_hours, user_result);
    }

    public String elapsed_between(LocalDate first_user_date, String first_user_hours, String user_result,
                                    LocalDate second_user_date, String second_user_hours) {
        String new_first_user_hours = check_format(first_user_hours);
        String new_second_user_hours = check_format(second_user_hours);
        LocalTime first_hours = LocalTime.parse(new_first_user_hours, DateTimeFormatter.ofPattern("HH:mm:ss"));
        LocalTime second_hours = LocalTime.parse(new_second_user_hours, DateTimeFormatter.ofPattern("HH:mm:ss"));
        LocalDateTime first_user_date_and_hours = first_user_date.atTime(first_hours);
        LocalDateTime second_user_date_and_hours = second_user_date.atTime(second_hours);
        if (second_user_date_and_hours.isBefore(first_user_date_and_hours) || second_user_date_and_hours.isEqual(first_user_date_and_hours)) {
            return "No time elapsed between those two dates";
        }
        return "not working yet";
        // return choices(first_user_date_and_hours, second_user_date_and_hours, user_result);
    }
}