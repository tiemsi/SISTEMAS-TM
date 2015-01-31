package es.arq.arquitectura.files;
/**
 * @author tsanzseg
 * 
 */
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;
 
public class PdfView extends AbstractPdfView {
 
    @SuppressWarnings("unchecked")
	protected void buildPdfDocument(        
            Map<String, Object> model,        
            Document document,        
            PdfWriter writer,        
            HttpServletRequest req,        
            HttpServletResponse resp)        
                    throws Exception {
 
        List<Object> lst = new ArrayList<Object>(); 
    	for (Map.Entry<String, Object> entry : model.entrySet()) {
    		lst = (List<Object>)entry.getValue();
    	}
        
    	Table table = new Table(lst.get(0).getClass().getDeclaredFields().length);
    	
    	int rowNum = 0;
    	for (Object le : lst){
	    	Field[] fields = le.getClass().getDeclaredFields();	    	
            
	        for(Field f:fields){
	            if(!f.isAccessible()){
	                f.setAccessible(true);
//	                Class<?> type = f.getType();
	                
	                if (rowNum==0) // Cabecera
	                	table.addCell(f.getName());
	                else // Filas de la tabla
	                	table.addCell(f.get(le)==null?"":f.get(le).toString());
	               
	                f.setAccessible(false);
	            }
	        }
	        rowNum ++;
    	}
    	// -- newline
        document.add(table);
    }
 
}