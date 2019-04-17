package com.javabasic.test01;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public class CSVUtils {

    /**
     * 导出
     *
     * @param file
     * @param dataList
     * @return
     */
    public static boolean exportCsv(File file, List<String> dataList){
        boolean isSucess = false;
        List<Object> list = new ArrayList<Object>();

        FileOutputStream out = null;
        OutputStreamWriter osw = null;
        BufferedWriter bw = null;
        try {
            out = new FileOutputStream(file);
            osw = new OutputStreamWriter(out);
            bw = new BufferedWriter(osw);
            if(dataList != null && !dataList.isEmpty()){
                for(String data : dataList){
                    if(!data.contains(",") || !data.contains("\"")){
                        bw.append(data).append("\r");
                        System.out.println(data);
                        continue;
                    }
                    String[] s = data.split(",");
                    String s1 = s[0];
                    Integer i = Integer.parseInt(s1);
                    String s2 = s[1];
                    String s3 = s[2];
                    String s4 = s[3];
                    //float v = Float.parseFloat(s4);
                    String s5 = s[4];
                    Date date = formatTime(s5);
                    bw.append(i.toString()).append("\t").append(s2).append("\t").append(s3).append("\t").append(s4).append("\t").append(s5).append("\t").append("\r");
                    System.out.println(data);
                }
            }
            isSucess = true;
        } catch (Exception e) {
            isSucess = false;
        }finally{
            if(bw != null){
                try {
                    bw.close();
                    bw = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(osw != null){
                try {
                    osw.close();
                    osw = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(out != null){
                try {
                    out.close();
                    out = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return  isSucess;
    }

    private static Date formatTime(String str) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = (Date) formatter.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }


    /**
     * 导入
     *
     * @param file csv文件(路径+文件)
     * @return
     */
    public static List<String> importCsv(File file){
        List<String> dataList = new ArrayList<String>();

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            String line = "";
            while ((line = br.readLine()) != null) {
                dataList.add(line);
            }
        }catch (Exception e) {
        }finally{
            if(br != null){
                try {
                    br.close();
                    br = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return dataList;
    }
}
