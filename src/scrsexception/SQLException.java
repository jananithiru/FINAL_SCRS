package scrsexception;

public class SQLException extends Exception {
	public SQLException() {

	}

	public SQLException(String message) {
		super("SQLException:" + message);
	}

	public SQLException(Throwable cause) {
		super(cause);
	}

	public SQLException(String message, Throwable cause) {
		super("SQLException:" + message, cause);
	}
}
