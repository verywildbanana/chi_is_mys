package com.avocado.makeyoursmile.util;

import android.text.TextUtils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;

public class DateUtil {

	private static final String TAG = DateUtil.class.getSimpleName();

	public static final String DATE_FORMAT_1 = "yyyy.MM.dd";
	public static final String DATE_FORMAT_2 = "yy.MM.dd";
	public static final String DATE_FORMAT_3 = "MM.dd";
	public static final String DATE_FORMAT_4 = "yyyy-MM-dd'T'HH:mm:ss";
	public static final String DATE_FORMAT_5 = "yyyy년 MM월 dd일";
	public static final String DATE_FORMAT_6 = "yyyy-MM-dd'T'HH:mm:ssZ";
	public static final String DATE_FORMAT_7 = "HH:mm";
	public static final String DATE_FORMAT_8 = "MM월 dd일";

	public static String getDateString(Date date) {

		//		return DateFormat.getDateInstance(DateFormat.MEDIUM).format(date);
		return new SimpleDateFormat("yyyy.MM.dd").format(date).toString();
	}

	public static String getDateStringShortYear(Date date) {

		//		return DateFormat.getDateInstance(DateFormat.MEDIUM).format(date);
		return new SimpleDateFormat("yy.MM.dd").format(date).toString();
	}

	public static String getDateTimeString(Date date) {

		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date).toString();
	}

	public static String getDateFormat(Date date, String format) {
		return new SimpleDateFormat(format).format(date).toString();
	}
	
	public static Date getDate(int year, int month, int day) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month);
		cal.set(Calendar.DAY_OF_MONTH, day);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		
		return cal.getTime();
	}
	
	public static ArrayList<String> getDateTimeStringForTimeline(Date date) {

		StringTokenizer st = new StringTokenizer(new SimpleDateFormat(
				"yyyy.MM/dd/a hh:mm").format(date).toString(), "/");

		ArrayList<String> returnStr = new ArrayList<String>();

		while (st.hasMoreTokens()) {
			returnStr.add(st.nextToken());
		}

		return returnStr;
	}


	public static Date getCreateTime(String created_at) {

		if (TextUtils.isEmpty(created_at) == false) {

			String pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'"; 
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			Date date = null;
			try {
				date = sdf.parse(created_at);
			} 
			catch (ParseException e) {

				SmartLog.getInstance().e(TAG, "Exception: " + e);
			}
			catch (Exception e) {

				SmartLog.getInstance().e(TAG, "Exception: " + e);
			}

			return date;

		}

		return null;
	}

	public static Date getCreateTime(String created_at, String pattern) {

		if (TextUtils.isEmpty(created_at) == false) {

			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			Date date = null;
			try {
				date = sdf.parse(created_at);
			} 
			catch (ParseException e) {

				SmartLog.getInstance().e(TAG, "Exception: " + e);
			}
			catch (Exception e) {

				SmartLog.getInstance().e(TAG, "Exception: " + e);
			}

			return date;

		}

		return null;
	}

	public static boolean compareDate(String str1, String str2) {

		SmartLog.getInstance().w(TAG,
				"compareDate : " + str1 + "   :   " + str2);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

		Date date1 = null;
		Date date2 = null;
		try {

			date1 = sdf.parse(str1);
			date2 = sdf.parse(str2);

		} catch (Exception e) {

			SmartLog.getInstance().w(TAG, "compareDate Exception : " + e);
		}
		if (date1 != null && date2 != null) {

			if (date1.compareTo(date2) > 0) {
				SmartLog.getInstance().w(TAG, "Date1 is after Date2");
				return true;

			} else {

				SmartLog.getInstance().w(TAG, "Date1 is before Date2");
				return false;
			}
		}

		return false;
	}

	public static String getCurrentTime() {

		Date date = new Date();
		String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date)
				.toString();

		return time;
	}

	public static String getCurrentTimeStamp() {

		Timestamp timestamp = new Timestamp(System.currentTimeMillis());

		return timestamp.toGMTString();
	}

	public static String getDateFormatTime(String format) {

		Date date = new Date();
		String time = new SimpleDateFormat(format).format(date).toString();

		return time;
	}


	public static String getDateFormatTime(String format, Date date) {

		String time = new SimpleDateFormat(format).format(date).toString();

		return time;
	}



	public static long getDday(int year, int month, int day) {

		Calendar cal = Calendar.getInstance();
		long nowDay = cal.getTimeInMillis(); // 현재 시간

		cal.set(year, month - 1, day); // 목표일을 cal에 set
		long eventDay = cal.getTimeInMillis(); // 목표일에 대한 시간

		long d_day = (eventDay - nowDay) / (60 * 60 * 24 * 1000);

		return d_day;
	}


	public static long getDTime(String format, String targetdate) {

		Calendar cal = Calendar.getInstance();
		long nowDay = cal.getTimeInMillis(); // 현재 시간

		SmartLog.getInstance().w(TAG, "Today:" + cal.getTime().toLocaleString());

		SimpleDateFormat	sdf = new SimpleDateFormat(format);
		Date target = null;
		try {
			target = sdf.parse(targetdate);

		} catch (ParseException e) {
			return -1;
		}
		cal.setTime(target);

		long eventDay = cal.getTimeInMillis(); // 목표일에 대한 시간

		SmartLog.getInstance().w(TAG, "TargetDate:" + cal.getTime().toLocaleString());

		long d_time = (eventDay - nowDay) / (60 * 60 * 1000);

		SmartLog.getInstance().w(TAG, "d_time:" + d_time);
		return d_time;
	}



	public static long getDDay(String format, String targetdate) {

		Calendar cal = Calendar.getInstance();
		long nowDay = cal.getTimeInMillis(); // 현재 시간

		SmartLog.getInstance().w(TAG, "Today:" + cal.getTime().toLocaleString());

		SimpleDateFormat	sdf = new SimpleDateFormat(format);
		Date target = null;
		try {
			target = sdf.parse(targetdate);

		} 
		catch (ParseException e) {
			return -1;
		}
		cal.setTime(target);

		long eventDay = cal.getTimeInMillis(); // 목표일에 대한 시간

		SmartLog.getInstance().w(TAG, "TargetDate:" + cal.getTime().toLocaleString());

		long d_day = (eventDay - nowDay) / (24 * 60 * 60 * 1000);

		SmartLog.getInstance().w(TAG, "d_time:" + d_day);
		return d_day;
	}





	/**
	 * 현재 남은 날짜를 구하는 함수 
	 * @param endDate 
	 * @return 남은 시간 Date 
	 */
	public static Date getRemainDateFromNow(Date endDate) {
		Date todayDate = new Date(); // today
		long diff = endDate.getTime() - todayDate.getTime();

		long diffDay = TimeUnit.MILLISECONDS.toDays(diff);
		long diffHour = (TimeUnit.MILLISECONDS.toHours(diff) - TimeUnit.DAYS.toHours(diffDay));
		long diffMin = (TimeUnit.MILLISECONDS.toMinutes(diff) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(diff)));

		SmartLog.getInstance().d(TAG, "++ diffDate = " + diffDay + ":" + diffHour + ":" + diffMin);
		return new Date(endDate.getYear(), endDate.getMonth(), (int) diffDay, (int) diffHour, (int) diffMin);
	}
}
