package scrs;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import scrs.Constants.PrimitiveDataType;
import scrs.ShibbolethAuth.Token.RoleType;

public class Student extends Person {

	boolean studentAddClass(ShibbolethAuth.Token token, int courseId, String grading, String courseTerm) {
		if (token.type == RoleType.ADMIN) {
			return false;
		}
		DBCoordinator dbCoordinator = new DBCoordinator();
		String sqlStr = "insert into StudentAndCourse values(" + courseId + ",'" + grading + "','" + courseTerm + "')"; // id
																												// is
																												// autoincrement

		ArrayList<String> dataList = new ArrayList<String>();
		dataList.add(Integer.toString(courseId));
		dataList.add(grading);
		dataList.add(courseTerm);
		ArrayList<PrimitiveDataType> typeList = new ArrayList<Constants.PrimitiveDataType>();
		typeList.add(PrimitiveDataType.INT);
		typeList.add(PrimitiveDataType.STRING);
		typeList.add(PrimitiveDataType.STRING);
		try {
			dbCoordinator.insertData(sqlStr, dataList, typeList);
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

	boolean studentDropClass(ShibbolethAuth.Token token, int courseID) {
		if (token.type == RoleType.ADMIN) {
			return false;
		}
		DBCoordinator dbCoordinator = new DBCoordinator();
		String sqlStr = "delete from StudentAndCourse where courseId=" + courseID;
		ArrayList<String> dataList = new ArrayList<String>();
		dataList.add(Integer.toString(courseID));
		ArrayList<PrimitiveDataType> typeList = new ArrayList<PrimitiveDataType>();
		typeList.add(PrimitiveDataType.INT);

		try {
			dbCoordinator.deleteData(sqlStr, dataList, typeList);
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
	
	// why we need the parameter courseTerm
	boolean studentEditClass(ShibbolethAuth.Token token, int courseID, String grading, String courseTerm){
		if (token.type == RoleType.ADMIN) {
			return false;
		}
		DBCoordinator dbCoordinator = new DBCoordinator();
		String sqlStr = "update StudentAndCourse set grading='" + grading + "' where courseId="+courseID;
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
