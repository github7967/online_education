package com.atguigu.eduservice.excel;

import com.alibaba.excel.EasyExcel;
import org.junit.Test;

/**
 * @author LiSong
 * @since 2022-09-24
 */
public class TestEasyExcel {
//    public static void main(String[] args) {
//        //实现Excel读操作
//        String filename = "D:\\JAVA\\JavaPerjects\\ProjectCollection\\OnlineOducation\\BackEnd\\write.xlsx";
//        EasyExcel.read(filename, DemoData.class, new ExcelListener()).sheet().doRead();
//    }

    @Test
    public void test() {
        //实现Excel读操作
        String filename = "D:\\JAVA\\JavaPerjects\\ProjectCollection\\OnlineOducation\\BackEnd\\write.xlsx";
        EasyExcel.read(filename, DemoData.class, new ExcelListener()).sheet().doRead();
    }
}
