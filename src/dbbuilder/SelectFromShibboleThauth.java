package dbbuilder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SelectFromShibboleThauth {
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
			ResultSet rs = stmt.executeQuery("SELECT * FROM SHIBBOLETHAUTH;");
			while (rs.next()) {
				int id = rs.getInt("id");
				String account = rs.getString("X500ACCOUNT");
				String password = rs.getString("X500PASSWORD");
				int userId = rs.getInt("USERID");
				String userType = rs.getString("USERTYPE");
				System.out.println("ID = " + id);
				System.out.println("account = " + account);
				System.out.println("password = " + password);
				System.out.println("userId = " + userId);
				System.out.println("userType = " + userType);
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
