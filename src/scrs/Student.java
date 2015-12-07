package scrs;

import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.internal.Throwables;

import scrs.Constants.PrimitiveDataType;
import scrs.ShibbolethAuth.Token.RoleType;
import scrsexception.SCRSException;

public class Student extends Person {

	/**
	 * This method is used when a student chooses to register for a class.
	 * 
	 * @param token
	 *            contains the Token object of the logged in student.
	 * @param courseId
	 * @param grading
	 *            'A-F', 'S/N', or 'AUD'
	 * @param courseTerm
	 *            e.g. 'Spring2015'
	 * @return True if the student was successfully added to the class. Else,
	 *         false.
	 */
	boolean studentAddClass(ShibbolethAuth.Token token, int courseId, String grading, String courseTerm) {
		boolean result = false;
		try {
			result = studentAddClass2(token, courseId, grading, courseTerm);
		} catch (SCRSException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		return result;
	}

	@SuppressWarnings("deprecation")
	boolean studentAddClass2(ShibbolethAuth.Token token, int courseId, String grading, String courseTerm)
			throws SCRSException {

		if (token.type == RoleType.ADMIN) {
			return false;
		}

		// get current date time with Date()
		java.util.Date utilDate = new java.util.Date();
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getYear(), utilDate.getMonth(), utilDate.getDay());

		if (UtilMethods.isInTimeFrame(sqlDate, courseTerm)) {
			throw new SCRSException(ErrorMessages.outTimeFrame);
		}

		DBCoordinator dbCoordinator = new DBCoordinator();
		int studentId = token.id; // will be token id
		String sqlStr = "INSERT INTO STUDENTANDCOURSE(COURSEID,GRADING,COURSETERM,STUDENTID) VALUES(?,?,?,?)";
		System.out.println("Here is the student add class and the string is generated");

		ArrayList<String> dataList = new ArrayList<String>();
		dataList.add(Integer.toString(courseId));
		dataList.add(grading);
		dataList.add(courseTerm);
		dataList.add(Integer.toString(studentId));

		ArrayList<PrimitiveDataType> typeList = new ArrayList<Constants.PrimitiveDataType>();
		typeList.add(PrimitiveDataType.INT);
		typeList.add(PrimitiveDataType.STRING);
		typeList.add(PrimitiveDataType.STRING);
		typeList.add(PrimitiveDataType.INT);
		try {
			dbCoordinator.insertData(sqlStr, dataList, typeList);
			System.out.println("insert data done!!");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			throw new SCRSException(ErrorMessages.classNotFound);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new SCRSException(ErrorMessages.sqlException);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			throw new SCRSException(ErrorMessages.ParseDataError);
		}
		return true;

	}

	/**
	 * This method is used when a student wishes to drop a class.
	 * 
	 * @param token
	 *            contains the Token object of the student.
	 * @param courseID
	 * @return True if the drop was successful. Else, false.
	 */
	boolean studentDropClass(ShibbolethAuth.Token token, int courseID) {
		boolean result = false;

		try {
			result = studentDropClass2(token, courseID);
		} catch (SCRSException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}

		return result;

	}

	@SuppressWarnings("deprecation")
	boolean studentDropClass2(ShibbolethAuth.Token token, int courseID) throws SCRSException {
		if (token.type == RoleType.ADMIN) {
			return false;
		}

		DBCoordinator dbCoordinator = new DBCoordinator();

		// get current date time with Date()
		java.util.Date utilDate = new java.util.Date();
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getYear(), utilDate.getMonth(), utilDate.getDay());

		String sqlCmd = "SELECT TERM FROM COURSE WHERE ID = " + courseID + ";";
		List<ArrayList<Object>> termList = null;

		try {
			termList = dbCoordinator.queryData(sqlCmd);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			throw new SCRSException(ErrorMessages.classNotFound);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			throw new SCRSException(ErrorMessages.sqlException);
		}

		if (termList == null || termList.size() == 0) {

			throw new SCRSException(ErrorMessages.NoRecordReturnFromDB);

		}
		String courseTerm = (String) termList.get(0).get(0);

		if (UtilMethods.isInTimeFrame(sqlDate, courseTerm)) {
			throw new SCRSException(ErrorMessages.outTimeFrame);
		}

		String sqlStr = "delete from StudentAndCourse where courseId=?";
		ArrayList<String> dataList = new ArrayList<String>();
		dataList.add(Integer.toString(courseID));
		ArrayList<PrimitiveDataType> typeList = new ArrayList<PrimitiveDataType>();
		typeList.add(PrimitiveDataType.INT);

		try {
			dbCoordinator.deleteData(sqlStr, dataList, typeList);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			throw new SCRSException(ErrorMessages.classNotFound);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new SCRSException(ErrorMessages.sqlException);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			throw new SCRSException(ErrorMessages.ParseDataError);
		}

		return true;
	}

	/**
	 * This method is used when a student wishes to change the grading basis of
	 * a course she is enrolled in.
	 * 
	 * @param token
	 *            contains the Token object of the student.
	 * @param courseID
	 * @param grading
	 *            grade basis the student wishes to change the course to.
	 * @param courseTerm
	 * @return
	 */
	// why we need the parameter courseTerm
	boolean studentEditClass(ShibbolethAuth.Token token, int courseID, String grading, String courseTerm) {
		if (token.type == RoleType.ADMIN) {
			return false;
		}
		DBCoordinator dbCoordinator = new DBCoordinator();
		String sqlStr = "update StudentAndCourse set grading=? where courseId=?";
		ArrayList<String> dataList = new ArrayList<String>();
		dataList.add(grading);
		dataList.add(Integer.toString(courseID));
		ArrayList<PrimitiveDataType> typeList = new ArrayList<PrimitiveDataType>();
		typeList.add(PrimitiveDataType.STRING);
		typeList.add(PrimitiveDataType.INT);

		try {
			dbCoordinator.updateData(sqlStr, dataList, typeList);
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
