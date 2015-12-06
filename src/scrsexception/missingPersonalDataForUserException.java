package scrsexception;

public class missingPersonalDataForUserException extends Exception {
	public missingPersonalDataForUserException() {

	}

	public missingPersonalDataForUserException(String message) {
		super("missingPersonalDataForUserException: " + scrs.ErrorMessages.missingPersonalDataForUser + message);
	}

	public missingPersonalDataForUserException(Throwable cause) {
		super(cause);
	}

	public missingPersonalDataForUserException(String message, Throwable cause) {
		super("missingPersonalDataForUserException: " + message, cause);
	}

}
