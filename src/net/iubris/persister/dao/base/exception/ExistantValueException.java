package net.iubris.persister.dao.base.exception;

public class ExistantValueException extends Exception {

	private static final long serialVersionUID = -7969921610422818406L;

	public ExistantValueException() {
		super();
	}

	public ExistantValueException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ExistantValueException(String message, Throwable cause) {
		super(message, cause);
	}

	public ExistantValueException(String message) {
		super(message);
	}

	public ExistantValueException(Throwable cause) {
		super(cause);
	}

	
}
