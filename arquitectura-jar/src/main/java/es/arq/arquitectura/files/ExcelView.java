package es.arq.arquitectura.files;

/**
 * @author tsanzseg
 * 
 */
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import es.arq.arquitectura.annotations.ColumnExcel;

public class ExcelView extends AbstractExcelView {
    @SuppressWarnings({ "unchecked" })
	@Override
    protected void buildExcelDocument(
            Map<String, Object> model, HSSFWorkbook book,
            HttpServletRequest request, HttpServletResponse response)
            throws IllegalAccessException {
    	
			//create a wordsheet
			HSSFSheet sheet = book.createSheet("Hoja");
     
			List<Object> lst = new ArrayList<Object>(); 
			for (Map.Entry<String, Object> entry : model.entrySet()) {
				lst = (List<Object>)entry.getValue();
			}
			    	
			setDataHeader(lst, sheet, book);
			setDataSheet(lst, sheet, book);
    }
    
    private void setDataHeader(List<Object> lst, HSSFSheet sheet, HSSFWorkbook book){
    	if(!lst.isEmpty()){
			Object le = lst.get(0);
			Field[] fields = le.getClass().getDeclaredFields();

		    //create the row data
		    HSSFRow row = sheet.createRow(0);
		    
			int cellNum = 0;
	    	boolean visible = true;
	    	
		    for(Field f:fields){
				Annotation[] anotaciones = null;
				try {
				    Field field = le.getClass().getDeclaredField(f.getName());
				    if (field != null) {
				   	 anotaciones = field.getAnnotations();
				    }
				} catch (SecurityException e) {
				    e.printStackTrace();
				} catch (NoSuchFieldException e) {
				    e.printStackTrace();
				}

			    String titulo = f.getName();
		         
		        for(Annotation anotacion : anotaciones){
					if(anotacion instanceof ColumnExcel){
						ColumnExcel myAnnotation = (ColumnExcel) anotacion;
					    visible = myAnnotation.visible();
					    titulo = myAnnotation.headerName().isEmpty()?f.getName():myAnnotation.headerName();
					}
		        }
		        
				if (visible){
			    	if(!f.isAccessible()){
			    		f.setAccessible(true);
		            	row.createCell(cellNum).setCellValue(titulo.toUpperCase());
		    			row.getCell(cellNum).setCellStyle(estiloCabecera(book));
		    			sheet.autoSizeColumn(sheet.getRow(0).getCell(cellNum).getColumnIndex());
		    			cellNum ++;
		    			f.setAccessible(false);
			    	}
				}
		    	visible = true;
		    }
		}
    }
    
    private void setDataSheet(List<Object> lst, HSSFSheet sheet, HSSFWorkbook book) throws IllegalAccessException{
    	int rowNum = 1;
		for (Object le : lst){
			Field[] fields = le.getClass().getDeclaredFields();

		    //create the row data
		    HSSFRow row = sheet.createRow(rowNum);
		    
			int cellNum = 0;
		    for(Field f:fields){
		    	Annotation[] anotaciones = null;
		    	boolean numerico = false;
		    	boolean visible = true;
		    	short alineacion = 0;
		    	String formato = "?";
		    	
		    	try {
				    Field field = le.getClass().getDeclaredField(f.getName());
				    if (field != null) {
				   	 anotaciones = field.getAnnotations();
				    }
				} catch (SecurityException e) {
				    e.printStackTrace();
				} catch (NoSuchFieldException e) {
				    e.printStackTrace();
				}
				
				for(Annotation anotacion : anotaciones){
					if(anotacion instanceof ColumnExcel){
						ColumnExcel myAnnotation = (ColumnExcel) anotacion;
					    visible = myAnnotation.visible();
					    numerico = myAnnotation.numerico();
					    alineacion = myAnnotation.alineacion();
					   	formato = myAnnotation.formato();
					}
				}
				
				if (visible){
			        if(!f.isAccessible()){
			            f.setAccessible(true);
			            Class<?> type = f.getType();
			            HSSFCell cell = row.createCell(cellNum);
			            CellStyle cellStyle = null;
			            
			            if (f.get(le) == null){
			            	cell.setCellValue("");
			            }else{
			            	 cellStyle = book.createCellStyle();
			            	 cellStyle.setDataFormat(book.createDataFormat().getFormat(formato));
			                 cellStyle.setAlignment(alineacion);
			                 cell.setCellStyle(cellStyle);
			                 
			                 if(numerico){
			                	 
			                	 cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
		                     	 			                	
			                	 if(type.equals(Date.class)){
			                		 cell.setCellValue(getFormatDate(f.get(le), formato));
			                	 }else if(type.equals(BigDecimal.class)){
			                		 cell.setCellValue(((BigDecimal)f.get(le)).doubleValue());
			                	 }else{
			                		 cell.setCellValue((Long)f.get(le));
			                	 }
			                	 
			                 } else {
			                	 cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			                	 cell.setCellValue(f.get(le).toString()); 
			                 }
			            }

						cellNum ++;
			            f.setAccessible(false);
			        }
				}
		    }
			
		    rowNum ++;
		}
    }
    
    private static HSSFCellStyle estiloCabecera(HSSFWorkbook book){
		HSSFCellStyle cellStyle = book.createCellStyle();
		//Color de la celda
		cellStyle.setFillBackgroundColor(HSSFColor.DARK_RED.index);
		cellStyle.setFillForegroundColor(HSSFColor.DARK_RED.index);
		cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		//Color de la letra
		HSSFFont font = book.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font.setColor(HSSFColor.WHITE.index);
		
		cellStyle.setFont(font);
		return cellStyle;
    }
    
    private static String getFormatDate(Object date, String formato){
    	Format fecha = new SimpleDateFormat(formato);
    	return fecha.format(date);
   }
}