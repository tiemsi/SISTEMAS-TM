package es.jar.tm.sistemastm.enums;


public enum ListadosAttributeEnum {
	FILTRO_GENERA_LISTADOS		("filtroGeneraListados"),
	FICHEROS					("ficheros"),
	FILTRO_OPERACIONES			("filtroOperaciones"),
	NOMBRE_IFI					("nombreIfi"),
	TIPO_LISTADO				("tipoListado");
	
	private String descripcion;
	
	private ListadosAttributeEnum(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String toString() {
		return descripcion;
	}
}
