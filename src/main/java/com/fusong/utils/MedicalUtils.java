package com.fusong.utils;

import com.predicate.user.model.PageUnit;
import com.predicate.user.model.User;
import com.predicate.user.service.UserService;
import net.sf.json.JSONObject;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:付风松
 * @Description:
 * @Date:Created in  17:49 2018/4/21
 * @ModefiedBy:
 */
public class MedicalUtils {


    /*判断并获取excel值*/
    private static String getCellValue(Cell cell) {
        String cellValue = "";
        if (cell == null) {
            return cellValue;
        }
        //把数字当成String来读，避免出现1读成1.0的情况
        if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            cell.setCellType(Cell.CELL_TYPE_STRING);
        }
        //判断数据的类型
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_NUMERIC: //数字
                cellValue = String.valueOf(cell.getNumericCellValue());
                break;
            case Cell.CELL_TYPE_STRING: //字符串
                cellValue = String.valueOf(cell.getStringCellValue());
                break;
            case Cell.CELL_TYPE_BOOLEAN: //Boolean
                cellValue = String.valueOf(cell.getBooleanCellValue());
                break;
            case Cell.CELL_TYPE_FORMULA: //公式
                cellValue = String.valueOf(cell.getCellFormula());
                break;
            case Cell.CELL_TYPE_BLANK: //空值
                cellValue = "";
                break;
            case Cell.CELL_TYPE_ERROR: //故障
                cellValue = "非法字符";
                break;
            default:
                cellValue = "未知类型";
                break;
        }
        return cellValue;
    }


    /*导入excel用Map方式*/
    public static void importUserInfoByMap(File file/*, UserService userService*/) throws Exception {
        XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(file));
        // 获取sheet页
        XSSFSheet sheet = workbook.getSheetAt(0);
        //获得当前sheet的结束行
        int num = sheet.getLastRowNum();// 获取总行数
        List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
        for (int i = 1; i <= num; i++) {
            // 获得当前行
            XSSFRow row = sheet.getRow(i);
            if (row == null)
                continue;
            Map<String, Object> map = new HashMap<String, Object>();
            // 列标
            int cellNum = 0;
                /*得到联系电话*/
            System.out.println(" before ");
            if (row.getCell(cellNum) != null) {
                map.put("phone", getCellValue(row.getCell(cellNum)));
            }

            cellNum++;
                /*得到姓名*/
            if (row.getCell(cellNum) != null) {
                map.put("name", getCellValue(row.getCell(cellNum)));
            }

            cellNum++;
            //得到密码
            map.put("password", getCellValue(row.getCell(cellNum)));
            cellNum++;
            //得到邮件
            map.put("email", getCellValue(row.getCell(cellNum)));
            cellNum++;
            //得到头像
            map.put("imageUrl", getCellValue(row.getCell(cellNum)));
            cellNum++;
              /*得到用户身份*/
            if (row.getCell(cellNum) != null) {
                map.put("status", getCellValue(row.getCell(cellNum)));
            }
            System.out.println(" name "+map.get("name")+" phone "+map.get("phone")+"password "+map.get("password"));
            maps.add(map);
        }
        /*userService.importExcelByMap(maps);
        */
    }

    /*导出用户信息*/
    public void exportUserInfo(HttpServletRequest request, HttpServletResponse response, UserService userService) {
        /*为了简便，这里不限定任何条件的查询，得到所有用户*/
        List<PageUnit> pageUnits = null;
        try {
            pageUnits = userService.selectAllUnit("hahaha");
        } catch (Exception e) {
            e.printStackTrace();
        }
        XSSFWorkbook workbook = null;
        /*获取模版路径*/
        String filePath = request.getServletContext().getRealPath("export/用户信息导出模版.xlsx");

        try {
            workbook = new XSSFWorkbook(new FileInputStream(filePath));
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
        for (int i = 0; i < pageUnits.size(); i++) {
            PageUnit pageUnit = pageUnits.get(i);
         /*定义列下标*/
            int cellNum = 0;
            row = sheet.createRow(rowNum++);
            row.createCell(cellNum++).setCellValue(pageUnit.getId());//给第row行第一列赋值
            row.createCell(cellNum++).setCellValue(pageUnit.getName());//给第row行第二列赋值
            row.createCell(cellNum++).setCellValue(pageUnit.getPassword());//给第row行第三列赋值
            row.createCell(cellNum++).setCellValue(pageUnit.getSex());//给第row行第四列赋值
            row.createCell(cellNum++).setCellValue(pageUnit.getEmail());//给第row行第五列赋值
            row.createCell(cellNum++).setCellValue(pageUnit.getAddress());//给第row行第六列赋值
        }
        try {
            exportExcel(response, workbook, "用户导出信息");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /*下载导入模版*/
    public void downloadTemplate(HttpServletRequest request, HttpServletResponse response) {

        /*获取模版路径*/
        String filepath = request.getServletContext().getRealPath("export/用户信息导出模版.xlsx");
        XSSFWorkbook workbook = null;
        try {
            workbook = new XSSFWorkbook(new FileInputStream(filepath));
            exportExcel(response, workbook, "用户导出信息");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void exportExcel(HttpServletResponse response, XSSFWorkbook wb, String name) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition",
                "attachment; fileName=" + new String(name.getBytes("UTF-8"), "ISO-8859-1") + ".xlsx");
        OutputStream os = response.getOutputStream();
        wb.write(os);
        os.flush();
        os.close();
    }

}
