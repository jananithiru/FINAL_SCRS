package scrsexception;

public class missingCourseDataException extends Exception {
	public missingCourseDataException() {

	}

	public missingCourseDataException(String message) {
		super("missingCourseDataException: " + scrs.ErrorMessages.missingCourseData + message);
	}

	public missingCourseDataException(Throwable cause) {
		super(cause);
	}

	public missingCourseDataException(String message, Throwable cause) {
		super("missingCourseDataException: " + message, cause);
	}

}
