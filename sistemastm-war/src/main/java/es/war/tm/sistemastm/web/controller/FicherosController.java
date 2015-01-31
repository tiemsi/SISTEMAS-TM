package es.war.tm.sistemastm.web.controller;

import java.io.File;
import java.io.IOException;
import java.net.ConnectException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import es.arq.arquitectura.util.PropertiesUtil;
import es.jar.tm.sistemastm.enums.ComunAttributeEnum;
import es.jar.tm.sistemastm.enums.ListadosAttributeEnum;
import es.jar.tm.sistemastm.files.Fichero;
import es.jar.tm.sistemastm.service.ListadoFicherosService;
import es.jar.tm.sistemastm.service.LogErroresService;
import es.war.tm.sistemastm.util.TrataMensajeError;
import es.war.tm.sistemastm.web.form.Message;

/** Esta clase se encarga de gestionar todos los eventos que se produzcan en la web dentro de la url /listados/.
 * Ej: Cuando el usuario quiera visualizar una página, o quiera realizar una consulta etc, todo lo que ocurra se gestionará a través del Controller, que
 * este a su vez se encargará de llamar a los Servicios pertinentes para ejecutar las ordenes necesarias.
 * @author isanfuen
 * @version 1.0
 * @since 29/10/2013
 *
 */
@RequestMapping("/listados")
@Controller
@Scope("session")
public class FicherosController {
	
    @Autowired
    private LogErroresService logErroresService;

    @Autowired
    private ListadoFicherosService listadoService;
    
	@Autowired
	private MessageSource messageSource;

	private static final String SEGURIDAD = "isAuthenticated()";
	
	
	
	/**Este método se encargara de llamar al service que llamará al método que se encarga de recorrer recursivamente todos los directorios y recibira un 
	 * arraylist con todos las carpetas y ficheros que se hayan encontrado.
	 * Si no existe la carpeta de usuarios se mostrará al usuario un error por pantalla que la carpeta no ha sido creada, de lo contrario dejaremos el array con los ficheros
	 * en el uiModel.
	 * @param uiModel
	 * @param req
	 * @param principal
	 * @param locale
	 * @return
	 */
	@PreAuthorize(SEGURIDAD)
	@RequestMapping(value = { "/consultaListados" }, method = RequestMethod.GET)
	public String consultaFicherosGET(Model uiModel, HttpServletRequest req, Principal principal, Locale locale) {
		
		String directorioRaiz = PropertiesUtil.getProperty("listados_directorio_raiz");
		File directorio = new File(directorioRaiz);
		List<Fichero> ficherosDirectorio = new ArrayList<Fichero>();
		
		if(directorio.exists()){
			ficherosDirectorio = listadoService.obtenerFicherosDirectorios(directorio, directorioRaiz);
		}else{
			uiModel.addAttribute(ComunAttributeEnum.MESSAGE.toString(), new Message(ComunAttributeEnum.ERROR.toString(), messageSource.getMessage("listados_mensaje_vacio", new Object[] {}, locale)));
		}

		uiModel.addAttribute(ListadosAttributeEnum.FICHEROS.toString(), ficherosDirectorio);
		return "listados/consulta";
	}
	


	/**Este método recibira una URL por parametro y se encargará de llamar al Service para abrir el fichero, convertirlo a un array de bytes y después devolverselo al usuario para
	 * poder ser descargado.
	 * @param response
	 * @param url
	 * @return
	 * @throws IOException
	 */
	@PreAuthorize(SEGURIDAD)
	@RequestMapping(value = "/descargas", method = RequestMethod.GET ,produces=MediaType.APPLICATION_OCTET_STREAM_VALUE)
	@ResponseBody
	public HttpEntity<byte[]> descargarFicheros(HttpServletResponse response,
			@RequestParam(value = "url", required = true) String url) throws IOException {
		String directorioRaiz = PropertiesUtil.getProperty("listados_directorio_raiz");
		return listadoService.descargarFicheros(url,directorioRaiz);
	}
	

	/**
	 * Este método es el encargado de la gestión de todas las excepciones, se encarga de capturar las excepciones del tipo
	 * Throwable.class, Exception.class, NullPointerException.class, AccessDeniedException.class, ConnectException.class, IOException.class.
	 * Una vez analizada la excepción genera un mensaje de error según el tipo y lo devuelve en un modelo a la web para ser mostrado al usuario.
	 * @param exception
	 * @param principal
	 * @param locale
	 * @return
	 */
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
