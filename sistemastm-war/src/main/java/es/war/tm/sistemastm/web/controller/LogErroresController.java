package es.war.tm.sistemastm.web.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.ConnectException;
import java.security.Principal;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import es.jar.tm.sistemastm.domain.LogErrores;
import es.jar.tm.sistemastm.enums.ComunAttributeEnum;
import es.jar.tm.sistemastm.enums.LogErroresAttributeEnum;
import es.jar.tm.sistemastm.service.LogErroresService;
import es.war.tm.sistemastm.util.TrataMensajeError;
import es.war.tm.sistemastm.web.form.LogErroresGrid;

@RequestMapping("/administracion/logErrores")
@Controller
@Scope("session")
public class LogErroresController {

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private LogErroresService logErroresService;

	private static final String SEGURIDAD = "isAuthenticated()";
	
	@PreAuthorize(SEGURIDAD)
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model uiModel, HttpServletRequest request) {
		return "administracion/logErrores/list";
	}

	@PreAuthorize(SEGURIDAD)
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String show(@PathVariable("id") BigDecimal id, Model uiModel, HttpServletRequest request) throws Exception {
		LogErrores logErrores = logErroresService.findById(id);
		uiModel.addAttribute(LogErroresAttributeEnum.LOG_ERRORES.toString(), logErrores);
		return "administracion/logErrores/show";
	}

	@PreAuthorize(SEGURIDAD)
	@RequestMapping(value = "/{id}", params = "form", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") BigDecimal id, Model uiModel) throws Exception {
		uiModel.addAttribute(LogErroresAttributeEnum.LOG_ERRORES.toString(), logErroresService.findById(id));
		return "administracion/logErrores/update";
	}

	/**
	 * Support data for front-end grid
	 * 
	 * @param id
	 * @return
	 */
	@PreAuthorize(SEGURIDAD)
	@RequestMapping(value = "/listgrid", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public LogErroresGrid listGrid(@RequestParam(value = "id", required = false) Long id,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "rows", required = false) Integer rows,
			@RequestParam(value = "sidx", required = false) String sortBy,
			@RequestParam(value = "sord", required = false) String order,
			@RequestParam(value = "reset", required = false) String reset) {

		PageRequest pageRequest = new PageRequest(page - 1, rows);

		LogErroresGrid logErroresGrid = new LogErroresGrid();
//		Page<LogErrores> logErrores;
		
//		if (id == null || id.toString().equals(ComunAttributeEnum.UNDEFINED.toString())){ 
//			logErrores = logErroresService.findByIdPageable(null, pageRequest);
//		}else {
//			logErrores = logErroresService.findByIdPageable(id, pageRequest);
//		}
//		
//		logErroresGrid.setCurrentPage(logErrores.getNumber() + 1);
//		logErroresGrid.setTotalPages(logErrores.getTotalPages());
//		logErroresGrid.setTotalRecords(logErrores.getTotalElements());
//		logErroresGrid.setLogErroresData(logErrores.getContent());

		return logErroresGrid;
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
