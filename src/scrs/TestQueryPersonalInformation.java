package scrs;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

import scrs.SCRS;
import scrs.SCRSImpl;
import scrs.ShibbolethAuth;
import scrs.ShibbolethAuth.Token;

public class TestQueryPersonalInformation {

	@Test
	public void testDisplayStudentDetails() {

		// INITIALIZATION
		SCRS testScrs = new SCRSImpl();

		// LOGIN
		ShibbolethAuth sbAuth = new ShibbolethAuth();
		Token myToken = ((SCRSImpl) testScrs).userLogin("alice001", "mypassword");

		try {
			if (myToken != null && sbAuth.TokenAuth(myToken)) {
				List<ArrayList<String>> testResult = testScrs.queryStudentPersonalData(myToken, myToken.id);
				Iterator<ArrayList<String>> printIter = testResult.iterator();
				while (printIter.hasNext()) {
					Iterator<String> printInnerIter = printIter.next().iterator();
					while (printInnerIter.hasNext()) {
						System.out.print(printInnerIter.next() + "\t");
					}
				}
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
	public void testDisplayInvalidAdminDetails() {

		// INITIALIZATION

		SCRS testScrs = new SCRSImpl();

		ShibbolethAuth sbAuth = new ShibbolethAuth();
		Token myToken = ((SCRSImpl) testScrs).userLogin("bob999", "mypassword");

		try {
			if (myToken != null && sbAuth.TokenAuth(myToken)) //
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
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testDisplayValidAdminDetails() {

		SCRS testScrs = new SCRSImpl();

		ShibbolethAuth sbAuth = new ShibbolethAuth();
		Token myToken = ((SCRSImpl) testScrs).userLogin("bob111", "mypassword");

		try {
			if (myToken != null && sbAuth.TokenAuth(myToken)) //
			{
				List<ArrayList<String>> testResult = testScrs.queryAdminPersonalData(myToken, myToken.id);
				Iterator<ArrayList<String>> printIter = testResult.iterator();
				while (printIter.hasNext()) {
					Iterator<String> printInnerIter = printIter.next().iterator();
					while (printInnerIter.hasNext()) {
						System.out.print(printInnerIter.next() + "\t");
					}
				}
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
