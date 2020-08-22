package com.example.tiims.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 *
 * @author qq1962247851
 * @date 2020/4/26 17:39
 */
public class DateUtil {

    private static Calendar calendar = Calendar.getInstance();

    public static Long getTodayStartTime() {
        Calendar calendar = Calendar.getInstance();
        generateCalender(calendar, 0, 0, 0, 0);
        return calendar.getTimeInMillis();
    }

    public static Long getYesterdayDate() {
        Calendar calendar = Calendar.getInstance();
        generateCalender(calendar, 0, 0, 0, 0);
        calendar.add(Calendar.DATE, -1);
        return calendar.getTimeInMillis();
    }

    private static void generateCalender(Calendar calendar, int hourOfDay, int minute, int second, int millisecond) {
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        calendar.set(Calendar.MILLISECOND, millisecond);
    }

    public static Long getTodayEndTime() {
        Calendar calendar = Calendar.getInstance();
        generateCalender(calendar, 23, 59, 59, 999);
        return calendar.getTimeInMillis();
    }

    public static Long getOnlyDate(Long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        generateCalender(calendar, 0, 0, 0, 0);
        return calendar.getTimeInMillis();
    }

    public static String timeInMillis2String(Long timeInMillis, String pattern) {
        if (timeInMillis == null) {
            return null;
        }
        return new SimpleDateFormat(pattern).format(new Date(timeInMillis));
    }

    public static String timeInMillis2String(Long timeInMillis) {
        return timeInMillis2String(timeInMillis, "yyyy/MM/dd HH:mm:ss");
    }

    public static Date string2Date(String timeString, String pattern) {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        try {
            return formatter.parse(timeString);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static Date string2Date(String timeString) {
        return string2Date(timeString, "yyyy/MM/dd HH:mm:ss");
    }

    public static Integer getOnlyYear(Long timeInMillis) {
        calendar.setTimeInMillis(timeInMillis);
        return calendar.get(Calendar.YEAR);
    }

    public static void main(String[] args) {
        System.out.println(getYesterdayDate());
    }

}
