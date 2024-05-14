package WS.Methods;

import java.time.*;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;

public class Utilities {
    public static int getWeekNumber(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.WEEK_OF_YEAR);
    }

    public static LocalDateTime getLocalDateTime(Date date) {
        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    public static Date getLocalDateTime(LocalDateTime dateToConvert) {
        return java.sql.Timestamp.valueOf(dateToConvert);
    }

    public static Date firstDayOfWeek(Date date) {
        LocalDateTime inputDate = getLocalDateTime(date);
        LocalDateTime lastMonday = inputDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        return getLocalDateTime(lastMonday);
    }
    public static Date lastDayOfWeek(Date date) {
        LocalDateTime inputDate = getLocalDateTime(date);
        LocalDateTime firstFriday = inputDate.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
        return getLocalDateTime(firstFriday);
    }
}
