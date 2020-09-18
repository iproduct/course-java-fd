package datetimeapi;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.*;

public class SimpleDemo {
    public static void main(String[] args) {
        LocalDate today = LocalDate.now();
        LocalDate reportDay = today.with(TemporalAdjusters.lastDayOfMonth());
        DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("dd.MM.yyyyг.");
        System.out.printf("Report due: %s%n", reportDay.format(dtf1));
        LocalDate paymentDay = reportDay.plus(-5, ChronoUnit.DAYS);
        System.out.printf("Report due: %s%n", paymentDay.format(dtf1));

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        System.out.printf("Report due: %s%n", now.format(dtf2));


    }
}
