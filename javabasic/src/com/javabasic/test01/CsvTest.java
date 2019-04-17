package com.javabasic.test01;


import java.io.File;
import java.util.List;


/**
 * @ProjectName: sum
 * @Package: com.javabasic.test01
 * @Description:
 * @Author: fanxx
 * <p>Copyright: Copyright (c) 2019</p>
 */
public class CsvTest {
    public static void main(String[] args) {
        //导入
        List<String> dataList = CSVUtils.importCsv(new File("D:/test/1.csv"));
        //导出
        CSVUtils.exportCsv(new File("D:/test/output.txt"), dataList);
    }
}
