package scrs;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import org.junit.Test;

import scrs.ShibbolethAuth.Token;

public class TestAdminFunctionality {

	// adminAddClass(ShibbolethAuth.Token token, int courseID, String
	// courseName, int courseCredits,
	// String instructor, String firstDay, String lastDay, String
	// classBeginTime, String classEndTime,
	// String weekDays, String location, String type, String prerequisite,
	// String description, String department)
	//
	@Test
	public void TestAdminAddClass() throws ClassNotFoundException, SQLException {
		SCRS testScrs = new SCRSImpl();
		ShibbolethAuth sbAuth = new ShibbolethAuth();
		Token tokenGenerator = sbAuth.tokenGenerator("bob111", "mypassword");
		if (tokenGenerator != null && tokenGenerator.type == Token.RoleType.ADMIN) {
			// where to judge the token type??? inside the function or in
			// the test
			assertEquals(true, testScrs.adminAddClass(tokenGenerator, 8735, "Advanced Database", 3, "Mohamed", "Sep.01",
					"Dec.20", "9:00am", "10:30am", "Tu,Th", "KHKH110", "campus", "No", "Databases", "CS"));

			// boolean testResult = testScrs.studentAddClass(myToken, 8735,
			// "A/F", "FALL");
			// if (testResult == true) {
			// System.out.println("Adding class is done!");
			// } else
			// System.out.println("Adding class meet error.");
		}
	}

	@Test
	public void TestAdminDeleteClass() throws ClassNotFoundException, SQLException {
		SCRS testScrs = new SCRSImpl();
		ShibbolethAuth sbAuth = new ShibbolethAuth();
		Token tokenGenerator = sbAuth.tokenGenerator("bob111", "mypassword");
		if (tokenGenerator != null && tokenGenerator.type == Token.RoleType.ADMIN) {

			// where to judge the token type??? inside the function or in
			// the test
			assertEquals(true, testScrs.adminDeleteClass(tokenGenerator, 8735));

			// boolean testResult = testScrs.studentAddClass(myToken, 8735,
			// "A/F", "FALL");
			// if (testResult == true) {
			// System.out.println("Adding class is done!");
			// } else
			// System.out.println("Adding class meet error.");
		}
	}

	@Test
	public void TestAdminEditClass() throws ClassNotFoundException, SQLException {
		SCRS testScrs = new SCRSImpl();
		ShibbolethAuth sbAuth = new ShibbolethAuth();
		Token tokenGenerator = sbAuth.tokenGenerator("bob111", "mypassword");
		if (tokenGenerator != null && tokenGenerator.type == Token.RoleType.ADMIN) {
			// where to judge the token type??? inside the function or in
			// the test
			assertEquals(true, testScrs.adminEditClass(tokenGenerator, 8735, "Advanced Database", 3, "Mohamed",
					"Sep.01", "Dec.20", "9:00am", "10:30am", "Tu,Th", "KHKH110", "Unite", "No", "Database", "CS"));

			// boolean testResult = testScrs.studentAddClass(myToken, 8735,
			// "A/F", "FALL");
			// if (testResult == true) {
			// System.out.println("Adding class is done!");
			// } else
			// System.out.println("Adding class meet error.");
		}
	}

	@Test
	public void TestAdminAddStudentToClass() throws ClassNotFoundException, SQLException {
		SCRS testScrs = new SCRSImpl();
		ShibbolethAuth sbAuth = new ShibbolethAuth();
		Token tokenGenerator = sbAuth.tokenGenerator("bob111", "mypassword");
		if (tokenGenerator != null && tokenGenerator.type == Token.RoleType.ADMIN) {
			// where to judge the token type??? inside the function or in
			// the test
			assertEquals(true, testScrs.adminAddStudentToClass(tokenGenerator, 5001, 8735, "A/F", "Fall"));

			// boolean testResult = testScrs.studentAddClass(myToken, 8735,
			// "A/F", "FALL");
			// if (testResult == true) {
			// System.out.println("Adding class is done!");
			// } else
			// System.out.println("Adding class meet error.");
		}
	}

	@Test
	public void TestAdminEditStudentRegisteredClass() throws ClassNotFoundException, SQLException {
		SCRS testScrs = new SCRSImpl();
		ShibbolethAuth sbAuth = new ShibbolethAuth();
		Token tokenGenerator = sbAuth.tokenGenerator("bob111", "mypassword");
		if (tokenGenerator != null && tokenGenerator.type == Token.RoleType.ADMIN) {
				// where to judge the token type??? inside the function or in
				// the test
				assertEquals(true, testScrs.adminEditStudentRegisteredClass(tokenGenerator, 5001, 8735, "S/N", "Fall"));

				// boolean testResult = testScrs.studentAddClass(myToken, 8735,
				// "A/F", "FALL");
				// if (testResult == true) {
				// System.out.println("Adding class is done!");
				// } else
				// System.out.println("Adding class meet error.");
			}
		} 

	@Test
	public void TestAdminDropStudentRegisteredClass() throws ClassNotFoundException, SQLException {
		SCRS testScrs = new SCRSImpl();
		ShibbolethAuth sbAuth = new ShibbolethAuth();
		Token tokenGenerator = sbAuth.tokenGenerator("bob111", "mypassword");
		if (tokenGenerator != null && tokenGenerator.type == Token.RoleType.ADMIN) {
				// where to judge the token type??? inside the function or in
				// the test
				assertEquals(true, testScrs.adminDropStudentRegisteredClass(tokenGenerator, 1, 5001));

				// boolean testResult = testScrs.studentAddClass(myToken, 8735,
				// "A/F", "FALL");
				// if (testResult == true) {
				// System.out.println("Adding class is done!");
				// } else
				// System.out.println("Adding class meet error.");
			}
		} 

}
