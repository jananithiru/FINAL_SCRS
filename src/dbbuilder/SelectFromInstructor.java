package dbbuilder;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SelectFromInstructor {
	
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
			ResultSet rs = stmt.executeQuery("SELECT * FROM INSTRUCTOR;");
			while (rs.next()) {
				int id = rs.getInt("ID");
				String firstName = rs.getString("FIRSTNAME");
				String lastName = rs.getString("LASTNAME");
				Date dateOfBirth = rs.getDate("DATEOFBIRTH");
				String gender = rs.getString("GENDER");
			    String title = rs.getString("TITLE");
			    int salary = rs.getInt("SALARY");
			    String department = rs.getString("DEPARTMENT");
			    
				
				System.out.println("ID = " + id);
				System.out.println("FIRSTNAME = " + firstName);
				System.out.println("lastName = " + lastName);
				System.out.println("dateOfBirth = " + dateOfBirth);
				System.out.println("gender = " + gender);
				System.out.println("title = " + title);
				System.out.println("salary = " + salary);
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
