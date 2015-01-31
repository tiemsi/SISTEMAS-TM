package es.jar.tm.sistemastm.files;


public class Fichero {

	private String fileName;
	private String pathFichero;
	private String pathPadre;
	private Boolean isFolder;
	private Boolean isFile;
	private Long size;
	private String rutaAbsoluta;
	private String fechaCreacion;
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getPathFichero() {
		return pathFichero;
	}
	public void setPathFichero(String pathFichero) {
		this.pathFichero = pathFichero;
	}
	public String getPathPadre() {
		return pathPadre;
	}
	public void setPathPadre(String pathPadre) {
		this.pathPadre = pathPadre;
	}
	public Boolean getIsFolder() {
		return isFolder;
	}
	public void setIsFolder(Boolean isFolder) {
		this.isFolder = isFolder;
	}
	public Boolean getIsFile() {
		return isFile;
	}
	public void setIsFile(Boolean isFile) {
		this.isFile = isFile;
	}
	public Long getSize() {
		return size;
	}
	public void setSize(Long size) {
		this.size = size;
	}
	public String getRutaAbsoluta() {
		return rutaAbsoluta;
	}
	public void setRutaAbsoluta(String rutaAbsoluta) {
		this.rutaAbsoluta = rutaAbsoluta;
	}
	public String getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	
	
}
