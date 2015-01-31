package es.jar.tm.sistemastm.enums;


public enum LogErroresAttributeEnum {
	ID				("id"),
	LOG_ERRORES		("logErrores"),
	TRUE			("true");
	
	private String descripcion;
	
	private LogErroresAttributeEnum(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String toString() {
		return descripcion;
	}
}
