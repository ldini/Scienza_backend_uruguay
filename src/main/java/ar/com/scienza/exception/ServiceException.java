package ar.com.scienza.exception;


public class ServiceException extends Exception {

	private static final long serialVersionUID = 1L;
	
	protected String message;
	
	public ServiceException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
