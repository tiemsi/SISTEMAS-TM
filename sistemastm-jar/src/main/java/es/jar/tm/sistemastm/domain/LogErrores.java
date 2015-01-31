package es.jar.tm.sistemastm.domain;


import java.math.BigDecimal;
import java.util.Date;

import es.arq.arquitectura.annotations.ColumnExcel;


public class LogErrores implements java.io.Serializable {
	@ColumnExcel(visible=false)
	private static final long serialVersionUID = 1L;
	@ColumnExcel(visible=false)
	private BigDecimal id;
	@ColumnExcel(headerName="DESC")
	private String descripcion;
	private String origen;
	@ColumnExcel(headerName="TRACE")
	private String traza;
	private String usuario;
	private Date fecha;
	private String parametros;

	public LogErrores() {
	}

	public LogErrores(BigDecimal id) {
		this.id = id;
	}

	public LogErrores(BigDecimal id, String descripcion, String origen,
			String traza, String usuario, Date fecha, String parametros) {
		this.id = id;
		this.descripcion = descripcion;
		this.origen = origen;
		this.traza = traza;
		this.usuario = usuario;
		this.fecha = fecha;
		this.parametros = parametros;
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

	public String getOrigen() {
		return this.origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public String getTraza() {
		return this.traza;
	}

	public void setTraza(String traza) {
		this.traza = traza;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getParametros() {
		return this.parametros;
	}

	public void setParametros(String parametros) {
		this.parametros = parametros;
	}

}
