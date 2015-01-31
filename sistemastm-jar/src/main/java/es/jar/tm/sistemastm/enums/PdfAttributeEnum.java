package es.jar.tm.sistemastm.enums;


public enum PdfAttributeEnum {
	FILTRO_OPERACIONES		("filtroOperaciones"),
	REPORTE_PDF 			("reportePdf"),
	ATT_FILE_PDF			("attachment; filename=\"listado.pdf\""),
	CONTENT_DISPOSITION		("Content-Disposition"),
	APP_PDF					("application/pdf"),
	CONTENT_TYPE			("Content-type"),
	DATOS					("datos");
	
	private String descripcion;
	
	private PdfAttributeEnum(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String toString() {
		return descripcion;
	}
}
