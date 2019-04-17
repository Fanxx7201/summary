package com.javabasic.test01;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @ProjectName: yunlian-ccm
 * @Package: com.sinochem.yunlian.yunlianccm.util
 * @Description:
 * @Author: fanxx
 * @CreateDate: 2018/12/20 13:22
 * <p>Copyright: Copyright (c) 2018</p>
 */
public class DateFormatUtil {

        public static Date TzFormat(String oldDate)throws ParseException {
            Date date1 = null;
            //DateFormat df2 = null;
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Date date = df.parse(oldDate);
            SimpleDateFormat df1 = new SimpleDateFormat("yyyyMMdd", Locale.US);
            date1 = df1.parse(date.toString());
            return date1;
        }

        /**
         * date类型进行格式化输出
         * @param date
         * @return
         */
        public static String dateFormat(Date date) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateString = formatter.format(date);
            return dateString;
        }

        /**
         * 将"2015-08-31 21:08:06"型字符串转化为Date
         * @param str
         * @return
         * @throws ParseException
         */
        public static Date StringToDate(String str) throws ParseException {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = (Date) formatter.parse(str);
            return date;
        }

        /**
         * 将CST时间类型字符串进行格式化输出
         * @param str
         * @return
         * @throws ParseException
         */
        public static String CSTFormat(String str) throws ParseException {
            SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
            Date date = (Date) formatter.parse(str);
            return dateFormat(date);
        }


        /**
         * 将long类型转化为Date
         * @param str
         * @return
         * @throws ParseException
         */
        public static Date LongToDare(long str) throws ParseException {
            return new Date(str * 1000);
        }


        /**
         * @Description  判断时间间隔是否超过3个月
         * @Date  2019/2/25
         * @Param [d1, d2]
         * @return java.lang.Integer
         **/
        public static boolean moreThenThreeMonth(Date d1){
            Date d = new Date();
            boolean b = Math.abs(d.getTime() - d1.getTime()) > 24 * 60 * 60 * 1000 * 90;
            return b;
        }


    public static void main(String[] args) {
            String date = "1988-02-16";
        Date date1 = null;
        try {
            date1 = DateFormatUtil.TzFormat(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(date1);
    }
}
