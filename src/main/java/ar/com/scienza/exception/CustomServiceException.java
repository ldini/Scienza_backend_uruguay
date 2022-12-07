package ar.com.scienza.exception;


public class CustomServiceException extends Exception {

	private static final long serialVersionUID = 1L;
	
	protected Integer code;
	
	protected String message;
	
	
	public CustomServiceException(Integer code, String message) {
		this.code = code;
		this.message = message;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
