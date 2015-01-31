package es.jar.tm.sistemastm.aspects;

public class ServiceAspectException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Contructor de la clase
	 */
	public ServiceAspectException(){
		super();
	}
	
	/**
	 * Contructor que recibe el mensaje de la excepción
	 * 
	 * @param mensaje
	 */
	public ServiceAspectException(String mensaje){
		super(mensaje);
	}
}
