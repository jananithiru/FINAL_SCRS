package scrs;

import dbbuilder.*;
import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import org.junit.Test;

import scrs.ShibbolethAuth.Token;

public class TestStudentFunctionality {
	@Test
	public void TestStudentAddClass() {
		SCRS testScrs = new SCRSImpl();
		ShibbolethAuth sbAuth = new ShibbolethAuth();
		Token myToken = ((SCRSImpl) testScrs).userLogin("bob999", "mypassword");
		try {
			if (myToken != null && sbAuth.TokenAuth(myToken)) {
				// where to judge the token type??? inside the function or in
				// the test
				assertEquals(true, testScrs.studentAddClass(myToken, 8735, "A/F", "FALL"));

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
	public void TestStudentDropClass() {
		SCRS testScrs = new SCRSImpl();
		ShibbolethAuth sbAuth = new ShibbolethAuth();
		Token myToken = ((SCRSImpl) testScrs).userLogin("bob999", "mypassword");
		try {
			if (myToken != null && sbAuth.TokenAuth(myToken)) {

				assertEquals(true, testScrs.studentDropClass(myToken, 8735));

				// boolean testResult = testScrs.studentDropClass(myToken,
				// 8735);
				// if (testResult == true) {
				// System.out.println("Dropping class is done!");
				// } else
				// System.out.println("Dropping class meet error.");

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
	public void testStudentEditClass() {
		SCRS testScrs = new SCRSImpl();
		ShibbolethAuth sbAuth = new ShibbolethAuth();
		Token myToken = ((SCRSImpl) testScrs).userLogin("bob999", "mypassword");
		try {
			if (myToken != null && sbAuth.TokenAuth(myToken)) {

				// boolean result = true;
				assertEquals(true, testScrs.studentEditClass(myToken, 8735, "S/N", "Fall"));
				// boolean testResult = testScrs.studentEditClass(myToken, 8735,
				// "S/N", "Fall");
				// if (testResult == true) {
				// System.out.println("Dropping class is done!");
				// } else
				// System.out.println("Dropping class meet error.");

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
