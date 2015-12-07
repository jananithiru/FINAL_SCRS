package scrsexception;

import java.sql.SQLException;

public class SCRSSQLException extends SQLException {
	public SCRSSQLException() {

	}

	public SCRSSQLException(String message) {
		super("SQLException:" + message);
	}

	public SCRSSQLException(Throwable cause) {
		super(cause);
	}

	public SCRSSQLException(String message, Throwable cause) {
		super("SQLException:" + message, cause);
	}
}
