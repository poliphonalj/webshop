package webshop.exception;

import java.util.List;

public class WebshopException extends Exception {
	List<String> errorMessages;

	public WebshopException(List<String> errorMessages) {
		super();
		this.errorMessages = errorMessages;
	}

	public List<String> getErrorMessages() {
		return errorMessages;
	}

	public void setErrorMessages(List<String> errorMessages) {
		this.errorMessages = errorMessages;
	}
	
	
	
	
}
