package scrsexception;

public class invalidCredentialsException extends Exception {
	public invalidCredentialsException() {

	}

	public invalidCredentialsException(String message) {
		super("invalidCredentialsException: " + scrs.ErrorMessages.invalidCredentials + message);
	}

	public invalidCredentialsException(Throwable cause) {
		super(cause);
	}

	public invalidCredentialsException(String message, Throwable cause) {
		super("invalidCredentialsException: " + message, cause);
	}

}