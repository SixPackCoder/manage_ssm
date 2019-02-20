package com.luobin.manage_ssm.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    /**
     * 传入date和需要转成的格式  得到一个字符串的日期格式
     * @param date
     * @param style
     * @return
     */
    public static String dateToString(Date date, String style){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(style);
        String s = simpleDateFormat.format(date);
        return s;
    }

    /**
     * 字符串日期转为date格式日期
     * @param date_Str
     * @return
     */
    public static Date stringToDate(String date_Str){
        Date date = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        try {
            date = simpleDateFormat.parse(date_Str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
