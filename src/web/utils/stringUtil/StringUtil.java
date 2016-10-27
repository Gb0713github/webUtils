package web.utils.stringUtil;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by shipengwei on 2016/10/8.
 */
public class StringUtil {
    /**
     * 将字符串以分隔符分隔后，以String类型存入list并放回list。
     * 如果separator为null则不进行分隔直接将srcString存入list中返回。如果没哟分隔符将字符串存入list中的第一个元素中。
     * @param srcString 要转化的字符串
     * @param separator 分隔符
     * @return 存有String的list，如果srcString为null或全部为空格，则 返回null。
     */
    public static List<String> string2List(final String srcString, final String separator){
        final List<String> list = new ArrayList<String>();
        //判断分隔符不为null或者不全部是空格。
        if(null != separator && separator.trim().length()>0){
            //判断字符串是否为空
            if(null != srcString && srcString.trim().length()>0){
                final String[] strArray = srcString.split(separator);
                for(String str :strArray){
                    list.add(str);
                }
                return list;
            }else{
                return null;
            }
        }else{
            list.add(srcString);
            return list;
        }
    }

    /**
     * 将字符串转化成符合format格式的Date对象。如果srcStr为空则返回空，否则尝试将字符串转化成Date如果成功则返回Date对象，如果失败着抛出异常.
     * format可以为空或是格式化字符串。如果format为空则转化成默认的yyyy-MM-dd形式。否则按照格式字符串尝试转化。
     * @param srcStr 要转化的字符串
     * @param format 转化成的Data的格式.可以为空
     * @return Date
     */
    public static Date string2Data(final String srcStr, final String format){
        //检测要转化的字符串不是null或者不全部是空格。
        if(null != srcStr && srcStr.trim().length()>0){
            Date date = null;
            if(null != format && format.trim().length()>0){
                final SimpleDateFormat sdf = new SimpleDateFormat(format);
                try {
                    date = sdf.parse(srcStr);
                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }else{
                final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    date = sdf.parse(srcStr);
                } catch (ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            return date;
        }else{
            return null;
        }
    }

    /**
     * 将字符串转化成符合format格式的Calendar对象。如果srcStr为空则返回null，否则尝试转化成Calendar对象。
     * 如果字符串符合yyyy-MM-dd格式format可以为空，否则需要传递相应的格式。
     * @param srcStr 要转化的字符串
     * @param format 字符串符合的时间格式。
     * @return Calendar
     */
    public static Calendar string2Calendar(final String srcStr, final String format){
        final Date date = StringUtil.string2Data(srcStr, format);
        //判断转化的日期是否为空。
        if(null !=date){
            Calendar calendar =  Calendar.getInstance();
            calendar.setTime(date);
            return calendar;
        }else{
            return null;
        }

    }

    /**
     * 将一种格式的日期字符串转化成另一种格式的日期字符串。如果srcStr为空则返回"",否则将fromFormat格式的日期字符串转化成toFormat格式的日期字符串。
     * 如果fromFormt和toFormat为null或是全部为空格，则默认为yyyy-MM-dd格式。否则按照输入格式转化。
     * @param srcStr 要转化的日期字符串。 时间字符串
     * @param fromFormat 原字符串符合的日期格式。
     * @param toFormat 要转化成的日期字符串的格式。 要转化成的日期字符串的格式。
     * @return String
     */
    public static String dateStr2DateStr(final String srcStr, final String fromFormat,final String toFormat){
        final Date date = StringUtil.string2Data(srcStr, fromFormat);
        String tarDateStr = "";
        if(null != date){
            final SimpleDateFormat sdf = new SimpleDateFormat(toFormat);
            tarDateStr = sdf.format(date);
        }
        return tarDateStr;
    }

    /**
     * 判断字符串是否是空。如果字符串是null或者内容全部是空格则返回true，否则返回false。
     * @param srcStr 要判断的字符串。
     * @return boolean
     */
    public static boolean isNull( final String srcStr){
        boolean flage = true;
        //判断srcStr不是null或不全部是空格。
        if(null != srcStr && srcStr.trim().length()>0){
            flage = false;
        }
        return flage;
    }

    /**
     * 将xml格式的字符串转化成Document对象(dom4j)。如果srcStr为null或者全部是空格则返回null，
     * 否则尝试转化成document对象。如果转化成功则返回否则抛出异常。
     * @param srcStr 要转化的字符串。不能为null或空字符串。
     * @return Document
     */
    public static Document string2Xml(final String srcStr){
        //判断srcStr不是null或不全部是空格。
        if(null != srcStr && srcStr.trim().length()>0){
            Document doc = null;
            try {
                doc = DocumentHelper.parseText(srcStr);
            } catch (DocumentException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return doc;
        }else{
            return null;
        }
    }

    /**
     * 将字符串转化成gson中的JsonObject对象。
     * @param jsonStr 要转化的字符串。
     * @return JsonObject gson
     */
    public static JsonObject String2JsonObject(final String jsonStr){
        JsonObject jsonObj = null;
        //判断jsonStr不为null或者是不为全部为空格的字符串
        if(null != jsonStr && jsonStr.trim().length()>0){
            jsonObj = new JsonParser().parse(jsonStr).getAsJsonObject();
        }
        return jsonObj;
    }

    /**
     * 判断srcStr是否是ip地址，
     * 格式如下
     * 1、 xxx.xxx.xxx.xxx
     * 2、 xxx.xxx.xxx.xxx/xx(xx的值为1-32)
     * 3、xxx.xxx.xxx.xxx-xxx
     * 4、xxx.xxx.xxx.xxx-xxx.xxx.xxx.xxx
     * 按照type的值进行匹配如果能够匹配成功则返回true，匹配失败则返回false。type可选值为1、2、3、4、和0，
     * type值与匹配模式对应关系如上。
     * 如果type的值为0则匹配任意一种形式则返回true如果都不能匹配则返回false。
     * @param srcStr 要检测的字符串。
     * @param type 要匹配的类型。
     * @return boolean
     */
    public static boolean isIpAddr(final String srcStr,final int type){
        boolean flag = false;
        //判断srcStr不为null或者不是全部为空格。
        if(null != srcStr && srcStr.trim().length()>0){
            //匹配形如xxx.xxx.xxx.xxx的ip地址
            Pattern pattern1 = Pattern.compile("(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])");
            //匹配形如xxx.xxx.xxx.xxx/xx(xx的值为1-32)的ip地址
            Pattern pattern2 = Pattern.compile("(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\/([1-9]|[1-2][0-9]|3[0-2])");
            //匹配形如xxx.xxx.xxx.xxx-xxx的ip地址
            Pattern pattern3 = Pattern.compile("(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\-(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])");
            //匹配形如xxx.xxx.xxx.xxx-xxx.xxx.xxx.xxx的ip地址
            Pattern pattern4 = Pattern.compile("(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])-(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])");
            final boolean flag1 = pattern1.matcher(srcStr).matches();
            final boolean flag2 = pattern2.matcher(srcStr).matches();
            final boolean flag3 = pattern3.matcher(srcStr).matches();
            final boolean flag4 = pattern4.matcher(srcStr).matches();
            if(type == 0){
                if(flag1 || flag2 || flag3 || flag4){
                    flag = true;
                }
            }else{
                if(type == 1){
                    if(flag1){
                        flag = true;
                    }
                }
                if(type == 2){
                    if(flag2){
                        flag = true;
                    }
                }
                if(type == 3){
                    if(flag3){
                        flag = true;
                    }
                }
                if(type == 4){
                    if(flag4){
                        flag = true;
                    }
                }
            }
        }
        return flag;
    }

    /**
     * 判断srcStr是否是一个电话号码，如果符合电话号码的格式返回true。否则返回false。
     * @param srcStr 要验证的字符串
     * @return boolean
     */
    public static boolean isPhone(final String srcStr){
        boolean flag = false;
        //判断srcStr不为null或者不是全部为空格。
        if(null != srcStr && srcStr.trim().length()>0){
            Pattern pattern = Pattern.compile("(13[0-9]|14[0-9]|15[0-9]|18[0-9])\\d{8}");
            flag = pattern.matcher(srcStr).matches();
        }
        return flag;
    }

    /**
     * 判断字符串是不是符合邮箱的格式。如果符合则返回true，否则返回false。
     * @param srcStr 要检测的字符串。
     * @return
     */
    public static boolean isEmail(final String srcStr){
        boolean flag  = false;
        //判断srcStr不为null或者不是全部为空格。
        if(null != srcStr && srcStr.trim().length()>0){
            Pattern pattern = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
            flag = pattern.matcher(srcStr).matches();
        }
        return flag;
    }

    /**
     * 验证字符串是否为身份证格式。
     * @param srcStr 要验证的字符串
     * @return boolean
     */
    public static boolean isIDCard(final String srcStr){
        boolean flag = false;
        //判断srcStr不为null或者不是全部为空格。
        if(null != srcStr && srcStr.trim().length()>0){
            Pattern pattern = Pattern.compile("(^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$)|(^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])((\\d{4})|\\d{3}[Xx])$)");
            flag = pattern.matcher(srcStr).matches();
        }
        return flag;
    }

    /**
     *
     * @param srcStr
     * @return
     */
    public static boolean isMobile(final String srcStr){
        boolean flag = false;
        //判断srcStr不为null或者不是全部为空格。
        if(null != srcStr && srcStr.trim().length()>0){
            Pattern pattern = Pattern.compile("(13[0-9]|14[0-9]|15[0-9]|18[0-9])\\d{8}");
            flag = pattern.matcher(srcStr).matches();
        }
        return flag;
    }

    /**
     * 对字符串中的特殊的字符进行转义
     * @param content 要转义的字符串
     * @return
     */
    public static String stringEscape(final String content){
        return null;
    }
}
