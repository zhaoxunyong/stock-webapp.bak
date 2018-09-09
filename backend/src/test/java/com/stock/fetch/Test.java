package com.stock.fetch;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Test {

    public static Date getWeekStartDate() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        Date date = cal.getTime();
        return date;
    }

    // 获取周第一天
    public static Date getStartDayOfWeek(LocalDate now) {
//        LocalDate now = LocalDate.parse(date);
        return getStartDayOfWeeks(now);
    }

    public static Date getStartDayOfWeeks(TemporalAccessor date) {
        TemporalField fieldISO = WeekFields.of(Locale.CHINA).dayOfWeek();
        LocalDate localDate = LocalDate.from(date);
        localDate.with(fieldISO, 1);
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public static void main(String[] args) throws IOException {
        // DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        // LocalDate localDate = LocalDate.parse("2018-06-07 17:30:30", pattern);
        // 取上一周
        LocalDate localDate = LocalDate.now().plusWeeks(-1);
        System.out.println("localDate: " + localDate);
//        System.out.println("ISO_LOCAL_DATE: " + localDate.format(DateTimeFormatter.ISO_LOCAL_DATE));
        System.out.println("当周第一天：" + localDate.with(WeekFields.of(Locale.CHINA).dayOfWeek(), 1));
        System.out.println("当周第7天：" + localDate.with(WeekFields.of(Locale.CHINA).dayOfWeek(), 7));
        System.out.println("当月第一天：" + localDate.with(TemporalAdjusters.firstDayOfMonth()));
        System.out.println("当月最后一天：" + localDate.with(TemporalAdjusters.lastDayOfMonth()));
        
//        System.out.println("当前年2月最后一天： " + localDate.withMonth(2).with(TemporalAdjusters.lastDayOfMonth()));
//        // 下周的日期两个方式，日、月、年类似
//        System.out.println("下周日期： " + localDate.plus(1, ChronoUnit.WEEKS));
//        System.out.println("下周日期： " + localDate.plusWeeks(1));
//        System.out.println("2018年1月第一个星期天： " + LocalDate.of(2018, 1, 1).with(TemporalAdjusters.firstInMonth(DayOfWeek.SUNDAY)));
    }
}
