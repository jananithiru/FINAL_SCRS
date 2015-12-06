package scrs;

public class SQLStrings {
	
	public static String selectAllFromStudent(int studentID){
		// TODO: Come up with this string escaped corrected
		String sqlStr = "select * from student where id=" + studentID;
		return sqlStr;
	}
	
	public static String selectAllFromAdmin(int adminID){
		// TODO: Come up with this string escaped corrected
		String sqlStr = "select * from administrator where id=" + adminID;
		return sqlStr;
	}

	//TODO not sure how to search for term
	public static String selectAllFromCourse(int courseID, String courseName, String location, String term,
			String department, String classType, String instructorName) {
		String sqlStr = "select * FROM course WHERE id = " + courseID + "AND department = " + department + 
				"AND location = " + location + "AND term = " + term;
		if (courseName != "") {
			sqlStr += " AND name = " + courseName;
		}
		if (classType != "") {
			sqlStr += " AND type = " + classType;
		}
		//TODO query instructor field
		if (instructorName != "") {
			;
		}
		return sqlStr;
	}

	public static String selectHistoryFromStudentAndCourse(int studentID) {
		// TODO Auto-generated method stub
		return null;
	}
	

	
}
