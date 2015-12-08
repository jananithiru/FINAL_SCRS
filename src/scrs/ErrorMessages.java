package scrs;

public class ErrorMessages {
	public static final String NULL_CREDENTIALS = "Null x500 id or password provided ";
	public static final String INVILID_CREDENTIALS = "Invalid x500 id or password provided ";
	public static final String NOT_ALPHANUMBERIC =" User Credentials cannot contain special characters";
	public static final String INCORRECT_TYPE_ACCOUNT = "Valid Account but incorrect type ";
	public static final String MISSING_PERSONAL_DATA_FOR_USER = "Valid Account but personal information is missing";
	public static final String MISSING_COURSE_DATA = "Course Data is missing";
	public static final String MISSING_STUDENT_REGISTER_DATA = "Registration data for the student is missing";
	public static final String ACCESS_NOT_ALLOWED = "You do not have access to do that";
	
	public static final String CLASS_NOT_FOUND = "ClassNotFoundException";
	public static final String SQL_EXCEPTION = "SqlException";
	public static final String OUT_TIME_FRAME = "Today is out of Time Frame";
	public static final String PARASE_DATA_ERROR = "The data could not be parsed";
	public static final String NO_RECORD_RETURN_FROM_DB = "There is no such record in database";
	public static final String MISSING_INSTRUCTOR = "Instructor is missing";
	public static final String MISSING_REQUIRED_FIELD = "Missing required search fields";
	public static final String STUDENTACCOUNT_FAILURE = "This is not a student account";
}