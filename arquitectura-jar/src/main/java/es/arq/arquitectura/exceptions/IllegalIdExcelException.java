package es.arq.arquitectura.exceptions;

public class IllegalIdExcelException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String Id;
	/**
	 * 
	 */
	public IllegalIdExcelException() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public IllegalIdExcelException(String id, String message) {
		super(message);
		Id = id;
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 */
	public IllegalIdExcelException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param message
	 * @param cause
	 */
	public IllegalIdExcelException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param cause
	 */
	public IllegalIdExcelException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return Id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		Id = id;
	}

	
}
