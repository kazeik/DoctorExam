package com.kazeik.doctor.doctorexam.utils;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import android.annotation.SuppressLint;

/**
 *
 * @author kazeik.chen QQ:77132995 2014-3-20上午10:01:18 TODO kazeik@163.com
 */
@SuppressLint("SimpleDateFormat")
public class MyDateUtils {
    public final static String TYPE_YMD = "yyyy-MM-dd";
    public final static String TYPE_YMD_SUB = "yyyyMMdd";
    public final static String TYPE_YMDHMS = "yyyy-MM-dd HH:mm:ss";
    public final static String TYPE_YMD_EEEE = "yyyy-MM-dd EEEE";
    public final static String TYPE_HHMM = "HH:mm";
    public final static String TYPE_HHMMSS = "HH:mm:ss";

    public final static String UTC_DATE = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";

    public static String getDate() {
        SimpleDateFormat f2 = new SimpleDateFormat(TYPE_YMD);
        Date now = new Date();
        return f2.format(now);
    }

    public static String getDate(long time) {
        SimpleDateFormat f2 = new SimpleDateFormat(TYPE_YMD);
        Date now = new Date();
        now.setTime(time);
        return f2.format(now);
    }

    public static String getTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    public static String getDate(long time, String type) {
        SimpleDateFormat f2 = new SimpleDateFormat(type);
        Date now = new Date();
        now.setTime(time);
        return f2.format(now);
    }

    public static String getDate(String type) {
        SimpleDateFormat f2 = new SimpleDateFormat(type);
        Date now = new Date();
        return f2.format(now);
    }

