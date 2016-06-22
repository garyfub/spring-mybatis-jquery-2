package com.kun.common.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class DateTimeUtil {

	public final static String DEFAULT_DATETIME_FORMAT = "yyyyMMddHHmmss";

	public final static String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";

	public final static String DEFAULT_TIME_FORMAT = "HH:mm:ss";

	private static int SERVER_TIME_ZONE = -1;

	static {
		// check server time zone
		if (SERVER_TIME_ZONE < 1) {
			TimeZone zone = TimeZone.getDefault();
			SERVER_TIME_ZONE = zone.getRawOffset();
		}
	}

	/**
	 * 取当前时间
	 * 
	 * @since
	 * @return
	 */
	public static Date getTime() {
		return Calendar.getInstance().getTime();
	}

	/**
	 * 获取当前时间【yyyyMMddHHmmss】
	 * 
	 * @create 2015年12月2日 下午8:51:59
	 * @return String
	 */
	public static long getCurrentTimeToLong() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.YEAR) * 10000000000l + (calendar.get(Calendar.MONTH) + 1) * 100000000l
				+ calendar.get(Calendar.DAY_OF_MONTH) * 1000000l + calendar.get(Calendar.HOUR_OF_DAY) * 10000
				+ calendar.get(Calendar.MINUTE) * 100 + calendar.get(Calendar.SECOND);
	}
	public static void main(String[] args) {
		System.out.println(getCurrentTimeToLong());
	}

	/**
	 * 取得指定field的给定差值对应的时间
	 * 
	 * @since
	 * @param field
	 * @param diff
	 * @return
	 */
	public static Date getTime(int field, int diff) {
		Calendar c = Calendar.getInstance();
		c.add(field, diff);
		return c.getTime();
	}

	/**
	 * This method return the current Timestamp
	 * 
	 * @return the current Timestamp
	 */
	public static Timestamp getCurrentTimestamp() {
		return new Timestamp(System.currentTimeMillis());
	}

	/**
	 * This method return the current Timestamp as long
	 * 
	 * @return the current Timestamp as long
	 */
	public static long getCurrentTimestampAsLong() {
		return System.currentTimeMillis();
	}

	/**
	 * 字符串类型日期转成Date
	 * 
	 * @param date
	 * @param fmt
	 * @return
	 * @throws Exception
	 */
	public static Date getDateFromString(String date, String fmt) throws Exception {
		if (date == null || date.trim().length() == 0)
			return null;
		SimpleDateFormat sdf = fmt == null ? new SimpleDateFormat(DEFAULT_DATE_FORMAT) : new SimpleDateFormat(fmt);
		return sdf.parse(date);
	}

	/**
	 * This method returns the earlist time of the input date, i.e 00:00:00
	 * 
	 * @return the earlist time
	 */
	public static Date getEarlistTime(Date in) {
		Calendar c = new GregorianCalendar();
		c.setTime(in);

		c.set(Calendar.HOUR, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		c.set(Calendar.AM_PM, Calendar.AM);

		return c.getTime();
	}

	/**
	 * This method returns the latest time of the input date, i.e 23:59:59
	 * 
	 * @return the latest time
	 */
	public static Date getLatestTime(Date in) {
		Calendar c = new GregorianCalendar();
		c.setTime(in);

		c.set(Calendar.HOUR, 11);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		c.set(Calendar.MILLISECOND, 999);
		c.set(Calendar.AM_PM, Calendar.PM);

		return c.getTime();
	}

	/**
	 * parse String to Date
	 * 
	 * @param date
	 *            - 锟街凤拷锟斤拷锟斤拷锟?1?7
	 * @param format
	 *            - 锟斤拷锟节革拷式
	 * @return Date - 锟斤拷锟斤拷
	 */
	public static Date parse(String date, String format) throws ParseException {
		if (date == null)
			return null;

		SimpleDateFormat fmt = new SimpleDateFormat(format);

		return fmt.parse(date);
	}

	/**
	 * Format date by the format
	 * 
	 * @param date
	 *            - the source date
	 * @param format
	 *            - the date format
	 * 
	 * @return the result String
	 */
	public static String formatDate(Date date, String format) {
		if (date == null)
			return null;

		Format formatter = new SimpleDateFormat(format);
		return formatter.format(date);
	}

	/**
	 * get GMT Time from local time.
	 * 
	 * @param localTime
	 * @return Date
	 */
	public static Date getGMTTime(Date localTime) {

		if (localTime == null)
			return null;
		return getTime(localTime, TimeZone.getDefault(), TimeZone.getTimeZone("GMT"));
	}

	/**
	 * Convert a local time to GMT time, the local time zone is specified by
	 * offset
	 * 
	 * @param localTime
	 * @param offset
	 *            - offset to GMT time in term of hours, e.g. +1, +2, etc.
	 * 
	 * @return GMT time
	 */
	public static Date getGMTFromLocal(Date localTime, int offset) {
		TimeZone tz1 = TimeZone.getTimeZone("GMT");
		if (offset > 0)
			tz1 = TimeZone.getTimeZone("GMT+" + String.valueOf(offset));
		else if (offset < 0)
			tz1 = TimeZone.getTimeZone("GMT" + String.valueOf(offset));

		return getTime(localTime, tz1, TimeZone.getTimeZone("GMT"));

	}

	/**
	 * get local time from GMT time
	 * 
	 * @param gmtTime
	 * @return Date
	 */
	public static Date getLocalTime(Date gmtTime) {
		return getTime(gmtTime, TimeZone.getTimeZone("GMT"), TimeZone.getDefault());
	}

	/**
	 * Convert a GMT time to a local time, the local time zone is specified by
	 * offset
	 * 
	 * @param gmtTime
	 * @param offset
	 *            - offset to GMT time in term of hours, e.g. +1, +2, etc.
	 * 
	 * @return Local time
	 */
	public static Date getLocalFromGMT(Date gmtTime, int offset) {
		TimeZone tz1 = TimeZone.getTimeZone("GMT");
		if (offset > 0)
			tz1 = TimeZone.getTimeZone("GMT+" + String.valueOf(offset));
		else if (offset < 0)
			tz1 = TimeZone.getTimeZone("GMT" + String.valueOf(offset));

		return getTime(gmtTime, TimeZone.getTimeZone("GMT"), tz1);
	}

	/**
	 * get time of dstTimeZone from time of srcTimeZone
	 * 
	 * @param time
	 * @param srcTimeZone
	 * @param dstTimeZone
	 * @return Data
	 */
	private static Date getTime(Date time, TimeZone srcTimeZone, TimeZone dstTimeZone) {
		if (time == null)
			return null;
		DateFormat df = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
		df.setTimeZone(dstTimeZone);
		String gmtTime = df.format(time); // convert current time to gmt time.
		df.setTimeZone(srcTimeZone);
		try {
			return df.parse(gmtTime); // gmt time to date
		} catch (ParseException e) {
			return time;
		}

	}

	/**
	 * get current GMT time
	 * 
	 * @return Date
	 */
	public static Date getCurrentGMTTime() {
		return getGMTTime(getTime());
	}

	/**
	 * Get the server's Time from the time of GMT.
	 * 
	 * @param gmtTime
	 *            - the GMT time
	 * @return the server time.
	 */
	public static Date getServerTime(Date gmtTime) {

		Date retuDate = null;
		retuDate = DateTimeUtil.getLocalFromGMT(gmtTime, SERVER_TIME_ZONE);
		return retuDate;
	}

	/**
	 * Get the GMT time from the server's Time
	 * 
	 * @param time
	 *            - the server time
	 * @return GMT time.
	 */
	public static Date getServerGMTTime(Date serverTime) {

		Date retuDate = null;
		retuDate = DateTimeUtil.getGMTFromLocal(serverTime, SERVER_TIME_ZONE);
		return retuDate;
	}

	/**
	 * format date to show in web pages, since 2005/09/13
	 * 
	 * @param gmtTime
	 *            GMT time, get from DB
	 * @param format
	 *            time format
	 * @param offset
	 *            timezone offset, can get from user information
	 * @return string data to show in web pages, in user timezone
	 */
	public static String formatGMTDate(Date gmtTime, String format, int offset) {
		Date localTime = getLocalFromGMT(gmtTime, offset);
		return formatDate(localTime, format);
	}

	/**
	 * format date to show in web pages, with default time format, since
	 * 2005/09/13
	 * 
	 * @param gmtTime
	 *            GMT time, get from DB
	 * @param offset
	 *            timezone offset, can get from user information
	 * @return string data to show in web pages, in user timezone
	 */
	public static String formatGMTDate(Date gmtTime, int offset) {
		return formatGMTDate(gmtTime, DEFAULT_DATE_FORMAT, offset);
	}

	/**
	 * calculate date add offset
	 * 
	 * @param beginDate
	 * @param offset
	 * @return Date
	 */
	public static Date getDateAfter(Date beginDate, int offset) {
		Calendar c = Calendar.getInstance();
		c.setTime(beginDate);
		c.add(Calendar.MINUTE, offset);

		return c.getTime();
	}

	/**
	 * calculate date add offset
	 * 
	 * @param beginDate
	 * @param field
	 *            - the field to be set
	 * @param offset
	 * @return Date
	 */
	public static Date getDateAfter(Date beginDate, int field, int offset) {
		Calendar c = Calendar.getInstance();
		c.setTime(beginDate);
		c.add(field, offset);

		return c.getTime();
	}

	/**
	 * get a Date instance with special date and time
	 * 
	 * @param date
	 * @param hour
	 * @param minute
	 * @param second
	 * @return
	 */
	public static Date getDateTime(Date date, int hour, int minute, int second) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, hour);
		calendar.set(Calendar.MINUTE, minute);
		calendar.set(Calendar.SECOND, second);
		return calendar.getTime();
	}

	/**
	 * get GMT Time string for given page date and offset, time would be
	 * formated to DEFAULT_DATE_FORMAT;
	 * 
	 * @param date
	 * @param offset
	 * @return
	 */
	public static String getGMTTimeStr(Date pageTime, int offset) {
		return formatDate(getGMTFromLocal(pageTime, offset), DEFAULT_DATE_FORMAT);
	}

	/**
	 * yyyy-MM-dd HH:mm:ss
	 * 
	 * @return
	 */
	public static String getDateAndTime() {
		return getToDate() + " " + getToSecond();
	}

	/**
	 * yyyy-MM-dd
	 * 
	 * @return
	 */
	public static String getToDate() {
		Date logonDate = Calendar.getInstance().getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		return sdf.format(logonDate);
	}

	/**
	 * HH:mm:ss
	 * 
	 */
	public static String getToSecond() {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		Date logonDate = Calendar.getInstance().getTime();
		return sdf.format(logonDate);
	}

}
