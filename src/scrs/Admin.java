package scrs;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import scrs.Constants.PrimitiveDataType;
import scrs.ShibbolethAuth.Token;

public class Admin extends Person {

	public boolean adminAddClass(ShibbolethAuth.Token token, int courseID, String courseName, int courseCredits,
			String instructor, String firstDay, String lastDay, String classBeginTime, String classEndTime,
			String weekDays, String location, String type, String prerequisite, String description, String department)
					throws SQLException {

		// if (token.type != Token.RoleType.ADMIN) {
		// System.out.println("THIS IS NOT ADMIN");
		//
		// return false;
		// }

		DBCoordinator dbcoordinator = new DBCoordinator();

		String sqlCmd = null;
		sqlCmd = "INSERT INTO COURSE (ID, NAME, CREDITS, INSTRUCTOR, FIRSTDAY, LASTDAY, CLASSBEGINTIME, CLASSENDTIME, ROUTINES, LOCATION, TYPE, PREREQUISITE, DESCRIPTION, DEPARTMENT) "
				+ "VALUES (" + courseID + "," + courseName + "," + courseCredits + "," + instructor + "," + firstDay
				+ "," + lastDay + "," + classBeginTime + "," + classEndTime + "," + weekDays + "," + location + ","
				+ type + "," + prerequisite + "," + description + "," + department + ")";

		ArrayList<String> dataList = new ArrayList();

		dataList.add(Integer.toString(courseID));
		dataList.add(courseName);
		dataList.add(Integer.toString(courseCredits));
		dataList.add(instructor);
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
		for (int i = 0; i < 11; i++) {
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
//		if (token.type != Token.RoleType.ADMIN) {
//			return false;
//		}

		DBCoordinator dbcoordinator = new DBCoordinator();
		
		String sqlCmd = null;
		sqlCmd = "DELETE FROM COURSE WHERE ID = ?";
		ArrayList<String> dataList =  new ArrayList();
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
		sqlCmd = "UPDATE COURSE SET" + "ID =" + courseID + "NAME =" + courseName + "CREDITS = " + courseCredits
				+ "INSTRUCTOR=" + instructor + "FIRSTDAY = " + firstDay + "LASTDAY = " + lastDay + "CLASSBEGINTIME = "
				+ classBeginTime + "CLASSENDTIME =" + classEndTime + "ROUTINES =" + weekDays + "LOCATION +" + location
				+ "TYPE = " + type + "PREREQUISITE =" + prerequisite + "DESCRIPTION =" + description + "DEPARTMENT = "
				+ department;
		ArrayList<String> dataList = null;
		dataList.add(Integer.toString(courseID));
		dataList.add(courseName);
		dataList.add(Integer.toString(courseCredits));
		dataList.add(instructor);
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

		ArrayList<PrimitiveDataType> typeList = null;
		typeList.add(PrimitiveDataType.INT);
		typeList.add(PrimitiveDataType.STRING);
		typeList.add(PrimitiveDataType.INT);
		for (int i = 0; i < 10; i++) {
			typeList.add(PrimitiveDataType.STRING);

		}

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

		sqlCmd = "INSERT INTO STUDENTANDCOURSE (STUDENTID, COURSEID, GRADING, COURSETERM) VALUES (" + studentID
				+ courseID + grading + courseTerm + ")";
		ArrayList<String> dataList = null;
		dataList.add(Integer.toString(studentID));
		dataList.add(Integer.toString(courseID));
		dataList.add(grading);
		dataList.add(courseTerm);

		ArrayList<PrimitiveDataType> typeList = null;
		typeList.add(PrimitiveDataType.INT);
		typeList.add(PrimitiveDataType.INT);
		typeList.add(PrimitiveDataType.STRING);
		typeList.add(PrimitiveDataType.STRING);

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
		sqlCmd = "UPDATE STUDENTANDCOURSE SET ID =" + studentID + "SET COURSEID =" + courseID + "SET GRADING = "
				+ grading + "SET COURSETERM =" + courseTerm;
		ArrayList<String> dataList = null;
		dataList.add(Integer.toString(studentID));
		dataList.add(Integer.toString(courseID));
		dataList.add(grading);
		dataList.add(courseTerm);

		ArrayList<PrimitiveDataType> typeList = null;
		typeList.add(PrimitiveDataType.INT);
		typeList.add(PrimitiveDataType.INT);
		typeList.add(PrimitiveDataType.STRING);
		typeList.add(PrimitiveDataType.STRING);

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
			return false;
		}

		DBCoordinator dbcoordinator = new DBCoordinator();

		String sqlCmd = null;
		sqlCmd = "DELETE FROM STUDENTANDCOURSE WHERE STUDENTID =" + studentID + " AND COURSEID = " + courseID;
		ArrayList<String> dataList = null;
		dataList.add(Integer.toString(studentID));
		dataList.add(Integer.toString(courseID));

		ArrayList<PrimitiveDataType> typeList = null;
		typeList.add(PrimitiveDataType.INT);
		typeList.add(PrimitiveDataType.INT);
		try {
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
