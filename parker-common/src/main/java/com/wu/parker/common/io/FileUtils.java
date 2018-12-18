package com.wu.parker.common.io;

import com.google.common.base.Charsets;
import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.io.Files;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 文件工具类
 * @author wusq
 * @date 2018-12-18
 */
public class FileUtils {

    /**
     * 追加写入文件
     * @param path 文件路径
     * @param content 内容
     * @return
     */
    public static boolean append(String path, String content){
        return append(path, content, Charsets.UTF_8);
    }

    /**
     * 追加写入文件
     * @param path 文件路径
     * @param lines 内容
     * @return
     */
    public static boolean append(String path, List<String> lines){
        return append(path, Joiner.on("\n").join(lines), Charsets.UTF_8);
    }

    /**
     * 追加写入文件
     * @param path 文件路径
     * @param content 内容
     * @param charset 字符集
     * @return
     */
    public static boolean append(String path, String content, Charset charset){
        boolean result = Boolean.FALSE;
        Preconditions.checkNotNull(path, "file path must not be null!");
        File file = new File(path);
        try {
            Files.append(content, file, charset);
            result = Boolean.TRUE;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 文件复制
     * @param fromPath 源文件
     * @param toPath 目标文件
     * @return boolean
     */
    public static boolean copy(String fromPath, String toPath){
        boolean result = Boolean.FALSE;
        Preconditions.checkNotNull(fromPath, "file fromPath must not be null!");
        Preconditions.checkNotNull(toPath, "file toPath must not be null!");
        File fromFile = new File(fromPath);
        File toFile = new File(toPath);
        if(fromFile.isFile()){
            try {
                Files.copy(fromFile, toFile);
                result = Boolean.TRUE;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 删除目录或文件
     * @param path 目录或文件路径
     * @return boolean
     */
    public static boolean delete(String path){
        boolean result = Boolean.FALSE;
        if(StringUtils.isNotBlank(path)){
            File file = new File(path);
            if(file.isDirectory()){
                String[] fileList = file.list();
                for(String f:fileList) {
                    String tmpPath = path + File.separator + f;
                    File tmpFile = new File(tmpPath);
                    if(tmpFile.isDirectory()){
                        delete(tmpPath);
                    }else{
                        tmpFile.delete();
                    }
                }
                file.delete();
            }else{
                file.delete();
            }
        }
        result = Boolean.TRUE;
        return result;
    }

    /**
     * 创建目录，如果目录已存在，则什么也不做
     * @param path 目录
     * @return boolean 是否成功
     */
    public static boolean mkdir(String path){
        boolean result = Boolean.FALSE;
        if(StringUtils.isNotBlank(path)){
            File file = new File(path);
            file.mkdir();
            result = Boolean.TRUE;
        }
        return result;
    }

    /**
     * 创建目录，如果目录已存在，则什么也不做
     * @param path 目录
     * @return boolean 是否成功
     */
    public static boolean mkdirs(String path){
        boolean result = Boolean.FALSE;
        if(StringUtils.isNotBlank(path)){
            File file = new File(path);
            file.mkdirs();
            result = Boolean.TRUE;
        }
        return result;
    }

    /**
     * 文件重命名
     * @param fromPath 源文件
     * @param toPath 目标文件
     * @return boolean
     */
    public static boolean move(String fromPath, String toPath){
        boolean result = Boolean.FALSE;
        Preconditions.checkNotNull(fromPath, "file fromPath must not be null!");
        Preconditions.checkNotNull(toPath, "file toPath must not be null!");
        File fromFile = new File(fromPath);
        File toFile = new File(toPath);
        if(fromFile.isFile()){
            try {
                Files.move(fromFile, toFile);
                result = Boolean.TRUE;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 读取一个文件，返回所有行，默认UTF-8编码
     * @param path
     * @return List
     */
    public static List<String> read(String path){
        return read(path, Charsets.UTF_8);
    }

    /**
     * 读取一个文件，返回所有行
     * @param path 文件路径
     * @param charset 字符集
     * @return List
     */
    public static List<String> read(String path, Charset charset){
        List<String> result = null;
        if(StringUtils.isNotBlank(path)){
            File file = new File(path);
            if(file.exists()){
                try {
                    result = Files.readLines(file, charset);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    /**
     * 去掉文件中的中文
     * @param sourceFile
     * @param toFile
     */
    public static void removeChinese(String sourceFile, String toFile){
        // 中文正则
        String chineseRegex = "[\u4e00-\u9fa5]";
        Pattern pattern = Pattern.compile(chineseRegex);
        List<String> result = new ArrayList<>();
        List<String> lines = read(sourceFile);
        if(lines != null){
            for(String line:lines){
                result.add(pattern.matcher(line).replaceAll(""));
            }
        }
        write(toFile, result);
    }

    /**
     * 去除字符串中的空格、回车、换行符、制表符
     * @param str
     * @param pattern "\\s*|\n|\r|\t"
     * @return
     */
    public static String replaceAll(String str, String pattern){
        String result = null;
        if(StringUtils.isNotBlank(str)){
            Pattern p = Pattern.compile(pattern);
            Matcher m = p.matcher(str);
            result = m.replaceAll("");
        }
        return result;
    }

    /**
     * 将文件转为UTF8文件
     * @param sourceFile
     * @param encode
     * @param utf8File
     */
    /*public static void toUtf8(String sourceFile, String encode, String utf8File){
        File gbk = new File(sourceFile);
        try {
            String content = org.apache.commons.io.FileUtils.readFileToString(gbk, encode);
            write(utf8File, content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    /**
     * 覆盖写入文件
     * @param path 文件路径
     * @param lines 内容
     * @return
     */
    public static boolean write(String path, List<String> lines){
        return write(path, Joiner.on("\n").join(lines), Charsets.UTF_8);
    }

    /**
     * 覆盖写入文件
     * @param path 文件路径
     * @param lines 内容
     * @param charset 字符集
     * @return
     */
    public static boolean write(String path, List<String> lines, Charset charset){
        return write(path, Joiner.on("\n").join(lines), charset);
    }

    /**
     * 覆盖写入文件
     * @param path 文件路径
     * @param content 内容
     * @return
     */
    public static boolean write(String path, String content){
        return write(path, content, Charsets.UTF_8);
    }

    /**
     * 覆盖写入文件
     * @param path 文件路径
     * @param content 内容
     * @param charset 字符集
     * @return
     */
    public static boolean write(String path, String content, Charset charset){
        boolean result = Boolean.FALSE;
        Preconditions.checkNotNull(path, "file path must not be null!");
        File file = new File(path);
        try {
            Files.write(content, file, charset);
            result = Boolean.TRUE;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {

        StringBuilder sb = new StringBuilder();
        List<String> list = read("d:/test/公路运输费用例子.txt");
        list.forEach(s -> sb.append(s));
        System.out.println(replaceAll(sb.toString(), "\\s*|\n|\r|\t"));
    }

}
