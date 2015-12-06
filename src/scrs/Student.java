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
		
//		if ( token.id == null )
//		// query Student Tbale with ID. 
//		String querySqlStr = " select id from student where id =" +token.id; 
//		DBCoordinator dbCo = new DbCoordinator(); 
//		ArrayList<ArrayList<Object>> results = dbCo.queryData(querySqlStr); 
//		
//		if ( results == null || results.size() == 0 )
//			 return customException // There should be ATLEAST 1 studnent witn 
//		if ( results.size > 1 )
//			return errorCustomException // There should be only one student with given id
//				
//		ArrayList<Object> studentObj = results.get(0);
//		String id = studentObj.get(0); 
//		
//		
//		if ( id == token.id)
		
		/*
		 * "(ID INT PRIMARY KEY     NOT NULL," +
                   " FIRSTNAME      TEXT    NOT NULL, " + // Only contains alphabets
                   " LASTNAME       TEXT    NOT NULL, " + // Only contains alphabets
                   " DATEOFBIRTH    DATE    NOT NULL, " + // Format: mm/dd/yyyy
                   " TYPE           CHAR(10) NOT NULL CHECK (TYPE IN ('Undergrad', 'Master', 'PHD')), " +  // Seminar or Lecture  
                   " GENDER         CHAR(15) CHECK (GENDER IN ('Male', 'Female', 'Transgender')), " + // Male, Female or Transgender
                   " ADVISOR        CHAR(20), " + 
                   " CREDITS        INT      NOT NULL CHECK (CREDITS >= 0), " + // Credits' value in [0, 1]
                   " DEPARTMENT     CHAR(50) NOT NULL CHECK (DEPARTMENT IN ('CS')))"; 
      stmt.executeUpdate(createStudentTableSql);
		 */
		
		
		
		
		
		if (token.type == RoleType.ADMIN) {
			return false;
		}
		DBCoordinator dbCoordinator = new DBCoordinator();
		int studentId = 123; // will be token id 
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
