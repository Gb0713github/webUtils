package web.utils.propertiesUtil;

import java.io.*;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 对properties类型的文件进行操作的工具类
 * Created by shipengwei on 2016/10/8.
 */

public class PropertiesUtil {



    /**
     * 根据key获取对应的value值,如果没有文件中没有对应的keyh或者文件fileName为null则返回null。properties类型的文件必须在
     * src目录下。
     * @param fileName 文件的名称。
     * @return
     */
    public static String getValueByKeyFromProperty(final String fileName,final String key){
        if(null != fileName && null != null && !"".equals(fileName.trim()) && !"".equals(key.trim())){
            final Properties prop = new Properties();
            final InputStream is = PropertiesUtil.class.getClassLoader().getResourceAsStream(fileName);
            try {
                prop.load(is);
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return prop.getProperty(key);
        }else{
            return null;
        }
    }

    /**
     * 将properties文件中的内容读取到map<String,String>中。properties的文件必须在src目录下.如果fileName是为null或者全部为空格
     * 则返回null。
     * @param fileName 要读取的properties文件
     * @return
     */
    public static Map<String,String> readProperties2Map(final String fileName){
        //判断fileName为null
        if(null != fileName && !"".equals(fileName.trim())){
            final Properties prop = new Properties();
            final InputStream is = PropertiesUtil.class.getClassLoader().getResourceAsStream(fileName);
            try {
                prop.load(is);
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            final Enumeration en = prop.propertyNames();
            final Map<String,String> map = new HashMap<String,String>();
            while(en.hasMoreElements()){
                final String key = en.nextElement().toString();
                final String value = prop.getProperty(key);
                map.put(key,value);
            }
            return map;
        }else{
            return null;
        }
    }
}
