package scrs;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import scrs.Constants.PrimitiveDataType;
import scrs.ShibbolethAuth.Token;

public class Admin extends Person {

	public boolean adminAddClass(ShibbolethAuth.Token token, int courseID, String courseName, int courseCredits,
			int capacity, String instructor, String firstDay, String lastDay, String classBeginTime,
			String classEndTime, String weekDays, String location, String type, String prerequisite, String description,
			String department) throws SQLException {

		// if (token.type != Token.RoleType.ADMIN) {
		// System.out.println("THIS IS NOT ADMIN");
		//
		// return false;
		// }

		DBCoordinator dbcoordinator = new DBCoordinator();

		String sqlCmd = null;
		sqlCmd = "INSERT INTO COURSE (ID, NAME, CREDITS,CAPACITY,FIRSTDAY, LASTDAY, CLASSBEGINTIME, CLASSENDTIME, ROUTINES, LOCATION, TYPE, PREREQUISITE, DESCRIPTION, DEPARTMENT) "
				+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		ArrayList<String> dataList = new ArrayList();
		dataList.add(Integer.toString(courseID));
		dataList.add(courseName);
		dataList.add(Integer.toString(courseCredits));
		dataList.add(Integer.toString(capacity));
		dataList.add(firstDay);
		dataList.add(lastDay);
		dataList.add(classBeginTime);
		dataList.add(classEndTime);
		dataList.add(weekDays);
		dataList.add(location);
		dataList.add(type);
		dataList.add(prerequisite);
		dataList.add(description);
		dataList.add(department);

		ArrayList<PrimitiveDataType> typeList = new ArrayList();

		typeList.add(PrimitiveDataType.INT);
		typeList.add(PrimitiveDataType.STRING);
		typeList.add(PrimitiveDataType.INT);
		typeList.add(PrimitiveDataType.INT);
		typeList.add(PrimitiveDataType.DATE);
		typeList.add(PrimitiveDataType.DATE);
		// typeList.add(PrimitiveDataType.INT);
		for (int i = 0; i < 8; i++) {
			typeList.add(PrimitiveDataType.STRING);

		}

		try {
			dbcoordinator.insertData(sqlCmd, dataList, typeList);
			System.out.println("ADMIN ADD CLASS real SUCCESSFUL");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;

	}

	public boolean adminDeleteClass(ShibbolethAuth.Token token, int courseID) throws SQLException {
		// if (token.type != Token.RoleType.ADMIN) {
		// return false;
		// }

		DBCoordinator dbcoordinator = new DBCoordinator();

		String sqlCmd = null;
		sqlCmd = "DELETE FROM COURSE WHERE ID = ?";
		ArrayList<String> dataList = new ArrayList();
		dataList.add(Integer.toString(courseID));

		ArrayList<PrimitiveDataType> typeList = new ArrayList();
		typeList.add(PrimitiveDataType.INT);
		try {
			System.out.println("WE HAVE SQLCMD DATALIST TYPELIST");

			dbcoordinator.deleteData(sqlCmd, dataList, typeList);
			System.out.println("ADMIN delete CLASS REAL SUCCESSFUL");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;

	}

	public boolean adminEditClass(ShibbolethAuth.Token token, int courseID, String courseName, int courseCredits,
			String instructor, String firstDay, String lastDay, String classBeginTime, String classEndTime,
			String weekDays, String location, String type, String prerequisite, String description, String department)
					throws SQLException {

		if (token.type != Token.RoleType.ADMIN) {
			return false;
		}

		DBCoordinator dbcoordinator = new DBCoordinator();

		String sqlCmd = null;
		sqlCmd = "UPDATE COURSE SET NAME = ?, CREDITS = ?, FIRSTDAY = ?, LASTDAY = ?, CLASSBEGINTIME = ?, CLASSENDTIME = ?, ROUTINES = ?, LOCATION = ?, TYPE = ?, PREREQUISITE = ?, DESCRIPTION = ?, DEPARTMENT = ? WHERE ID = ?";
		ArrayList<String> dataList = new ArrayList();
		dataList.add(courseName);
		dataList.add(Integer.toString(courseCredits));
		dataList.add(firstDay);
		dataList.add(lastDay);
		dataList.add(classBeginTime);
		dataList.add(classEndTime);
		dataList.add(weekDays);
		dataList.add(location);
		dataList.add(type);
		dataList.add(prerequisite);
		dataList.add(description);
		dataList.add(department);
		dataList.add(Integer.toString(courseID));


		ArrayList<PrimitiveDataType> typeList = new ArrayList();
		typeList.add(PrimitiveDataType.STRING);
		typeList.add(PrimitiveDataType.INT);
		for (int i = 0; i < 10; i++) {
			typeList.add(PrimitiveDataType.STRING);

		}
		typeList.add(PrimitiveDataType.INT);


		try {
			dbcoordinator.updateData(sqlCmd, dataList, typeList);
			System.out.println("ADMIN EDIT CLASS SUCCESSFUL");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
	}

	public boolean adminAddStudentToClass(ShibbolethAuth.Token token, int studentID, int courseID, String grading,
			String courseTerm) throws SQLException {

		if (token.type != Token.RoleType.ADMIN) {
			return false;
		}

		DBCoordinator dbcoordinator = new DBCoordinator();

		String sqlCmd = null;
		sqlCmd = "INSERT INTO STUDENTANDCOURSE (ID, COURSEID, GRADING, COURSETERM, STUDENTID)" + " VALUES (?,?,?,?,?)";
		System.out.println("WE HAVE THE SQL CMD " + sqlCmd);

		ArrayList<String> dataList = new ArrayList();

		dataList.add(Integer.toString(courseID * studentID));
		dataList.add(Integer.toString(courseID));
		dataList.add(grading);
		dataList.add(courseTerm);
		dataList.add(Integer.toString(studentID));

		ArrayList<PrimitiveDataType> typeList = new ArrayList();
		typeList.add(PrimitiveDataType.INT);
		typeList.add(PrimitiveDataType.INT);
		typeList.add(PrimitiveDataType.STRING);
		typeList.add(PrimitiveDataType.STRING);
		typeList.add(PrimitiveDataType.INT);

		try {
			dbcoordinator.insertData(sqlCmd, dataList, typeList);
			System.out.println("ADMIN ADD STUDENT TO CLASS SUCCESSFUL");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;

	}

	public boolean adminEditStudentRegisteredClass(ShibbolethAuth.Token token, int studentID, int courseID,
			String grading, String courseTerm) throws SQLException {
		if (token.type != Token.RoleType.ADMIN) {
			return false;
		}

		DBCoordinator dbcoordinator = new DBCoordinator();

		String sqlCmd = null;
		sqlCmd = "UPDATE STUDENTANDCOURSE SET GRADING = ?,  COURSETERM = ? WHERE COURSEID = ? AND STUDENTID = ?";
		ArrayList<String> dataList = new ArrayList();
		
		dataList.add(grading);
		dataList.add(courseTerm);
		dataList.add(Integer.toString(studentID));
		dataList.add(Integer.toString(courseID));

		ArrayList<PrimitiveDataType> typeList = new ArrayList();
		
		typeList.add(PrimitiveDataType.STRING);
		typeList.add(PrimitiveDataType.STRING);
		typeList.add(PrimitiveDataType.INT);
		typeList.add(PrimitiveDataType.INT);

		try {
			dbcoordinator.updateData(sqlCmd, dataList, typeList);
			System.out.println("ADMIN EDIT STUDENT REGISTERED CLASS SUCCESSFUL");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;

	}

	public boolean adminDropStudentRegisteredClass(ShibbolethAuth.Token token, int studentID, int courseID) {
		if (token.type != Token.RoleType.ADMIN) {
			System.out.println("NOW WE ARE IN WRONGW WAY");
			return false;
		}
		System.out.println("WE ARE ON THE RIGHT WAY");

		DBCoordinator dbcoordinator = new DBCoordinator();

		String sqlCmd = null;
		sqlCmd = "DELETE FROM STUDENTANDCOURSE WHERE STUDENTID = ?  AND COURSEID = ?";
		System.out.println(sqlCmd);

		ArrayList<String> dataList = new ArrayList();
		dataList.add(Integer.toString(studentID));
		dataList.add(Integer.toString(courseID));

		System.out.println(sqlCmd);

		ArrayList<PrimitiveDataType> typeList = new ArrayList();
		typeList.add(PrimitiveDataType.INT);
		typeList.add(PrimitiveDataType.INT);
		try {
			System.out.println("HERE IS THE DATALIST" + dataList);

			dbcoordinator.deleteData(sqlCmd, dataList, typeList);
			System.out.println("ADMIN DROP STUDENT REGISTERED CLASS SUCCESSFUL");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;

	}

}
