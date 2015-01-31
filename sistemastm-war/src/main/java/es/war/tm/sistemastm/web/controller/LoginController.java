package es.war.tm.sistemastm.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.jar.tm.sistemastm.enums.ComunAttributeEnum;

@Controller
public class LoginController {

	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login(Model uiModel, HttpServletRequest request) {
//		String isLogout = (String) request.getParameter(ComunAttributeEnum.LOGOUT.toString());// viene de logout
//		
//		// declaracion de variables
//		StringBuilder script = new StringBuilder("");
//		boolean moreThanOneUser = false;
//		SesionUsuarioDTO sesionUsuarioDTO = null;
//		Message message = null;
//		
//		// Se obtiene de la sesión la última excepción de spring security, en caso de que haya habido alguna
//		Exception springLastException = (Exception) request.getSession().getAttribute("SPRING_SECURITY_LAST_EXCEPTION");
//		//Se intenta obtener la excepción de ASSA que ha causado el error de autenticación, en caso de que sea cosa de ASSA
//		SSAException lastSSAException = (SSAException) extraeException(springLastException, SSAException.class);
//		if (lastSSAException != null) {// Si se encuentra una excepción se intenta obtener el error de ASSA y si se consigue, se retorna la descripción a la página
//			String mensajeError = SSACodigosRetornoEnum.getDescripcionErrorSSA(lastSSAException.getCodError());
//			uiModel.addAttribute(ComunAttributeEnum.MESSAGE.toString(), new Message(ComunAttributeEnum.ERROR.toString(), mensajeError));
//			return ComunAttributeEnum.LOGIN.toString();
//		}
//		
//		if (request.getParameter(ComunAttributeEnum.ERROR.toString()) != null){
//			// si viene de error el mismo comportamiento que si hace logout
//			isLogout = ComunAttributeEnum.TRUE.toString();
//		}
//		
//		if (isLogout == null) {
//			// si no estó haciendo logout ni viene error
//			try {
//				ClienteAut clienteAut = new ClienteAutImpl(Constantes.URL_APLICACION_SEGURIDAD);
////				String sIPAddress = request.getRemoteAddr(); 
//				String sIPAddress = "7.109.242.94";
//				String idUsuario = (String) request.getParameter(ComunAttributeEnum.ID_USUARIO.toString());// hacemos login con un usuario de la lista
//
//				if (idUsuario != null) {
//					// varios usuario logados, nos logamos con el elegido
//					sesionUsuarioDTO = clienteAut.autenticaUsuarioDadoPorSSO(idUsuario, sIPAddress, Constantes.ID_APLICACION);
//				}
//				else {// si hay un solo usuario en ssa, validamos usuario por IP contra SSA
//					sesionUsuarioDTO = clienteAut.autenticaUsuarioPorSSOEx(sIPAddress, Constantes.ID_APLICACION);
//				}
//				// ponemos la sesion de ssa en session para poder cerrarla posteriormente
//				String ssaSession = sesionUsuarioDTO.getSesion().getIdSesion();
//				if (ssaSession != null) {
//					request.getSession().setAttribute(ComunAttributeEnum.SSA_SESSION.toString(), ssaSession);
//				}
//				request.getSession().setAttribute(ComunAttributeEnum.PRINCIPAL.toString(), sesionUsuarioDTO);
//
//			} catch (SSAServicioSSOException e) {
//				message = new Message(ComunAttributeEnum.ERROR.toString(), SSACodigosRetornoEnum.getDescripcionErrorSSA(e));
//				script.append(getScriptHtml(e));
//				moreThanOneUser = true;
//			} catch (Exception e) {
//				message = new Message(ComunAttributeEnum.ERROR.toString(), "Error en autenticación por credenciales: " + e.getMessage());
//			}
//		}
//		
//		script.append(getLoginHtml(sesionUsuarioDTO, moreThanOneUser, isLogout));
//			
//		uiModel.addAttribute(ComunAttributeEnum.SCRIPT.toString(), script.toString());
//		uiModel.addAttribute(ComunAttributeEnum.MESSAGE.toString(), message);
		
		return ComunAttributeEnum.LOGIN.toString();
	}

	
	/**
	 * Este método busca en un {@link Throwable} recibido por parámetro de forma recursiva (a través del método getCause()) hasta encontrar un elemento de la clase claseBuscada ({@link Class})
	 * La lógica de este método se ejecuta en el método "extraeExceptionRecursiva", al que se llama desde este.
	 * 
	 * @param excepcion {@link Throwable} a inspeccionar de forma recursiva
	 * @param claseBuscada {@link Class} de la excepción que buscamos
	 * @return {@link Throwable} cuya clase coincide con "claseBuscada"
	 */
	@SuppressWarnings({"unchecked", "rawtypes"})
	private Throwable extraeException(Throwable excepcion, Class claseBuscada) {
		List<Throwable> excepcionesPadre = new ArrayList<Throwable>();
		return extraeExceptionRecursiva(excepcion, claseBuscada, excepcionesPadre);
	}
	
