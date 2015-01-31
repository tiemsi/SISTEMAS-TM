package es.jar.tm.sistemastm.domain;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("rawtypes")
public class TipoMail implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private BigDecimal id;
	private String descripcion;
	private Set mails = new HashSet(0);

	public TipoMail() {
	}

	public TipoMail(BigDecimal id, String descripcion) {
		this.id = id;
		this.descripcion = descripcion;
	}

	public TipoMail(BigDecimal id, String descripcion, Set mails) {
		this.id = id;
		this.descripcion = descripcion;
		this.mails = mails;
	}

	public BigDecimal getId() {
		return this.id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Set getMails() {
		return this.mails;
	}

	public void setMails(Set mails) {
		this.mails = mails;
	}

}
