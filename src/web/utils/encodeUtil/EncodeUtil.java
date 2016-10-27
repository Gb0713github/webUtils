package web.utils.encodeUtil;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import web.utils.stringUtil.StringUtil;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * 对字符串的加密解密,在utf-8字符编码下
 * Created by Administrator on 2016/10/9.
 */
public class EncodeUtil {

    /**
     * 使用MD5算法对字符串进行加密
     * @param content 要加密的内容
     * @return
     * @throws Exception content为null则抛出异常
     */
    public static String md5EncodeString(final String content) throws Exception{
        if(StringUtil.isNull(content)){
            throw new Exception("content 不能为空或不能为空字符串");
        }
        return DigestUtils.md5Hex(content);
    }

    /**
     * 使用MD5算法对输入流进行加密
     * @param is
     * @return
     * @throws Exception is为null则抛出异常
     */
    public static String md5EncodeIs(final InputStream is) throws Exception{
        //判断is为空
        if(null == is){
            throw new Exception("is can not be null");
        }
        String result = "";
        try {
            result = new String(DigestUtils.md5(is));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 使用base64算法对字符串进行加密
     * @param content 要进行加密的内容
     * @return
     * @throws Exception content为null这抛出异常
     */
    public static String base64Encode(final String content) throws Exception{
        //判断content为空
        if(StringUtil.isNull(content)){
            throw new Exception("content can not be null");
        }
        final Base64 base64 = new Base64();
        return new String(base64.encode(content.getBytes()));
    }

    /**
     * 使用base64算法对字符串进行解密
     * @param content 要解密的字符串
     * @return
     * @throws Exception content（要解密的字符串）为null这抛出异常
     */
    public static String base64Decode(final String content) throws Exception{
        //判断content为空
        if(StringUtil.isNull(content)){
            throw new Exception("content can not be null");
        }
        final Base64 base64 = new Base64();
        return new String(base64.decode(content));
    }

    /**
     * 对url进行加密
     * @param url 要加密的字符串
     * @return
     * @throws Exception 当url为空时抛出异常
     */
    public static String urlEncode(final String url) throws Exception{
        //判断url为空
        if (StringUtil.isNull(url)){
            throw new Exception("url can not be null");
        }
        String result = "";
        try {
            result = URLEncoder.encode(url,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 对url进行解密
     * @param url
     * @return
     * @throws Exception 当url为空时抛出异常
     */
    public static String urlDecode(final String url) throws Exception{
        //判断url为空
        if (StringUtil.isNull(url)){
            throw new Exception("url can not be null");
        }
        String result = "";
        try {
            result = URLDecoder.decode(url,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }
}
