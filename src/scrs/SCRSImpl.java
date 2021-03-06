package scrs;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import scrs.ShibbolethAuth.Token;
import scrs.ShibbolethAuth.Token.RoleType;

public class SCRSImpl implements SCRS {
    /**
     * user login with x500 account and password
     * 
     * @param x500
     * @param password
     * @return
     */

    public Token userLogin(String x500, String password) {

        Token retToken = null;

        try {
            retToken = userLogin2(x500, password);
        } catch (SCRSException e) {
            // TODO Auto-generated catch block
            System.out.print(e.getMessage());
            return emptyToken();
        }

        return retToken;

    }

    private Token userLogin2(String x500, String password) throws SCRSException {
        ShibbolethAuth sbAuth = new ShibbolethAuth();

        if (x500 == null || password == null) {
            throw new SCRSException(ErrorMessages.NULL_CREDENTIALS);
        }

        if (!UtilMethods.isAlphaNumeric(x500) && !UtilMethods.isAlphaNumeric(password)) {
            throw new SCRSException(ErrorMessages.NOT_ALPHANUMBERIC);
        }

        Token myToken = null;

        try {
            myToken = sbAuth.tokenGenerator(x500, password);
        } catch (ClassNotFoundException e) {
            throw new SCRSException(ErrorMessages.CLASS_NOT_FOUND);
        } catch (SQLException e) {
            throw new SCRSException(ErrorMessages.SQL_EXCEPTION);
        }

        return myToken;
    }

    private Token emptyToken() {
        return new Token(-1, Token.RoleType.UNDEFINED, "");
    }

    /**
     * This interface is used for querying student personal data.
     * 
     * @param token
     * @param studentID
     * @return Student basic personal data, such as name, id, age, gender,
     *         degree, advisor(if applicable), total credits, etc. Empty list
     *         will be returned if the query is failed.
     */
    @Override
    public List<ArrayList<String>> queryStudentPersonalData(Token token, int studentID) {
        List<ArrayList<Object>> objList = null;

        try {
            objList = queryStudentPersonalData1(token, studentID);
        } catch (SCRSException e) {
            System.out.println(e.getMessage());
            return null;
        }
        List<ArrayList<String>> result = UtilMethods.convertObjListToStringList(objList);
        return result;
    }

    private List<ArrayList<Object>> queryStudentPersonalData1(Token token, int studentID) throws SCRSException {

        if (token.type == Token.RoleType.UNDEFINED) {
            throw new SCRSException(ErrorMessages.INVILID_CREDENTIALS);
        } else if (token.type == Token.RoleType.ADMIN) {
            throw new SCRSException(ErrorMessages.INCORRECT_TYPE_ACCOUNT);
        }
        String sqlStr = SQLStrings.selectAllFromStudent(studentID);

        DBCoordinator dbcoordinator = new DBCoordinator();
        List<ArrayList<Object>> objList = null;

        try {
            objList = dbcoordinator.queryData(sqlStr);
        } catch (ClassNotFoundException e) {
            throw new SCRSException(ErrorMessages.CLASS_NOT_FOUND);
        } catch (SQLException e) {
            throw new SCRSException(ErrorMessages.SQL_EXCEPTION);
        }

        if (objList == null || objList.isEmpty()) {
            throw new SCRSException(ErrorMessages.MISSING_PERSONAL_DATA_FOR_USER);
        }

        return objList;
    }

    /**
     * This interface is used for querying admin basic information.
     * 
     * @param token
     *            Only Admin can invoke this function
     * @return Admin ID, Admin Name, Admin Department, etc. Empty list will be
     *         returned if the query is failed.
     * @throws SCRSException
     */
    @Override
    public List<ArrayList<String>> queryAdminPersonalData(Token token) {

        List<ArrayList<Object>> objList = null;

        try {
            objList = queryAdminPersonalData2(token);
        } catch (SCRSException e) {
            System.out.println(e.getMessage());
            return null;
        }

        List<ArrayList<String>> result = UtilMethods.convertObjListToStringList(objList);

        return result;

    }

