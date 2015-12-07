package scrs;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import scrs.Constants.PrimitiveDataType;
import scrs.ShibbolethAuth.Token;
import scrsexception.SCRSException;

/**
 * admin functions
 * 
 * @author Bruce
 *
 */
public class Admin extends Person {
	/**
	 * admin add class method, create new class into course table and
	 * instructorandcourse table
	 * 
	 * @param token
	 * @param courseID
	 * @param courseName
	 * @param courseCredits
	 * @param capacity
	 * @param term
	 * @param instructor
	 * @param firstDay
	 * @param lastDay
	 * @param classBeginTime
	 * @param classEndTime
	 * @param weekDays
	 * @param location
	 * @param type
	 * @param prerequisite
	 * @param description
	 * @param department
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public boolean adminAddClass(ShibbolethAuth.Token token, int courseID, String courseName, int courseCredits,
			int capacity, String term, String instructor, String firstDay, String lastDay, String classBeginTime,
			String classEndTime, String weekDays, String location, String type, String prerequisite, String description,
			String department) throws SQLException, Exception {

		if (token.type != Token.RoleType.ADMIN) {
			System.out.println(
					new scrsexception.incorrectTypeOfAccountException("ACCOUNT TYPE FAILURE:THIS IS NOT ADMIN"));

			return false;
		}

		DBCoordinator dbcoordinator = new DBCoordinator();

		String sqlCmd = null;
		sqlCmd = "INSERT INTO COURSE (ID, NAME, CREDITS,CAPACITY,TERM,FIRSTDAY, LASTDAY, CLASSBEGINTIME, CLASSENDTIME, ROUTINES, LOCATION, TYPE, PREREQUISITE, DESCRIPTION, DEPARTMENT) "
				+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		ArrayList<String> dataList = new ArrayList<String>();
		dataList.add(Integer.toString(courseID));
		dataList.add(courseName);
		dataList.add(Integer.toString(courseCredits));
		dataList.add(Integer.toString(capacity));
		dataList.add(term);
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

		ArrayList<PrimitiveDataType> typeList = new ArrayList<PrimitiveDataType>();
		typeList.add(PrimitiveDataType.INT);
		typeList.add(PrimitiveDataType.STRING);
		typeList.add(PrimitiveDataType.INT);
		typeList.add(PrimitiveDataType.INT);
		typeList.add(PrimitiveDataType.STRING);
		typeList.add(PrimitiveDataType.DATE);
		typeList.add(PrimitiveDataType.DATE);
		for (int i = 0; i < 8; i++) {
			typeList.add(PrimitiveDataType.STRING);

		}

		try {
			dbcoordinator.insertData(sqlCmd, dataList, typeList);
			System.out.println("ADMIN ADD CLASS TO COURSE TABLE SUCCESSFUL");

		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (ParseException e1) {
			e1.printStackTrace();
		}

		sqlCmd = null;
		sqlCmd = "SELECT ID FROM INSTRUCTOR WHERE LASTNAME = '" + instructor + "'";

		List<ArrayList<Object>> objectList = dbcoordinator.queryData(sqlCmd);
		if (objectList.size() == 0) {
			new scrsexception.missingPersonalDataForUserException("NO INSTRUCTOR IN DATABASE");
		}
		if (objectList.size() > 0) {
			new SCRSException("MULTIPLE PERSON WITH THE SAME NAME");
		}

		Integer instructorID = (Integer) objectList.get(0).get(0);

		sqlCmd = "INSERT INTO INSTRUCTORANDCOURSE (COURSEID, INSTRUCTORID) VALUES (?,?)";

		dataList = new ArrayList<String>();
		dataList.add(Integer.toString(courseID));
		dataList.add(Integer.toString(instructorID));

		typeList = new ArrayList<PrimitiveDataType>();
		typeList.add(PrimitiveDataType.INT);
		typeList.add(PrimitiveDataType.INT);
		try {
			dbcoordinator.insertData(sqlCmd, dataList, typeList);
			System.out.println("ADMIN ADD CLASS TO INSTRUCTORANDCOURSE TABLE SUCCESSFUL");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return true;

	}

	/**
	 * admin delete method, this will delete class from course table and
	 * instructorandcourse table
	 * 
	 * @param token
	 * @param courseID
	 * @return
	 * @throws SQLException
	 */
	public boolean adminDeleteClass(ShibbolethAuth.Token token, int courseID) throws SQLException {
		if (token.type != Token.RoleType.ADMIN) {
			System.out.println(
					new scrsexception.incorrectTypeOfAccountException("ACCOUNT TYPE FAILURE:THIS IS NOT ADMIN"));

			return false;
		}

		DBCoordinator dbcoordinator = new DBCoordinator();

		String sqlCmd = null;
		sqlCmd = "DELETE FROM COURSE WHERE ID = ?";
		ArrayList<String> dataList = new ArrayList<String>();
		dataList.add(Integer.toString(courseID));

		ArrayList<PrimitiveDataType> typeList = new ArrayList<PrimitiveDataType>();
		typeList.add(PrimitiveDataType.INT);
		try {
			System.out.println("WE HAVE SQLCMD DATALIST TYPELIST");

			dbcoordinator.deleteData(sqlCmd, dataList, typeList);
			System.out.println("ADMIN DELETE CLASS FROM COURSE TABLE SUCCESSFUL");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		sqlCmd = null;
		sqlCmd = "DELETE FROM INSTRUCTORANDCOURSE WHERE COURSEID = ?";
		dataList = new ArrayList<String>();
		dataList.add(Integer.toString(courseID));

		typeList = new ArrayList<PrimitiveDataType>();
		typeList.add(PrimitiveDataType.INT);
		try {

			dbcoordinator.deleteData(sqlCmd, dataList, typeList);
			System.out.println("ADMIN DELETE CLASS FROM INSTRUCTORANDCOURSE TABLE SUCCESSFUL");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return false;

	}

	/**
	 * admin edit class, this will allow admin to edit classinformation in
	 * course table
	 * 
	 * @param token
	 * @param courseID
	 * @param courseName
	 * @param courseCredits
	 * @param instructor
	 * @param firstDay
	 * @param lastDay
	 * @param classBeginTime
	 * @param classEndTime
	 * @param weekDays
	 * @param location
	 * @param type
	 * @param prerequisite
	 * @param description
	 * @param department
	 * @return
	 * @throws SQLException
	 */
	public boolean adminEditClass(ShibbolethAuth.Token token, int courseID, String courseName, int courseCredits,
			String instructor, String firstDay, String lastDay, String classBeginTime, String classEndTime,
			String weekDays, String location, String type, String prerequisite, String description, String department)
					throws SQLException {

		if (token.type != Token.RoleType.ADMIN) {
			System.out.println(
					new scrsexception.incorrectTypeOfAccountException("ACCOUNT TYPE FAILURE:THIS IS NOT ADMIN"));

			return false;
		}

		DBCoordinator dbcoordinator = new DBCoordinator();

		String sqlCmd = null;
		sqlCmd = "UPDATE COURSE SET NAME = ?, CREDITS = ?, FIRSTDAY = ?, LASTDAY = ?, CLASSBEGINTIME = ?, CLASSENDTIME = ?, ROUTINES = ?, LOCATION = ?, TYPE = ?, PREREQUISITE = ?, DESCRIPTION = ?, DEPARTMENT = ? WHERE ID = ?";
		ArrayList<String> dataList = new ArrayList<String>();
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

		ArrayList<PrimitiveDataType> typeList = new ArrayList<PrimitiveDataType>();
		typeList.add(PrimitiveDataType.STRING);
		typeList.add(PrimitiveDataType.INT);
		typeList.add(PrimitiveDataType.DATE);
		typeList.add(PrimitiveDataType.DATE);
		for (int i = 0; i < 8; i++) {
			typeList.add(PrimitiveDataType.STRING);

		}
		typeList.add(PrimitiveDataType.INT);

		try {
			dbcoordinator.updateData(sqlCmd, dataList, typeList);
			System.out.println("ADMIN EDIT CLASS SUCCESSFUL");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return true;
	}

	/**
	 * admin add student to specific class, create new one in studentandcourse
	 * table
	 * 
	 * @param token
	 * @param studentID
	 * @param courseID
	 * @param grading
	 * @param courseTerm
	 * @return
	 * @throws SQLException
	 */
	public boolean adminAddStudentToClass(ShibbolethAuth.Token token, int studentID, int courseID, String grading,
			String courseTerm) throws SQLException {

		if (token.type != Token.RoleType.ADMIN) {
			System.out.println(
					new scrsexception.incorrectTypeOfAccountException("ACCOUNT TYPE FAILURE:THIS IS NOT ADMIN"));

			return false;
		}

		DBCoordinator dbcoordinator = new DBCoordinator();

		String sqlCmd = null;
		sqlCmd = "INSERT INTO STUDENTANDCOURSE (COURSEID, GRADING, COURSETERM, STUDENTID)" + " VALUES (?,?,?,?)";
		System.out.println("WE HAVE THE SQL CMD " + sqlCmd);

		ArrayList<String> dataList = new ArrayList<String>();

		dataList.add(Integer.toString(courseID));
		dataList.add(grading);
		dataList.add(courseTerm);
		dataList.add(Integer.toString(studentID));

		ArrayList<PrimitiveDataType> typeList = new ArrayList<PrimitiveDataType>();
		typeList.add(PrimitiveDataType.INT);
		typeList.add(PrimitiveDataType.STRING);
		typeList.add(PrimitiveDataType.STRING);
		typeList.add(PrimitiveDataType.INT);

		try {
			dbcoordinator.insertData(sqlCmd, dataList, typeList);
			System.out.println("ADMIN ADD STUDENT TO CLASS SUCCESSFUL");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return true;

	}

	/**
	 * admin edit student class information in studentandcourse table
	 * 
	 * @param token
	 * @param studentID
	 * @param courseID
	 * @param grading
	 * @param courseTerm
	 * @return
	 * @throws SQLException
	 */
	public boolean adminEditStudentRegisteredClass(ShibbolethAuth.Token token, int studentID, int courseID,
			String grading, String courseTerm) throws SQLException {
		if (token.type != Token.RoleType.ADMIN) {
			System.out.println(
					new scrsexception.incorrectTypeOfAccountException("ACCOUNT TYPE FAILURE:THIS IS NOT ADMIN"));

			return false;
		}

		DBCoordinator dbcoordinator = new DBCoordinator();

		String sqlCmd = null;
		sqlCmd = "UPDATE STUDENTANDCOURSE SET GRADING = ?,  COURSETERM = ? WHERE COURSEID = ? AND STUDENTID = ?";
		ArrayList<String> dataList = new ArrayList<String>();
		dataList.add(grading);
		dataList.add(courseTerm);
		dataList.add(Integer.toString(courseID));
		dataList.add(Integer.toString(studentID));

		ArrayList<PrimitiveDataType> typeList = new ArrayList<PrimitiveDataType>();
		typeList.add(PrimitiveDataType.STRING);
		typeList.add(PrimitiveDataType.STRING);
		typeList.add(PrimitiveDataType.INT);
		typeList.add(PrimitiveDataType.INT);

		try {
			dbcoordinator.updateData(sqlCmd, dataList, typeList);
			System.out.println("ADMIN EDIT STUDENT REGISTERED CLASS SUCCESSFUL");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return true;

	}

	/**
	 * admin drop student from studentandcourse table
	 * 
	 * @param token
	 * @param studentID
	 * @param courseID
	 * @return
	 */
	public boolean adminDropStudentRegisteredClass(ShibbolethAuth.Token token, int studentID, int courseID) {
		if (token.type != Token.RoleType.ADMIN) {
			System.out.println(
					new scrsexception.incorrectTypeOfAccountException("ACCOUNT TYPE FAILURE:THIS IS NOT ADMIN"));

			return false;
		}

		DBCoordinator dbcoordinator = new DBCoordinator();

		String sqlCmd = null;
		sqlCmd = "DELETE FROM STUDENTANDCOURSE WHERE STUDENTID = ?  AND COURSEID = ?";

		ArrayList<String> dataList = new ArrayList<String>();
		dataList.add(Integer.toString(studentID));
		dataList.add(Integer.toString(courseID));

		ArrayList<PrimitiveDataType> typeList = new ArrayList<PrimitiveDataType>();
		typeList.add(PrimitiveDataType.INT);
		typeList.add(PrimitiveDataType.INT);
		try {

			dbcoordinator.deleteData(sqlCmd, dataList, typeList);
			System.out.println("ADMIN DROP STUDENT REGISTERED CLASS FROM STUDENTANDCOURSE TABLE SUCCESSFUL");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return true;

	}

}
