package es.war.tm.sistemastm.web.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import es.jar.tm.sistemastm.enums.ComunAttributeEnum;
import es.war.tm.sistemastm.util.TrataMensajeError;
import es.war.tm.sistemastm.web.form.Message;

@RequestMapping("/")
@Controller
public class SecurityController {

	@Autowired
	private MessageSource messageSource;

	@RequestMapping("/loginfail")
	public String loginFail(Model uiModel,HttpServletRequest request, Locale locale) {
		request.getSession().setAttribute(ComunAttributeEnum.ERROR_MESSAGE.toString(), new Message(ComunAttributeEnum.ERROR.toString(), messageSource.getMessage("message_login_fail", new Object[] {}, locale)));
		return "redirect:/login?error=true";
	}

	@ExceptionHandler(Throwable.class)
	public ModelAndView exception(Exception e , HttpServletRequest request){
		return new ModelAndView(ComunAttributeEnum.JAVA_ERROR.toString(), ComunAttributeEnum.MESSAGE.toString(), TrataMensajeError.generaMensaje(e));
	}
}
