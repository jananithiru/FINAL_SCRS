package scrsexception;

public class IOException extends Exception {
	public IOException() {

	}

	public IOException(String message) {
		super("IOException: " + message);
	}

	public IOException(Throwable cause) {
		super(cause);
	}

	public IOException(String message, Throwable cause) {
		super("IOException: " + message, cause);
	}

}
