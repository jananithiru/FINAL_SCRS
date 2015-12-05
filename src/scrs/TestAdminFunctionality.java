package scrs;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import org.junit.Test;

import scrs.ShibbolethAuth.Token;

public class TestAdminFunctionality {

	
	@Test
	public void TestAdminAddClass() throws ClassNotFoundException, SQLException {
		SCRS testScrs = new SCRSImpl();
		ShibbolethAuth sbAuth = new ShibbolethAuth();
		Token tokenGenerator = sbAuth.tokenGenerator("bob111", "mypassword");
		System.out.println("READY FOR ADMIN");
		System.out.println(tokenGenerator.type);

		 if (tokenGenerator != null && tokenGenerator.type ==
		 Token.RoleType.ADMIN) {

			
			System.out.println("ADMIN ADD CLASS START");

			assertEquals(true,
					testScrs.adminAddClass(tokenGenerator, 8735, "Advanced Database", 1,30, "Mokbel", "09/01/2014",
							"12/20/2014", "9:00", "10:30", "Tu,Th", "KHKH110", "Lecture", "No", "Databases", "CS"));
			
			System.out.println("ADMIN ADD CLASS SUCCESSFUL");

		}
	}

	@Test
	public void TestAdminDeleteClass() throws ClassNotFoundException, SQLException {
		SCRS testScrs = new SCRSImpl();
		ShibbolethAuth sbAuth = new ShibbolethAuth();
		Token tokenGenerator = sbAuth.tokenGenerator("bob111", "mypassword");
		System.out.println("usertype  " + tokenGenerator.type);

		if (tokenGenerator != null && tokenGenerator.type == Token.RoleType.ADMIN) {
			if (tokenGenerator != null) {


				assertEquals(true, testScrs.adminDeleteClass(tokenGenerator, 777));

			
			}
		}
	}

	@Test
	public void TestAdminEditClass() throws ClassNotFoundException, SQLException {
		SCRS testScrs = new SCRSImpl();
		ShibbolethAuth sbAuth = new ShibbolethAuth();
		Token tokenGenerator = sbAuth.tokenGenerator("bob111", "mypassword");
		if (tokenGenerator != null && tokenGenerator.type == Token.RoleType.ADMIN) {
			
			System.out.println("ADMIN EDIT CLASS START");

			assertEquals(true, testScrs.adminEditClass(tokenGenerator, 8735, "Advanced Database", 3, "Mokbel", "09/01/2014",
					"12/20/2014", "9:00", "10:30", "Tu,Th", "KHKH110", "Lecture", "No", "Databases", "CS" ));

		
		}
	}

	@Test
	public void TestAdminAddStudentToClass() throws ClassNotFoundException, SQLException {
		SCRS testScrs = new SCRSImpl();
		ShibbolethAuth sbAuth = new ShibbolethAuth();
		Token tokenGenerator = sbAuth.tokenGenerator("bob111", "mypassword");
		if (tokenGenerator != null && tokenGenerator.type == Token.RoleType.ADMIN) {
			
			System.out.println("ADMIN ADD STDUENTCLASS START");

			assertEquals(true, testScrs.adminAddStudentToClass(tokenGenerator, 5001, 8735, "A-F", "2014Fall"));

			
		}
	}

	@Test
	public void TestAdminEditStudentRegisteredClass() throws ClassNotFoundException, SQLException {
		SCRS testScrs = new SCRSImpl();
		ShibbolethAuth sbAuth = new ShibbolethAuth();
		Token tokenGenerator = sbAuth.tokenGenerator("bob111", "mypassword");
		if (tokenGenerator != null && tokenGenerator.type == Token.RoleType.ADMIN) {
			
			assertEquals(true, testScrs.adminEditStudentRegisteredClass(tokenGenerator, 1, 5001, "S/N", "2015Fall"));

			
		}
	}

	@Test
	public void TestAdminDropStudentRegisteredClass() throws ClassNotFoundException, SQLException {
		SCRS testScrs = new SCRSImpl();
		ShibbolethAuth sbAuth = new ShibbolethAuth();
		Token tokenGenerator = sbAuth.tokenGenerator("bob111", "mypassword");
		System.out.println(tokenGenerator.type);
		if (tokenGenerator != null && tokenGenerator.type == Token.RoleType.ADMIN) {
			
			assertEquals(true, testScrs.adminDropStudentRegisteredClass(tokenGenerator, 1, 5001));

		}
	}

}
