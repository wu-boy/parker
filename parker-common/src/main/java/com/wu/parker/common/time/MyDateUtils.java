package com.wu.parker.common.time;

import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间工具类
 * @author wusq
 * @date 2018/12/3
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

    public static void main(String[] args) {

    }
}