    private List<ArrayList<Object>> queryAdminPersonalData2(Token token) throws SCRSException {

        validateCredentials(token);

        String sqlStr = SQLStrings.selectAllFromAdmin(token.id);

        DBCoordinator dbcoordinator = new DBCoordinator();

        List<ArrayList<Object>> objList = null;

        try {
            objList = dbcoordinator.queryData(sqlStr);
        } catch (ClassNotFoundException e) {
            throw new SCRSException(ErrorMessages.CLASS_NOT_FOUND);
        } catch (SQLException e) {
            throw new SCRSException(ErrorMessages.SQL_EXCEPTION);
        }

        if (objList == null || objList.isEmpty()) {
            throw new SCRSException(ErrorMessages.MISSING_PERSONAL_DATA_FOR_USER);
        }

        return objList;
    }

    private void validateCredentials(Token token) throws SCRSException {
        if (token.type == Token.RoleType.UNDEFINED) {
            throw new SCRSException(ErrorMessages.INVILID_CREDENTIALS);
        } else if (token.type == Token.RoleType.STUDENT) {
            throw new SCRSException(ErrorMessages.INCORRECT_TYPE_ACCOUNT);
        }
    }

    /**
     * This interface should allow the students add one class to the database.
     * 
     * @param token
     * @param courseID
     * @param grading
     * @param courseTerm
     * @return Return true if the operation is successfully, false otherwise
     */
    @Override
    public boolean studentAddClass(Token token, int courseID, String grading, String courseTerm) {
        // TODO Auto-generated method stub
        Student student = new Student();
        return student.studentAddClass(token, courseID, grading, courseTerm);

    }

    /**
     * This interface should allow the students drop one class from the
     * database.
     * 
     * @param token
     * @param courseID
     * @return Return true if the operation is successfully, false otherwise
     */
    @Override
    public boolean studentDropClass(Token token, int courseID) {
        // TODO Auto-generated method stub

        Student student = new Student();

        return student.studentDropClass(token, courseID);
    }

    /**
     * This interface should allow the students edit one registered class in the
     * database.
     * 
     * @param token
     * @param courseID
     * @param grading
     *            This parameter should just have one of "A/F", "Audit", "S/N"
     *            value
     * @param courseTerm
     * @return Return true if the operation is successfully, false otherwise
     */
    @Override
    public boolean studentEditClass(Token token, int courseID, String grading, String courseTerm) {
        // TODO Auto-generated method stub
        Student student = new Student();
        return student.studentEditClass(token, courseID, grading, courseTerm);
    }

