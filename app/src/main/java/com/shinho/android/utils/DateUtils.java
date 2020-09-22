package com.shinho.android.utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 日期时间工具类
 */
public class DateUtils {

    public static final long ONE_SECOND_MILLIONS = 1000;
    public static final long ONE_MINUTE_MILLIONS = 60 * ONE_SECOND_MILLIONS;
    public static final long ONE_HOUR_MILLIONS = 60 * ONE_MINUTE_MILLIONS;
    public static final long ONE_DAY_MILLIONS = 24 * ONE_HOUR_MILLIONS;

    // 日期格式为 2016-02-03 17:04:58
    public static final String PATTERN_DATE = "yyyy.M.d";
    public static final String PATTERN_APP_SHORT_DATE = "yy.M.d";
    public static final String PATTERN_SERVER_DATE = "yyyy-MM-dd";
    public static final String PATTERN_TIME = "HH:mm";
    public static final String PATTERN_SPLIT = " ";
    public static final String PATTERN = PATTERN_DATE + PATTERN_SPLIT + PATTERN_TIME;
    public static final String CAR_PATTERN_MINUTE = "yyyy.MM.dd HH:mm";
    public static final String PATTERN_FULL = "yyyy-MM-dd HH:mm:ss";

    /**
     * 计算天数差
     */
    public static int calculateDayDiff(Calendar targetTime, Calendar compareTime) {
        // 重置时分秒
        targetTime.set(Calendar.HOUR_OF_DAY, 0);
        targetTime.set(Calendar.MINUTE, 0);
        targetTime.set(Calendar.SECOND, 0);

        compareTime.set(Calendar.HOUR_OF_DAY, 0);
        compareTime.set(Calendar.MINUTE, 0);
        compareTime.set(Calendar.SECOND, 0);

        long targetTimeInMillis = targetTime.getTimeInMillis();
        long compareTimeInMillis = compareTime.getTimeInMillis();

        if (targetTimeInMillis > compareTimeInMillis) {
            return (int) (1f * (targetTimeInMillis - compareTimeInMillis) / ONE_DAY_MILLIONS + 0.5f);
        } else {
            return -(int) (1f * (compareTimeInMillis - targetTimeInMillis) / ONE_DAY_MILLIONS + 0.5f);
        }
    }




    /**
     * 计算天数差
     */
    public static float calculateDayDiffDetail(Calendar targetTime, Calendar compareTime) {
        // 重置时分秒
        targetTime.set(Calendar.HOUR_OF_DAY, 0);
        targetTime.set(Calendar.MINUTE, 0);
        targetTime.set(Calendar.SECOND, 0);

        compareTime.set(Calendar.HOUR_OF_DAY, 0);
        compareTime.set(Calendar.MINUTE, 0);
        compareTime.set(Calendar.SECOND, 0);

        long targetTimeInMillis = targetTime.getTimeInMillis();
        long compareTimeInMillis = compareTime.getTimeInMillis();

        return 1f * (targetTimeInMillis - compareTimeInMillis) / ONE_DAY_MILLIONS;
    }

    /**
     * 判断Calendar是否是今天
     */
    public static boolean isToday(Calendar targetTime) {
        return isSameDay(Calendar.getInstance(), targetTime);
    }

    /**
     * 判断时间戳是否是今天
     */
    public static boolean isToday(long targetTime) {
        Calendar targetCalendar = Calendar.getInstance();
        targetCalendar.setTimeInMillis(targetTime);
        return isToday(targetCalendar);
    }

    /**
     * 判断两个时间戳是否为同一天
     */
    public static boolean isSameDay(long targetTime, long compareTime) {
        Calendar targetCalendar = Calendar.getInstance();
        targetCalendar.setTimeInMillis(targetTime);

        Calendar compareCalendar = Calendar.getInstance();
        compareCalendar.setTimeInMillis(compareTime);

        return isSameDay(targetCalendar, compareCalendar);
    }

    /**
     * 判断两个Calendar是否为同一天
     */
    public static boolean isSameDay(Calendar targetTime, Calendar compareTime) {
        return targetTime.get(Calendar.YEAR) == compareTime.get(Calendar.YEAR) &&
                targetTime.get(Calendar.DAY_OF_YEAR) == compareTime.get(Calendar.DAY_OF_YEAR);
    }

    /**
     * 判断两个Calendar是否为同一个月
     */
    public static boolean isSameMonth(Calendar targetTime, Calendar compareTime) {
        return targetTime.get(Calendar.YEAR) == compareTime.get(Calendar.YEAR) &&
                targetTime.get(Calendar.MONTH) == compareTime.get(Calendar.MONTH);
    }


