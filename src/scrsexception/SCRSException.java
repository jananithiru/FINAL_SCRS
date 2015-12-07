package scrsexception;

public class SCRSException extends ClassNotFoundException {
	public SCRSException() {

	}

	public SCRSException(String message) {
		super("SCRSException:" + message);
	}

	public SCRSException(Throwable cause) {
		super();
	}

	public SCRSException(String message, Throwable cause) {
		super("SCRSException:" + message, cause);
	}
}
