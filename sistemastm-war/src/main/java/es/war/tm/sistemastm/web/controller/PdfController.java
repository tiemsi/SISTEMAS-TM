package es.war.tm.sistemastm.web.controller;

import java.io.IOException;
import java.net.ConnectException;
import java.security.Principal;
import java.util.Locale;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import es.jar.tm.sistemastm.enums.ComunAttributeEnum;
import es.jar.tm.sistemastm.enums.PdfAttributeEnum;
import es.jar.tm.sistemastm.service.ExcelService;
import es.jar.tm.sistemastm.service.LogErroresService;
import es.war.tm.sistemastm.util.TrataMensajeError;

@Controller
@RequestMapping("/pdf")
public class PdfController {
 
    @Autowired
    private ExcelService excelService;

	@Autowired
	private MessageSource messageSource;
	
    @Autowired
    private LogErroresService logErroresService;
	
    @RequestMapping(method = RequestMethod.GET)
    public String generarPdf(ModelMap modelMap, HttpServletResponse response) throws Exception {
        modelMap.put(PdfAttributeEnum.DATOS.toString(), excelService.findAll());
        
        response.setHeader(PdfAttributeEnum.CONTENT_TYPE.toString(), PdfAttributeEnum.APP_PDF.toString());
        response.setHeader(PdfAttributeEnum.CONTENT_DISPOSITION.toString(), PdfAttributeEnum.ATT_FILE_PDF.toString());
        return PdfAttributeEnum.REPORTE_PDF.toString();
    }
    
    @ExceptionHandler({ Throwable.class, Exception.class, NullPointerException.class, AccessDeniedException.class, ConnectException.class, IOException.class })
	public ModelAndView handleException(Exception exception, Principal principal, Locale locale) {
		String mensaje = null;
		if (exception.getClass() == NullPointerException.class){
			mensaje = messageSource.getMessage("null_pointer_exception_fail", new Object[] {}, locale);
		}
		if (exception.getClass() == AccessDeniedException.class){
			mensaje = messageSource.getMessage("access_denied_exception_fail", new Object[] { principal.getName() }, locale);
		}
		return new ModelAndView(ComunAttributeEnum.JAVA_ERROR.toString(), ComunAttributeEnum.MESSAGE.toString(), TrataMensajeError.generaError(exception, principal.getName(), logErroresService, mensaje));
		}
}
