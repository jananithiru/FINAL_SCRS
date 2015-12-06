package scrsexception;

public class incorrectTypeOfAccountException extends Exception {
	public incorrectTypeOfAccountException() {

	}

	public incorrectTypeOfAccountException(String message) {
		super("incorrectTypeOfAccountException: " + scrs.ErrorMessages.incorrectTypeOfAccount + message);
	}

	public incorrectTypeOfAccountException(Throwable cause) {
		super(cause);
	}

	public incorrectTypeOfAccountException(String message, Throwable cause) {
		super("incorrectTypeOfAccountException: " + message, cause);
	}
}
