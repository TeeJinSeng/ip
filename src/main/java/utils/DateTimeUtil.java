package utils;

import java.time.LocalDateTime;

import exceptions.ApunableException;

/**
 * A util that help to parse from {@code String} into {@code LocalDateTime}. 
 */
public class DateTimeUtil {
    /**
     * Converts a 2-digits years(e.g. "25") into a 4-digits year(e.g. "2025")
     * @param yearStr the 2-digits year string
     * @return the converted 4-digits year string
     */
    private static int toFourDigitsYear(String yearStr) {
        if (yearStr.length() == 2) {
            int year = Integer.parseInt(yearStr);
            int century = (LocalDateTime.now().getYear() - year) / 100;

            return century * 100 + year;
        }

        return Integer.parseInt(yearStr);
    }

    /**
     * Parses {@code dateTimeStr} into a {@code LocalDateTime} object, as {@code LocalDateTime.parse} handle too little cases.
     * 
     * @param dateTimeStr string that is going to be parsed
     * @return a {@code LocalDateTime} object
     */
    public static LocalDateTime tryParse(String dateTimeStr) throws ApunableException {
        try {
            dateTimeStr = dateTimeStr.trim();

            String dateStr = dateTimeStr;
            String timeStr = "00:00";

            if (dateTimeStr.split("[ T]").length > 1) {
                dateStr = dateTimeStr.split("[ T]")[0];
                timeStr = dateTimeStr.split("[ T]")[1];
            }

            int day;
            int month;
            int year;
            int hour;
            int minute;

            String[] dayMonthYear = dateStr.split("[/-]");
            String[] hourMinute = timeStr.split(":");

            if (dayMonthYear[0].length() == 4 || dayMonthYear[2].length() == 1) {
                day = Integer.parseInt(dayMonthYear[2]);
                month = Integer.parseInt(dayMonthYear[1]);
                year = toFourDigitsYear(dayMonthYear[0]);
            } else {
                day = Integer.parseInt(dayMonthYear[0]);
                month = Integer.parseInt(dayMonthYear[1]);
                year = toFourDigitsYear(dayMonthYear[2]);
            }

            if (hourMinute.length == 1) {
                hour = Integer.parseInt(timeStr.substring(0, 2));
                minute = Integer.parseInt(timeStr.substring(2, 4));
            } else {
                hour = Integer.parseInt(hourMinute[0]);
                minute = Integer.parseInt(hourMinute[1]);
            }

            return LocalDateTime.of(year, month, day, hour, minute);
        } catch (IndexOutOfBoundsException e) {
            throw new ApunableException("Invalid date time format");
        } catch (NumberFormatException e) {
            throw new ApunableException("Invalid date time format");
        } catch (Exception e) {
            throw new ApunableException(e.getMessage());
        }
    }
}