    /**
     * 将一个时间String解析为一个Data
     */
    public static Date str2date(String str, String format) {
        Date date = null;
        try {
            if (str != null) {
                SimpleDateFormat sdf = new SimpleDateFormat(format);
                date = sdf.parse(str);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 按照 yyyy.M.d HH:mm 将String解析为Date
     * @param str
     * @return
     */

    public static Date str2date(String str) {
        return str2date(str, PATTERN);
    }

    /**
     * 按照 yyyy.M.d HH:mm 将Date输出为String
     */
    public static String date2str(Date date) {
        return date2str(date, PATTERN);
    }

    /**
     * 按照format格式返回Date
     */
    public static String date2str(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.CHINA);
        return sdf.format(date);
    }

    /**
     * 按照 yyyy.M.d HH:mm 将String解析为Calendar
     */
    public static Calendar str2calendar(String str) {
        Calendar calendar = null;
        Date date = str2date(str);
        if (date != null) {
            calendar = Calendar.getInstance();
            calendar.setTime(date);
        }
        return calendar;
    }


    /**
     * 按照指定format，将String解析为Calendar
     */
    public static Calendar str2calendar(String str, String format) {
        Calendar calendar = null;
        Date date = str2date(str, format);
        if (date != null) {
            calendar = Calendar.getInstance();
            calendar.setTime(date);
        }
        return calendar;
    }

    /**
     * 按照 yyyy.M.d HH:mm 将Calendar输出为String
     */
    public static String calendar2str(Calendar calendar) {
        return date2str(calendar.getTime());
    }

    /**
     * 按照 yyyy.M.d 将String时间戳输出为String
     */
    public static String timeInMillsToDayStr(String timeInMills) {
        Date date = new Date(Long.parseLong(timeInMills));
        return date2str(date, PATTERN_DATE);
    }

    /**
     * 按照 yyyy.M.d 将Long时间戳输出为String
     */
    public static String timeInMillsToDayStr(Long timeInMills) {
        if (timeInMills == null) return null;
        return date2str(new Date(timeInMills), PATTERN_DATE);
    }

    /**
     * 按照 yyyy.M.d HH:mm 将String时间戳输出为String
     */
    public static String timeInMillsToDayMinuteStr(String timeInMills) {
        try {
            Date date = new Date(Long.parseLong(timeInMills));
            return date2str(date);
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 按照指定pattern将String时间戳输出为String
     */
    public static String timeInMillsToStr(String timeInMills, String pattern) {
        try {
            Date date = new Date(Long.parseLong(timeInMills));
            return date2str(date, pattern);
        } catch (Exception e) {
            return "";
        }

    }

    /**
     * 按照 yyyy.M.d HH:mm 将Long时间戳输出为String
     */
    public static String timeInMillsToDayMinuteStr(Long timeInMills) {
        if (timeInMills == null) return null;
        return date2str(new Date(timeInMills));
    }


    /**
     * 按照指定format将Long时间戳输出为String
     */
    public static String calendar2str(Calendar calendar, String format) {
        return date2str(calendar.getTime(), format);
    }

    /**
     * 按照 yyyy.M.d HH:mm - yyyy.M.d HH:mm 将两个Long时间戳输出为String
     */
    public static String periodCalendar2Str(Long startTimeInMills, Long endTimeInMills, String pattern) {
        if (startTimeInMills == null || endTimeInMills == null) return "";

        SimpleDateFormat df = new SimpleDateFormat(pattern, Locale.getDefault());
        return String.format("%s - %s", df.format(new Date(startTimeInMills)),
                df.format(new Date(endTimeInMills)));
    }


    /**
     * 将字符串由yyyy-MM-dd格式转化为yyyy.M.d格式
     */
    public static String ServerDateString2App(String serverString) {
        return dateStr2Str(PATTERN_SERVER_DATE, PATTERN_DATE, serverString);
    }

    /**
     * 将字符串由yyyy.M.d格式转化为yyyy-MM-dd格式
     */
    public static String AppDateString2Server(String appString) {
        return dateStr2Str(PATTERN_DATE, PATTERN_SERVER_DATE, appString);
    }

    /**
     * 将字符串由fromPattern格式转化为toPattern
     */
    public static String dateStr2Str(String fromPattern, String toPattern, String fromString) {
        try {
            Date date = new SimpleDateFormat(fromPattern, Locale.getDefault())
                    .parse(fromString);
            return new SimpleDateFormat(toPattern, Locale.getDefault()).format(date);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 将String按照yyyy-MM-dd格式解析为Date
     */
    public static Date serverDateString2Date(String serverString) {
        try {
            return new SimpleDateFormat(PATTERN_SERVER_DATE, Locale.getDefault())
                    .parse(serverString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     *
     * 将String按照yyyy-MM-dd格式解析为Calendar
     */
    public static Calendar serverDateString2Calendar(String serverString) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(serverDateString2Date(serverString));
        return cal;
    }

    /**
     *
     * 获取今天0点0分0秒的Calendar
     */
    public static Calendar getDayCalendar() {
        return clearToDayCalendar(Calendar.getInstance());
    }

    /**
     * 将Calendar的时分秒毫秒置为零
     */
    public static Calendar clearToDayCalendar(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar;
    }

    /**
     *
     *获取到本月1日0点0分0秒0毫秒的Calendar
     */
    public static Calendar getMonthCalendar() {
        return clearToMonthCalendar(Calendar.getInstance());
    }

    /**
     * 将long时间戳转化为Calendar
     */
    public static Calendar getCalendar(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar;
    }

    /**
     * 将String时间戳转化为Calendar
     */
    public static Calendar getCalendar(String millis) {
        try {
            return getCalendar(Long.parseLong(millis));
        } catch (Exception e) {
            e.printStackTrace();
            return Calendar.getInstance();
        }
    }


    /**
     * 将Calendar信息重置到某月1日
     */
    public static Calendar clearToMonthCalendar(Calendar calendar) {
        clearToDayCalendar(calendar).set(Calendar.DAY_OF_MONTH, 1);
        return calendar;
    }

    /**
     * 将时间String按照指定pattern解析后以yyyy.M.d HH:mm输出
     */
    public static String removeSecond(String timeStr, String pattern) {
        Calendar calendar = str2calendar(timeStr, pattern);
        if (calendar == null) {
            return timeStr;
        }
        return calendar2str(calendar, PATTERN);
    }
    /**
     * 将时间String按照yyyy-MM-dd HH:mm:ss解析后以yyyy.M.d HH:mm输出
     */
    public static String removeSecondAio(String timeStr) {
        return removeSecond(timeStr, PATTERN_FULL);
    }

}
