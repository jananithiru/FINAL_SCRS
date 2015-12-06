package scrsexception;

public class missingStudentRegistrationDataExeption extends Exception {
	public missingStudentRegistrationDataExeption() {

	}

	public missingStudentRegistrationDataExeption(String message) {
		super("missingStudentRegistrationDataExeption: " + scrs.ErrorMessages.missingStudentRegistrationData + message);
	}

	public missingStudentRegistrationDataExeption(Throwable cause) {
		super(cause);
	}

	public missingStudentRegistrationDataExeption(String message, Throwable cause) {
		super("invalidCredentialsException: " + message, cause);
	}
}
