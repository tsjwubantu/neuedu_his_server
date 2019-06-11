package com.neuedu.his.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

/** 
* Excel操作
* @auther: 东软教师-liyw
* @date:   2019-01-29
*/ 
public class ExcelUtil {

	private static final String EXCEL_XLS = "xls";
	private static final String EXCEL_XLSX = "xlsx";

	

	/**
	 * 读取Excel测试，兼容 Excel 2003/2007/2010
	 * 
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		// 同时支持Excel 2003、2007
		File excelFile = new File("d:/product.xlsx"); // 创建文件对象
		List<Map<String, Object>> dataList = readExcel(excelFile);
		for(Map<String,Object> map : dataList) {
			Set<Entry<String, Object>> set = map.entrySet();
			for(Entry entry : set) {
				System.out.print(entry.getKey()+":"+entry.getValue()+"  ");
			}
			System.out.println();
		}
	}
	public static List<Map<String,Object>> readExcel(MultipartFile file) throws Exception{
		List<Map<String, Object>> dataList = new ArrayList<>();
		boolean isCheck = checkExcelVaild(file);
		if(isCheck) {
			Workbook wb = getWorkbook(file);
			List<Sheet> list = readWorkbook(wb);
			
			for(Sheet sheet : list) {
				dataList = readCell(sheet);
				
			}
		}
		return dataList;
	}
	public static List<Map<String,Object>> readExcel(File file) throws Exception{
		List<Map<String, Object>> dataList = new ArrayList<>();
		if(checkExcelVaild(file)) {
			Workbook wb = getWorkbook(file);
			List<Sheet> list = readWorkbook(wb);			
			for(Sheet sheet : list) {
				dataList = readCell(sheet);
			}
		}
		return dataList;
	}
	/**
	 * 判断文件是否是excel
	 * @throws IOException 
	 * @throws Exception
	 */
	private static boolean checkExcelVaild(MultipartFile file) throws IOException {
		if (file==null || file.getInputStream()==null) {
			return false;
		}
		String fileName = file.getOriginalFilename();
		if (fileName.endsWith(EXCEL_XLS) || fileName.endsWith(EXCEL_XLSX)) {
			return true;
		}
		return false;
	}
	/**
	 * 判断文件是否是excel
	 * @throws Exception
	 */
	private static boolean checkExcelVaild(File file) throws IOException {
		if (!file.exists()) {
			return false;
		}
		if (!(file.isFile() && (file.getName().endsWith(EXCEL_XLS) || file.getName().endsWith(EXCEL_XLSX)))) {
			return false;
		}
		return true;
	}
	/**
	 * 判断Excel的版本,获取Workbook
	 * @param in
	 * @param filename
	 * @return
	 * @throws IOException
	 */
	private static Workbook getWorkbook(MultipartFile file) throws IOException {
		InputStream in = file.getInputStream();
		Workbook wb = null;
		if (file.getOriginalFilename().endsWith(EXCEL_XLS)) { // Excel 2003
			wb = new HSSFWorkbook(in);
		} else if (file.getOriginalFilename().endsWith(EXCEL_XLSX)) { // Excel 2007/2010
			wb = new XSSFWorkbook(in);
		}
		return wb;
	}
	/**
	 * 判断Excel的版本,获取Workbook
	 * @param in
	 * @param filename
	 * @return
	 * @throws IOException
	 */
	private static Workbook getWorkbook(File file) throws IOException {
		InputStream in = new FileInputStream(file);
		return getWorkbook(in , file);
	}
	/**
	 * 判断Excel的版本,获取Workbook
	 * @param in
	 * @param filename
	 * @return
	 * @throws IOException
	 */
	private static Workbook getWorkbook(InputStream in, File file) throws IOException {
		Workbook wb = null;
		if (file.getName().endsWith(EXCEL_XLS)) { // Excel 2003
			wb = new HSSFWorkbook(in);
		} else if (file.getName().endsWith(EXCEL_XLSX)) { // Excel 2007/2010
			wb = new XSSFWorkbook(in);
		}
		return wb;
	}

	
	//读取文件中所有sheet工作簿
	public static List<Sheet> readWorkbook(Workbook workbook) {
		List<Sheet> sheetList = new ArrayList<>();
		int sheetCount = workbook.getNumberOfSheets(); // Sheet的数量
		System.out.println("sheet数量为："+sheetCount);
		if(sheetCount<=0) {
			throw new RuntimeException("没有sheet！！");
		}
		for(int i=0;i<sheetCount;i++) {
			 Sheet sheet = workbook.getSheetAt(i);  
             if(sheet == null){  
                 continue;  
             }
			sheetList.add(sheet);
		}
		return sheetList;
	}

	//读取sheet中所有cell单元格,返回的Map中使用第一行数据为key，每行对应cell值为value
	public static List<Map<String,Object>> readCell(Sheet sheet) {
		List<Map<String,Object>> list = new ArrayList<>();
		if(sheet==null||sheet.getLastRowNum()<1) {//没有行数据
			return list;
		}else {
			if(sheet.getRow(1).getPhysicalNumberOfCells()<=0) {//没有列数据
				return list;
			}else {
				//获得当前sheet的开始行
				int firstRowNum  = sheet.getFirstRowNum();
				Row rowKey = sheet.getRow(firstRowNum);
				
				for(int i=firstRowNum+1;i<=sheet.getLastRowNum();i++) {
					Row rowValue = sheet.getRow(i);
					// 如果当前行没有数据，跳出循环
					if(rowValue == null){  
	                    continue;  
	                }
					Map<String,Object> map = new HashMap<>();
					//获得当前行的开始列  
                    int firstCellNum = rowValue.getFirstCellNum();  
                    //获得当前行的列数  (空格的不计算)
                    int lastCellNum = rowValue.getPhysicalNumberOfCells();  
                    //循环当前行  
                    for(int j = firstCellNum; j < lastCellNum;j++){
                    	String cellKey = rowKey.getCell(j).getStringCellValue();
                        Object cellValue = getValue(rowValue.getCell(j));                        
                        map.put(cellKey, cellValue);                        
                    }
                    list.add(map);
				}
			}
		}
		return list;
	}
	
	//读取每个单元格数据
	private static Object getValue(Cell cell) {
		String cellValue = "";  
        if(cell == null){  
            return cellValue;  
        }  
        //把数字当成String来读，避免出现1读成1.0的情况  
        if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){  
            cell.setCellType(Cell.CELL_TYPE_STRING);  
        }  
        //判断数据的类型  
        switch (cell.getCellType()){  
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
				try {
					cellValue = String.valueOf(cell.getNumericCellValue());					
				} catch (IllegalStateException e) {
					cellValue = String.valueOf(cell.getStringCellValue());
				} catch (Exception e) {
					cellValue = String.valueOf(cell.getStringCellValue());
				}
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
}
