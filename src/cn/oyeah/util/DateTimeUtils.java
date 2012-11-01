package cn.oyeah.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;

/**
 * 时间格式化工具类
 * @author xiaochen
 *
 */
public class DateTimeUtils {
	/**
	 * "yyyyMMddHHmmss"
	 */
	public static final String PATTERN_DEFAULT = "yyyyMMddHHmmss";
	/**
	 * "yyyy-MM-dd"
	 */
	public static final String PATTERN_DATETIME = "yyyy-MM-dd";
	
	/**
	 * "yyyy-MM-dd hh:mm:ss"
	 */
	public static final String PATTERN_1="yyyy-MM-dd HH:mm:ss";
	
	/**
	 * MM月dd日hh时mm分
	 */
	public static final String PATTERN_2="MM月dd日HH时mm分";
	
	/**
	 * "yyyy-MM-dd hh:mm"
	 */
	public static final String PATTERN_3="yyyy-MM-dd HH:mm";
	
	/**
	 * "yyyyMMdd"
	 */
	public static final String PATTERN_4="yyyyMMdd";
	
	/**
	 * "yyyyMMddHHmm"
	 */
	public static final String PATTERN_5="yyyyMMddHHmm";
	/**
	 * 返回当时间戳,默认格式为yyyyMMddHHmmss
	 * 
	 * @param patternFormat
	 * 
	 *            返回时间格式
	 * @return
	 */
	public static String createTimeId(String patternFormat) {
		Date now = new Date();
		return new SimpleDateFormat(
				StringUtils.isEmpty(patternFormat) ? PATTERN_DEFAULT
						: patternFormat).format(now);
	}

	public static Date parseDate(String datestring, String pattern) {
		try {
			if(datestring==null||datestring.equals("")){
				return null;
			}else
				return new SimpleDateFormat(pattern).parse(datestring);
		} catch (ParseException e) {
			return null;
		}
	}

	public static String formatDate(Date date, String pattern) {
		if(date==null){
			return "";
		}else
			return new SimpleDateFormat(
				StringUtils.isEmpty(pattern) ? PATTERN_DEFAULT : pattern)
				.format(date);
	}
	
	/**
	 * 获取开始时间
	 * @return
	 */
	public static String getStartTime(){
		Calendar ca = Calendar.getInstance();
		ca.setTime(new java.util.Date());
		int year = ca.get(Calendar.YEAR);
		int month = ca.get(Calendar.MONTH);
		//int day = ca.get(Calendar.DAY_OF_MONTH);
		String d= "01";
		String m = "";
		
		if(month < 10){
			month++;
			m = "0"+  month;
		} else {
			m = String.valueOf(month+1);
		}
		String startTime = year + "-" + m + "-" + d;
		return startTime;
	}

	
	/**
	 * 获取结束时间
	 * @return
	 */
	public static String getEndTime(){
		Calendar ca = Calendar.getInstance();
		ca.setTime(new java.util.Date());
		SimpleDateFormat simpledate = new SimpleDateFormat("yyyy-MM-dd");
		String endTime = simpledate.format(ca.getTime());
		//System.out.println(eTime);
		return endTime;
	}
	
	/**
	 * 获取当前日期
	 * @return
	 */
	public static String getCurrentDate(){
		Calendar ca = Calendar.getInstance();
		ca.setTime(new java.util.Date());
		SimpleDateFormat simpledate = new SimpleDateFormat("yyyy-MM-dd");
		String endTime = simpledate.format(ca.getTime());
		//System.out.println(eTime);
		return endTime;
	}

	/**
	 * 获取2011-01-12 00:00:00时间
	 * @return
	 */
	public static String getTime(){
		Calendar ca = Calendar.getInstance();
		ca.setTime(new java.util.Date());
		@SuppressWarnings("unused")
		SimpleDateFormat simpledate = new SimpleDateFormat("yyyy-MM-dd");
		int year = ca.get(Calendar.YEAR);
		int month = ca.get(Calendar.MONTH);
		
		String d= "06";
		String m = "";
		if(month < 10){
			m = "0" + month + 1;
		} else {
			m = String.valueOf(month + 1);
		}
		String startTime = year + "-" + m + "-" + d;
		//System.out.println(sTime);
		return startTime;
	}
	
   public static void main(String[] args)throws Exception{
	   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd",Locale.SIMPLIFIED_CHINESE);
	  // System.out.println(sdf.parse("2012-01-01"));
	   java.util.Date utildate=sdf.parse("2012-02-01");
	   java.sql.Date date=new java.sql.Date(utildate.getTime());
	   System.out.println(date);
   }
}
