package dbbuilder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SelectFromInstructorAndCourse {
	public static void main(String args[]) {
		String databaseName = "jdbc:sqlite:SCRSDataBase.db";
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection(databaseName);
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM INSTRUCTORANDCOURSE;");
			while (rs.next()) {
				int id = rs.getInt("id");
				int courseID = rs.getInt("COURSEID");
				int instructorId = rs.getInt("INSTRUCTORID");
				System.out.println("ID = " + id);
				System.out.println("COURSEID = " + courseID);
				System.out.println("instructorId = " + instructorId);
				System.out.println();
			}
			rs.close();
			stmt.close();
			c.close();
			
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Operation done successfully");
	}

	

}