    /**
     * This interface have several responsibilities: 1. Can be used to perform
     * generic class query; 2. Can be used to perform auto fill out relevant
     * information for the users GUI;
     * 
     * @param courseID
     *            can be used by administrator for fast class query
     * @param courseName
     * @param location
     * @param term
     * @param department
     * @param classType
     * @param instructorName
     * @return A list of ArrayList, in the ArrayList, each entry stores one
     *         query result in the table property order. E.g in Course Table,
     *         the proper order is ID, Name, Credits, etc. so in one entry of
     *         ArrayList, it should stores the value in this order, which is ID,
     *         Name, Credits, etc. Empty list will be returned if the query is
     *         failed.
     * @throws SCRSException
     */
    @Override
    public List<ArrayList<String>> queryClass(int courseID, String courseName, String location, String term,
            String department, String classType, String instructorName) {

        List<ArrayList<String>> result = new ArrayList<>();
        try {
            result = queryClass2(courseID, courseName, location, term, department, classType, instructorName);
        } catch (SCRSException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

   public List<ArrayList<String>> queryClass2(int courseID, String courseName, String location, String term,
            String department, String classType, String instructorName) throws SCRSException {

        //Validate data
        if (courseID <= 0 || location == null || term == null) {
            throw new SCRSException(ErrorMessages.MISSING_REQUIRED_FIELD);
        }
        if ((courseName != null && (courseName.length() > 50 || !(courseName.matches("[A-Za-z]+")))) ||
                (location != null && location.length() > 100) || 
                (department != null && department != "CS") || 
                (classType != null && !(classType == "Lecture" || classType == "Seminar")) ||
                (instructorName != null && !(instructorName.matches("[A-Za-z]+")))) {
            throw new SCRSException(ErrorMessages.invalidData);
        }

        //data initialization
        DBCoordinator dbcoordinator = new DBCoordinator();
        List<ArrayList<String>> instrCoursesListStr = null;
        List<String> instrCourses = null;

        // if instructor name is given, need her ID
        if (instructorName != null) {
            List<ArrayList<Object>> instrIDList = null;

            String instrSQLStr = "select id FROM instructor WHERE lastname = '" + instructorName + "';";

            try {
                instrIDList = dbcoordinator.queryData(instrSQLStr);
            } catch (SQLException e) {
                throw new SCRSException(ErrorMessages.SQL_EXCEPTION);
            } catch (ClassNotFoundException e) {
                throw new SCRSException(ErrorMessages.CLASS_NOT_FOUND);
            }

            if (instrIDList == null || instrIDList.isEmpty()) {
                throw new SCRSException(ErrorMessages.MISSING_INSTRUCTOR);
            }

            String instrID = UtilMethods.convertObjListToStringList(instrIDList).get(0).get(0);

            String instrCoursesSQLStr = "select courseid FROM instructorandcourse WHERE instructorID = " + instrID
                    + ";";
            List<ArrayList<Object>> instrCoursesList = null;
            try {
                instrCoursesList = dbcoordinator.queryData(instrCoursesSQLStr);
            } catch (ClassNotFoundException e) {
                throw new SCRSException(ErrorMessages.CLASS_NOT_FOUND);
            } catch (SQLException e) {
                throw new SCRSException(ErrorMessages.SQL_EXCEPTION);
            }
            
            if (instrCoursesList == null || instrCoursesList.isEmpty()) {
                throw new SCRSException(ErrorMessages.MISSING_COURSE_DATA);
            }
            instrCoursesListStr = UtilMethods.convertObjListToStringList(instrCoursesList);

            
            //Format instructorCourses list
            instrCourses = new ArrayList<>();
            for(int i=0; i<instrCoursesListStr.size();i++) {
                instrCourses.add(instrCoursesListStr.get(i).get(0));
            }
            
            if (!instrCourses.contains(String.valueOf(courseID))) {
                throw new SCRSException(ErrorMessages.MISSING_COURSE_DATA);
            }
            
            
        }
        //Get query String
        String sqlStr = SQLStrings.selectAllFromCourse(courseID, courseName, location, term, 
                department, classType);

        List<ArrayList<Object>> objList = null;

        try {
            objList = dbcoordinator.queryData(sqlStr);
        } catch (ClassNotFoundException e) {
            throw new SCRSException(ErrorMessages.CLASS_NOT_FOUND);
        } catch (SQLException e) {
            throw new SCRSException(ErrorMessages.SQL_EXCEPTION);
        }

        if (objList == null || objList.isEmpty()) {
            throw new SCRSException(ErrorMessages.MISSING_COURSE_DATA);
        }

        List<ArrayList<String>> result = UtilMethods.convertObjListToStringList(objList);
        return result;
    }

    /**
     * This interface is used for querying student registration history
     * 
     * @param token
     * @param studentID
     * @return ClassID, ClassName, Registration Time(term), Status(Finished,
     *         Dropped), Credits Empty list will be returned if the query is
     *         failed.
     */
    @Override
    public List<ArrayList<String>> queryStudentRegistrationHistory(Token token, int studentID) {

        List<ArrayList<String>> result = new ArrayList<>();
        try {
            result = queryStudentRegistrationHistory2(token, studentID);
        } catch (SCRSException e) {
            System.out.println(e.getMessage());
        }
        ;

        return result;
    }

    public List<ArrayList<String>> queryStudentRegistrationHistory2(Token token, int studentID) throws SCRSException {

        if ((token == null || !(token.type == RoleType.ADMIN || token.id == studentID))) {
            throw new SCRSException(ErrorMessages.ACCESS_NOT_ALLOWED);
        }

        if (studentID <= 0) {
            throw new SCRSException(ErrorMessages.invalidData);
        }

        DBCoordinator dbcoordinator = new DBCoordinator();

        String sqlStr = SQLStrings.selectHistoryFromStudentAndCourse(studentID);

        List<ArrayList<Object>> objList = null;

        try {
            objList = dbcoordinator.queryData(sqlStr);
        } catch (ClassNotFoundException e) {
            throw new SCRSException(ErrorMessages.CLASS_NOT_FOUND);
        } catch (SQLException e) {
            throw new SCRSException(ErrorMessages.SQL_EXCEPTION);
        }

        if (objList == null || objList.isEmpty()) {
            throw new SCRSException(ErrorMessages.MISSING_STUDENT_REGISTER_DATA);
        }

        List<ArrayList<String>> result = UtilMethods.convertObjListToStringList(objList);

        return result;
    }

    /**
     * This interface should allow the admin to add class into the database
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
     */
    @Override
    public boolean adminAddClass(ShibbolethAuth.Token token, int courseID, String courseName, int courseCredits,
            int courseCapacity, String term, int instructorID, String firstDay, String lastDay, String classBeginTime,
            String classEndTime, String weekDays, String location, String type, String prerequisite, String description,
            String department) {

        try {
            return adminAddClass1(token, courseID, courseName, courseCredits, courseCapacity, term, instructorID,
                    firstDay, lastDay, classBeginTime, classEndTime, weekDays, location, type, prerequisite,
                    description, department);
        } catch (Exception e) {
            System.out.println(e.getMessage());

            return false;

        }
    }

    public boolean adminAddClass1(Token token, int courseID, String courseName, int courseCredits, int courseCapacity,
            String term, int instructorID, String firstDay, String lastDay, String classBeginTime, String classEndTime,
            String weekDays, String location, String type, String prerequisite, String description, String department)
                    throws SQLException, Exception {

        if (!UtilMethods.isString(courseName)) {
            throw new SCRSException(ErrorMessages.notString);
        }
        Admin admin = new Admin();

        return admin.adminAddClass(token, courseID, courseName, courseCredits, courseCapacity, term, instructorID,
                firstDay, lastDay, classBeginTime, classEndTime, weekDays, location, type, prerequisite, description,
                department);

    }

    /**
     * This interface should allow the admin to delete a class from the database
     * if and only if there is not one register it.
     * 
     * @param token
     * @param courseID
     * @return Return true if the operation is successfully, false otherwise
     */
    @Override
    public boolean adminDeleteClass(Token token, int courseID) {
        try {
            adminDeleteClass1(token, courseID);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public boolean adminDeleteClass1(Token token, int courseID) throws Exception {
        Admin admin = new Admin();
        validateCredentials(token);
        try {
            admin.adminDeleteClass(token, courseID);
        } catch (SCRSException e) {
            throw new SCRSException(ErrorMessages.CLASS_NOT_FOUND);
        }

        return true;
    }

    /**
     * This interface should allow the admin to modify the existed class in the
     * database. The admin can only edit the class's description if at least one
     * student registers this class The admin can edit everything of the class
     * if no one registers it
     * 
     * @param token
     * @param courseID
     * @param courseName
     * @param courseCredits
     * @param instructor
     * @param firstDay
     *            The first day of the class in the new semester
     * @param lastDay
     *            The last day of the class in the new semester
     * @param classBeginTime
     *            E.g. 9:00
     * @param classEndTime
     *            E.g. 16:00
     * @param weekDays
     *            E.g. Tu, Fri
     * @param location
     * @param type
     * @param prerequisite
     * @param description
     * @param department
     * @return Return true if the operation is successfully, false otherwise
     */
    @Override
    public boolean adminEditClass(ShibbolethAuth.Token token, int courseID, String courseName, int courseCredits,
            int instructorID, String firstDay, String lastDay, String classBeginTime, String classEndTime,
            String weekDays, String location, String type, String prerequisite, String description, String department) {
        try {
            adminEditClass1(token, courseID, courseName, courseCredits, instructorID, firstDay, lastDay, classBeginTime,
                    classEndTime, weekDays, location, type, prerequisite, description, department);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public boolean adminEditClass1(Token token, int courseID, String courseName, int courseCredits, int instructorID,
            String firstDay, String lastDay, String classBeginTime, String classEndTime, String weekDays,
            String location, String type, String prerequisite, String description, String department) throws Exception {

        Admin admin = new Admin();
        validateCredentials(token);
        try {
            admin.adminEditClass(token, courseID, courseName, courseCredits, instructorID, firstDay, lastDay,
                    classBeginTime, classEndTime, weekDays, location, type, prerequisite, description, department);
        } catch (SCRSException e) {
            throw new SCRSException(ErrorMessages.CLASS_NOT_FOUND);
        }
        return true;
    }

    /**
     * This interface should allow the admin to add one student to one specific
     * class if exist
     * 
     * @param token
     * @param studentID
     * @param courseID
     * @param grading
     * @param courseTerm
     * @return Return true if the operation is successfully, false otherwise
     */
    @Override
    public boolean adminAddStudentToClass(Token token, int studentID, int courseID, String grading, String courseTerm) {
        try {
            adminAddStudentToClass1(token, studentID, courseID, grading, courseTerm);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public boolean adminAddStudentToClass1(Token token, int studentID, int courseID, String grading, String courseTerm)
            throws Exception {
        Admin admin = new Admin();
        validateCredentials(token);
        try {
            admin.adminAddStudentToClass(token, studentID, courseID, grading, courseTerm);
        } catch (SCRSException e) {
            throw new SCRSException(ErrorMessages.CLASS_NOT_FOUND);
        }
        return true;
    }

    /**
     * This interface should allow the admin to edit one student to one specific
     * class if exist
     * 
     * @param token
     * @param studentID
     * @param courseID
     * @param grading
     * @param courseTerm
     * @return Return true if the operation is successfully, false otherwise
     */
    @Override
    public boolean adminEditStudentRegisteredClass(Token token, int studentID, int courseID, String grading,
            String courseTerm) {
        try {
            adminEditStudentRegisteredClass1(token, studentID, courseID, grading, courseTerm);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public boolean adminEditStudentRegisteredClass1(Token token, int studentID, int courseID, String grading,
            String courseTerm) throws Exception {
        // TODO Auto-generated method stub
        Admin admin = new Admin();
        validateCredentials(token);
        try {
            admin.adminEditStudentRegisteredClass(token, studentID, courseID, grading, courseTerm);
        } catch (SCRSException e) {
            throw new SCRSException(ErrorMessages.SQL_EXCEPTION);
        }
        return true;
    }

    /**
     * This interface is used for querying instructors basic personal
     * information
     * 
     * @param token
     * @param instructorID
     *            -1 if all instructors information need to be returned
     * @return Store a designated instructor's basic information in database
     *         table property order. Empty list will be returned if the query is
     *         failed.
     */
    @Override
    public List<ArrayList<String>> queryInstructor(Token token, int instructorID) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * This interface should allow the admin to remove one registered class from
     * a student's registered class's list.
     * 
     * @param token
     * @param studentID
     * @param courseID
     * @return Return true if the operation is successfully, false otherwise
     */
    @Override
    public boolean adminDropStudentRegisteredClass(Token token, int studentID, int courseID) {
        try {
            adminDropStudentRegisteredClass1(token, studentID, courseID);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public boolean adminDropStudentRegisteredClass1(Token token, int studentID, int courseID) throws Exception {
        Admin admin = new Admin();
        validateCredentials(token);

        try {
            admin.adminDropStudentRegisteredClass(token, studentID, courseID);
        } catch (SCRSException e) {
            throw new SCRSException(ErrorMessages.SQL_EXCEPTION);
        }
        return true;

    }

}
