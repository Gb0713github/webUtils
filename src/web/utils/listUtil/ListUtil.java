package web.utils.listUtil;

import java.util.List;

/**
 * Created by shipengwei on 2016/10/8.
 */
public class ListUtil {
    /**
     * 将list中的数据转化成以separator分隔是的字符串。
     * @param list  要转化的list
     * @param separator 分隔符
     * @return  如果list为null或者长度为0则返回“”，否则返回以分隔符分隔的字符串。
     */
    public static String list2String(final List list , final String separator){
        if(null != list && list.size()!=0){
            final StringBuffer sb = new StringBuffer();
            sb.append(list.get(0).toString());
            for(int i=1;i<list.size();i++){
                sb.append(separator);
                sb.append(list.get(i).toString());
            }
            return sb.toString();
        }else{
            return "";
        }
    }

    /**
     * 检验list是否为空。当list为null，或是长度为0的时候返回false，否则返回true
     * @param list
     * @return
     */
    public static boolean isNull(final List list){
        if(null != list && list.size()>0){
            return true;
        }else{
            return false;
        }
    }
}
