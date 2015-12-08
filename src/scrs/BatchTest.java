package scrs;

import static org.junit.Assert.*;

import org.junit.Test;

import scrs.ShibbolethAuth.Token;

public class BatchTest {
	@Test
	public void batchTest() {

		fail("Not yet implemented");
		SCRS testScrs = new SCRSImpl();
		
		//Admin Log in
		Token adminToken = ((SCRSImpl) testScrs).userLogin("John193", "password");
		Token studentToken = ((SCRSImpl) testScrs).userLogin("YUWEI1005", "mypassword");
		
		assertNotNull(adminToken);
		assertNotNull(studentToken);

		// Add Class
		assertEquals(true, testScrs.adminAddClass(adminToken, 555, "Course 555", 4, 
				25, "Spring2016", 206, "02/01/2016", "06/01/2016", "8:00", 
				"9:15", "M,W,F", "KH2150", "Seminar", "No", "555 Description", "CS"));
		assertEquals(true, testScrs.adminAddClass(adminToken, 666, "Course 666", 4, 
				25, "Spring2016", 206, "02/01/2016", "06/01/2016", "8:00", 
				"9:15", "M,W,F", "KH2150", "Lecture", "No", "666 Description", "CS"));
		
	}
}
