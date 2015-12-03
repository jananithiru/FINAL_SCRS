package dbbuilder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class SetupDB {
	static String databaseName = "jdbc:sqlite:SCRSDataBase.db";
	public static void createStudentTable() {
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection(databaseName);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			String sql = "CREATE TABLE STUDENT " 	
					+ "(ID INT PRIMARY KEY     NOT NULL,"
					+ " FIRSTNAME           TEXT    NOT NULL, " 
					+ " LASTNAME           TEXT    NOT NULL, "
					+ " DATEOFBIRTH    DATE    NOT NULL, " 
					+ " TYPE           CHAR(10)    NOT NULL, "
					+ " GENDER        CHAR(10), " 
					+ " ADVISOR        CHAR(20), "
					+ " PLAN            CHAR(30)     NOT NULL, " 
					+ " CREDITS            INT     NOT NULL, "
					+ " DEPARTMENT        CHAR(50)	NOT NULL)";
			stmt.executeUpdate(sql);
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Table STUDENT created successfully");
	}

	public static void createAdministratorTable() {
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection(databaseName);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			String sql = "CREATE TABLE ADMINISTRATOR " 
					+ "(ID INT PRIMARY KEY     NOT NULL,"
					+ " FIRSTNAME           TEXT    NOT NULL, " 
					+ " LASTNAME           TEXT    NOT NULL, "
					+ " DATEOFBIRTH    DATE    NOT NULL, " 
					+ " GENDER        CHAR(10), " 
					+ " DEPARTMENT        CHAR(50)	NOT NULL)";
			stmt.executeUpdate(sql);
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Table ADMINISTRATOR created successfully");
	}

	public static void createInstructorTable() {
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection(databaseName);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			String sql = "CREATE TABLE INSTRUCTOR " 
			+ "(ID INT PRIMARY KEY     NOT NULL,"
					+ " FIRSTNAME           TEXT    NOT NULL, " 
					+ " LASTNAME           TEXT    NOT NULL, "
					+ " DATEOFBIRTH    DATE    NOT NULL, " 
					+ " GENDER        CHAR(10), " 
					+ " TITLE        CHAR(20), "
					+ " SALARY		INT(1), "
					+ " DEPARTMENT        CHAR(50)	NOT NULL)";
			stmt.executeUpdate(sql);
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Table INSTRUCTOR created successfully");
	}

	public static void createCourseTable() {
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection(databaseName);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			String sql = "CREATE TABLE COURSE " 
					+ "(ID INT PRIMARY KEY     NOT NULL,"
					+ " NAME           CHAR(50)    NOT NULL, " 
					+ " CREDITS           INT    NOT NULL, "
					+ " FIRSTDAY    DATE    NOT NULL, "
					+ " LASTDAY    DATE    NOT NULL, "
					+ " CLASSBEGINTIME    CHAR(10)    NOT NULL, "
					+ " CLASSENDTIME    CHAR(10)    NOT NULL, "
					+ " ROUTINES    CHAR(50)    NOT NULL, "
					+ " LOCATION    CHAR(100)    NOT NULL, "
					+ " TYPE    CHAR(20)    NOT NULL, "
					+ " PREREQUISITE    TEXT, "
					+ " DESCRIPTION TEXT NOT NULL, "
					+ " DEPARTMENT        CHAR(50)	NOT NULL)";
			stmt.executeUpdate(sql);
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Table COURSE created successfully");
	}

	public static void createStudentAndCourseTable() {
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection(databaseName);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			String sql = "CREATE TABLE STUDENTANDCOURSE " 
					+ "(ID INTEGER PRIMARY KEY   AUTOINCREMENT  NOT NULL,"
					+ " COURSEID INT REFERENCES COURSE(ID) ON UPDATE CASCADE, " 
					+ " GRADING        CHAR(10) NOT NULL, " 
					+ " COURSETERM        CHAR(20) NOT NULL, "
					+ " STUDENTID	INT REFERENCES STUDENT(ID) ON UPDATE CASCADE) ";
			stmt.executeUpdate(sql);
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Table STUDENTANDCOURSE created successfully");
	}

	public static void createInstructorAndCourseTable() {
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection(databaseName);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			String sql = "CREATE TABLE INSTRUCTORANDCOURSE " 
					+ "(ID INTEGER PRIMARY KEY   AUTOINCREMENT  NOT NULL,"
					+ " COURSEID INT REFERENCES COURSE(ID) ON UPDATE CASCADE, " 
					+ " INSTRUCTORID	INT REFERENCES INSTRUCTOR(ID) ON UPDATE CASCADE) ";
			stmt.executeUpdate(sql);
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Table INSTRUCTORANDCOURSE created successfully");
	}

	public static void createShibbolethAuthTable() {
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection(databaseName);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			String sql = "CREATE TABLE SHIBBOLETHAUTH " 
					+ "(ID INTEGER PRIMARY KEY   AUTOINCREMENT  NOT NULL,"
					+ "X500ACCOUNT    CHAR(50) NOT NULL, " 
					+ "X500PASSWORD   CHAR(20) NOT NULL, "
					+ " USERID         INT NOT NULL, " 
					+ " USERTYPE       CHAR(10) NOT NULL) ";
			stmt.executeUpdate(sql);
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Table SHIBBOLETHAUTH created successfully");
	}
	
	
	public static void main(String args[]) {
			createShibbolethAuthTable();
			createStudentTable();
			createStudentAndCourseTable();
			createAdministratorTable();
			createInstructorTable();
			createInstructorAndCourseTable();
			createCourseTable();
	}

}
