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
		int studentId = token.id; // will be token id 
		String  sqlStr = "INSERT INTO STUDENTANDCOURSE(COURSEID,GRADING,COURSETERM,STUDENTID) VALUES(?,?,?,?)";
		//String sqlStr = "INSERT INTO STUDENTANDCOURSE(COURSEID,GRADING,COURSETERM，STUDENTID) VALUES(" +
		//courseId + ",'" + grading + "'," + "'" + courseTerm + "'," + studentId + ")";
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
		String sqlStr = "delete from StudentAndCourse where courseId=?";
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
