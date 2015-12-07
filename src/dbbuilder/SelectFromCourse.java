package dbbuilder;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SelectFromCourse {
	public static void main(String args[]) {
		selectAll();
	}
	
	public static void selectAll() {
		String databaseName = "jdbc:sqlite:SCRSDataBase.db";
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection(databaseName);
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM COURSE;");
			while (rs.next()) {
				int id = rs.getInt("ID");
				String name = rs.getString("NAME");
				int credits = rs.getInt("CREDITS");
				int capacity = rs.getInt("CAPACITY");
				String term = rs.getString("TERM");
				Date firstDay = rs.getDate("FIRSTDAY");
				Date lastDay = rs.getDate("LASTDAY");
				String classBeginTime = rs.getString("CLASSBEGINTIME");
				String classEndTime = rs.getString("CLASSENDTIME");
				String routines = rs.getString("ROUTINES");
				String location = rs.getString("LOCATION");
				String courseType = rs.getString("TYPE");
				String prerequisite = rs.getString("PREREQUISITE");
				String description = rs.getString("DESCRIPTION");
			    String department = rs.getString("DEPARTMENT");
			    
				
				System.out.println("ID = " + id);
				System.out.println("name = " + name);
				System.out.println("lastName = " + credits);
				System.out.println("dateOfBirth = " + capacity);
				System.out.println("term = " + term);
				System.out.println("title = " + firstDay);
				System.out.println("salary = " + lastDay);
				System.out.println("classBeginTime = " + classBeginTime);
				System.out.println("classEndTime = " + classEndTime);
				System.out.println("routines = " + routines);
				System.out.println("location = " + location);
				System.out.println("courseType = " + courseType);
				System.out.println("prerequisite = " + prerequisite);
				System.out.println("description = " + description);
				System.out.println("department = " + department);
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
