package dbbuilder;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SelectQueries {

	public static void main(String args[]) {
		String databaseName = "jdbc:sqlite:CSRSDataBase.db";
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection(databaseName);
			c.setAutoCommit(false);
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM STUDENT;");
			while (rs.next()) {
				int id = rs.getInt("id");
				String firstname = rs.getString("firstname");
				String lastname = rs.getString("lastname");
				Date dateofbirth = rs.getDate("dateofbirth");
				String type = rs.getString("type");
				String gender = rs.getString("gender");
				String advisor = rs.getString("advisor");
				String plan = rs.getString("plan");
				int credits = rs.getInt("credits");
				String department = rs.getString("department");
				System.out.println("ID = " + id);
				System.out.println("FIRSTNAME = " + firstname);
				System.out.println("LASTNAME = " + lastname);
				System.out.println("DATEOFBIRTH = " + dateofbirth);
				System.out.println("TYPE = " + type);
				System.out.println("GENDER = " + gender);
				System.out.println("ADVISOR = " + advisor);
				System.out.println("PLAN = " + gender);
				System.out.println("CREDITS = " + advisor);
				System.out.println("DEPARTMENT = " + department);
				System.out.println();
			}
			// ID,FIRSTNAME, LASTNAME,
			// DATEOFBIRTH,TYPE,GENDER,ADVISOR,PLAN,CREDITS,DEPARTMENT
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
