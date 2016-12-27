package cn.itcast.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

public class TestPoi {

	@Test
	public void testpoi() throws IOException{
		Workbook wb=new HSSFWorkbook();
		Sheet sheet=wb.createSheet();
		Row row=sheet.createRow(0);
		Cell cell= row.createCell(0);
		cell.setCellValue("fuck");
		OutputStream os=new FileOutputStream(new File("D:\\testpoi.xls"));
		wb.write(os);
		os.close();
	}
}
