package es.jar.tm.sistemastm.files;

import java.util.Date;
import java.util.List;

public class Directorio {

	private String directoryName;
	private Date date;
	private String size;
	private List<Fichero> files;
	
	public Directorio(){}
	
	public Directorio(String directoryName, Date date, String size,
			List<Fichero> files) {
		this.directoryName = directoryName;
		this.date = new Date(date.getTime());
		this.size = size;
		this.files = files;
	}
	
	public String getDirectoryName() {
		return directoryName;
	}
	public void setDirectoryName(String directoryName) {
		this.directoryName = directoryName;
	}
	public Date getDate() {
		return new Date(this.date.getTime());
	}
	public void setDate(Date date) {
		this.date = new Date(date.getTime());
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public List<Fichero> getFiles() {
		return files;
	}
	public void setFiles(List<Fichero> files) {
		this.files = files;
	}
}
