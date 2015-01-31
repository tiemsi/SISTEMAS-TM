package es.war.tm.sistemastm.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.Arrays;

import es.jar.tm.sistemastm.enums.ComunAttributeEnum;
import es.jar.tm.sistemastm.service.LogErroresService;
import es.war.tm.sistemastm.web.form.Message;

public final class TrataMensajeError {
		
	private TrataMensajeError() {}
	
	public static Message generaError(Exception exception, String user, LogErroresService logErroresService, String mensaje){
		
		String origen = "";
		String traza = "";
		String descripcion = "";
		
		if (Arrays.toString(exception.getStackTrace()).length() >= 250){
			origen = Arrays.toString(exception.getStackTrace()).substring(0, 249);
		}else{
			origen = Arrays.toString(exception.getStackTrace());
		}
		StringWriter errors = new StringWriter();
		exception.printStackTrace(new PrintWriter(errors));

		if (errors.toString().length() >= 250){
			traza = errors.toString().substring(0, 249);
		}else{
			traza = errors.toString();
		}
		if (exception.getMessage()!=null){
			if (exception.getMessage().length() >= 250){
				descripcion = exception.getMessage().substring(0, 249);
			}else{
				descripcion = exception.getMessage();
			}
		} else {
			descripcion = traza;
		}
				
		BigDecimal idError = BigDecimal.ONE;//logErroresService.setAndSave(descripcion, origen, traza, user);
		
		return generaMensaje(exception, idError, mensaje);
	}
	
	private static Message generaMensaje(Throwable e, BigDecimal id, String mensaje)
	{
		String mensajeException = ComunAttributeEnum.COD_SECUENCIA.toString().concat(id.toString());
		if (mensaje != null){
			mensajeException += ComunAttributeEnum.MENSAJE.toString().concat(mensaje);
		}			
		else if(e.getMessage() != null){
			mensajeException += ComunAttributeEnum.MENSAJE.toString().concat(e.getMessage());
		}
		else{ 
			if (e.getCause() != null){
				mensajeException += ComunAttributeEnum.MENSAJE.toString().concat(e.getCause().toString());
			}
			else{
				mensajeException += ComunAttributeEnum.MENSAJE.toString().concat(e.toString());
			}
		}
		return new Message(ComunAttributeEnum.ERROR.toString(), mensajeException);
	}
	
	public static Message generaMensaje(Exception e)
	{
		String mensajeException = ComunAttributeEnum.COD_SECUENCIA.toString()
								.concat(ThreadAttributes.get(ComunAttributeEnum.SECUENCIA.toString()).toString());
		
		if (e.getMessage() != null){
			mensajeException += ComunAttributeEnum.MENSAJE.toString() + e.getMessage();
		}
		else{ 
			if (e.getCause() != null){
				mensajeException += ComunAttributeEnum.MENSAJE.toString() + e.getCause().toString();
			}
			else{
				mensajeException += ComunAttributeEnum.MENSAJE.toString() + e.toString();
			}
		}
		
		if (mensajeException.length()>2000)
		{
			mensajeException = mensajeException.substring(0, 1999);
		}
		
		return new Message(ComunAttributeEnum.ERROR.toString(), mensajeException);
	}
}
