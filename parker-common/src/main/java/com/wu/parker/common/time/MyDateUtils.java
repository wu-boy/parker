package com.wu.parker.common.time;

import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 时间工具类
 * @author wusq
 * @date 2019/4/20
 */
public class MyDateUtils {

    public static final String FORMAT_DAY = "yyyy-MM-dd";
    public static final String FORMAT_TIME = "yyyy-MM-dd HH:mm:ss";

    public static final String DAY = "day";
    public static final String WEEK = "week";
    public static final String MONTH = "month";
    public static final String YEAR = "year";

    /**
     * 根据结束时间获取开始时间
     * @param endDate 结束时间
     * @param timeUnit 时间单位
     * @return
     */
    public static Date getBeginDate(Date endDate, String timeUnit){
        Date result = endDate;
        if(DAY.equals(timeUnit)){
            return result;
        }else if(WEEK.equals(timeUnit)){
            result = DateUtils.addDays(result, -6);
        }else if(MONTH.equals(timeUnit)){
            Date sameDay = DateUtils.addMonths(result, -1);
            result = DateUtils.addDays(sameDay, 1);
        }else if(YEAR.equals(timeUnit)){
            Date sameDay = DateUtils.addYears(result, -1);
            result = DateUtils.addDays(sameDay, 1);
        }
        return result;
    }

    /**
     * 清除时分秒
     * @param date
     * @return
     */
    public static Date clearHms(Date date){
        Date result = date;
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DAY);
        try {
            result = sdf.parse(sdf.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 获取日期是周几
     * @param date
     * @return
     */
    public static Integer getWeekDay(Date date){
        int result = 0;
        if(date == null){
            return result;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        result = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        return result;
    }

    /**
     * 判断是否是工作日
     * @param date
     * @return
     */
    public static boolean isWorkDay(Date date){
        Boolean result = null;
        if(date == null){
            return result;
        }
        int weekday = getWeekDay(date);
        if(1 <= weekday && weekday <= 5){
            result = Boolean.TRUE;
        }else{
            result = Boolean.FALSE;
        }
        return result;
    }

    /**
     * 获取某日期下一个工作日
     * @param date
     * @return Date
     */
    public static Date getNextWorkDate(Date date){
        Date result = null;
        if(date == null){
            return result;
        }
        while(Boolean.TRUE){
            date = DateUtils.addDays(date, 1);
            if(isWorkDay(date)){
                result = date;
                break;
            }
        }
        return result;
    }

    /**
     * 获取某日期下一个工作日
     * @param date
     * @return String
     */
    public static String getNextWorkDay(Date date){
        String result = null;
        Date nextWorkDate = getNextWorkDate(date);
        if(nextWorkDate != null){
            SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DAY);
            result = sdf.format(nextWorkDate);
        }
        return result;
    }

    /**
     * 获取当月第一天的日期
     * @return string
     */
    public static String getMonthFirstDay(){
        String result = null;
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DAY);
        result = sdf.format(calendar.getTime());
        return result;
    }

    /**
     * 获取当月第一天的日期
     * @return date
     */
    public static Date getMonthFirstDate(){
        Date result = null;
        String day = getMonthFirstDay();
        SimpleDateFormat sdf = new SimpleDateFormat(MyDateUtils.FORMAT_DAY);
        try {
            result = sdf.parse(day);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 获取两个日期之间的所有日期
     * @param beginDate 开始日期
     * @param endDate 结束日期
     * @return
     */
    public static List<Date> listDates(Date beginDate, Date endDate){
        List<Date> result = new ArrayList<>();

        // 需要将日期的时分秒清零
        beginDate = clearHms(beginDate);
        endDate = clearHms(endDate);

        result.add(beginDate);
        Date tmp = beginDate;
        Calendar calendar = Calendar.getInstance();
        while (tmp.before(endDate)){
            calendar.setTime(tmp);
            calendar.add(Calendar.DATE, 1);
            result.add(calendar.getTime());
            tmp = calendar.getTime();
        }
        return result;
    }

    /**
     * 获取两个日期之间的所有日期
     * @param beginDate 开始日期
     * @param endDate 结束日期
     * @return
     */
    public static List<String> listDays(Date beginDate, Date endDate){
        List<String> result = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DAY);
        List<Date> dateList = listDates(beginDate, endDate);
        dateList.forEach(o -> result.add(sdf.format(o)));
        return result;
    }

    /**
     * 获取两个日期之间的所有日期
     * @param beginDay 开始日期
     * @param endDay 结束日期
     * @return
     */
    public static List<String> listDays(String beginDay, String endDay){
        List<String> result = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DAY);
        try {
            List<Date> dateList = listDates(sdf.parse(beginDay), sdf.parse(endDay));
            dateList.forEach(o -> result.add(sdf.format(o)));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) throws Exception{
        System.out.println(getMonthFirstDay());
    }
}
