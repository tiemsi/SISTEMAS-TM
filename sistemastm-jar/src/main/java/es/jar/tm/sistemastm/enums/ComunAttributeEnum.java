package es.jar.tm.sistemastm.enums;


public enum ComunAttributeEnum {
	MESSAGE				("message"),
	SUCCESS 			("success"),
	ERROR				("error"),
	ERROR_MESSAGE		("errorMessage"),
	UNDEFINED			("undefined"),
	PASSWORD			("password"),
	TRUE				("true"),
	LOGIN				("login"),
	LOGOUT				("logout"),
	SCRIPT				("script"),
	PRINCIPAL			("principal"),
	MENSAJE				(" Mensaje: "),
	COD_SECUENCIA		("Cod. Secuencia:"),
	SECUENCIA			("secuencia"),
	JAVA_ERROR			("javaError/controller"),
	DEFAULT				("default"),
	ID_USUARIO			("idUsuario"),
	SSA_SESSION			("ssaSession");
	
	private String descripcion;
	
	private ComunAttributeEnum(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String toString() {
		return descripcion;
	}
}
