package scrs;

import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.print.attribute.standard.RequestingUserName;

import scrs.Constants.PrimitiveDataType;
import scrs.ShibbolethAuth.Token.RoleType;

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

        if (token.type != RoleType.STUDENT) {
            System.out.println(new SCRSException(ErrorMessages.STUDENTACCOUNT_FAILURE));
            return false;
        }

        if (grading == null | courseTerm == null) {
            System.out.println(new SCRSException(ErrorMessages.MISSING_REQUIRED_FIELD));
            return false;
        }

        // get current date time with Date()
        LocalDateTime now = LocalDateTime.now();
        int year = now.getYear();
        int month = now.getMonthValue();
        int day = now.getDayOfMonth();

        Date currentDate = new Date(year, month, day);

        if (!UtilMethods.isInTimeFrame(currentDate, courseTerm)) {
            throw new SCRSException(ErrorMessages.OUT_TIME_FRAME);
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
            throw new SCRSException(ErrorMessages.CLASS_NOT_FOUND);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            throw new SCRSException(ErrorMessages.SQL_EXCEPTION);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            throw new SCRSException(ErrorMessages.PARASE_DATA_ERROR);
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
            return false;
        }

        return result;

    }

    @SuppressWarnings("deprecation")
    boolean studentDropClass2(ShibbolethAuth.Token token, int courseID) throws SCRSException {
        if (token.type != RoleType.STUDENT) {
            System.out.println(new SCRSException(ErrorMessages.STUDENTACCOUNT_FAILURE));
            return false;
        }
        // how to judge courseId is not given, since the type is int not
        // Integer??

        DBCoordinator dbCoordinator = new DBCoordinator();

        // get current date time with Date()
        LocalDateTime now = LocalDateTime.now();
        int year = now.getYear();
        int month = now.getMonthValue();
        int day = now.getDayOfMonth();

        Date currentDate = new Date(year, month, day);

        String sqlCmd = "SELECT TERM FROM COURSE WHERE ID = " + courseID;
        String sqlCmd2 = "SELECT * FROM STUDENTANDCOURSE WHERE COURSEID=" + courseID + " AND STUDENTID=" + token.id;
        List<ArrayList<Object>> termList = null;
        List<ArrayList<Object>> rs = null;

        try {
            termList = dbCoordinator.queryData(sqlCmd);
            rs = dbCoordinator.queryData(sqlCmd2);

        } catch (ClassNotFoundException e1) {
            // TODO Auto-generated catch block
            throw new SCRSException(ErrorMessages.CLASS_NOT_FOUND);
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            throw new SCRSException(ErrorMessages.SQL_EXCEPTION);
        }

        if (termList == null || termList.size() == 0) {

            throw new SCRSException(ErrorMessages.NO_RECORD_RETURN_FROM_DB);

        }
        if (rs == null || rs.size() == 0) {
            throw new SCRSException(ErrorMessages.NO_RECORD_RETURN_FROM_DB);
        }
        String courseTerm = (String) termList.get(0).get(0);

        if (!UtilMethods.isInTimeFrame(currentDate, courseTerm)) {
            throw new SCRSException(ErrorMessages.OUT_TIME_FRAME);

        }

        String sqlStr = "delete from StudentAndCourse where courseId=? and studentID=?";
        ArrayList<String> dataList = new ArrayList<String>();
        dataList.add(Integer.toString(courseID));
        dataList.add(Integer.toString(token.id));
        ArrayList<PrimitiveDataType> typeList = new ArrayList<PrimitiveDataType>();
        typeList.add(PrimitiveDataType.INT);
        typeList.add(PrimitiveDataType.INT);

        try {
            dbCoordinator.deleteData(sqlStr, dataList, typeList);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            throw new SCRSException(ErrorMessages.CLASS_NOT_FOUND);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            throw new SCRSException(ErrorMessages.SQL_EXCEPTION);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            throw new SCRSException(ErrorMessages.PARASE_DATA_ERROR);
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
        boolean result = false;
        try {
            result = studentEditClass2(token, courseID, grading, courseTerm);
        } catch (SCRSException e) {
            // TODO Auto-generated catch block
            System.out.println(e.getMessage());
            return false;
        }
        return result;
    }

    boolean studentEditClass2(ShibbolethAuth.Token token, int courseID, String grading, String courseTerm)
            throws SCRSException {
        if (token.type == RoleType.ADMIN) {
            System.out.println(new SCRSException(ErrorMessages.STUDENTACCOUNT_FAILURE));
            return false;
        }
        if (token.type == RoleType.BOTH) {
            System.out.println(new SCRSException(ErrorMessages.STUDENTACCOUNT_FAILURE));
            return false;
        }
        if (grading == null || courseTerm == null) {
            System.out.println(new SCRSException(ErrorMessages.MISSING_REQUIRED_FIELD));
            return false;
        }
        LocalDateTime now = LocalDateTime.now();
        int year = now.getYear();
        int month = now.getMonthValue();
        int day = now.getDayOfMonth();

        Date currentDate = new Date(year, month, day);
        System.out.println("Time frame function value is" + UtilMethods.isInTimeFrame(currentDate, courseTerm));
        if (!UtilMethods.isInTimeFrame(currentDate, courseTerm)) {

            throw new SCRSException(ErrorMessages.OUT_TIME_FRAME);
        }
        DBCoordinator dbCoordinator = new DBCoordinator();

        String sqlStr = "update StudentAndCourse set grading=? where courseId=? and studentID=?";
        String sqlCmd2 = "SELECT * FROM STUDENTANDCOURSE WHERE COURSEID=" + courseID + " AND STUDENTID=" + token.id;
        List<ArrayList<Object>> rs = null;
        try {
            rs = dbCoordinator.queryData(sqlCmd2);
        } catch (ClassNotFoundException e1) {
            throw new SCRSException(ErrorMessages.CLASS_NOT_FOUND);
        } catch (SQLException e1) {
            throw new SCRSException(ErrorMessages.SQL_EXCEPTION);
        }
        if (rs == null || rs.size() == 0) {
            throw new SCRSException(ErrorMessages.NO_RECORD_RETURN_FROM_DB);
        }

        ArrayList<String> dataList = new ArrayList<String>();
        dataList.add(grading);
        dataList.add(Integer.toString(courseID));
        dataList.add(Integer.toString(token.id));
        ArrayList<PrimitiveDataType> typeList = new ArrayList<PrimitiveDataType>();
        typeList.add(PrimitiveDataType.STRING);
        typeList.add(PrimitiveDataType.INT);
        typeList.add(PrimitiveDataType.INT);

        try {

            dbCoordinator.updateData(sqlStr, dataList, typeList);

        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            throw new SCRSException(ErrorMessages.CLASS_NOT_FOUND);
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
