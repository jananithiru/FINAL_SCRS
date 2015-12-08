package scrs;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import scrs.ShibbolethAuth.Token;
import scrsexception.SCRSException;

public class TestStudentFunctionality {
	@Test
	public void TestStudentAddClass() {
		SCRS testScrs = new SCRSImpl();
		//ShibbolethAuth sbAuth = new ShibbolethAuth();

		// Add Class
		Token myTokenAdmin = ((SCRSImpl) testScrs).userLogin("John!196", "password");	
		Token myTokenStu = ((SCRSImpl) testScrs).userLogin("YUWEI1005", "mypassword");
		
		// Add Class	
		if (myTokenAdmin != null && myTokenStu != null) {
			// where to judge the token type??? inside the function or in
			// the test
			assertEquals(false, testScrs.studentAddClass(myTokenAdmin, 888, "A-F", "Fall2015"));
			assertEquals(true, testScrs.studentAddClass(myTokenStu, 888, "A-F", "Fall2015")); 
			assertEquals(false, testScrs.studentAddClass(myTokenStu, 888, null, "Fall2015")); //did not fill out the grade basis
			assertEquals(false, testScrs.studentAddClass(myTokenStu, 888, "A-F", null)); // did not fill out coure term
            
		}
	}

	
	@Test
	public void TestStudentDropClass() {
		SCRS testScrs = new SCRSImpl();
		ShibbolethAuth sbAuth = new ShibbolethAuth();
		Token myToken = ((SCRSImpl) testScrs).userLogin("YUWEI1005", "mypassword");
		if (myToken != null) {
			
			assertEquals(true, testScrs.studentDropClass(myToken, 11111));

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
