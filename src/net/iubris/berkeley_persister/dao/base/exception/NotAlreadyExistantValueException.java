package net.iubris.berkeley_persister.dao.base.exception;

public class NotAlreadyExistantValueException extends UpdateException {

	private static final long serialVersionUID = -7172016836315207653L;

	public NotAlreadyExistantValueException() {
		super();
	}

	public NotAlreadyExistantValueException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public NotAlreadyExistantValueException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotAlreadyExistantValueException(String message) {
		super(message);
	}

	public NotAlreadyExistantValueException(Throwable cause) {
		super(cause);
	}
}
