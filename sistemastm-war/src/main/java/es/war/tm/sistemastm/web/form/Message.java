package es.war.tm.sistemastm.web.form;

public class Message implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private String type;

	private String message;

	public Message() {
	}

	public Message(String type, String message) {
		this.type = type;
		this.message = message;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