    public static String changeUTC(String dateStr){
        try {
           return new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").parse(dateStr).getTime()).toString();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static Date getDateForStr(String dateString) {
        SimpleDateFormat sdf = new SimpleDateFormat(TYPE_YMDHMS);
        try {
            return sdf.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Date strToDate(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat(TYPE_YMD);
        ParsePosition pos = new ParsePosition(0);
        return formatter.parse(strDate, pos);
    }

    public static Date strToDate(String strDate, String type) {
        SimpleDateFormat formatter = new SimpleDateFormat(type);
        ParsePosition pos = new ParsePosition(0);
        return formatter.parse(strDate, pos);
    }

    /**
     * 根据一个日期，返回是星期几的字符串
     *
     * @param sdate
     * @return
     */
    public static String getWeek(String sdate) {
        // 再转换为时间
        Date date = strToDate(sdate);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        // 1=星期日 7=星期六，其他类推
        return new SimpleDateFormat("EEEE").format(c.getTime());
    }

    public static int getIndexWeek(String strDate) {
        Date date = strToDate(strDate);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 根据当前日期计算当前之前多少天中每天的日期
     *
     * @param len
     *            总数据长度
     * @param currentDate
     *            当前时间
     * @author kazeik.chen QQ:77132995 2015-3-24下午3:29:46 TODO kazeik@163.com
     */
    public static ArrayList<String> checkDateForLastTime(int len,
                                                         String currentDate) {
        ArrayList<String> allDays = new ArrayList<String>();
        Date date = MyDateUtils.strToDate(currentDate);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        long currentTime = calendar.getTimeInMillis();// 当前时间
        long dayTimes = 1000 * 60 * 60 * 24; // 24小时所占毫秒
        // long beiTian = 100*dayTimes;//100天所占的毫秒
        // long beiTianTimes = currentTime - beiTian; //一百天前的毫秒
        // System.out.println(getDate(beiTianTimes)); //一百前天的时间

        for (int i = len; i > 0; i--) {
            long tempTime = i * dayTimes;
            long beiTianTimes = currentTime - tempTime; //
            allDays.add(MyDateUtils.getDate(beiTianTimes));
        }
        return allDays;
    }

    /**
     * 得到当月第一天日期
     *
     * @return
     * @author kazeik.chen QQ:77132995 2015-3-24下午3:55:08 TODO kazeik@163.com
     */
    public static String getFristDayForCurrentMonth() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
        return format.format(c.getTime());
    }

    /**
     * 得到当月最后一天的日期
     *
     * @return
     * @author kazeik.chen QQ:77132995 2015-3-24下午3:55:44 TODO kazeik@163.com
     */
    public static String getLastDayForCurrentMonth() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.DAY_OF_MONTH,
                ca.getActualMaximum(Calendar.DAY_OF_MONTH) + 1);
        return format.format(ca.getTime());
    }

    public static Date getDateForTime(long time) {
        Date now = new Date();
        now.setTime(time);
        return now;
    }

    /**
     * 获取当前日期是星期几<br>
     *
     * @param dt
     * @return 当前日期是星期几
     */
    public static int getWeekOfDateIndex(Date dt) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK);
        if (w < 0)
            w = 0;
        return w;
    }

    public static String getWeekOfDateStr(Date dt) {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        int w = getWeekOfDateIndex(dt);
        return weekDays[w];
    }

    public static String getWeekOfDate() {
        Date date = new Date();
        SimpleDateFormat dateFm = new SimpleDateFormat("EEEE");
        return dateFm.format(date);
    }

    public static int getNowYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    public static int getNowMonth() {
        return Calendar.getInstance().get(Calendar.MONTH) + 1;
    }

    public static int getNowDay() {
        return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    }

    public static int getNowHour() {
        return Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
    }

    public static int getNowMin() {
        return Calendar.getInstance().get(Calendar.MINUTE);
    }

    public static int getNowSecond() {
        return Calendar.getInstance().get(Calendar.SECOND);
    }

    public static String getNowTime() {
        return formatTime(getNowYear(), getNowMonth(), getNowDay(),
                getNowHour(), getNowMin(), getNowSecond());
    }

    public static String getNowTime2() {
        return formatTime2(getNowYear(), getNowMonth(), getNowDay(),
                getNowHour(), getNowMin());
    }

    public static String getToday() {
        return formatTime(getNowYear(), getNowMonth(), getNowDay(), 0, 0, 0);
    }

    public static String formatDate(int year, int month, int day) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        Date date = calendar.getTime();
        return dateFormat.format(date);
    }

    public static String formatTime(int year, int month, int day, int hour,
                                    int min, int sec) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(TYPE_YMDHMS);
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day, hour, min, sec);
        Date date = calendar.getTime();
        return dateFormat.format(date);
    }

    public static String formatTime2(int year, int month, int day, int hour,
                                     int min) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day, hour, min);
        Date date = calendar.getTime();
        return dateFormat.format(date);
    }

    public static Calendar getCalendarByDate(Date date) {
        Calendar calender = Calendar.getInstance();
        calender.setTime(date);
        return calender;
    }

    /**
     *
     * 功能描述：获取时间在当前星期的第几天
     *
     *
     * @param date
     * @return 返回星期数, 其中: 1表示星期一, ...7表示星期天
     *
     */
    public static int getTimeDateOfWeek(Date date) {
        Calendar calender = Calendar.getInstance();
        calender.setTime(date);
        int week = calender.get(Calendar.DAY_OF_WEEK) - 1;
        if (week == 0)
            week = 7;
        return week;
    }

    /**
     *
     * 功能描述：获取时间在当前星期的第几天
     *
     * @param date
     * @return 返回星期数, 其中: 7表示星期六..1表示星期天
     *
     */
    public static int getQuartzTimeDateOfWeek(Date date) {
        Calendar calender = Calendar.getInstance();
        calender.setTime(date);
        int week = calender.get(Calendar.DAY_OF_WEEK);
        return week;
    }

    /**
     *
     * @Description:某个时间与当前时间进行求差
     * @return
     * @ReturnType long
     * @author:
     * @Created 2012 2012-12-12下午01:00:53
     */
    public static long getAppointTimeDifference(Date startDate, Date endDate) {
        long l = endDate.getTime() - startDate.getTime();
        long day = l / (24 * 60 * 60 * 1000);
        return day;
    }

    /**
     *
     * @Description:将某种日期转换成指定格式的日期数据 <获取相应格式的Date>
     * @param date
     *            需要格式的日期参数
     * @param pattern
     *            日期格式
     * @ReturnType Date
     * @author:
     * @Created 2012 2012-9-20上午09:08:24
     */
    public static Date getFormatDate(Date date, String pattern) {
        if (date == null) {
            throw new IllegalArgumentException("date can not be null!");
        }
        if (pattern == null) {
            pattern = TYPE_YMD;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String dateStr = sdf.format(date);
        try {
            return sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     *
     * @Description:获取当天日期，日期格式为YYYY-MM-DD HH:mm:ss
     * @return
     * @ReturnType Date
     * @author:
     * @Created 2012 2012-9-20上午09:58:36
     */
    public static Date getCurrentlyDate() {
        Date currentDate = new Date();
        return getFormatDate(currentDate, TYPE_YMDHMS);
    }

    /**
     *
     * @Description:某个时间与当前时间进行求差
     * @param date
     * @return
     * @ReturnType long
     * @author:
     * @Created 2012 2012-12-12下午01:00:53
     */
    public static long getTimeDifference(Date date) {
        /** 获取当前系统时间 **/
        Date currentlyDate = getCurrentlyDate();
        long l = date.getTime() - currentlyDate.getTime();
        long day = l / (24 * 60 * 60 * 1000);
        long hour = (l / (60 * 60 * 1000) - day * 24);
        long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        System.out.println("" + day + "天" + hour + "小时" + min + "分" + s + "秒");
        return day;
    }

    /**
     * 获取过去每一个月的年月
     *
     * @param len
     *            总月份，即过去多少个月
     * @author kazeik.chen QQ:77132995 2015-3-25上午11:15:44 TODO kazeik@163.com
     */
    public static ArrayList<String> getLastMonth(int len) {
        ArrayList<String> allMonth = new ArrayList<String>();
        Calendar c = Calendar.getInstance();
        for (int i = 0; i < len; i++) {
            c.add(Calendar.MONTH, -1);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
            String payMonth = sdf.format(c.getTime());
            System.out.println(payMonth);
            allMonth.add(payMonth);
        }
        return allMonth;
    }

    /**
     * 得到当前月份里有多少天
     *
     * @param fomartStr
     *            如"2011-4"
     */
    public static int getNumOfMonth(String fomartStr) {
        // 获得当月天数
        Calendar cal = new GregorianCalendar();
        SimpleDateFormat oSdf = new SimpleDateFormat("yyyy-MM");
        try {
            cal.setTime(oSdf.parse(fomartStr));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
    }
}
