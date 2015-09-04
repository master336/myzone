package com.web.core.utility;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

/**
 * Created by sks on 2015/7/31.
 */
public class ExcelUtils {
    static Logger log = Logger.getLogger(ExcelUtils.class);

    /**
     * 功能：生成Excel的Workbook对象
     * @param dataSet 需要导出的数据列表
     * @param sheetName Excel文件的表单名称
     * @param columnNameAndTitle map格式，excel的列名和java对象属性名的对应关系，key为生成excel文件的列名称，value为excel每一列数据对应的属性名，属性名必须与javabean中的属性名完全一致。
     * @return  Workbook表单对象。
     */
    public static Workbook createBook(List<Object> dataSet,String sheetName,Map<String,String> columnNameAndTitle){
        // 创建excel工作簿
        Workbook wb = new HSSFWorkbook();
        // 创建第一个sheet（页），并命名
        Sheet sheet = wb.createSheet(sheetName);
        // 手动设置列宽。第一个参数表示要为第几列设；，第二个参数表示列的宽度，n为列高的像素数。
        for(int i=0;i<columnNameAndTitle.size();i++){
            sheet.setColumnWidth((short) i, (short) (35.7 * 150));
        }

        // 创建第一行
        Row row = sheet.createRow((short) 0);

        // 创建两种单元格格式
        CellStyle cs = wb.createCellStyle();
        CellStyle cs2 = wb.createCellStyle();

        // 创建两种字体
        Font f = wb.createFont();
        Font f2 = wb.createFont();

        // 创建第一种字体样式（用于列名）
        f.setFontHeightInPoints((short) 10);
        f.setColor(IndexedColors.BLACK.getIndex());
        f.setBoldweight(Font.BOLDWEIGHT_BOLD);

        // 创建第二种字体样式（用于值）
        f2.setFontHeightInPoints((short) 10);
        f2.setColor(IndexedColors.BLACK.getIndex());

//      Font f3=wb.createFont();
//      f3.setFontHeightInPoints((short) 10);
//      f3.setColor(IndexedColors.RED.getIndex());

        // 设置第一种单元格的样式（用于列名）
        cs.setFont(f);
        cs.setBorderLeft(CellStyle.BORDER_THIN);
        cs.setBorderRight(CellStyle.BORDER_THIN);
        cs.setBorderTop(CellStyle.BORDER_THIN);
        cs.setBorderBottom(CellStyle.BORDER_THIN);
        cs.setAlignment(CellStyle.ALIGN_CENTER);

        // 设置第二种单元格的样式（用于值）
        cs2.setFont(f2);
        cs2.setBorderLeft(CellStyle.BORDER_THIN);
        cs2.setBorderRight(CellStyle.BORDER_THIN);
        cs2.setBorderTop(CellStyle.BORDER_THIN);
        cs2.setBorderBottom(CellStyle.BORDER_THIN);
        cs2.setAlignment(CellStyle.ALIGN_CENTER);
        //设置列名
        int columnIndex = 0;
        for(Map.Entry<String,String> entry:columnNameAndTitle.entrySet()){
            Cell cell = row.createCell(columnIndex++);
            cell.setCellValue(entry.getKey());
            cell.setCellStyle(cs);
        }

        //设置每行每列的值
        Object o = null;
        for (short i = 0; i < dataSet.size(); i++) {
            // Row 行,Cell 方格 , Row 和 Cell 都是从0开始计数的
            // 创建一行，在页sheet上
            Row row1 = sheet.createRow((short) (i+1));
            // 在row行上创建一个方格
            columnIndex = 0;
            o = dataSet.get(i);
            Object value = null;
            for(Map.Entry<String,String> entry:columnNameAndTitle.entrySet()){
                Cell cell = row1.createCell(columnIndex++);
                //cell.setCellValue(list.get(i).get(keys[j]) == null?" ": list.get(i).get(keys[j]).toString());
                value = getFieldValue(entry.getValue(),o);
                cell.setCellValue(value == null?"":value.toString());
                cell.setCellStyle(cs2);
            }
        }
        return wb;
    }

    public static Object getFieldValue(String fieldName, Object distObj){
        Object result = null;
        try{
            Class clazz = distObj.getClass();
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            result = field.get(distObj);
        }catch(Exception e){
            log.error("ExcelUtils -> getFieldValue,反射获取字段值失败");
        }

        return result;
    }
    /***
     * Excel 导出
     * @param  dataSet 需要导出的数据清单
     * @param columnNameAndTitle  数据对照关系
     * @param sheetName  sheet页名称
     * @param fileName 文件名
     * @param response 请求相应对象
     * */
    public static HttpServletResponse exportExcel(List<Object> dataSet,String sheetName,Map<String,String> columnNameAndTitle,String fileName,HttpServletResponse response){
        /**处理相应请求及类型*/
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        try{
            fileName = new String(fileName.getBytes("UTF-8"),"ISO-8859-1");
        }catch (Exception e){
        }
        response.setHeader("Content-disposition", "attachment;filename=" +fileName +".xls");
        Workbook book = ExcelUtils.createBook(dataSet,sheetName, columnNameAndTitle);
        /**写入流*/
        try{
            ServletOutputStream out = response.getOutputStream();
            book.write(out);
            out.flush();
            out.close();
        }catch (Exception e){
        }
        return response;
    }
}

