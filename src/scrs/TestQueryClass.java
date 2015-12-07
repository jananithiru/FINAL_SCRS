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

public class TestQueryClass {

	@Test
	public void generateData() {
		String databaseName = "jdbc:sqlite:SCRSDataBase.db";
		Connection c = null;
		Statement stmt = null;

		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection(databaseName);
			c.setAutoCommit(false); // TODO: Fix this ?
			System.out.println("Opened database successfully");

			stmt = c.createStatement();

			String sqlStr = "INSERT INTO SHIBBOLETHAUTH (ID, X500ACCOUNT, X500PASSWORD, USERID, USERTYPE) "
					+ "VALUES (null, 'alice001', 'mypassword', 111, 'ADMIN')";
			stmt.executeUpdate(sqlStr);

			sqlStr = "INSERT INTO COURSE (ID, NAME,  CREDITS,CAPACITY, TERM, FIRSTDAY, LASTDAY, CLASSBEGINTIME, CLASSENDTIME, ROUTINES, LOCATION, TYPE, PREREQUISITE, DESCRIPTION, DEPARTMENT) "
					+ "VALUES (777, '2011', 3, 30, 'Spring2015', 02/01/2015, 05/30/2015, '08:00', '09:15', 'Tu, Th', 'KH2150', 'Lecture', 'NONE', '2011 Description', 'CS')";
			stmt.executeUpdate(sqlStr);

			c.commit();

			List<ArrayList<Object>> objList = null;
			DBCoordinator dbcoordinator = new DBCoordinator();
			sqlStr = "select * FROM course c WHERE c.id = 777 AND c.department = 'CS' AND c.location = 'KH2150' AND c.term = 'Spring2015' AND c.name = '2011' AND c.type = 'Lecture';";
			objList = dbcoordinator.queryData(sqlStr);
			System.out.println(objList);

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Records created successfully");
	}

	@Test
	public void testQueryClass() {

		// INITIALIZATION
		SCRS testScrs = new SCRSImpl();

		// LOGIN
		Token myToken = ((SCRSImpl) testScrs).userLogin("alice001", "mypassword");

		if (myToken != null) {
			List<ArrayList<String>> testResult = testScrs.queryClass(777, "2011", "KH2150", "Spring2015", "CS",
					"Lecture", null);
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
			List<ArrayList<String>> testResult;
			testResult = testScrs.queryClass(777, "2010", "KH2150", "Spring2015", "CS", "Lecture", null);
			assert (testResult == null);
		}
		System.out.println("test");
	}

}
