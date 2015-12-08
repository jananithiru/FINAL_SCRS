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
		Token myTokenAdmin = ((SCRSImpl) testScrs).userLogin("John!196", "password");
		if (myTokenAdmin != null) {
			testScrs.adminAddClass(myTokenAdmin, 777, "2011", 4, 30, "Spring2015", null, 
					"02/01/2015", "06/01/2015", "9:00", "10:30", "Tu,Th", "KH2150", 
					"Lecture", "No", "Description", "CS");
		}
		
		Token myTokenStu = ((SCRSImpl) testScrs).userLogin("YUWEI1005", "mypassword");
		if (myTokenStu != null) {
			//TODO class not in studentandcourse table, SqlException.
			testScrs.studentAddClass(myTokenStu, 777, "A-F", "Spring2015");
			
			List<ArrayList<String>> testResult = testScrs.queryStudentRegistrationHistory(myTokenStu, 1005);
			
			assertNotNull(testResult);
			assertEquals(5, testResult.get(0).size());
			
			testScrs.studentDropClass(myTokenStu, 777);
		}
		
		if (myTokenAdmin != null) {
			testScrs.adminDeleteClass(myTokenAdmin, 777);
		}

	}
}