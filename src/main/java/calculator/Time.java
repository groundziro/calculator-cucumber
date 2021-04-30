package calculator;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * The Time class contains all what is needed to perform time specific actions.
 *
 * @author Laurent Bossart
 */
public class Time {

    /**
     * This method is used to know the current date and hours.
     *
     * @return A string containing the current date and current time.
     */
    public static String current_time() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return formatter.format(LocalTime.now());
    }

    /**
     * This method is used to check if the user insertion for adding or removing an amount of time is correct.
     *
     * @param checking A string specified by the user. Should by hh:mm[:ss] (the amount of hours, minutes et seconds to add) or an a number specifying the amount to add.
     * @param what This param is chosen by the user. It tells the program if the user wants to add days, hours, etc to the current date and time.
     * @return true if the string specified by the user is well formatted, false otherwise.
     */
    public static boolean add_remove_well_formatted(String checking, String what) {
        if (what.equals("Mixe")) {
            return checking.matches("^\\d+:\\d+(:\\d+)?$");
        }
        else {
            return checking.matches("^\\d+$");
        }
    }

    /**
     * This method helps to know if the string representing time entered by the user is well formatted.
     *
     * @param checking The string specified by the user.
     * @return true if the string is well formatted, false otherwise.
     */
    public static boolean hours_well_formatted(String checking) {
        boolean hh_am_zone = checking.matches("^(((0?[0-9]|1[0-9]|2[0-3]):[0-5][0-9](:[0-5][0-9])?)|(([1-9]|1[0-2])(:[0-5][0-9])?(:[0-5][0-9])? (AM|PM))) [A-Z]{1,4}((\\+([0-9]|1[1-4]))|(-([1-9]|1[1-2])))$");
        if (hh_am_zone) {
            boolean known_zone_id =
                    checking.contains("+") ?
                            checking.split("\\+")[0].split(" ").length == 3 ?
                                    TimeZones.getTimeZones().contains(checking.split("\\+")[0].split(" ")[2])
                                    : checking.split("\\+")[0].split(" ").length == 2 ?
                                    TimeZones.getTimeZones().contains(checking.split("\\+")[0].split(" ")[1]) : false
                            : checking.split("-")[0].split(" ").length == 3 ?
                                    TimeZones.getTimeZones().contains(checking.split("-")[0].split(" ")[2])
                            : checking.split("-")[0].split(" ").length == 2 ?
                            TimeZones.getTimeZones().contains(checking.split("-")[0].split(" ")[1]) : false;
            hh_am_zone = known_zone_id;
        }
        return checking.matches("^(0?[0-9]|1[0-9]|2[0-3]):[0-5][0-9](:[0-5][0-9])?$") ||
               checking.matches("^([1-9]|1[0-2])(:[0-5][0-9])?(:[0-5][0-9])? (AM|PM)$") || hh_am_zone;

    }

    /**
     * This method check if the format is standardised. If so, transform the time to a standardised one.
     *
     * @param p_hour The time specified by the user.
     * @return The standardised version of the time specified by the user.
     */
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

    /**
     * This method converts a time with a time zone not standardised to a standardised one. The standard is 24 hours format hh:mm:ss.
     *
     * @param p_hour The time specified by the user.
     * @return The time standardised.
     */
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
            hours = check_format(p_hour.split(" ")[0]);
            hours_int = (Integer.parseInt(hours.split(":")[0]) + Integer.parseInt(p_hour_list[1].contains("+") ? p_hour_list[1].split("\\+")[1] : p_hour_list[1].split("-")[1])) % 24;
        }
        if (hours.split(":").length == 3) {
            hours = hours_int + ":" + hours.split(":")[1] + ":" + hours.split(":")[2];
        }
        else {
            hours = hours_int + ":" + hours.split(":")[1] + ":00";
        }
        return check_format(hours);
    }

    /**
     * This method convert a 12 hours time to a 24 hours time.
     *
     * @param p_hour An hour in the 12 hours format.
     * @return The time specified by the user but in 24 hours format with seconds.
     */
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

    /**
     * This method is used to remove an amount of time to a specific date and time.
     *
     * @param first_hour The amount the user wants to remove to the current date.
     * @param what_to_add_remove What the user wants to remove (days, seconds, etc).
     * @return The date that will be after removing the amount specified by the user.
     */
    public String minus(String first_hour, String what_to_add_remove) {
        LocalDateTime current_date_and_hours = LocalDateTime.now().withNano(0);
        if (what_to_add_remove.equals("Mixe")) {
            int hours_to_remove = Integer.parseInt(first_hour.split(":")[0]);
            int minutes_to_remove = Integer.parseInt(first_hour.split(":")[1]);
            int seconds_to_remove;
            if (first_hour.split(":").length == 3) {
                seconds_to_remove = Integer.parseInt(first_hour.split(":")[2]);
            }
            else {
                seconds_to_remove = 0;
            }
            return current_date_and_hours.plusHours(-hours_to_remove).plusMinutes(-minutes_to_remove).plusSeconds(-seconds_to_remove).toString().replace('T', ' ');
        }
        else {
            LocalDateTime new_current_date_and_hours;
            switch (what_to_add_remove) {
                case "Years":
                    new_current_date_and_hours = current_date_and_hours.plusYears(-Integer.parseInt(first_hour));
                    return new_current_date_and_hours.toString().replace('T', ' ');
                case "Months":
                    new_current_date_and_hours = current_date_and_hours.plusMonths(-Integer.parseInt(first_hour));
                    return new_current_date_and_hours.toString().replace('T', ' ');
                case "Weeks":
                    new_current_date_and_hours = current_date_and_hours.plusWeeks(-Integer.parseInt(first_hour));
                    return new_current_date_and_hours.toString().replace('T', ' ');
                case "Days":
                    new_current_date_and_hours = current_date_and_hours.plusDays(-Integer.parseInt(first_hour));
                    return new_current_date_and_hours.toString().replace('T', ' ');
                case "Hours":
                    new_current_date_and_hours = current_date_and_hours.plusHours(-Integer.parseInt(first_hour));
                    return new_current_date_and_hours.toString().replace('T', ' ');
                case "Minutes":
                    new_current_date_and_hours = current_date_and_hours.plusMinutes(-Integer.parseInt(first_hour));
                    return new_current_date_and_hours.toString().replace('T', ' ');
                case "Seconds":
                    new_current_date_and_hours = current_date_and_hours.plusSeconds(-Integer.parseInt(first_hour));
                    return new_current_date_and_hours.toString().replace('T', ' ');
                default:
                    return "Bad entry";
            }
        }
    }

    /**
     * This method is used to add an amount of time to a specific date and time.
     *
     * @param first_hour The amount the user wants to add to the current date.
     * @param what_to_add_remove What the user wants to add (days, seconds, etc).
     * @return The date that will be after adding the amount specified by the user.
     */
    public String plus(String first_hour, String what_to_add_remove) {
        LocalDateTime current_date_and_hours = LocalDateTime.now().withNano(0);
        if (what_to_add_remove.equals("Mixe")) {
            int hours_to_remove = Integer.parseInt(first_hour.split(":")[0]);
            int minutes_to_remove = Integer.parseInt(first_hour.split(":")[1]);
            int seconds_to_remove;
            if (first_hour.split(":").length == 3) {
                seconds_to_remove = Integer.parseInt(first_hour.split(":")[2]);
            }
            else {
                seconds_to_remove = 0;
            }
            return current_date_and_hours.plusHours(hours_to_remove).plusMinutes(minutes_to_remove).plusSeconds(seconds_to_remove).toString().replace('T', ' ');
        }
        else {
            LocalDateTime new_current_date_and_hours;
            switch (what_to_add_remove) {
                case "Years":
                    new_current_date_and_hours = current_date_and_hours.plusYears(Integer.parseInt(first_hour));
                    return new_current_date_and_hours.toString().replace('T', ' ');
                case "Months":
                    new_current_date_and_hours = current_date_and_hours.plusMonths(Integer.parseInt(first_hour));
                    return new_current_date_and_hours.toString().replace('T', ' ');
                case "Weeks":
                    new_current_date_and_hours = current_date_and_hours.plusWeeks(Integer.parseInt(first_hour));
                    return new_current_date_and_hours.toString().replace('T', ' ');
                case "Days":
                    new_current_date_and_hours = current_date_and_hours.plusDays(Integer.parseInt(first_hour));
                    return new_current_date_and_hours.toString().replace('T', ' ');
                case "Hours":
                    new_current_date_and_hours = current_date_and_hours.plusHours(Integer.parseInt(first_hour));
                    return new_current_date_and_hours.toString().replace('T', ' ');
                case "Minutes":
                    new_current_date_and_hours = current_date_and_hours.plusMinutes(Integer.parseInt(first_hour));
                    return new_current_date_and_hours.toString().replace('T', ' ');
                case "Seconds":
                    new_current_date_and_hours = current_date_and_hours.plusSeconds(Integer.parseInt(first_hour));
                    return new_current_date_and_hours.toString().replace('T', ' ');
                default:
                    return "Bad entry";
            }
        }
    }

    /**
     * This method is used to print in a specific format the time elapsed between two dates.
     *
     * @param first_date_time The start date and time.
     * @param second_date_time The end date and time.
     * @param user_result How the user wants the result to be displayed.
     * @return The amount of time between two dates.
     */
    public String choices(ZonedDateTime first_date_time, ZonedDateTime second_date_time, String user_result) {
        switch (user_result) {
            case "Mixe":
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


    /**
     * This method compute the amount of time between the specified time and the current time.
     *
     * @param user_date The date selected by the user.
     * @param user_hours The hours specified by the user.
     * @param user_result How the user wants the output to be displayed.
     * @return The amount of time elapsed between the time specified by the user.
     */
    public String elapsed_since(LocalDate user_date, String user_hours, String user_result) {
        boolean using_time_zone = user_hours.matches("^(((0?[0-9]|1[0-9]|2[0-3]):[0-5][0-9](:[0-5][0-9])?)|(([1-9]|1[0-2])(:[0-5][0-9])?(:[0-5][0-9])? (AM|PM))) [A-Z]{1,4}((\\+([0-9]|1[1-4]))|(-([0-9]|1[1-2])))$");
        String time_zone;
        if (using_time_zone) {
            if (user_hours.split(" ").length == 2) {
                time_zone = user_hours.contains("+") ? user_hours.split(" ")[1].split("\\+")[0] : user_hours.split(" ")[1].split("-")[0];
            }
            else {
                time_zone = user_hours.contains("+") ? user_hours.split(" ")[2].split("\\+")[0] : user_hours.split(" ")[2].split("-")[0];
            }
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
        if (current_zoned_date_and_hours.isBefore(user_zoned_date_and_hours) || current_zoned_date_and_hours.isEqual(user_zoned_date_and_hours)) {
            return "No time elapsed since this time.";
        }
        return choices(user_zoned_date_and_hours, current_zoned_date_and_hours, user_result);
    }


    /**
     * This method compute the amount of time between two distinct moments. If the first date and time is "after" the second one, tell the user that no time has elapsed.
     *
     * @param first_user_date The first date specified by the user. If the user don't specify any, the current date is selected.
     * @param first_user_hours The first time specified by the user. If the user don't specify any, the current time is selected.
     * @param user_result How the user want the output to be displayed.
     * @param second_user_date The second date specified by the user. If the user don't specify any, the current date is selected.
     * @param second_user_hours The second time specified by the user. If the user don't specify any, the current time is selected.
     * @return The amount of time elapsed between the two specified date and time.
     */
    public String elapsed_between(LocalDate first_user_date, String first_user_hours, String user_result, LocalDate second_user_date, String second_user_hours) {
        boolean using_first_time_zone = first_user_hours.matches("^(((0?[0-9]|1[0-9]|2[0-3]):[0-5][0-9](:[0-5][0-9])?)|(([1-9]|1[0-2])(:[0-5][0-9])?(:[0-5][0-9])? (AM|PM))) [A-Z]{1,4}((\\+([0-9]|1[1-4]))|(-([0-9]|1[1-2])))$");
        boolean using_second_time_zone = second_user_hours.matches("^(((0?[0-9]|1[0-9]|2[0-3]):[0-5][0-9](:[0-5][0-9])?)|(([1-9]|1[0-2])(:[0-5][0-9])?(:[0-5][0-9])? (AM|PM))) [A-Z]{1,4}((\\+([0-9]|1[1-4]))|(-([0-9]|1[1-2])))$");
        String first_time_zone;
        String second_time_zone;
        if (using_first_time_zone && using_second_time_zone) {
            if (first_user_hours.split(" ").length == 2 && second_user_hours.split(" ").length == 2){
                first_time_zone = first_user_hours.contains("+") ? first_user_hours.split(" ")[1].split("\\+")[0] : first_user_hours.split(" ")[1].split("-")[0];
                second_time_zone = second_user_hours.contains("+") ? second_user_hours.split(" ")[1].split("\\+")[0] : second_user_hours.split(" ")[1].split("-")[0];
            }
            else if (first_user_hours.split(" ").length == 2) {
                first_time_zone = first_user_hours.contains("+") ? first_user_hours.split(" ")[1].split("\\+")[0] : first_user_hours.split(" ")[1].split("-")[0];
                second_time_zone = second_user_hours.contains("+") ? second_user_hours.split(" ")[2].split("\\+")[0] : second_user_hours.split(" ")[2].split("-")[0];
            }
            else if (second_user_hours.split(" ").length == 2) {
                first_time_zone = first_user_hours.contains("+") ? first_user_hours.split(" ")[2].split("\\+")[0] : first_user_hours.split(" ")[2].split("-")[0];
                second_time_zone = second_user_hours.contains("+") ? second_user_hours.split(" ")[1].split("\\+")[0] : second_user_hours.split(" ")[1].split("-")[0];
            }
            else {
                first_time_zone = first_user_hours.contains("+") ? first_user_hours.split(" ")[2].split("\\+")[0] : first_user_hours.split(" ")[2].split("-")[0];
                second_time_zone = second_user_hours.contains("+") ? second_user_hours.split(" ")[2].split("\\+")[0] : second_user_hours.split(" ")[2].split("-")[0];
            }
        }
        else if (using_first_time_zone) {
            if (first_user_hours.split(" ").length == 2) {
                first_time_zone = first_user_hours.contains("+") ? first_user_hours.split(" ")[1].split("\\+")[0] : first_user_hours.split(" ")[1].split("-")[0];
            }
            else {
                first_time_zone = first_user_hours.contains("+") ? first_user_hours.split(" ")[2].split("\\+")[0] : first_user_hours.split(" ")[2].split("-")[0];
            }
            second_time_zone = "CET";
        }
        else if (using_second_time_zone) {
            if (second_user_hours.split(" ").length == 2) {
                second_time_zone = second_user_hours.contains("+") ? second_user_hours.split(" ")[1].split("\\+")[0] : second_user_hours.split(" ")[1].split("-")[0];
            }
            else {
                second_time_zone = second_user_hours.contains("+") ? second_user_hours.split(" ")[2].split("\\+")[0] : second_user_hours.split(" ")[2].split("-")[0];
            }
            first_time_zone = "CET";
        }
        else {
            first_time_zone = "CET";
            second_time_zone = "CET";
        }
        String new_first_user_hours = check_format(first_user_hours);
        String new_second_user_hours = check_format(second_user_hours);
        LocalTime first_hours = LocalTime.parse(new_first_user_hours, DateTimeFormatter.ofPattern("HH:mm:ss"));
        LocalTime second_hours = LocalTime.parse(new_second_user_hours, DateTimeFormatter.ofPattern("HH:mm:ss"));
        LocalDateTime first_user_date_and_hours = first_user_date.atTime(first_hours);
        LocalDateTime second_user_date_and_hours = second_user_date.atTime(second_hours);
        ZonedDateTime first_zoned_date_and_hours;
        ZonedDateTime second_zoned_date_and_hours;
        first_zoned_date_and_hours = first_user_date_and_hours.atZone(ZoneId.of(TimeZones.valueOf(first_time_zone).label));
        second_zoned_date_and_hours = second_user_date_and_hours.atZone(ZoneId.of(TimeZones.valueOf(second_time_zone).label));
        if (second_zoned_date_and_hours.isBefore(first_zoned_date_and_hours) || second_zoned_date_and_hours.isEqual(first_zoned_date_and_hours)) {
            return "No time elapsed between those two dates";
        }
        return choices(first_zoned_date_and_hours, second_zoned_date_and_hours, user_result);
    }
}