package es.jar.tm.sistemastm.enums;


public enum ExcelAttributeEnum {
	FILTRO_OPERACIONES		("filtroOperaciones"),
	REPORTE_EXCEL 			("reporteExcel"),
	ATT_FILE_EXCEL			("attachment; filename=\"excel.xls\""),
	CONTENT_DISPOSITION		("Content-Disposition"),
	APP_VND_EXCEL			("application/vnd.ms-excel"),
	CONTENT_TYPE			("Content-type"),
	DATOS					("datos");
	
	private String descripcion;
	
	private ExcelAttributeEnum(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String toString() {
		return descripcion;
	}
}
