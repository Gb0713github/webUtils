package web.utils.fileUtil;

import web.utils.stringUtil.StringUtil;

import java.io.*;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by shipengwei on 2016/10/11.
 */
public class FileUtil {

    /**
     * 通过目录和文件的名称获取文件的File对象(非目录文件)
     * @param dir 文件所在的目录
     * @param fileName 文件名称
     * @return the file
     * @throws Exception 当dir或者fileName为null，指向的文件不存在或者为目录时抛出异常
     */
    public static File getFile(final String dir,final String fileName) throws IOException{
        //判断dir和name不为空
        if(StringUtil.isNull(dir) || StringUtil.isNull(fileName)){
            throw new IOException("dir or name can not be null");
        }
        final File file = new File(dir,fileName);
        //判断不存在
        if(!file.exists()){
            throw  new IOException("the file is not exist");
        }
        //判断文件是目录
        if(file.isDirectory()){
            throw new IOException("the file is a directory");
        }
        return file;
    }

    /**
     * 通过路径获取文件（非目录）的File对象
     * @param path 不能为null或不能为空字符串或为目录，否则抛出IO异常
     * @return the file
     * @throws IOException
     */
    public static  File getFile(final String path ) throws IOException{
        //判断path不能为空或不能为空字符串
        if(StringUtil.isNull(path)){
            throw new IOException("path can not be null");
        }
        final File file = new File(path);
        //判断不存在
        if(!file.exists()){
            throw  new IOException("the file is not exist");
        }
        //判断文件是目录
        if(file.isDirectory()){
            throw new IOException("the file is a directory");
        }
        return file;
    }

    /**
     * 打开一个文件的输入流
     * @param path 文件的路径
     * @return
     * @throws IOException
     */
    public static FileInputStream openInputStream(final String path) throws IOException{
        final File file = FileUtil.getFile(path);
        return new FileInputStream(file);
    }

    /**
     *打开文件的输入流
     * @param file 文件的对象
     * @return
     * @throws IOException 当输入的文件为空时抛异常
     */
    public static FileInputStream openInputStream(final File file) throws IOException{
        if(!file.exists()){
            throw new IOException("file not exist");
        }
        return new FileInputStream(file);
    }

    /**
     * 打开文件的输出流
     * @param path 指向文件的路径
     * @return
     * @throws IOException
     */
    public static FileOutputStream openOutputStream(final String path) throws IOException{
        final File file = FileUtil.getFile(path);
        return new FileOutputStream(file);
    }

    /**
     *打开文件得输出流
     * @param file 文件得对象
     * @return
     * @throws IOException
     */
    public static FileOutputStream openOutputStream(final File file) throws IOException{
        //判断文件不存在
        if(!file.exists()){
            throw new IOException("file not exist");
        }
        return new FileOutputStream(file);
    }

    /**
     * 读取字符类型的文件
     * @param path 文件的路径
     * @param charset 编码格式
     * @return
     * @throws IOException
     */
    public static String readFileToString(final String path,final String charset) throws IOException{
        final File file = FileUtil.getFile(path);
        BufferedReader  reader = new BufferedReader(new InputStreamReader(new FileInputStream(file),charset));
        final StringBuffer sb = new StringBuffer();
        String temp = "";
        while((temp = reader.readLine()) != null){
            sb.append(temp);
        }
        return sb.toString();
    }

    /**
     * 读取字符类型的文件（默认以utf-8编码格式读取）
     * @param path 文件的路径
     * @return
     * @throws IOException
     */
    public static String readFileToString1(final String path) throws IOException{
        return FileUtil.readFileToString(path,"utf-8");
    }

    /**
     * 读取字节类型的文件
     * @param path 文件的路径
     * @return
     * @throws IOException
     */
    public static String readFileToStringByByte(final String path) throws IOException{
        final File file = FileUtil.getFile(path);
        final FileInputStream fis = new FileInputStream(file);
        final StringBuffer sb = new StringBuffer();
        int count = 0;
        byte[] buffer = new byte[1024];
        try{
            while((count = fis.read(buffer))!=-1){
                sb.append(new String(buffer,0,count));
            }
        }finally {
            fis.close();
        }
        return sb.toString();
    }

    /**
     * 读取文件
     * @param is
     * @return
     */
    public static String readFile(InputStream is){
        return null;
    }

    /**
     * 按照行将文件中的内容读取到list集合中
     * @param path 文件的路径
     * @param charset 编码格式
     * @return
     */
    public static List<String> readLine(final String path,final String charset) throws IOException{
        final File file = FileUtil.getFile(path);
        BufferedReader  reader = new BufferedReader(new InputStreamReader(new FileInputStream(file),charset));
        final List<String> list = new ArrayList<String>();
        String temp = "";
        while((temp = reader.readLine()) != null){
            list.add(temp);
        }
        return list;
    }

