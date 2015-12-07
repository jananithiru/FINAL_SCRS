package dbbuilder;
<<<<<<< HEAD
=======

>>>>>>> c551eb00f95eeba62b6d8b6bd1836917eb7fb710
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import scrs.ShibbolethAuth.Token;

public class InsertQueries {
	static String databaseName = "jdbc:sqlite:SCRSDataBase.db";

	public static void insertStudentTable() {
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection(databaseName);
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();

			// SHIBBOLETHAUTH

			String userNames11 = "INSERT INTO SHIBBOLETHAUTH (ID, X500ACCOUNT, X500PASSWORD, USERID, USERTYPE) "
					+ "VALUES (999, 'bob111', 'mypassword', 111, 'ADMIN')";
			stmt.executeUpdate(userNames11);

			// STUDENT
			String allValues = "INSERT INTO STUDENT (ID, FIRSTNAME, LASTNAME, DATEOFBIRTH,TYPE,GENDER,ADVISOR,CREDITS,DEPARTMENT) "
					+ "VALUES (888, 'Alice1', 'Liddell',  10/03/2000 ,'Master', 'Female', 'Mad Hatter', 1, 'CS');";
			stmt.executeUpdate(allValues);

			// ADMIN
			String admin1 = "INSERT INTO ADMINISTRATOR (ID, FIRSTNAME, LASTNAME, DATEOFBIRTH, GENDER, DEPARTMENT) "
					+ "VALUES (111, 'AdminAlice1', 'Johnson',  12/01/1900, 'Female', 'CS');";
			stmt.executeUpdate(admin1);

			// INSTRUCTOR
			String instructor1 = "INSERT INTO INSTRUCTOR (ID, FIRSTNAME, LASTNAME, DATEOFBIRTH, GENDER,TITLE, DEPARTMENT) "
					+ "VALUES (111, 'Mokbel', 'Mohamed',  12/01/1900, 'Male', 'Professor', 'CS');";
			stmt.executeUpdate(instructor1);

			// COURSE
			String course1 = "INSERT INTO COURSE (ID, NAME,  CREDITS,CAPACITY,TERM, FIRSTDAY, LASTDAY, CLASSBEGINTIME, CLASSENDTIME, ROUTINES, LOCATION, TYPE, PREREQUISITE, DESCRIPTION, DEPARTMENT) "
					+ "VALUES (777, '2011', 3, 30, '2014Spring', 01/25/2015, 05/30/2015, '08:00', '09:15', 'Tu, Th', 'KH2150', 'Lecture', 'NONE', '2011 Description', 'CS')";

			stmt.executeUpdate(course1);

			// STUDENTANDCOURSE
			String sTable1 = "INSERT INTO STUDENTANDCOURSE (ID,COURSEID, GRADING, COURSETERM, STUDENTID) "
					+ "VALUES (666, 5001, 'A-F',  'Spring 2015', 1);";
			stmt.executeUpdate(sTable1);

			stmt.close();
			c.commit();
			c.close();

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Records created successfully");
	}

	public static void insertAdminTable() {
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection(databaseName);
			c.setAutoCommit(false); // TODO: Fix this ?
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			String userNames = "INSERT INTO SHIBBOLETHAUTH (ID, X500ACCOUNT, X500PASSWORD, USERID, USERTYPE) "
					+ "VALUES (null, 'bob111', 'mypassword', 111, 'ADMIN');";
			stmt.executeUpdate(userNames);

			String userNames2 = "INSERT INTO SHIBBOLETHAUTH (ID, X500ACCOUNT, X500PASSWORD, USERID, USERTYPE) "
					+ "VALUES (null, 'bob222', 'mypassword', 222, 'ADMIN');";
			stmt.executeUpdate(userNames2);

			String userNames3 = "INSERT INTO SHIBBOLETHAUTH (ID, X500ACCOUNT, X500PASSWORD, USERID, USERTYPE) "
					+ "VALUES (null, 'bob333', 'mypassword', 333, 'ADMIN');";
			stmt.executeUpdate(userNames3);

			String allValues = "INSERT INTO ADMINISTRATOR (ID, FIRSTNAME, LASTNAME, DATEOFBIRTH, DEPARTMENT, GENDER) "
					+ "VALUES (111, 'Bob111', 'Watson111',  2013-10-07 ,'Electrical Engg', 'Male');";
			stmt.executeUpdate(allValues);

			String reqdValues1 = "INSERT INTO ADMINISTRATOR (ID, FIRSTNAME, LASTNAME, DATEOFBIRTH, DEPARTMENT, GENDER) "
					+ "VALUES (222, 'Bob222', 'Watson222',  2013-10-07 ,'Electrical Engg', 'Male');";
			stmt.executeUpdate(reqdValues1);

			String reqdValues2 = "INSERT INTO ADMINISTRATOR (ID,FIRSTNAME, LASTNAME, DATEOFBIRTH, DEPARTMENT, GENDER) "
					+ "VALUES (333, 'Bob333', 'Watson333',  2013-10-07 ,'Electrical Engg', 'Male');";
			stmt.executeUpdate(reqdValues2);

			stmt.close();
			c.commit();
			c.close();

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Records created successfully");
	}

	public static void main(String args[]) {
		insertStudentTable();
		// insertAdminTable();
	}
}