package net.iubris.berkeley_persister.dao.base.exception;

public class UpdateException extends Exception {

	private static final long serialVersionUID = -8331662845679914301L;

	public UpdateException() {
		super();
	}

	public UpdateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public UpdateException(String message, Throwable cause) {
		super(message, cause);
	}

	public UpdateException(String message) {
		super(message);
	}

	public UpdateException(Throwable cause) {
		super(cause);
	}

}
