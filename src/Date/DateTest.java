package Date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Date;

public class DateTest {

    public static void main(String[] args) throws ParseException {
        Date date = new Date(116, 2, 18);
        System.out.println(date);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date parseDate = sdf.parse("20160505");
    }

    private static void testLocalDate() {
        LocalDate date = LocalDate.of(2015,01,02);
        System.out.println(date.getYear());
        System.out.println(date.getMonth());
        System.out.println(date.getMonthValue());
        System.out.println(date.getDayOfMonth());
        System.out.println(date.getDayOfYear());
        System.out.println(date.getDayOfWeek());

        LocalDate.now();

    }

    private static void testLocalTime() {
        LocalDate localDate = LocalDate.now();
        LocalTime time = LocalTime.now();
        System.out.println(time);

        LocalDateTime localDateTime = LocalDateTime.of(localDate, time);
    }

    private static void testInstant() throws InterruptedException {
        Instant start = Instant.now();
        Thread.sleep(1000L);
        Instant end = Instant.now();
        Duration duration = Duration.between(start, end);
        System.out.println(duration.toMillis());
    }

    private static void testDuration() {
        LocalTime time = LocalTime.now();
        LocalTime befor = time.minusHours(1);
        Duration duration = Duration.between(time, befor);
        System.out.println(duration.toHours());
    }

    private static void testPerid() {
        Period period = Period.between(LocalDate.of(2014, 1, 10), LocalDate.of(2016, 1, 10));
        System.out.println(period.getDays());
    }
}
