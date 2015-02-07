package es.jar.tm.sistemastm.aspects;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * @author vsempere
 * 
 */
@Aspect
public class ServiceInterceptorAspect {
	
	private final transient Logger	logger	= Logger.getLogger(this.getClass());

	/**
	 * Aspecto encargado de realizar LOG de la ejecución de los métodos de la
	 * aplicación, con la excepción de el paquete de aspectos, ya que causaría
	 * bucle infinito
	 * 
	 * @param joinPoint
	 * @return
	 * @throws Throwable
	 */
	@Around("execution(public * es.jar.tm.sistemastm..*.*(..)) && !execution(public * es.jar.tm.sistemastm.aspects..*.*(..))")
	public Object aroundAll(ProceedingJoinPoint joinPoint) throws Throwable {
		return executeAndLog(joinPoint);
	}
	
	/**
	 * Este método recibe un {@link ProceedingJoinPoint} y realiza el LOG y la
	 * ejecución controlada, también realiza LOG de la ejecución correcta o
	 * error del {@link ProceedingJoinPoint} recibido.
	 * 
	 * @param joinPoint
	 * @return
	 * @throws Throwable
	 */
	private Object executeAndLog(ProceedingJoinPoint joinPoint) throws Throwable {
		Object result = null;
		logExecution(joinPoint);
		try {
			result = joinPoint.proceed();
			logOK(result, joinPoint);
			return result;
		} catch (Exception e) {
			logError(e, joinPoint);
			throw e;
		}
	}

	
	/**
	 * Método que realiza el LOG de un método que se va a ejecutar.
	 * 
	 * Ejemplo de traza:
	 * method-execution: es.jar.tm.sistemastm.service.ContactoServiceImpl.findByNombrePageable
	 * 
	 * @param jp
	 */
	private void logExecution(ProceedingJoinPoint jp){
		String accion	= jp.getStaticPart().getKind();
		String clase	= jp.getSourceLocation().getWithinType().getCanonicalName();
		String metodo	= jp.getSignature().getName();
		
		logger.info(accion.concat(": ").concat(clase.concat(".").concat(metodo)));
	}
	
	/**
	 * Método que realiza el LOG de un método que ha lanzado una excepción.
	 * 
	 * Ejemplo de traza:
	 * [QuerySyntaxException] es.jar.sistemastm.service.ContactoServiceImpl.findByNombrePageable: Contacto is not mapped [from Contacto c where c.nombre like :nombre]
	 * (Impresión completa del StackTrace)
	 * 
	 * @param t
	 * @param jp
	 */
	private void logError(Exception t, ProceedingJoinPoint jp){
		String localizedMessage			= t.getLocalizedMessage();
		if(localizedMessage==null){
			localizedMessage = "";
		}
		String errorName				= t.getClass().getSimpleName();
		
		String clase	= jp.getSourceLocation().getWithinType().getCanonicalName();
		String metodo	= jp.getSignature().getName();
		
		String errorMessage = "[".concat(errorName).concat("] ").concat(clase).concat(".").concat(metodo).concat(": ").concat(localizedMessage);
		
		logger.error(errorMessage, t);
	}
	
	/**
	 * Método que realiza el LOG de un método que ha finalizado su ejecución correctamente.
	 * 
	 * Ejemplo de traza:
	 * [OK] es.jar.sistemastm.web.controller.LoginController.login. Retorno: <String - "login">
	 * 
	 * @param result
	 * @param jp
	 */
	private void logOK(Object result, ProceedingJoinPoint jp){
		String clase	= jp.getSourceLocation().getWithinType().getCanonicalName();
		String metodo	= jp.getSignature().getName();
		logger.info("[OK] ".concat(clase).concat(".").concat(metodo).concat(". Retorno: ").concat(describeResult(result)));
	}
	
	/**
	 * Método que genera una cadena descriptiva para un objeto recibido por
	 * parámetro, la cadena contiene el nombre de la clase del objeto y el
	 * resultado de ejecutar el método toString() del objeto
	 * 
	 * Ejemplos:
	 * <BigDecimal - "1047">
	 * <ModelAndView - "ModelAndView: reference to view with name 'javaError/controller'; model is {message=es.jar.sistemastm.web.form.Message@5d65c3}">
	 * <Boolean - "false">
	 * <String - "contacto/list">
	 * 
	 * @param result
	 * @return
	 */
	private String describeResult(Object result) {
		String clase = null;
		String descr = "";
		if(result!=null){
			clase = result.getClass().getSimpleName();
			descr = "\"".concat(String.valueOf(result)).concat("\"");
		}else{
			clase = "NULL OBJECT";
		}
		
		return "<".concat(clase).concat(" - ").concat(descr).concat(">");
	}
	
	
	
//	private void pruebaLogs(ProceedingJoinPoint jp) {
//		/*##############- EJEMPLOS de info que se puede obtener de joinpoint sin error -##############*/
//		/*
//		jp.getSourceLocation()											;//org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint$SourceLocationImpl@1c44cc1
//		jp.getSourceLocation()	.getWithinType()	.getCanonicalName()	;//es.jar.tm.sistemastm.service.LogErroresServiceImpl
//		jp.getSignature()												;//BigDecimal es.jar.tm.sistemastm.service.LogErroresService.setAndSave(String,String,String,String)
//		jp.getSignature()		.getDeclaringType()	.getCanonicalName()	;//es.jar.tm.sistemastm.service.LogErroresService
//		jp.getSignature()		.getName()								;//setAndSave
//		jp.getSignature()		.toLongString()							;//public abstract java.math.BigDecimal es.jar.tm.sistemastm.service.LogErroresService.setAndSave(java.lang.String,java.lang.String,java.lang.String,java.lang.String)
//		jp.getSignature()		.toShortString()						;//LogErroresService.setAndSave(..)
//		jp.getStaticPart()												;//execution(BigDecimal es.jar.tm.sistemastm.service.LogErroresService.setAndSave(String,String,String,String))
//		jp.getStaticPart()		.getId()								;//0
//		jp.getStaticPart()		.getKind()								;//method-execution
//		jp.getStaticPart()		.toLongString()							;//execution(public abstract java.math.BigDecimal es.jar.tm.sistemastm.service.LogErroresService.setAndSave(java.lang.String,java.lang.String,java.lang.String,java.lang.String))
//		jp.getStaticPart()		.toShortString()						;//execution(LogErroresService.setAndSave(..))
//		jp.toLongString()												;//execution(public abstract java.math.BigDecimal es.jar.tm.sistemastm.service.LogErroresService.setAndSave(java.lang.String,java.lang.String,java.lang.String,java.lang.String))
//		jp.toShortString()												;//execution(LogErroresService.setAndSave(..))
//		*/
//		
//		
//		/* ##############- EJEMPLOS de logging con jog4j -############## */
//		/*
//		logger.trace("[VLOG]");
//		logger.debug("[VLOG]");
//		logger.error("[VLOG]");
//		logger.fatal("[VLOG]");
//		logger.info("[VLOG]");
//		logger.log(Level.OFF, "[VLOG]");
//		*/
//
//	}
	
}
