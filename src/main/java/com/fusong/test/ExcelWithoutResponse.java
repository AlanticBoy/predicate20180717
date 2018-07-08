package com.fusong.test;

import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author:付风松
 * @Description:
 * @Date:Created in  11:07 2018/6/709
 * @ModefiedBy:
 */
public class ExcelWithoutResponse {

    private final static String TEMPLATE_PATH = "E:\\medicalTrain.xlsx";

    public static void ExportNoResponse(Map<Integer, List<Map<String, String>>> allMap_) throws Exception {

        final BlockingQueue<PrintService> queue = new ArrayBlockingQueue<PrintService>(162);
                  /*导出模版路径*/
        String filePath = "";
        for (int i = 0; i < allMap_.size(); i++) {
            System.out.println(" whose size is  " + allMap_.size() + " is executing current loop " + i);
            filePath = "E:\\medical\\train_" + i + ".xlsx";
            XSSFWorkbook workbook = null;
            try {
                workbook = new XSSFWorkbook(TEMPLATE_PATH);
            } catch (IOException e) {
                e.printStackTrace();
            }
            // 获取sheet页
            XSSFSheet sheet = workbook.getSheetAt(0);
            // 行
            XSSFRow row = null;
            // 行下标
            int rowNum = 1;
           /*遍历查询结果*/
            List<Map<String, String>> mapList = allMap_.get(i);
            System.out.println("  has records  " + mapList.size());
            for (int n = 0; n < mapList.size(); n++) {
                Map<String, String> map = mapList.get(n);
                 /*定义列下标*/
                int cellNum = 0;
                row = sheet.createRow(rowNum++);
                row.createCell(cellNum++).setCellValue(map.get("M01"));
                row.createCell(cellNum++).setCellValue(map.get("M02"));
                row.createCell(cellNum++).setCellValue(map.get("M03"));
                row.createCell(cellNum++).setCellValue(map.get("M04"));
                row.createCell(cellNum++).setCellValue(map.get("M05"));
                row.createCell(cellNum++).setCellValue(map.get("M06"));
                row.createCell(cellNum++).setCellValue(map.get("M07"));
                row.createCell(cellNum++).setCellValue(map.get("M08"));
                row.createCell(cellNum++).setCellValue(map.get("M09"));
                row.createCell(cellNum++).setCellValue(map.get("O01"));
                row.createCell(cellNum++).setCellValue(map.get("O02"));
                row.createCell(cellNum++).setCellValue(map.get("O03"));
                row.createCell(cellNum++).setCellValue(map.get("O04"));
                row.createCell(cellNum++).setCellValue(map.get("O05"));
                row.createCell(cellNum++).setCellValue(map.get("O06"));
                row.createCell(cellNum++).setCellValue(map.get("O07"));
                row.createCell(cellNum++).setCellValue(map.get("J01"));
                row.createCell(cellNum++).setCellValue(map.get("J02"));
                row.createCell(cellNum++).setCellValue(map.get("J03"));
                row.createCell(cellNum++).setCellValue(map.get("J04"));
                row.createCell(cellNum++).setCellValue(map.get("J05"));
                row.createCell(cellNum++).setCellValue(map.get("J06"));
                row.createCell(cellNum++).setCellValue(map.get("C01"));
                row.createCell(cellNum++).setCellValue(map.get("C02"));
                row.createCell(cellNum++).setCellValue(map.get("C03"));
                row.createCell(cellNum++).setCellValue(map.get("C04"));
                row.createCell(cellNum++).setCellValue(map.get("C05"));
                row.createCell(cellNum++).setCellValue(map.get("C06"));
                row.createCell(cellNum++).setCellValue(map.get("C07"));
                row.createCell(cellNum++).setCellValue(map.get("C08"));
                row.createCell(cellNum++).setCellValue(map.get("H01"));
                row.createCell(cellNum++).setCellValue(map.get("P01"));
                row.createCell(cellNum++).setCellValue(map.get("P02"));
                row.createCell(cellNum++).setCellValue(map.get("P03"));
                row.createCell(cellNum++).setCellValue(map.get("P04"));
                row.createCell(cellNum++).setCellValue(map.get("P05"));
                row.createCell(cellNum++).setCellValue(map.get("P06"));
                row.createCell(cellNum++).setCellValue(map.get("P07"));
                row.createCell(cellNum++).setCellValue(map.get("P08"));
                row.createCell(cellNum++).setCellValue(map.get("P09"));
                row.createCell(cellNum++).setCellValue(map.get("P10"));
                row.createCell(cellNum++).setCellValue(map.get("Group_"));

            }
            FileOutputStream fout = null;
            try {
                fout = new FileOutputStream(filePath);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            // 第六步，将文件存到指定位置
            try {
                workbook.write(fout);


                System.out.println(" export failure ");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(" export failure ");
            } finally {
                fout.close();
            }
        }

        /*for (int i = 0; i < 20; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    FileOutputStream stream = null;
                    while (true) {
                        try {
                            PrintService service = queue.take();
                            XSSFWorkbook workbook = service.getWorkbook();
                            stream = service.getOutputStream();
                            System.out.println(" workbook " + workbook + "  FileOutputStream " + stream);
                            workbook.write(stream);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } finally {
                            try {
                                stream.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }).start();
        }*/
    }


    public static void ExportNoResponseWithThird(List<Map<String, String>> allMap_) throws Exception {
        /*导出模版路径*/
        String filePath = "";
        filePath = "E:\\20180618\\medical_.xlsx";

        XSSFWorkbook workbook = new XSSFWorkbook("E:\\DQ_ML.xlsx");
        // 获取sheet页
        XSSFSheet sheet = workbook.getSheetAt(0);
        // 行
        XSSFRow row = null;
        // 行下标
        int rowNum = 1;

        for (int n = 0; n < allMap_.size(); n++) {
            Map<String, String> map = allMap_.get(n);
                 /*定义列下标*/
            int cellNum = 0;
            row = sheet.createRow(rowNum++);
            Iterator<String> iterator = map.keySet().iterator();
            while (iterator.hasNext()) {
                String key = iterator.next();
                row.createCell(cellNum++).setCellValue(map.get(key));
            }
        }
        FileOutputStream fout = new FileOutputStream(filePath);
        // 第六步，将文件存到指定位置
        try {
                /*threadPool.execute(new WorkThread(workbook, fout,lock));*/
            workbook.write(fout);
            System.out.println(" export success ");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(" export failure ");
        } finally {
            fout.close();
        }
       /* threadPool.shutdown();*/
    }

   /* static class WorkThread implements Runnable {

        private XSSFWorkbook xssfWorkbook;
        private FileOutputStream outputStream;

        private Lock lock;

        public WorkThread(XSSFWorkbook xssfWorkbook, FileOutputStream outputStream, Lock lock) {
            this.xssfWorkbook = xssfWorkbook;
            this.outputStream = outputStream;
            this.lock = lock;
        }

        @Override
        public void run() {
            System.out.println("  start a new thread ");
            try {
                lock.lock();
                xssfWorkbook.write(outputStream);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                lock.unlock();
            }
        }
    }*/
}
