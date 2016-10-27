package web.utils.dateUtil;

import web.utils.stringUtil.StringUtil;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by shipengwei on 2016/10/8.
 */
public class DateUtil {
    public static String SEC_FORMAT="yyyy-MM-dd HH:mm:ss";
    public static String DAY_FORMAT="yyyy-MM-dd";
    public static String YEAR = "year";
    public static String MONTH = "month";
    public static String DAY = "day";

    /**
     * 将date对象转化成format格式的字符串。如果date为null则返回“”，如果format为null或者全部为空格则按照默认的yyyy-MM-dd
     * 的格式进行转化。
     * @param date 要转化的时间对象
     * @param format 要格式成的字符串格式。
     * @return String
     */
    public static String date2String(final Date date , final String format){
        //判断date不为null
        if(null != date){
            if(!StringUtil.isNull(format)){
                final SimpleDateFormat sdf = new SimpleDateFormat(format);
                return sdf.format(date);
            }else{
                final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                return sdf.format(date);
            }
        }else{
            return "";
        }
    }

    /**
     * 将calendar类型的对象转化成format格式的字符串。如果calendar为null则返回“”，如果format为null或是全部为空格的字符串
     * 则默认按照yyyy-MM-dd的格式转化。
     * @param calendar 要转化的对象
     * @param format 要转化成的格式。
     * @return String.
     */
    public static String calendar2String(final Calendar calendar, final String format){
        //判断calendar 不为null
        if(null != calendar){
            //判断format不为null和不全部为空格
            if(!StringUtil.isNull(format)){
                final SimpleDateFormat sdf = new SimpleDateFormat(format);
                return sdf.format(calendar.getTime());
            }else{
                final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                return sdf.format(calendar.getTime());
            }
        }else{
            return "";
        }
    }

    /**
     * 对日期进行运算。
     * @param date 要进行运算的日期 ,不能为null，如果为null则返回null.
     * @param type 表示对年对月还是对日的运算对应关系如下,type的值可以为null，如果为null则按照day进行运算，
     * 如果type的值不为如下所列，则返回原来的时间值。
     * year--年
     * month--月
     * day--日
     * @param value 日期的偏移量
     * @return Date 运算完后的时间。
     */
    public static Date addDate(final Date date,final String type,final int value){
        //判断date不为null
        if(null != date){
            final Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            //判断在什么单位上进行运算
            if("year".equals(type)){
                calendar.add(Calendar.YEAR,value);
                return calendar.getTime();
            }else if("month".equals(type)){
                calendar.add(Calendar.MONTH,value);
                return calendar.getTime();
            }else if("day".equals(type) || null == type){
                calendar.add(Calendar.DAY_OF_YEAR,value);
                return calendar.getTime();
            }else{
                return date;
            }
        }else{
            return null;
        }
    }

    /**
     * 计算两个日期之间得差值，late-early。如果第二个日期比第一个日期早则返回负值，如果第二个日期值比第一个晚则返回正值。
     * 如果两个日期中任意一个为null则返回0.
     * @param early not null
     * @param late not null
     * @return
     */
    public static  int daysBetween(final Date early, final Date late) {
        //判断两个日期都不为null;
        if(null != early && null != late){
            final Calendar calst = Calendar.getInstance();
            final Calendar caled = Calendar.getInstance();
            calst.setTime(early);
            caled.setTime(late);
            //设置时间为0时
            calst.set(java.util.Calendar.HOUR_OF_DAY, 0);
            calst.set(java.util.Calendar.MINUTE, 0);
            calst.set(java.util.Calendar.SECOND, 0);
            caled.set(java.util.Calendar.HOUR_OF_DAY, 0);
            caled.set(java.util.Calendar.MINUTE, 0);
            caled.set(java.util.Calendar.SECOND, 0);
            //得到两个日期相差的天数
            return  ((int) (caled.getTime().getTime() / 1000) - (int) (calst.getTime().getTime() / 1000)) / 3600 / 24;
        }else{
            return 0;
        }
    }

}
