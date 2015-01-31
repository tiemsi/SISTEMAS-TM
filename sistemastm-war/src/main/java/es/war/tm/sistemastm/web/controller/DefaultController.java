package es.war.tm.sistemastm.web.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import es.jar.tm.sistemastm.enums.ComunAttributeEnum;

@RequestMapping("/")
@Controller
public class DefaultController {

	@RequestMapping(value = "/*", method = RequestMethod.GET)
	public String loginFail(Model uiModel, Locale locale, HttpServletRequest request) {
		return ComunAttributeEnum.DEFAULT.toString();
	}

	@ExceptionHandler(Throwable.class)
	public ModelAndView exception(Exception e , HttpServletRequest request){
		return new ModelAndView(ComunAttributeEnum.JAVA_ERROR.toString(), ComunAttributeEnum.MESSAGE.toString(), e.getLocalizedMessage());
	}

}
