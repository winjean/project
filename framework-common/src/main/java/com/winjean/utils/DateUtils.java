package com.winjean.utils;

import com.winjean.enums.DateTimeEnum;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public final class DateUtils {

    /**
     * 日期时间格式 转换成  参数设定或系统特定格式的字符串
     *
     * @param type 传入的转换成的日期格式   , DateUtils.TimeType中有一部分日期时间格式
     * @return 字符串类型
     * <p>
     * 示例：DateUtils.dd2(new Date(),DateUtils.TimeType.type2.getValue()) 或者 DateUtils.dd2(new Date(),"yyyy-MM-dd H:m:s") ;
     */
    public static String getSbyDT(Date date, String type) {
        try {
            return getDateFormat(type).format(date);
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * 获取当前系统时间
     *
     * @return
     */
    public static Date getDateTime() {
        Calendar calendar = Calendar.getInstance();
        return calendar.getTime();
    }



    /**
     * 用指定的年、月、日构造日期对象
     *
     * @param year  年
     * @param month 月
     * @param day   日
     * @return 日期对象
     */
    public static Date getDate(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day);
        return calendar.getTime();
    }

    /**
     * 设为严格格式
     *
     * @param type 传入的格式
     * @return SimpleDateFormat的对象
     */
    public static DateFormat getDateFormat(String type) {
        DateFormat dateFormat = new SimpleDateFormat(type,Locale.CHINA);
        dateFormat.setLenient(false);
        return dateFormat;
    }

    /**
     * 计算时间
     *
     * @param date   日期
     * @param field  类型 如按秒计算为： Calendar.SECOND
     * @param amount 计算量
     * @return
     * @throws Exception 例子：计算当前时间的前10秒的时间？
     *                   TimeCalculate(new Date(), Calendar.SECOND, -10)
     *                   例子：计算当前时间的后10秒的时间？
     *                   TimeCalculate(new Date(), Calendar.SECOND, 10)
     */
    public static Date TimeCalculate(Date date, int field, int amount) {
        try {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(field, amount);
            return cal.getTime();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 取得当月天数
     */
    public static int getCurrentMonthLastDay() {
        Calendar a = Calendar.getInstance();
        a.set(Calendar.DATE, 1);//把日期设置为当月第一天
        a.roll(Calendar.DATE, -1);//日期回滚一天，也就是最后一天
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }

    /**
     * 得到指定月的天数
     */
    public static int getMonthLastDay(int year, int month) {
        Calendar a = Calendar.getInstance();
        a.set(Calendar.YEAR, year);
        a.set(Calendar.MONTH, month - 1);
        a.set(Calendar.DATE, 1);//把日期设置为当月第一天
        a.roll(Calendar.DATE, -1);//日期回滚一天，也就是最后一天
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }

    public static String getDateTime(Date date,String parrent) {
        SimpleDateFormat format = new SimpleDateFormat(parrent, Locale.CHINA);
        return format.format(date);
    }

    public static String getDateTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat(DateTimeEnum.dateTime1.getValue(), Locale.CHINA);
        return format.format(date);
    }

    public static String getDateTime(String parrent) {
        Calendar c = Calendar.getInstance(Locale.CHINESE);
        return getDateTime(c.getTime(),parrent);
    }

    /**
     * 格式化时间 date转string
     *
     * @param date
     * @return
     */
    public static String formatDate(Date date) {
        return formatDate(date,DateTimeEnum.dateTime1);
    }

    public static String formatDate(Date date,DateTimeEnum dte) {
        SimpleDateFormat format = new SimpleDateFormat(dte.getValue(), Locale.CHINA);
        return format.format(date);
    }

    public static String getDatetime(String datetime, String parrent, int field, int offset) {
        if (parrent == null || parrent.equals("")) {
            parrent = "yyyy-MM-dd HH:mm:ss";
        }
        try {
            SimpleDateFormat sformat = new SimpleDateFormat(parrent);
            Calendar c = Calendar.getInstance(Locale.CHINESE);
            c.setTime(sformat.parse(datetime));
            c.add(field, offset);
            return sformat.format(c.getTime());
        } catch (Exception e) {
            e.printStackTrace();
            return datetime;
        }
    }

    /**
     * 计算日期，返回yyyy-MM-dd
     *
     * @param date   String 原始时间
     * @param field  the calendar field,eg:Calendar.WEEK_OF_YEAR
     * @param offset the amount of date or time to be added to the field, eg:-2
     * @return
     */
    public static String getDate(String date, int field, int offset) {
        String parrent = "yyyy-MM-dd";
        try {
            date = date.substring(0, 10);
            SimpleDateFormat sformat = new SimpleDateFormat(parrent);
            Calendar c = Calendar.getInstance(Locale.CHINESE);
            c.setTime(sformat.parse(date));
            c.add(field, offset);
            return sformat.format(c.getTime());
        } catch (Exception e) {
            e.printStackTrace();
            return date;
        }
    }

    /**
     * 是否早于当天
     *
     * @param date yyyy-MM-dd
     * @return
     */
    public static boolean beforeToday(String date) {
        SimpleDateFormat sformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date d = sformat.parse(date + " 00:00:00");
            Date now = sformat.parse(getDateTime("yyyy-MM-dd") + " 00:00:00");
            return d.before(now);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 是否晚于当天
     *
     * @param date yyyy-MM-dd
     * @return
     */
    public static boolean afterToday(String date) {
        SimpleDateFormat sformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date d = sformat.parse(date + " 00:00:00");
            Date now = sformat.parse(getDateTime("yyyy-MM-dd") + " 00:00:00");
            return d.after(now);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
     * 是否是当天(工作日)
     * @return
     */
    @SuppressWarnings("deprecation")
    public static boolean isWeekday(int weekday) {
        return (new Date().getDay() == weekday - 1) ? true : false;
    }

    /**
     * 是否在两个日期段内
     *
     * @param startDate yyyy-MM-dd
     * @return
     */
    public static boolean inDate(String startDate, String endDate) {
        return !afterToday(startDate) && !beforeToday(endDate);
    }

    /**
     * 是否在两个时间段内
     *
     * @param startTime HHmm
     * @return
     */
    public static boolean inTime(String startTime, String endTime) {
        startTime = startTime.replace(":", "");
        if (startTime.length() == 4) {
            startTime = startTime + "00";
        }
        endTime = endTime.replace(":", "");
        if (endTime.length() == 4) {
            endTime = endTime + "00";
        }
        int now = Integer.parseInt(getDateTime("HHmmss"));
        if (now >= Integer.parseInt(startTime) && now <= Integer.parseInt(endTime)) {
            return true;
        }
        return false;
    }

    /**
     * 是否早于当前时间
     *
     * @param time HHmm
     * @return
     */
    public static boolean beforeNow(String time) {
        time = time.replace(":", "");
        if (time.length() == 4) {
            time = time + "00";
        }
        int now = Integer.parseInt(getDateTime("HHmmss"));
        if (Integer.parseInt(time) < now) {
            return true;
        }
        return false;
    }

    /**
     * 是否晚于当前时间
     *
     * @param time HHmm
     * @return
     */
    public static boolean afterNow(String time) {
        time = time.replace(":", "");
        if (time.length() == 4) {
            time = time + "00";
        }
        int now = Integer.parseInt(getDateTime("HHmmss"));
        if (Integer.parseInt(time) > now) {
            return true;
        }
        return false;
    }


    public static String getWeekStr(int week) {
        switch (week) {
            case 1:
                return "周日";
            case 2:
                return "周一";
            case 3:
                return "周二";
            case 4:
                return "周三";
            case 5:
                return "周四";
            case 6:
                return "周五";
            case 7:
                return "周六";
        }
        return "未知[" + week + "]";
    }


    public static String simpleDatetime(String datetime) {
        if (datetime == null) {
            return "";
        }
        if (datetime.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}")) {
            return datetime.substring(5, 16);
        } else if (datetime.matches("\\d{4}-\\d{2}-\\d{2}")) {
            return datetime.substring(5, 10);
        } else if (datetime.matches("\\d{2}:\\d{2}:\\d{2}")) {
            return datetime.substring(0, 5);
        }
        return datetime;
    }

    public static String formatDate(String datetime) {
        if (datetime == null) {
            return "";
        }
        if (datetime.length() > 10) {
            return datetime.substring(0, 10);
        }
        return datetime;
    }

    public static final String REG_MOBILE = "1[3458]\\d{9}";// 手机号正则表达试
    public static final String REG_TEL = "(0\\d{2,3}-?\\d{7,8}(-\\d{3,})?)|(400\\d{7})";// 验证座机
    public static final String REG_MAIL = "\\w+([\\-+\\.]\\w+)*@\\w+([\\-\\.]\\w+)*\\.\\w+([\\-\\.]\\w+)*";// 验证邮箱

    /**
     * 将字符串时间转换成日期格式
     *
     * @param datetime String 默认返回yyyy-MM-dd HH:mm:ss格式的时间
     * @return
     */
    public static Date toDate(String datetime) {
        if (datetime == null || datetime.length() < 0) {
            return null;
        }
        if (datetime.matches("\\d{14,}")) {
            datetime = datetime.substring(0, 4) + "-" + datetime.substring(4, 6) + "-" + datetime.substring(6, 8) + " " + datetime.substring(8, 10) + ":" + datetime.substring(10, 12) + ":" + datetime.substring(12, 14);
        } else if (datetime.matches("\\d{4}-\\d{2}-\\d{2}")) {
            datetime = datetime + " 00:00:00";
        } else if (datetime.matches("\\d{2}:\\d{2}:\\d{2}(.\\d+)?")) {
            datetime = getDateTime("yyyy-MM-dd") + " " + datetime;
        } else if (!datetime.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}(.\\d+)?")) {
            try {
                return new Date(datetime);
            } catch (Exception e) {
                System.err.println("Unparseable date: \"" + datetime + "\"");
                return null;
            }
        }
        try {
            SimpleDateFormat sformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return sformat.parse(datetime);
        } catch (Exception e) {
            System.err.println("Unparseable date: \"" + datetime + "\"");
            return null;
        }
    }

    /**
     * 计算时间差 (时间单位,开始时间,结束时间) 调用方法
     *
     * @unit ：0-毫秒；1-秒；2-分；3-小时；4-天
     */
    public static long timeDiff(String time1, String time2, int unit) {
        return timeDiff(time1, time2, unit, true);
    }

    public static final int MILLISECOND = 0;
    public static final int SECOND = 1;
    public static final int MINUTE = 2;
    public static final int HOUR = 3;
    public static final int DAY = 4;

    /**
     * 计算时间差 (时间单位,开始时间,结束时间) 调用方法
     *
     * @unit ：0-毫秒；1-秒；2-分；3-小时；4-天
     * @flag : true-返回绝对值,false-区分正分
     */
    public static long timeDiff(String time1, String time2, int unit, boolean flag) {
        // 单位(如：不足1天(24小时) 则返回0)，开始时间，结束时间
        Date date1 = toDate(time1);
        Date date2 = toDate(time2);
        long ltime = date2.getTime() - date1.getTime();
        if (flag) {
            ltime = Math.abs(ltime);
        }
        if (unit == SECOND) {
            return ltime / 1000;// 返回秒
        } else if (unit == MINUTE) {
            return ltime / 60000;// 返回分钟
        } else if (unit == HOUR) {
            return ltime / 3600000;// 返回小时
        } else if (unit == DAY) {
            return ltime / 86400000;// 返回天数
        } else {
            return ltime;// 毫秒
        }
    }


    /**
     * 得到几天前的时间
     *
     * @param date
     * @param day
     * @return
     */
    public static String[][] getDateBefore(String date, int day) {
        String[][] res = new String[2][];
        String[] real_date = new String[day];// 真实的日期
        String[] show_date = new String[day];// 用来显示的日期
        try {
            Calendar now = Calendar.getInstance();
            SimpleDateFormat sformat = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
            SimpleDateFormat show_date_format = new SimpleDateFormat("d/M", Locale.CHINA);
            now.setTime(sformat.parse(date));
            now.set(Calendar.DATE, now.get(Calendar.DATE) - day - 1);
            int index = 0;
            for (int i = 1; i <= day; i++) {
                now.set(Calendar.DATE, now.get(Calendar.DATE) + 1);
                real_date[index] = sformat.format(now.getTime());
                show_date[index] = show_date_format.format(now.getTime());
                index++;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        res[0] = real_date;
        res[1] = show_date;
        return res;
    }

    /**
     * 几天前
     *
     * @param day
     * @return
     */
    public static String getDateBefore(int day) {
        try {
            Calendar now = Calendar.getInstance();
            SimpleDateFormat sformat = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
            // SimpleDateFormat show_date_format = new SimpleDateFormat("d/M",
            // Locale.CHINA);
            now.setTime(sformat.parse(getDateTime("yyyy-MM-dd")));
            now.set(Calendar.DATE, now.get(Calendar.DATE) - day - 1);
            return sformat.format(now.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return getDateTime("yyyy-MM-dd");
    }



    public static boolean isValidDateTime(String date, String simpleDateFormat) {
        boolean convertSuccess = true;
        SimpleDateFormat format = new SimpleDateFormat(simpleDateFormat);
        try {
            format.setLenient(false);
            format.parse(date);
        } catch (ParseException e) {
            convertSuccess = false;
        }
        return convertSuccess;
    }



    public static boolean isValidDateTime(String date, DateTimeEnum dateTimeEnum) {
        return isValidDateTime(date, dateTimeEnum.getValue());
    }

    public static boolean isValidDateTime(String datetime) {
        return isValidDateTime(datetime, DateTimeEnum.dateTime1);
    }

}

