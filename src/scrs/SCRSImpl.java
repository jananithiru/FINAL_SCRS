package scrs;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import scrs.ShibbolethAuth.Token;
import scrs.ShibbolethAuth.Token.RoleType;

public class SCRSImpl implements SCRS {

	public Token userLogin(String x500, String password) {
		ShibbolethAuth sbAuth = new ShibbolethAuth();
		if (x500 == null || password == null)
			return null; // TODO: Check if we need to throw exceptions
		Token myToken = null;
		try {
			myToken = sbAuth.tokenGenerator(x500, password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return myToken;
	}

	@Override
	public List<ArrayList<String>> queryStudentPersonalData(Token token, int studentID) {

		if (token.type == Token.RoleType.UNDEFINED) {
			System.out.print(ErrorMessages.invalidCredentials);
			return null; // CUSTOM EXCEPTION
		} else if (token.type == Token.RoleType.ADMIN) {
			System.out.print(ErrorMessages.incorrectTypeOfAccount);
			return null; // CUSTOM EXCEPTION
		}

		DBCoordinator dbcoordinator = new DBCoordinator();
		String sqlStr = SQLStrings.selectAllFromStudent(studentID);
		List<ArrayList<Object>> objList = null;
		try {
			objList = dbcoordinator.queryData(sqlStr);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (objList == null || objList.isEmpty()) {
			System.out.println(ErrorMessages.missingPersonalDataForUser);
			return null; // CUSTOM EXCEPTION
		}
		List<ArrayList<String>> result = UtilMethods.convertObjListToStringList(objList);

		return result;
	}

	@Override
	public List<ArrayList<String>> queryAdminPersonalData(Token token, int adminID) {

		if (token.type == Token.RoleType.UNDEFINED) {
			System.out.print(ErrorMessages.invalidCredentials);
			return null; // CUSTOM EXCEPTION
		} else if (token.type == Token.RoleType.STUDENT) {
			System.out.print(ErrorMessages.incorrectTypeOfAccount);
			return null; // CUSTOM EXCEPTION
		}

		DBCoordinator dbcoordinator = new DBCoordinator();

		String sqlStr = SQLStrings.selectAllFromAdmin(adminID);

		List<ArrayList<Object>> objList = null;
		try {
			objList = dbcoordinator.queryData(sqlStr);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (objList == null || objList.isEmpty()) {
			System.out.println(ErrorMessages.missingPersonalDataForUser);
			return null; // CUSTOM EXCEPTION
		}

		List<ArrayList<String>> result = UtilMethods.convertObjListToStringList(objList);

		return result;

	}

	@Override
	public boolean studentAddClass(Token token, int courseID, String grading, String courseTerm) {
		// TODO Auto-generated method stub
		Student student = new Student();
		return student.studentAddClass(token, courseID, grading, courseTerm);

	}

	@Override
	public boolean studentDropClass(Token token, int courseID) {
		// TODO Auto-generated method stub

		Student student = new Student();

		return student.studentDropClass(token, courseID);
	}

	@Override
	public boolean studentEditClass(Token token, int courseID, String grading, String courseTerm) {
		// TODO Auto-generated method stub
		Student student = new Student();
		return student.studentEditClass(token, courseID, grading, courseTerm);
	}

	@Override
	public List<ArrayList<String>> queryClass(int courseID, String courseName, String location, String term,
			String department, String classType, String instructorName) {

		DBCoordinator dbcoordinator = new DBCoordinator();
		String instrID = null;

		// TODO how to check this?
		if (instructorName != null) {
			List<ArrayList<Object>> instrIDList = null;

			String instrSQLStr = "select instructorid FROM instructor WHERE lastname = " + instructorName + ";";

			try {
				instrIDList = dbcoordinator.queryData(instrSQLStr);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			instrID = UtilMethods.convertObjListToStringList(instrIDList).get(0).get(0);
		}

		String sqlStr = SQLStrings.selectAllFromCourse(courseID, courseName, location, term, department, classType,
				instrID);

		List<ArrayList<Object>> objList = null;

		try {
			objList = dbcoordinator.queryData(sqlStr);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (objList == null || objList.isEmpty()) {
			System.out.println(ErrorMessages.missingCourseData);
			return null; // CUSTOM EXCEPTION
		}

		List<ArrayList<String>> result = UtilMethods.convertObjListToStringList(objList);
<<<<<<< HEAD

=======
>>>>>>> d32a4d3e279497893eb3bad9e7bf369ce562c66e
		return result;
	}

	@Override
	public List<ArrayList<String>> queryStudentRegistrationHistory(Token token, int studentID) {
<<<<<<< HEAD

		if (token.type != RoleType.ADMIN || token.id != studentID) {
			// TODO create exception
			// throw new Exception();
=======
		
		if (!(token != null && (token.type == RoleType.ADMIN || token.id == studentID))) {
			System.out.println(ErrorMessages.accessNotAllowed);
			//TODO custom exception
>>>>>>> d32a4d3e279497893eb3bad9e7bf369ce562c66e
		}

		DBCoordinator dbcoordinator = new DBCoordinator();

		String sqlStr = SQLStrings.selectHistoryFromStudentAndCourse(studentID);

		List<ArrayList<Object>> objList = null;

		try {
			objList = dbcoordinator.queryData(sqlStr);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (objList == null || objList.isEmpty()) {
			System.out.println(ErrorMessages.missingStudentRegistrationData);
			return null; // CUSTOM EXCEPTION
		}

		List<ArrayList<String>> result = UtilMethods.convertObjListToStringList(objList);

		return result;
	}

	@Override
	public boolean adminAddClass(Token token, int courseID, String courseName, int courseCredits, int capacity,
			String term, String instructor, String firstDay, String lastDay, String classBeginTime, String classEndTime,
			String weekDays, String location, String type, String prerequisite, String description, String department) throws Exception {
		// TODO Auto-generated method stub

		Admin admin = new Admin();
		try {
			try {
				admin.adminAddClass(token, courseID, courseName, courseCredits, capacity, term, instructor, firstDay,
						lastDay, classBeginTime, classEndTime, weekDays, location, type, prerequisite, description,
						department);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	public boolean adminDeleteClass(Token token, int courseID) {
		// TODO Auto-generated method stub
		Admin admin = new Admin();
		try {
			admin.adminDeleteClass(token, courseID);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	@Override

	public boolean adminEditClass(Token token, int courseID, String courseName, int courseCredits, String instructor,
			String firstDay, String lastDay, String classBeginTime, String classEndTime, String weekDays,
			String location, String type, String prerequisite, String description, String department) {
		// TODO Auto-generated method stub

		Admin admin = new Admin();
		try {
			admin.adminEditClass(token, courseID, courseName, courseCredits, instructor, firstDay, lastDay,
					classBeginTime, classEndTime, weekDays, location, type, prerequisite, description, department);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	public boolean adminAddStudentToClass(Token token, int studentID, int courseID, String grading, String courseTerm) {
		// TODO Auto-generated method stub
		Admin admin = new Admin();
		try {
			admin.adminAddStudentToClass(token, studentID, courseID, grading, courseTerm);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	public boolean adminEditStudentRegisteredClass(Token token, int studentID, int courseID, String grading,
			String courseTerm) {
		// TODO Auto-generated method stub
		Admin admin = new Admin();
		try {
			admin.adminEditStudentRegisteredClass(token, studentID, courseID, grading, courseTerm);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public List<ArrayList<String>> queryInstructor(Token token, int instructorID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean adminDropStudentRegisteredClass(Token token, int studentID, int courseID) {
		// TODO Auto-generated method stub
		Admin admin = new Admin();
		admin.adminDropStudentRegisteredClass(token, studentID, courseID);
		return true;
	}

}
