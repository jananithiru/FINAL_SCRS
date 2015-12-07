package scrs;

import static org.junit.Assert.*;

import org.junit.Test;

import scrs.ShibbolethAuth.Token;

public class TestMultipleTests {

	@Test
	public void testCompleteStudentSuite() {
		fail("Not yet implemented");
		SCRS testScrs = new SCRSImpl();
		ShibbolethAuth sbAuth = new ShibbolethAuth();
		
		Token myToken = ((SCRSImpl) testScrs).userLogin("John193", "password");

		
		
		
		
		if (myToken == null) {
			// Catch exception and print useful message
		}

		

		while (myToken.type != ShibbolethAuth.Token.RoleType.UNDEFINED) {
			// Search Class
			testScrs.queryClass(888, "Advanced Database2", "KHKH110", "Fall2015", "CS", null, null);

			// Add Class
			assertEquals(true, testScrs.studentAddClass(myToken, 666, "A-F", "FALL"));
			assertEquals(true, testScrs.studentAddClass(myToken, 666, "A-F", "FALL"));
			assertEquals(true, testScrs.studentAddClass(myToken, 666, "A-F", "FALL"));
			assertEquals(true, testScrs.studentAddClass(myToken, 666, "A-F", "FALL"));

		}

		myToken = ((SCRSImpl) testScrs).userLogin("Alice121", "mypassword");

		while (myToken.type != ShibbolethAuth.Token.RoleType.UNDEFINED) {
			// Search Class
			testScrs.queryClass(888, "Advanced Database2", "KHKH110", "Fall2015", "CS", null, null);

			// Add Class
			assertEquals(true, testScrs.studentAddClass(myToken, 666, "A-F", "FALL"));
			assertEquals(true, testScrs.studentAddClass(myToken, 666, "A-F", "FALL"));
			assertEquals(true, testScrs.studentAddClass(myToken, 666, "A-F", "FALL"));
			assertEquals(true, testScrs.studentAddClass(myToken, 666, "A-F", "FALL"));

		}

	}

}
