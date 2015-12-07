package scrs;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

import scrs.ShibbolethAuth.Token;

public class TestQueryPersonalInformation {

	@Test
	public void testDisplayStudentDetails() {

		// INITIALIZATION
		SCRS testScrs = new SCRSImpl();

		// LOGIN
		ShibbolethAuth sbAuth = new ShibbolethAuth();
		Token myToken = ((SCRSImpl) testScrs).userLogin("alice001", "mypassword");

		if (myToken != null) {
			List<ArrayList<String>> testResult = testScrs.queryStudentPersonalData(myToken, myToken.id);
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
	public void testDisplayInvalidAdminDetails() {

		// INITIALIZATION

		SCRS testScrs = new SCRSImpl();

		ShibbolethAuth sbAuth = new ShibbolethAuth();
		Token myToken = ((SCRSImpl) testScrs).userLogin("bob999", "mypassword");

		if (myToken != null) //
		{
			List<ArrayList<String>> testResult = testScrs.queryStudentPersonalData(myToken, myToken.id);

			Iterator<ArrayList<String>> printIter = testResult.iterator();
			while (printIter.hasNext()) {
				Iterator<String> printInnerIter = printIter.next().iterator();
				while (printInnerIter.hasNext()) {
					// metaRes.add(innerIter.next().toString());
					System.out.print(printInnerIter.next() + "\t");
				}
			}
		}
	}

	@Test
	public void testDisplayValidAdminDetails() {

		SCRS testScrs = new SCRSImpl();

		ShibbolethAuth sbAuth = new ShibbolethAuth();
		Token myToken = ((SCRSImpl) testScrs).userLogin("bob111", "mypassword");

		if (myToken != null) //
		{
			List<ArrayList<String>> testResult = testScrs.queryAdminPersonalData(myToken);
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
