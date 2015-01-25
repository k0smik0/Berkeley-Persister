package net.iubris.berkeley_persister.dao.base.exception;

public class MismatchKeysException extends UpdateException {

	private static final long serialVersionUID = 2469362429923667357L;

	public MismatchKeysException() {
	}

	public MismatchKeysException(String message) {
		super(message);
	}

	public MismatchKeysException(Throwable cause) {
		super(cause);
	}

	public MismatchKeysException(String message, Throwable cause) {
		super(message, cause);
	}

	public MismatchKeysException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
