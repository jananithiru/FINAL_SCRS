package scrs;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

import scrs.ShibbolethAuth.Token;

public class TestQueryClass {

	@Test
	public void testQueryClass() {

		// INITIALIZATION
		SCRS testScrs = new SCRSImpl();

		// LOGIN
		ShibbolethAuth sbAuth = new ShibbolethAuth();
		Token myToken = ((SCRSImpl) testScrs).userLogin("alice001", "mypassword");

			if (myToken != null) {
				List<ArrayList<String>> testResult = testScrs.queryClass(20, "2011", "KH2150", "Spring2015", "CS", "CAMPUS", "");
				Iterator<ArrayList<String>> printIter = testResult.iterator();
				while (printIter.hasNext()) {
					Iterator<String> printInnerIter = printIter.next().iterator();
					while (printInnerIter.hasNext()) {
						System.out.print(printInnerIter.next() + "\t");
					}
				}
			}
	
	}

	@Test
	public void testQueryNonExistentClass() {

		// INITIALIZATION
		SCRS testScrs = new SCRSImpl();

		// LOGIN
		ShibbolethAuth sbAuth = new ShibbolethAuth();
		Token myToken = ((SCRSImpl) testScrs).userLogin("alice001", "mypassword");

			if (myToken != null) {
				List<ArrayList<String>> testResult = testScrs.queryClass(20, "2010", "KH2150", "Spring 2015", "Computer Science", "CAMPUS", "");
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
