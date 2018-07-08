package com.fusong.test;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;

/**
 * @Author:付风松
 * @Description:
 * @Date:Created in  16:30 2018/6/19
 * @ModefiedBy:
 */
public class PrintService {
    private XSSFWorkbook workbook;
    private FileOutputStream outputStream;

    public XSSFWorkbook getWorkbook() {
        return workbook;
    }

    public PrintService(XSSFWorkbook workbook, FileOutputStream outputStream) {
        this.workbook = workbook;
        this.outputStream = outputStream;
    }

    public void setWorkbook(XSSFWorkbook workbook) {
        this.workbook = workbook;
    }

    public FileOutputStream getOutputStream() {
        return outputStream;
    }

    public void setOutputStream(FileOutputStream outputStream) {
        this.outputStream = outputStream;
    }
}
