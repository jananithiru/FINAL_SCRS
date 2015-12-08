package scrsexception;

public class ParseException extends Exception {

	public ParseException() {

	}

	public ParseException(String message) {
		super("ParseException:" + message);
	}

	public ParseException(Throwable cause) {
		super(cause);
	}

	public ParseException(String message, Throwable cause) {
		super("ParseException:" + message, cause);
	}

}
