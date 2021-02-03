package kr.or.ddit.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.formula.functions.Column;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.servlet.view.AbstractView;

import kr.or.ddit.ioc.vo.UserVo;

public class ExcelDownloadView extends AbstractView{

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		//content type
		response.setContentType("application/vnd.ms-excel; charset=utf-8");
		response.setHeader("Content-Disposition","attachement; filename=text.xlsx");
		
		//header : List<String>
		//data : List<UserVo>
		
		List<String> header = (List<String>)model.get("header");
		List<UserVo> data = (List<UserVo>)model.get("data");
		
		//excel 파일 생성
		XSSFWorkbook book = new XSSFWorkbook();
		Sheet sheet = book.createSheet("users");
		
		int rownum = 0;
		int colnum = 0;
		
		Row row = sheet.createRow(rownum++);
		

		for(String h : header) {
			Cell cell = row.createCell(colnum++);
			cell.setCellValue(h);
			
		}
		
		for(UserVo vo : data) {
			//colnum ++ 하면서 가로열 채우고 입력되고
			//rownum ++ 하면서 아래쪽으로 한칸씩 내려간다.
			colnum = 0;
			row = sheet.createRow(rownum++);
			Cell cell = row.createCell(colnum++);
			cell.setCellValue(vo.getUserid());
			
			cell = row.createCell(colnum++);
			cell.setCellValue(vo.getUsernm());
			
			cell = row.createCell(colnum++);
			cell.setCellValue(vo.getAlias());
		}
		
		//data는 나중에
		book.write(response.getOutputStream());
	}

}
