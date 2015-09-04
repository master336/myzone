package com.web.core.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	public static boolean timeBefore(Date currentTime, Date validTime) {
		return currentTime.before(validTime);
	}

	public static long timeStamp() {
		return new Date().getTime();
	}
	public static String timeNow() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	}
	public static int hourNow() {
		return Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
	}
	public static String dateNow() {
		return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
	}
	public static String dateAssignDay(int day) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_MONTH, day);
		return new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
	}
	public static boolean isSunday() {
		Calendar c = Calendar.getInstance();
		return c.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY;
	}
	
	public static String date(Date time) {
		return new SimpleDateFormat("yyyy-MM-dd").format(time);
	}
	public static Calendar assignHours(int hour) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.HOUR,hour);
		return c;
	}
	public static Calendar parseTime(String date) throws ParseException{
		Calendar c =  Calendar.getInstance();
		c.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date));
		return c;
	}
	public static Calendar parseHour(String date) throws ParseException{
		Calendar c =  Calendar.getInstance();
		c.setTime(new SimpleDateFormat("yyyy-MM-dd HH").parse(date));
		return c;
	}
	public static Calendar parseDate(String date) throws ParseException{
		Calendar c =  Calendar.getInstance();
		c.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(date));
		return c;
	}
}
