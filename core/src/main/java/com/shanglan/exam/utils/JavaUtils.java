package com.shanglan.exam.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by cuishiying on 2017/6/29.
 */
public class JavaUtils {

    /**
     *  java去除字符串中的空格、回车、换行符、制表符
     * @param str
     * @return
     */
    public static String replaceBlank(String str) {
        String dest = "";
        if (str!=null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }
}
