package es.jar.tm.sistemastm.domain;

import java.math.BigDecimal;

public class Mail implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private BigDecimal id;
	private TipoMail tipoMail;
	private String subject;
	private String texto;
	private String origen;
	private String destino;

	public Mail() {
	}

	public Mail(BigDecimal id) {
		this.id = id;
	}

	public Mail(BigDecimal id, TipoMail tipoMail, String subject,
			String texto, String origen, String destino) {
		this.id = id;
		this.tipoMail = tipoMail;
		this.subject = subject;
		this.texto = texto;
		this.origen = origen;
		this.destino = destino;
	}

	public BigDecimal getId() {
		return this.id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public TipoMail getTipoMail() {
		return this.tipoMail;
	}

	public void setTipoMail(TipoMail tipoMail) {
		this.tipoMail = tipoMail;
	}

	public String getSubject() {
		return this.subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getTexto() {
		return this.texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public String getOrigen() {
		return this.origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public String getDestino() {
		return this.destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

}
