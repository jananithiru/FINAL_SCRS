package scrs;

public class ErrorMessages {
	public static final String nullCredentials = "Null x500 id or password provided ";
	public static final String invalidCredentials = "Invalid x500 id or password provided ";
	public static final String notAlphaNumberic =" User Credentials cannot contain special characters";
	public static final String incorrectTypeOfAccount = "Valid Account but incorrect type ";
	public static final String missingPersonalDataForUser = "Valid Account but personal information is missing";
	public static final String missingCourseData = "Course Data is missing";
	public static final String missingStudentRegistrationData = "Registration data for the student is missing";
	public static final String accessNotAllowed = "You do not have access to do that";
	
	public static final String classNotFound = "ClassNotFoundException";
	public static final String sqlException = "SqlException";
	public static final String outTimeFrame = "Today is out of Time Frame";
	public static final String ParseDataError = "The data could not be parsed";
	public static final String NoRecordReturnFromDB = "There is no such record in database";
	public static final String missingInstructor = "Instructor is missing";
	public static final String missingRequiredField = "Missing required search fields";
	public static final String StudentAcoountTypeFailure = "This is not a student account";
}