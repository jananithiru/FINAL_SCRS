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
	public void TestAdminAddClass() {
		SCRS testScrs = new SCRSImpl();
		ShibbolethAuth sbAuth = new ShibbolethAuth();
		Token myToken = ((SCRSImpl) testScrs).userLogin("bob111", "mypassword");
		try {
			if (myToken != null && sbAuth.TokenAuth(myToken)) {
				// where to judge the token type??? inside the function or in
				// the test
				assertEquals(true, testScrs.adminAddClass(myToken, 8735, "Advanced Database", 3, "Mohamed", "Sep.01",
						"Dec.20", "9:00am", "10:30am", "Tu,Th", "KHKH110", "campus", "No", "Databases", "CS"));

				// boolean testResult = testScrs.studentAddClass(myToken, 8735,
				// "A/F", "FALL");
				// if (testResult == true) {
				// System.out.println("Adding class is done!");
				// } else
				// System.out.println("Adding class meet error.");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void TestAdminDeleteClass() {
		SCRS testScrs = new SCRSImpl();
		ShibbolethAuth sbAuth = new ShibbolethAuth();
		Token myToken = ((SCRSImpl) testScrs).userLogin("bob111", "mypassword");
		try {
			if (myToken != null && sbAuth.TokenAuth(myToken)) {
				// where to judge the token type??? inside the function or in
				// the test
				assertEquals(true, testScrs.adminDeleteClass(myToken, 8735));

				// boolean testResult = testScrs.studentAddClass(myToken, 8735,
				// "A/F", "FALL");
				// if (testResult == true) {
				// System.out.println("Adding class is done!");
				// } else
				// System.out.println("Adding class meet error.");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void TestAdminEditClass() {
		SCRS testScrs = new SCRSImpl();
		ShibbolethAuth sbAuth = new ShibbolethAuth();
		Token myToken = ((SCRSImpl) testScrs).userLogin("bob111", "mypassword");
		try {
			if (myToken != null && sbAuth.TokenAuth(myToken)) {
				// where to judge the token type??? inside the function or in
				// the test
				assertEquals(true, testScrs.adminEditClass(myToken, 8735, "Advanced Database", 3, "Mohamed", "Sep.01",
						"Dec.20", "9:00am", "10:30am", "Tu,Th", "KHKH110", "Unite", "No", "Database", "CS"));

				// boolean testResult = testScrs.studentAddClass(myToken, 8735,
				// "A/F", "FALL");
				// if (testResult == true) {
				// System.out.println("Adding class is done!");
				// } else
				// System.out.println("Adding class meet error.");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void TestAdminAddStudentToClass() {
		SCRS testScrs = new SCRSImpl();
		ShibbolethAuth sbAuth = new ShibbolethAuth();
		Token myToken = ((SCRSImpl) testScrs).userLogin("bob111", "mypassword");
		try {
			if (myToken != null && sbAuth.TokenAuth(myToken)) {
				// where to judge the token type??? inside the function or in
				// the test
				assertEquals(true, testScrs.adminAddStudentToClass(myToken, 5001, 8735, "A/F", "Fall"));

				// boolean testResult = testScrs.studentAddClass(myToken, 8735,
				// "A/F", "FALL");
				// if (testResult == true) {
				// System.out.println("Adding class is done!");
				// } else
				// System.out.println("Adding class meet error.");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void TestAdminEditStudentRegisteredClass() {
		SCRS testScrs = new SCRSImpl();
		ShibbolethAuth sbAuth = new ShibbolethAuth();
		Token myToken = ((SCRSImpl) testScrs).userLogin("bob111", "mypassword");
		try {
			if (myToken != null && sbAuth.TokenAuth(myToken)) {
				// where to judge the token type??? inside the function or in
				// the test
				assertEquals(true, testScrs.adminEditStudentRegisteredClass(myToken, 5001, 8735, "S/N", "Fall"));

				// boolean testResult = testScrs.studentAddClass(myToken, 8735,
				// "A/F", "FALL");
				// if (testResult == true) {
				// System.out.println("Adding class is done!");
				// } else
				// System.out.println("Adding class meet error.");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void TestAdminDropStudentRegisteredClass() {
		SCRS testScrs = new SCRSImpl();
		ShibbolethAuth sbAuth = new ShibbolethAuth();
		Token myToken = ((SCRSImpl) testScrs).userLogin("bob111", "mypassword");
		try {
			if (myToken != null && sbAuth.TokenAuth(myToken)) {
				// where to judge the token type??? inside the function or in
				// the test
				assertEquals(true, testScrs.adminDropStudentRegisteredClass(myToken, 1, 5001));

				// boolean testResult = testScrs.studentAddClass(myToken, 8735,
				// "A/F", "FALL");
				// if (testResult == true) {
				// System.out.println("Adding class is done!");
				// } else
				// System.out.println("Adding class meet error.");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
