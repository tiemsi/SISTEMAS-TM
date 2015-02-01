package es.war.tm.sistemastm.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import es.jar.tm.sistemastm.enums.ComunAttributeEnum;
import es.war.tm.sistemastm.util.TrataMensajeError;

@RequestMapping("/")
@Controller
public class LogoutController {

	@PreAuthorize("isAuthenticated()")
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String list(Model uiModel, HttpServletRequest request) throws Exception {

//		ClienteAut clienteAut;
//		
//		//cerramos sesion en ssa y en local
//		clienteAut = new ClienteAutImpl(Constantes.URL_APLICACION_SEGURIDAD);
//		String ssaSession = (String) request.getSession().getAttribute(ComunAttributeEnum.SSA_SESSION.toString());
//		
//		if (ssaSession != null){
//			clienteAut.terminaSesionUsuario(ssaSession);
//		}
//		request.getSession().invalidate();

		return "redirect:/login?logout=true";
	}

	@ExceptionHandler(Throwable.class)
	public ModelAndView exception(Exception e , HttpServletRequest request){
		return new ModelAndView(ComunAttributeEnum.JAVA_ERROR.toString(), ComunAttributeEnum.MESSAGE.toString(), TrataMensajeError.generaMensaje(e));
	}

}