    /**
     * 按照行将文件中的内容读取到list集合中.（默认编码为utf-8）
     * @param path 文件的路径
     * @return
     * @throws IOException
     */
    public static List<String> readLine(final String path) throws IOException{
        return FileUtil.readLine(path,"utf-8");
    }

    /**
     * 按照行将文件中的内容读取到list集合中
     * @param file
     * @param charset
     * @return
     */
    public static List<String> readLine(File file,final String charset) throws IOException{
        BufferedReader  reader = new BufferedReader(new InputStreamReader(new FileInputStream(file),charset));
        final List<String> list = new ArrayList<String>();
        String temp = "";
        while((temp = reader.readLine()) != null){
            list.add(temp);
        }
        return list;
    }

    /**
     * 写文件
     * @param path
     * @param content
     * @param cover
     */
    public static void writeFile(String path,String content,boolean cover){

    }

    /**
     * 写文件
     * @param file
     * @param content
     * @param cover
     */
    public static void writeFile(File file , String content, boolean cover){

    }

    /**
     * 将list中的内容按照一个元素一行的格式写入文件中
     * @param content
     * @param path
     * @param cover
     */
    public static void writeLine(List<String> content,String path,boolean cover){

    }

    /**
     * 将list中的内容按照一个元素一行的格式写入文件中
     * @param content
     * @param file
     * @param cover
     */
    public static void writeLine(List<String> content,File file ,boolean cover){

    }
    /**
     * 复制文件。可以将srcDir文件中的内容追加到desPath中
     * @param srcDir
     * @param desPath
     * @param cover
     */
    public static void copyFile(String srcDir,String desPath,boolean cover){

    }

    /**
     *复制文件。可以将srcDir文件中的内容追加到desPath中
     * @param srcFile
     * @param desFile
     * @param cover
     */
    public static void copyFile(File srcFile,File desFile,boolean cover){

    }

    /**
     * 复制大文件，没有对文件的覆盖
     * @param srcFile 源文件
     * @param desFile 目的文件
     */
    public static void copyFile(File srcFile,File desFile) throws  IOException{
        //判断文件不能为空
        if(null == srcFile || null == desFile){
            throw new IOException("file can not be null");
        }
        //判断源文件不能为目录
        if(srcFile.exists() && srcFile.isDirectory()){
            throw new IOException(srcFile.getName() +" is a directory");
        }
        //判断目的文件不能为目录
        if(desFile.exists() && desFile.isDirectory()){
            throw new IOException(desFile.getName() +" is a directory");
        }
        FileInputStream fis = null;
        FileOutputStream fos = null;
        FileChannel input = null;
        FileChannel output = null;
        try{
            fis = new FileInputStream(srcFile);
            fos = new FileOutputStream(desFile);
            input = fis.getChannel();
            output = fos.getChannel();
            long size = input.size();
            long pos = 0;
            long count = 0;
            long bufferSize = 1024;
            while(pos<size){
                final long surplus = size - pos;
                count = surplus > bufferSize ? bufferSize : surplus;
                final long copySize = output.transferFrom(input,pos,count);
                if(copySize == 0){
                    break;
                }
                pos += copySize;
            }
        }finally {
            fis.close();
            fos.close();
            input.close();
            output.close();
        }

        final long srcLen = srcFile.length();
        final long desLen = desFile.length();
        //判断复制后的文件是不是一致
        if(srcLen != desLen){
            throw new IOException("copy file is not finish");
        }

    }

    /**
     *复制大文件，完成复制没有对文件的追加
     * @param srcPath 源文件路径
     * @param desPath 目标文件路径
     * @throws IOException
     */
    public static void copyFile(final String srcPath,final String desPath) throws IOException {
        final File srcFile = getFile(srcPath);
        final File decFile = getFile(desPath);
        copyFile(srcFile, decFile);
    }

    /**
     * 判断路径指向的文件是否为空
     * @param path 文件的路径
     * @return
     * @throws Exception
     */
    public static boolean isEmptyFile(final String path) throws Exception{
        //判断path为null或者为空字符串
        if(StringUtil.isNull(path)){
            throw new Exception("path can not be null");
        }
        final  File file = FileUtil.getFile(path);
        //判断文件的内容不为null
        if(file.length()==0){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 判断文件否为空
     * @param file 待判断的文件
     * @return
     * @throws IOException 当文件不存在是抛出异常
     */
    public static boolean isEmptyFile(final File file) throws IOException{
        //判断文件不存在
        if(!file.exists()){
            throw new IOException("file not exist");
        }
        if(file.length()==0){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 删除文件
     * @param path 要删除文件得路径
     * @throws IOException
     */
    public static void removeFile(final String path) throws IOException{
        final File file = FileUtil.getFile(path);
        file.delete();
    }

    /**
     * 删除文件
     * @param file 要删除的文件的对象
     * @throws IOException 当文件不存在的时候抛出异常
     */
    public static void removeFile(final File file) throws IOException{
        //判断文件不存在
        if(!file.exists()){
            throw new IOException("file not exist");
        }
        file.delete();
    }
}
