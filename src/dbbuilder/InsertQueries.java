package dbbuilder;

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
			c.setAutoCommit(false); // TODO: Fix this ?
			System.out.println("Opened database successfully");

			stmt = c.createStatement();

			// SHIBBOLETHAUTH

			String userNames11 = "INSERT INTO SHIBBOLETHAUTH (ID, X500ACCOUNT, X500PASSWORD, USERID, USERTYPE) "
					+ "VALUES (999, 'bob111', 'mypassword', 111, 'ADMIN')";
			stmt.executeUpdate(userNames11);

			// String userNames22 = "INSERT INTO SHIBBOLETHAUTH (ID,
			// X500ACCOUNT, X500PASSWORD, USERID, USERTYPE) "
			// + "VALUES (null, 'bob222', 'mypassword', 222, 'ADMIN');";
			// stmt.executeUpdate(userNames22);
			//
			// String userNames33 = "INSERT INTO SHIBBOLETHAUTH (ID,
			// X500ACCOUNT, X500PASSWORD, USERID, USERTYPE) "
			// + "VALUES (null, 'bob333', 'mypassword', 333, 'ADMIN');";
			// stmt.executeUpdate(userNames33);
			//
			// String userNames = "INSERT INTO SHIBBOLETHAUTH (ID, X500ACCOUNT,
			// X500PASSWORD, USERID, USERTYPE) "
			// + "VALUES (null, 'alice001', 'mypassword', 1, 'STUDENT');";
			// stmt.executeUpdate(userNames);
			//
			// String userNames2 = "INSERT INTO SHIBBOLETHAUTH (ID, X500ACCOUNT,
			// X500PASSWORD, USERID, USERTYPE) "
			// + "VALUES (null, 'alice002', 'mypassword', 2, 'STUDENT');";
			// stmt.executeUpdate(userNames2);
			//
			// String userNames3 = "INSERT INTO SHIBBOLETHAUTH (ID, X500ACCOUNT,
			// X500PASSWORD, USERID, USERTYPE) "
			// + "VALUES (null, 'alice003', 'mypassword', 3, 'STUDENT');";
			// stmt.executeUpdate(userNames3);
			//
			// String userNames4 = "INSERT INTO SHIBBOLETHAUTH (ID, X500ACCOUNT,
			// X500PASSWORD, USERID, USERTYPE) "
			// + "VALUES (null, 'bob001', 'mypassword', 4, 'ADMIN');";
			// stmt.executeUpdate(userNames4);

			// STUDENT
			String allValues = "INSERT INTO STUDENT (ID, FIRSTNAME, LASTNAME, DATEOFBIRTH,TYPE,GENDER,ADVISOR,CREDITS,DEPARTMENT) "
					+ "VALUES (888, 'Alice1', 'Liddell',  10/03/2000 ,'Master', 'Female', 'Mad Hatter', 1, 'CS');";
			stmt.executeUpdate(allValues);

			// String reqdValues1 = "INSERT INTO STUDENT (ID,FIRSTNAME,
			// LASTNAME, DATEOFBIRTH,TYPE,CREDITS,DEPARTMENT) "
			// + "VALUES (2, 'Alice2', 'Liddell2', 03/10/2000 ,'BS', 22,
			// 'Computer Science');";
			// stmt.executeUpdate(reqdValues1);
			//
			// String reqdValues2 = "INSERT INTO STUDENT (ID,FIRSTNAME,
			// LASTNAME, DATEOFBIRTH,TYPE,CREDITS,DEPARTMENT) "
			// + "VALUES (3, 'Alice3', 'Liddell3', 04/09/1998 ,'BS', 22,
			// 'Computer Science');";
			// stmt.executeUpdate(reqdValues2);

			// ADMIN
			String admin1 = "INSERT INTO ADMINISTRATOR (ID, FIRSTNAME, LASTNAME, DATEOFBIRTH, GENDER, DEPARTMENT) "
					+ "VALUES (111, 'AdminAlice1', 'Johnson',  12/01/1900, 'Female', 'CS');";
			stmt.executeUpdate(admin1);

			// String admin2 = "INSERT INTO ADMINISTRATOR (ID,FIRSTNAME,
			// LASTNAME, DATEOFBIRTH,GENDER, DEPARTMENT) "
			// + "VALUES (102, 'AdminAlice2', 'Johnson2', 1899-06-04 , 'Male',
			// 'Botany');";
			// stmt.executeUpdate(admin2);
			//
			// String admin3 = "INSERT INTO ADMINISTRATOR (ID,FIRSTNAME,
			// LASTNAME, DATEOFBIRTH,GENDER,DEPARTMENT) "
			// + "VALUES (103, 'AdminAlice3', 'Johnson3', 2001-11-30 , 'Female',
			// 'Horticulture');";
			// stmt.executeUpdate(admin3);
			// INSTRUCTOR
			String instructor1 = "INSERT INTO INSTRUCTOR (ID, FIRSTNAME, LASTNAME, DATEOFBIRTH, GENDER,TITLE, DEPARTMENT) "
					+ "VALUES (111, 'Mokbel', 'Mohamed',  12/01/1900, 'Male', 'Professor', 'CS');";
			stmt.executeUpdate(instructor1);

			// COURSE
			String course1 = "INSERT INTO COURSE (ID, NAME,  CREDITS,CAPACITY, TERM, FIRSTDAY, LASTDAY, CLASSBEGINTIME, CLASSENDTIME, ROUTINES, LOCATION, TYPE, PREREQUISITE, DESCRIPTION, DEPARTMENT) "
					+ "VALUES (777, '2011', 3, 30, '2014Fall',01/25/2015, 05/30/2015, '08:00', '09:15', 'Tu, Th', 'KH2150', 'Lecture', 'NONE', '2011 Description', 'CS')";
			stmt.executeUpdate(course1);

			// String course2 = "INSERT INTO COURSE (ID, NAME, CREDITS,
			// FIRSTDAY, LASTDAY, CLASSBEGINTIME, CLASSENDTIME, ROUTINES,
			// LOCATION, TYPE, PREREQUISITE, DESCRIPTION, DEPARTMENT) "
			// + "VALUES (11, '4511', 4, 2015-01-25, 2015-05-30, '1200', '1300',
			// 'M, W, F', 'ME2119', 'CAMPUS', '2011', '4511 Description',
			// 'Computer Science')";
			// stmt.executeUpdate(course2);
			//
			// String course3 = "INSERT INTO COURSE (ID, NAME, CREDITS,
			// FIRSTDAY, LASTDAY, CLASSBEGINTIME, CLASSENDTIME, ROUTINES,
			// LOCATION, TYPE, PREREQUISITE, DESCRIPTION, DEPARTMENT) "
			// + "VALUES (12, '5001', 4, 2015-01-25, 2015-05-30, '0900', '1015',
			// 'Tu, Th', 'ONLINE', 'UNITE', 'NONE', '5001 Description',
			// 'Computer Science')";
			// stmt.executeUpdate(course3);
			//
			// String course4 = "INSERT INTO COURSE (ID, NAME, CREDITS,
			// FIRSTDAY, LASTDAY, CLASSBEGINTIME, CLASSENDTIME, ROUTINES,
			// LOCATION, TYPE, PREREQUISITE, DESCRIPTION, DEPARTMENT) "
			// + "VALUES (13, '5021', 3, 2015-01-25, 2015-05-30, '1500', '1630',
			// 'Th', 'KH3001', 'CAMPUS', 'NONE', '5021 Description', 'Computer
			// Science')";
			// stmt.executeUpdate(course4);

			// STUDENTANDCOURSE
			String sTable1 = "INSERT INTO STUDENTANDCOURSE (ID,COURSEID, GRADING, COURSETERM, STUDENTID) "
					+ "VALUES (666, 5001, 'A-F',  'Spring 2015', 1);";
			stmt.executeUpdate(sTable1);
			//
			// String sTable2 = "INSERT INTO STUDENTANDCOURSE (ID,COURSEID,
			// GRADING, COURSETERM, STUDENTID) "
			// + "VALUES (11, 5001, 'S/N', 'Spring 2015', 2);";
			// stmt.executeUpdate(sTable2);
			//
			// String sTable3 = "INSERT INTO STUDENTANDCOURSE (ID,COURSEID,
			// GRADING, COURSETERM, STUDENTID) "
			// + "VALUES (12, 5021, 'AUD', 'Spring 2015', 3);";
			// stmt.executeUpdate(sTable3);
			//
			// String sTable4 = "INSERT INTO STUDENTANDCOURSE (ID,COURSEID,
			// GRADING, COURSETERM, STUDENTID) "
			// + "VALUES (13, 5021, 'A/F', 'Spring 2015', 1);";
			// stmt.executeUpdate(sTable4);
			//
			// String sTable5 = "INSERT INTO STUDENTANDCOURSE (ID,COURSEID,
			// GRADING, COURSETERM, STUDENTID) "
			// + "VALUES (14, 2011, 'AUD', 'Spring 2015', 1);";
			// stmt.executeUpdate(sTable5);
			//
			// String sTable6 = "INSERT INTO STUDENTANDCOURSE (ID,COURSEID,
			// GRADING, COURSETERM, STUDENTID) "
			// + "VALUES (15, 4511, 'S/N', 'Spring 2015', 3);";
			// stmt.executeUpdate(sTable6);
			//
			// String sTable7 = "INSERT INTO STUDENTANDCOURSE (ID,COURSEID,
			// GRADING, COURSETERM, STUDENTID) "
			// + "VALUES (16, 4511, 'S/N', 'Spring 2015', 2);";
			// stmt.executeUpdate(sTable7);
			//
			// String sTable8 = "INSERT INTO STUDENTANDCOURSE (ID,COURSEID,
			// GRADING, COURSETERM, STUDENTID) "
			// + "VALUES (17, 4511, 'AUD', 'Spring 2015', 1);";
			// stmt.executeUpdate(sTable8);

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
