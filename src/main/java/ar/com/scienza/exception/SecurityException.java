package ar.com.scienza.exception;

public class SecurityException extends RuntimeException {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -4333014846881453594L;

	private String message;
	
	private String value;
	
	
	public SecurityException(String message) {
		this.message = message;
	}
	
	public SecurityException(String message, String value) {
		this.message = message;
		this.value = value;
	}


	public String getMessage() {
		if(value != null)
			return message + " - Value: " + this.value;
		else
			return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
