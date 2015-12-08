package scrs;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

import scrs.SCRS;
import scrs.SCRSImpl;
import scrs.ShibbolethAuth;
import scrs.ShibbolethAuth.Token;

public class TestRegHistory {

	@Test
	public void testQueryRegHistory() {

		// INITIALIZATION
		SCRS testScrs = new SCRSImpl();

		// LOGIN
		Token myToken = ((SCRSImpl) testScrs).userLogin("John!196", "password");
		if (myToken != null) {
			testScrs.adminAddClass(myToken, 777, "2011", 4, 30, "Spring2015", null, 
					"02/01/2015", "06/01/2015", "9:00", "10:30", "Tu,Th", "KH2150", 
					"Lecture", "No", "Description", "CS");
		}
		
		myToken = ((SCRSImpl) testScrs).userLogin("YUWEI1005", "mypassword");
		if (myToken != null) {
			testScrs.studentAddClass(myToken, 777, "A-F", "Spring2015");
			
			List<ArrayList<String>> testResult = testScrs.queryStudentRegistrationHistory(myToken, 1005);
			
			assertNotNull(testResult);
			assertEquals(5, testResult.get(0).size());
			
			Iterator<ArrayList<String>> printIter = testResult.iterator();
			while (printIter.hasNext()) {
				Iterator<String> printInnerIter = printIter.next().iterator();
				while (printInnerIter.hasNext()) {
					System.out.print(printInnerIter.next() + "\t");
				}
			}
		}

	}
}