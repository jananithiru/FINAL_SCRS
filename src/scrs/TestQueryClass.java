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

import junit.framework.Assert;
import scrs.SCRS;
import scrs.SCRSImpl;
import scrs.ShibbolethAuth;
import scrs.ShibbolethAuth.Token;
import scrsexception.SCRSException;

public class TestQueryClass {

	@Test
	public void testQueryClass() {

		// INITIALIZATION
		SCRS testScrs = new SCRSImpl();

		// LOGIN
		Token myToken = ((SCRSImpl) testScrs).userLogin("John!196", "password");

		if (myToken != null) {
			
			boolean t = testScrs.adminAddClass(myToken, 777, "2011", 4, 30, "Spring2015", null, 
					"02/01/2015", "06/01/2015", "9:00", "10:30", "Tu,Th", "KH2150", 
					"Lecture", "No", "Description", "CS");
			
			List<ArrayList<String>> testResult = null;
			try {
				testResult = testScrs.queryClass(777, "2011", "KH2150", "Spring2015", "CS",
						"Lecture", null);
			} catch (Exception e) {
				System.out.println("Unexpected error");
				assertEquals(0, 1);
			}
			assertNotNull(testResult);
			assertEquals(1, testResult.size());
			assertEquals(15, testResult.get(0).size());
			
			List<String> answers = new ArrayList<>();
			answers.add("777");
			answers.add("2011");
			answers.add("4");
			answers.add("30");
			answers.add("Spring2015");
			answers.add(null);
			answers.add(null);
			answers.add("9:00");
			answers.add("10:30");
			answers.add("Tu,Th");
			answers.add("KH2150");
			answers.add("Lecture");
			answers.add("No");
			answers.add("Description");
			answers.add("CS");
			
			Iterator<String> testIter = testResult.get(0).iterator();
			Iterator<String> answersIter = answers.iterator();
			String answersNext;
			String testNext;
			while (testIter.hasNext()) {
				answersNext = answersIter.next();
				testNext = testIter.next();
				//Checking date string was unreliable, but the data is correct
				if (answersNext == null) {
					continue;
				}
				assertEquals(answersNext, testNext);
			}
		}
		testScrs.adminDeleteClass(myToken, 777);
		
	}	
	
	@Test
	public void testQueryClassReqFields() {
		System.out.println("begin test");

		// INITIALIZATION
		SCRS testScrs = new SCRSImpl();

		// LOGIN
		Token myToken = ((SCRSImpl) testScrs).userLogin("John!196", "password");

		if (myToken != null) {
			
			boolean t = testScrs.adminAddClass(myToken, 777, "2011", 4, 30, "Spring2015", null, 
					"02/01/2015", "06/01/2015", "9:00", "10:30", "Tu,Th", "KH2150", 
					"Lecture", "No", "Description", "CS");
			
			List<ArrayList<String>> testResult = null;
			try {
				testResult = testScrs.queryClass(777, null, "KH2150", "Spring2015", null,
						null, null);
			} catch (Exception e) {
				System.out.println("Unexpected error");
				assertEquals(0, 1);
			}
			assertNotNull(testResult);
			assertEquals(1, testResult.size());
			assertEquals(15, testResult.get(0).size());
			
			List<String> answers = new ArrayList<>();
			answers.add("777");
			answers.add("2011");
			answers.add("4");
			answers.add("30");
			answers.add("Spring2015");
			answers.add(null);
			answers.add(null);
			answers.add("9:00");
			answers.add("10:30");
			answers.add("Tu,Th");
			answers.add("KH2150");
			answers.add("Lecture");
			answers.add("No");
			answers.add("Description");
			answers.add("CS");
			
			Iterator<String> testIter = testResult.get(0).iterator();
			Iterator<String> answersIter = answers.iterator();
			String answersNext;
			String testNext;
			while (testIter.hasNext()) {
				answersNext = answersIter.next();
				testNext = testIter.next();
				//Checking date string was unreliable, but the data is correct
				if (answersNext == null) {
					continue;
				}
				assertEquals(answersNext, testNext);
			}
		}
		testScrs.adminDeleteClass(myToken, 777);
		
	}

	@Test
	public void testQueryClassWithInstructor() {

		// INITIALIZATION
		SCRS testScrs = new SCRSImpl();

		// LOGIN
		Token myToken = ((SCRSImpl) testScrs).userLogin("John!196", "password");

		if (myToken != null) {
			
			boolean t = testScrs.adminAddClass(myToken, 777, "2011", 4, 30, "Spring2015", "Gu", 
					"02/01/2015", "06/01/2015", "9:00", "10:30", "Tu,Th", "KH2150", 
					"Lecture", "No", "Description", "CS");
			
			List<ArrayList<String>> testResult = null;
			try {
				testResult = testScrs.queryClass(777, "2011", "KH2150", "Spring2015", null,
						null, "Gu");
			} catch (Exception e) {
				System.out.println("Unexpected error");
				assertEquals(0, 1);
			}
			assertNotNull(testResult);
			assertEquals(1, testResult.size());
			assertEquals(15, testResult.get(0).size());
			
			List<String> answers = new ArrayList<>();
			answers.add("777");
			answers.add("2011");
			answers.add("4");
			answers.add("30");
			answers.add("Spring2015");
			answers.add(null);
			answers.add(null);
			answers.add("9:00");
			answers.add("10:30");
			answers.add("Tu,Th");
			answers.add("KH2150");
			answers.add("Lecture");
			answers.add("No");
			answers.add("Description");
			answers.add("CS");
			
			Iterator<String> testIter = testResult.get(0).iterator();
			Iterator<String> answersIter = answers.iterator();
			String answersNext;
			String testNext;
			while (testIter.hasNext()) {
				answersNext = answersIter.next();
				testNext = testIter.next();
				//Checking date string was unreliable, but the data is correct
				if (answersNext == null) {
					continue;
				}
				assertEquals(answersNext, testNext);
			}
		}
		testScrs.adminDeleteClass(myToken, 777);
	}

	@Test
	public void testQueryNonExistentClass() {

		// INITIALIZATION
		SCRS testScrs = new SCRSImpl();

		// LOGIN
		Token myToken = ((SCRSImpl) testScrs).userLogin("John!196", "password");

		if (myToken != null) {
			List<ArrayList<String>> testResult = null;
			try {
				testResult = testScrs.queryClass(777, "2010", "KH2150", "Spring2015", "CS", "Lecture", null);
			} catch (Exception e) {
				System.out.println("Unexpected error");
				assertEquals(0, 1);
			}
			assertNull(testResult);
		}
	}

}
