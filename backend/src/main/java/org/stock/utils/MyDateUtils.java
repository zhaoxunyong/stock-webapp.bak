package org.stock.utils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * https://github.com/tank2140896/JavaWeb/blob/master/src/main/java/com/javaweb/util/common/DateUtil.java
 * https://my.oschina.net/mcyy568/blog/809608
 * 
 * @author Dave.zhao
 * @date 2018/09/07
 */
public class MyDateUtils {

    /*public static boolean isWeekend(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            return true;
        }
        return false;
    }

    public static Date getNextNatureWorkDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        if (cal.get(Calendar.DAY_OF_WEEK) == 7) {
            return DateUtils.addDays(date, 1);
        } else if (cal.get(Calendar.DAY_OF_WEEK) == 6) {
            return DateUtils.addDays(date, 1);
        }
        return date;
    }*/
    
    // 判断给定的字符串日期是否是周末(周六和周日)
    public static boolean isWeekends(LocalDate localDate) {
        int value = localDate.getDayOfWeek().getValue();
        // 周六(6)和周日(7)
        if (value == 6 || value == 7) {
            return true;
        }
        return false;
    }

    public static LocalDate getNextNatureWorkDay(LocalDate localDate) {
        int value = localDate.getDayOfWeek().getValue();
        if (value == 7) {
            return localDate.plusDays(1);
        } else if (value == 6) {
            return localDate.plusDays(2);
        }
        return localDate;
    }

    public static Date getNextNatureWorkDay(Date date) {
        LocalDate localDate = date2LoalDate(date);
        return localDate2Date(getNextNatureWorkDay(localDate));
    }
    
    public static Date localDate2Date(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
    
    public static LocalDate date2LoalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
    
    public static LocalDate getFirstDayOfWeek(LocalDate localDate) {
        return localDate.with(WeekFields.of(Locale.CHINA).dayOfWeek(), 1);
    }
    
    public static LocalDate getLastDayOfWeek(LocalDate localDate) {
        return localDate.with(WeekFields.of(Locale.CHINA).dayOfWeek(), 7);
    }
    
    public static LocalDate getFirstDayOfMonth(LocalDate localDate) {
        return localDate.with(TemporalAdjusters.firstDayOfMonth());
    }
    
    public static LocalDate getLastDayOfMonth(LocalDate localDate) {
        return localDate.with(TemporalAdjusters.lastDayOfMonth());
    }
    
    public static void main(String[] args) {
        System.out.println(getNextNatureWorkDay(LocalDate.of(2018, 7, 1)));
    }

}
