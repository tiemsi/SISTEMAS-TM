package es.war.tm.sistemastm.web.form;

import java.util.List;

import es.jar.tm.sistemastm.domain.LogErrores;

public class LogErroresGrid {

	private int totalPages;

	private int currentPage;

	private long totalRecords;

	private List<LogErrores> logErroresData;

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public long getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(long totalRecords) {
		this.totalRecords = totalRecords;
	}

	public List<LogErrores> getLogErroresData() {
		return logErroresData;
	}

	public void setLogErroresData(List<LogErrores> logErroresData) {
		this.logErroresData = logErroresData;
	}

}
