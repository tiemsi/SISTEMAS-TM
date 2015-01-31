package es.jar.tm.sistemastm.enums;

import java.math.BigDecimal;

public enum SendMailMessageEnum {
	GeneracionReparto		(1,	"Generación de reparo"),
	GeneracionListados2013	(2,	"Listado General 2013");
	
	private int idMensaje;
	private String descripcion;
	
	private SendMailMessageEnum(int idEstado, String descripcion) {
		this.idMensaje = idEstado;
		this.descripcion = descripcion;
	}

	public int getIdMensaje() {
		return idMensaje;
	}
	
	/** retorna el idMensaje en formato BigDecimal
	 * @return idMensaje en formato BigDecimal
	 */
	public BigDecimal getIdMensajeBD(){
		return BigDecimal.valueOf(idMensaje);
	}
	
	public String getDescripcion() {
		return descripcion;
	}
}
