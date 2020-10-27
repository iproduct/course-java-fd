package datetimeapi;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class DateTimeApiDemo {

	public static void main(String[] args) {
//		String str = "2019-05-17 12:30";
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
//		LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
		
//		// begin output
//		System.out.println("Current Time");
//		System.out.println(dateTime.format(formatter));

		// create a Pacific Standard Time time zone
		String[] ids = TimeZone.getAvailableIDs(2 * 60 * 60 * 1000);
		System.out.println(Arrays.toString(ids));

		SimpleTimeZone eet = new SimpleTimeZone(2 * 60 * 60 * 1000, "Europe/Sofia");
		System.out.println(eet);
		eet.setEndRule(Calendar.OCTOBER, -1, Calendar.SUNDAY, 2 * 60 * 60 * 1000);
//
//		// create a GregorianCalendar with the Pacific Daylight time zone
//		// and the current date and time
		Locale bgLocale = new Locale("bg", "BG");
		GregorianCalendar calendar = new GregorianCalendar(eet, bgLocale);
//		Date trialTime = new Date();
//		calendar.setTime(trialTime);
//
//		ZonedDateTime zdt = ZonedDateTime.ofInstant(calendar.toInstant(), eet.toZoneId());
		ZonedDateTime zdt = calendar.toZonedDateTime();
//
		DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm:ss ZZZZ zzzz");
//		LocalDateTime dateTime = LocalDateTime.of(1986, Month.APRIL, 8, 12, 30);
		LocalDateTime dateTime = LocalDateTime.now();
		ZonedDateTime dateInPast = ZonedDateTime.of(dateTime, eet.toZoneId());
		String formattedDateTime = dateInPast.format(formatter2); // "1986-04-08 12:30"
		System.out.println(formattedDateTime);
	}

}
