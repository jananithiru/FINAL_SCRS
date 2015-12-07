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
			String department, String classType, String instructorID) {
		String sqlStr = "select * FROM course c";
		if (instructorID != null) {
			sqlStr += " JOIN instructorandcourse ic on c.ID = ic.courseID";
		}
			sqlStr += " WHERE c.id = " + courseID + 
				" AND c.location = '" + location + "'" + " AND c.term = '" + term + "'";
		if (department != null) {
			sqlStr += " AND c.department = '" + department + "'";
		}
		if (courseName != null) {
			sqlStr += " AND c.name = '" + courseName + "'";
		}
		if (classType != null) {
			sqlStr += " AND c.type = '" + classType + "'";
		}
		if (instructorID != null) {
			sqlStr += " AND ic.id = " + instructorID;
		}
		sqlStr += ";";
		return sqlStr;
	}

	public static String selectHistoryFromStudentAndCourse(int studentID) {
		String sqlStr = "select * FROM studentandcourse WHERE studentid = " + studentID + ";";
		return sqlStr;
	}
}