	/**
	 * Método llamado desde extraeException, éste es el método que lleva la lógica real.
	 * Pero extraeException se encarga de inicializar una colección que se usa de forma interna y por tanto, este método no se debe llamar de forma independiente 
	 * 
	 * @param excepcion {@link Throwable} a inspeccionar de forma recursiva
	 * @param claseBuscada {@link Class} de la excepción que buscamos
	 * @param excepcionesPadre {@link List}<{@link Throwable}> que irá incluyendo todas las excepciones ya procesadas, de forma que no se causen bucles recursivos.
	 * @return {@link Throwable} cuya clase coincide con "claseBuscada"
	 */
	private Throwable extraeExceptionRecursiva(Throwable excepcion, Class<Throwable> claseBuscada, List<Throwable> excepcionesPadre) {
		try {
			Throwable excepcionEncontrada = claseBuscada.cast(excepcion);
			return excepcionEncontrada;
		} catch (ClassCastException ccE) {
			if (excepcionesPadre.contains(excepcion)) {
				return null;
			} else {
				excepcionesPadre.add(excepcion);
			}
			return extraeExceptionRecursiva(excepcion.getCause(), claseBuscada, excepcionesPadre);
		} catch (Exception e) {
			return null;
		}
	}
	
//	private String getScriptHtml(SSAServicioSSOException e){
//		StringBuilder script = new StringBuilder("");
//		if(e.getCodError() == CodigoError.SSO_USUARIOS_DISTINTOS_PARA_IP){
//			script.append("<script type='text/javascript'>\n");
//			script.append("$(document).ready(function() {\n");
//			script.append("$('#login').css('display','none');\n");
//			script.append("$('#userListContainer').append('<p class=\"info align-justify\">Hay varios usuarios con sesión abierta en SSA. Por favor, elija un usuario para iniciar sesión</p>');\n");
//      		for (int i = 0; i<e.getIdsUsuarios().length ; i++){
//      			script.append("$('#userListContainer').append('<a class=\"link login-link text-color-ico\" href=\"login?idUsuario="+ e.getIdsUsuarios()[i] +"\">" + e.getIdsUsuarios()[i] + "</a>');\n"); 
//      		}		
//      		script.append("$('#userListContainer').append('<hr>');");
//      		script.append("$('#userListContainer').append('<a href=\"login?logout=true\">Nuevo Login</a>');\n");
//      		script.append("$('#userList').css('display','block');\n");
//      		script.append("$('#userListContainer').css('display','block');\n");
//      		script.append("});\n"); 
//      		script.append("</script>\n"); 
//		}
//		
//		return script.toString();
//	}
//	
//	private String getLoginHtml(SesionUsuarioDTO sesionUsuarioDTO, boolean moreThanOneUser, String isLogout){
//		StringBuilder script = new StringBuilder("");
//		if (!moreThanOneUser && ((isLogout != null && !isLogout.equals("true")) || sesionUsuarioDTO != null)) {
//			script.append("<script type='text/javascript'>" + "$(document).ready(function() {");
//			script.append("$('#login').css('display','none');");
//			script.append("$('#sso').css('display','block');");
//			script.append("$('#username').val('" + sesionUsuarioDTO.getUsuario().getIdUsuario() + "');");
//			script.append("$('#password').val('" + UUID.randomUUID().toString() + "');" + "$('#submit').click();"
//					+ "});");
//			script.append("</script>");
//		}
//		return script.toString();
//	}
	
//	@RequestMapping(value="/cambioLogin", method = RequestMethod.POST)
//	public String cambioLogin(
//			Model uiModel,
//			HttpServletRequest request,
//			@RequestParam("j_username") String usuario,
//			@RequestParam("j_password") String passwordActual,
//			@RequestParam("j_new_password") List<String> passwordNueva
//			) {
//		Message message = null;
//		try {
//			ClienteAut clienteAut = new ClienteAutImpl(Constantes.URL_APLICACION_SEGURIDAD);
////			String sIPAddress = request.getRemoteAddr(); 
//			String sIPAddress = "7.109.242.94";
//			
//			SesionUsuarioDTO sesion = null;
//			try{
//				sesion = clienteAut.autenticaUsuarioPorCredenciales(usuario, passwordActual, sIPAddress, Constantes.ID_APLICACION);
//			}catch(SSAServicioException e){
//				String descErrorSSA = SSACodigosRetornoEnum.getDescripcionErrorSSA(e);
//				message = new Message(ComunAttributeEnum.ERROR.toString(), descErrorSSA);
//				uiModel.addAttribute(ComunAttributeEnum.MESSAGE.toString(), message);
//				return ComunAttributeEnum.PASSWORD.toString();
//			}
//			
//			if(sesion != null){
//				//LA SESIÓN SE HA INICIADO CORRECTAMENTE EN ASSA
//				if(passwordNueva.get(0)==null || passwordNueva.get(0).length()<=0){
//					//LA NUEVA CONTRASEÑA ESTÁ VACÍA
//					message = new Message(ComunAttributeEnum.ERROR.toString(), "Error: La nueva contraseña está vacía");
//					uiModel.addAttribute(ComunAttributeEnum.MESSAGE.toString(), message);
//					return ComunAttributeEnum.PASSWORD.toString();
//				}
//				if(passwordNueva.get(0).equals(passwordNueva.get(1))){
//					//LOS CAMPOS CONTRASEÑA NUEVA Y REPETIR CONTRASEÑA NUEVA COINCIDEN
//					ResultadoCambioContraseña result = clienteAut.cambiaContraseñaUsuarioEx(usuario, passwordActual, passwordNueva.get(0), ModoCambioContraseña.HOST_SI_ES_POSIBLE);
//					clienteAut.terminaSesionUsuario(sesion.getSesion().getIdSesion());
//					if(result.hasIncidencia()){
//						message = new Message(ComunAttributeEnum.ERROR.toString(), result.getMensajeIncidencia());
//						uiModel.addAttribute(ComunAttributeEnum.MESSAGE.toString(), message);
//						return ComunAttributeEnum.PASSWORD.toString();
//					}
//					message = new Message(ComunAttributeEnum.SUCCESS.toString(), SSACodigosRetornoEnum.getDescripcionErrorSSA(result.getCodigoIncidencia()));
//				}else{
//					//LA NUEVA CONTRASEÑA NO COINCIDE CON EL CAMPO DE VERIFICACIÓN
//					message = new Message(ComunAttributeEnum.ERROR.toString(), "Error: La nueva contraseña no coincide con el campo de repetir contraseña");
//					uiModel.addAttribute(ComunAttributeEnum.MESSAGE.toString(), message);
//					return ComunAttributeEnum.PASSWORD.toString();
//				}
//			}else{
//				//NO SE PUEDE AUTENTICAR AL USUARIO CON LAS CREDENCIALES OBTENIDAS
//				message = new Message(ComunAttributeEnum.ERROR.toString(), "El usuario o la contraseña originales no son correctos");
//				uiModel.addAttribute(ComunAttributeEnum.MESSAGE.toString(), message);
//				return ComunAttributeEnum.PASSWORD.toString();
//			}
//			
//			
//		} catch (SSAServicioException e) {
//			message = new Message(ComunAttributeEnum.ERROR.toString(), "Error en cambio de Login: " + e.getMessage());
//		} catch (SSACriptoException e) {
//			message = new Message(ComunAttributeEnum.ERROR.toString(), "Error en cambio de Login: " + e.getMessage());
//		}
//		
//		uiModel.addAttribute(ComunAttributeEnum.MESSAGE.toString(), message);
//		
//		return ComunAttributeEnum.LOGIN.toString();
//	}
	
	@RequestMapping(value="/password", method = RequestMethod.GET)
	public String password(Model uiModel, HttpServletRequest request) {
		return ComunAttributeEnum.PASSWORD.toString();
	}
}
