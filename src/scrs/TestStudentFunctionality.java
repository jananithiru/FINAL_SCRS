package scrs;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import org.junit.Test;

import scrs.ShibbolethAuth.Token;

public class TestStudentFunctionality {
	@Test
	public void TestStudentAddClass() {
		SCRS testScrs = new SCRSImpl();
		ShibbolethAuth sbAuth = new ShibbolethAuth();
		Token myToken = ((SCRSImpl) testScrs).userLogin("Alice121", "mypassword");		
		
		// Search Class 
		testScrs.queryClass(888, "Advanced Database2", "KHKH110", "Fall2015", "CS", null, null);
		
		// Add Class	
		if (myToken != null) {
			// where to judge the token type??? inside the function or in
			// the test
			assertEquals(true, testScrs.studentAddClass(myToken, 666, "A-F", "FALL"));

			// boolean testResult = testScrs.studentAddClass(myToken, 8735,
			// "A/F", "FALL");
			// if (testResult == true) {
			// System.out.println("Adding class is done!");
			// } else
			// System.out.println("Adding class meet error.");
		}
	}

	@Test
	public void TestStudentDropClass() {
		SCRS testScrs = new SCRSImpl();
		ShibbolethAuth sbAuth = new ShibbolethAuth();
		Token myToken = ((SCRSImpl) testScrs).userLogin("alice001", "mypassword");
		if (myToken != null) {

			assertEquals(true, testScrs.studentDropClass(myToken, 8735));

			// boolean testResult = testScrs.studentDropClass(myToken,
			// 8735);
			// if (testResult == true) {
			// System.out.println("Dropping class is done!");
			// } else
			// System.out.println("Dropping class meet error.");
		}
	}

	@Test
	public void testStudentEditClass() {
		SCRS testScrs = new SCRSImpl();
		ShibbolethAuth sbAuth = new ShibbolethAuth();
		Token myToken = ((SCRSImpl) testScrs).userLogin("alice001", "mypassword");
		if (myToken != null) {

			// boolean result = true;
			assertEquals(true, testScrs.studentEditClass(myToken, 8735, "S/N", "Fall"));
			// boolean testResult = testScrs.studentEditClass(myToken, 8735,
			// "S/N", "Fall");
			// if (testResult == true) {
			// System.out.println("Dropping class is done!");
			// } else
			// System.out.println("Dropping class meet error.");

		}
	}

}
