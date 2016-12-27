package cn.itcast.jk.controller.print;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itcast.jk.controller.BaseController;
import cn.itcast.jk.domain.Contract;
import cn.itcast.jk.service.ContractService;
import cn.itcast.jk.vo.ContractVO;
import cn.itcast.util.DownloadUtil;

@Controller
public class ContractPrintController extends BaseController {

	@Resource
	ContractService contractService;

	@RequestMapping("/toprint.action")
	public String toprint(Model model) {
		return "/print/jPrint.jsp";
	}

	@RequestMapping("/toview.action")
	public String toview() {
		return "/print/jView.jsp";
	}

	@RequestMapping("/view.action")
	public String view(String contractId, Model model) {
		List<ContractVO> dataList = contractService.view(contractId);
		model.addAttribute("dataList", dataList);
		return "/print/jView.jsp";
	}

	@RequestMapping("/print.action")
	public String print(HttpServletResponse response, Model model)
			throws IOException {
		List<Contract> dataList = contractService.findAllByContractId();
		Workbook wb = new HSSFWorkbook();
		Sheet sheet = wb.createSheet();
//		sheet.setColumnWidth(1, 256 * 15);
//		sheet.setColumnWidth(2, 256 * 15);
//		sheet.setColumnWidth(3, 256 * 15);
//		sheet.setColumnWidth(4, 256 * 15);
//		sheet.setColumnWidth(5, 256 * 15);
//		sheet.setColumnWidth(6, 256 * 25);
//		sheet.setColumnWidth(7, 256 * 25);
		// sheet.setColumnWidth(0, 80);
//		sheet.setAutoFilter(new CellRangeAddress(1, 1, 7, 7));
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 1, 7));
		int rowIndex = 1;
		int cellIndex = 1;
		Row row = sheet.createRow(1);
		row.setHeightInPoints(40);
		Cell cell = row.createCell(1);
		cell.setCellValue("购销合同报表");
		cell.setCellStyle(this.setTableNameStyle(wb));
		rowIndex++;
		String[] heads = { "", "总金额", "货物数", "附件数", "客户", "合同日期", "船期", "合同号" };
		row = sheet.createRow(rowIndex);
		row.setHeightInPoints(36);
		// row.setRowStyle(this.setTitleStyle(wb));
		for (int i = 1; i <= 7; i++) {
			cellIndex = i;
			cell = row.createCell(cellIndex);
			cell.setCellStyle(this.setTitleStyle(wb));
			cell.setCellValue(heads[i]);
		}
		for (int i = 0; i < dataList.size(); i++) {
			row = sheet.createRow(++rowIndex);
			row.setHeightInPoints(33);
			Contract obj = dataList.get(i);
			for (int j = 1; j <= 7; j++) {
				cellIndex = j;
				cell = row.createCell(cellIndex);
				cell.setCellStyle(this.setTextStyle(wb));
				switch (j) {
				case 1:
					cell.setCellValue(obj.getTotalAmount() != null ? obj
							.getTotalAmount() + "" : "空");
					break;
				case 2:
					cell.setCellValue(obj.getCpNum() != null ? obj.getCpNum()
							+ "" : "空");
					break;
				case 3:
					cell.setCellValue(obj.getExtNum() != null ? obj.getExtNum()
							+ "" : "空");
					break;
				case 4:
					cell.setCellValue(obj.getCustomName() != null ? obj
							.getCustomName() + "" : "空");
					break;
				case 5:
					cell.setCellValue(obj.getDeliveryPeriod() != null ? obj
							.getDeliveryPeriod() + "" : "空");
					break;
				case 6:
					cell.setCellValue(obj.getShipTime() != null ? obj
							.getShipTime() + "" : "空");
					break;
				case 7:
					cell.setCellValue(obj.getId() != null ? obj.getId() + ""
							: "空");
					break;
				}
			}

		}

		DownloadUtil du = new DownloadUtil();
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		wb.write(os);
		du.download(os, response, "出货表.xls");

		// OutputStream os=new FileOutputStream(new File("D:\\testpoi.xls"));
		// wb.write(os);
		// os.close();
		return "/print/jPrint.jsp";
	}

	public CellStyle setTitleStyle(Workbook wb) {
		CellStyle style = wb.createCellStyle();
		Font font = wb.createFont();
		font.setFontName("Microsoft JhengHei UI");
		font.setFontHeightInPoints((short) 10);
		font.setBoldweight((short) 10);
		style.setFont(font);
		style.setAlignment(CellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		style.setBorderTop(CellStyle.BORDER_THIN);
		style.setBorderBottom(CellStyle.BORDER_THIN);
		style.setBorderLeft(CellStyle.BORDER_THIN);
		style.setBorderRight(CellStyle.BORDER_THIN);
		return style;
	}

	public CellStyle setTableNameStyle(Workbook wb) {
		CellStyle style = wb.createCellStyle();
		Font font = wb.createFont();
		font.setFontName("Microsoft JhengHei UI");
		font.setFontHeightInPoints((short) 12);
		font.setBoldweight((short) 20);
		font.setColor((short) 30);
		style.setFont(font);
		style.setAlignment(CellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		return style;
	}

	public CellStyle setTextStyle(Workbook wb) {
		CellStyle style = wb.createCellStyle();
//		style.setWrapText(true);
		Font font = wb.createFont();
		font.setFontName("Microsoft JhengHei UI");
		font.setFontHeightInPoints((short) 10);
		font.setBoldweight((short) 10);
		style.setFont(font);
		style.setAlignment(CellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		style.setBorderTop(CellStyle.BORDER_THIN);
		style.setBorderBottom(CellStyle.BORDER_THIN);
		style.setBorderLeft(CellStyle.BORDER_THIN);
		style.setBorderRight(CellStyle.BORDER_THIN);
		return style;
	}

}
