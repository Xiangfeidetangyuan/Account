package com.example.account.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {


    /**
     * 时间戳 转 日期   精确到秒
     * @param timeStamp   时间戳
     * @param format      转换格式    "yyyy-MM-dd HH:mm:ss"  根据需要传入
     * @return
     */
    public static String timeStamp_Date(long timeStamp,String format) {
        if(format == null || format.isEmpty()){
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(timeStamp);
    }

    /**
     * 日期 转 时间戳
     * @param date_str
     * @param format
     * @return
     */
    public static long date_TimeStamp(String date_str,String format){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.parse(date_str).getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     *  返回 该月的 开始时间戳
     * @param year
     * @param month
     * @return
     */
    public  static  long getStartTimeStampByMonth(int year,int month){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  //获取格式化格式
        Date nowtime = new Date();     //获取当前日期
        Calendar c = Calendar.getInstance();
        c.setTime(nowtime);         //塞入当前日期
        //将时分秒设置成0，便于格式获取，若格式的为yyyy-MM-dd则不需要
        c.set(year, month-1,1,0,0,0);
        return c.getTimeInMillis();
    }

    /**
     *  返回 该月的 结束时间戳
     * @param year
     * @param month
     * @return
     */
    public static   long getEndTimeStampByMonth(int year,int month){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  //获取格式化格式
        Date nowtime = new Date();     //获取当前日期
        Calendar c = Calendar.getInstance();
        c.setTime(nowtime);         //塞入当前日期
        c.set(Calendar.YEAR,year);
        c.set(Calendar.HOUR_OF_DAY, 0);   //将时分秒设置成0，便于格式获取，若格式的为yyyy-MM-dd则不需要
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.DAY_OF_MONTH,1); //下一个月 1号 零点
        c.set(Calendar.MONTH,month);
        return c.getTimeInMillis();
    }

    /**
     * 返回 该天的开始时间
     * @param day
     * @return
     */
    public static long getStartTimeStampByDay(int year,int month,int day){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  //获取格式化格式
        Date nowtime = new Date();     //获取当前日期
        Calendar c = Calendar.getInstance();
        c.setTime(nowtime);         //塞入当前日期
        c.set(Calendar.YEAR,year);
        c.set(Calendar.HOUR_OF_DAY, 0);   //将时分秒设置成0，便于格式获取，若格式的为yyyy-MM-dd则不需要
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.DAY_OF_MONTH,day);
        c.set(Calendar.MONTH,month-1);
        return c.getTimeInMillis();
    }

    /**
     * 返回 该天的 结束时间戳
     * @param year
     * @param month
     * @param day
     * @return
     */
    public static long getEndTimeStampByDay(int year,int month,int day){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  //获取格式化格式
        Date nowtime = new Date();     //获取当前日期
        Calendar c = Calendar.getInstance();
        c.setTime(nowtime);         //塞入当前日期
        c.set(year,month-1,day+1,0,0,0);
        return c.getTimeInMillis();
    }

}
