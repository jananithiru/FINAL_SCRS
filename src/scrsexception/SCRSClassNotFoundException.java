package scrsexception;

public class SCRSClassNotFoundException extends ClassNotFoundException {
	public SCRSClassNotFoundException() {

	}

	public SCRSClassNotFoundException(String message) {
		super("SCRSException:" + message);
	}

	public SCRSClassNotFoundException(Throwable cause) {
		super();
	}

	public SCRSClassNotFoundException(String message, Throwable cause) {
		super("SCRSException:" + message, cause);
	}
}
