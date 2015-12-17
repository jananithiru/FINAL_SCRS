package scrs;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import scrs.Constants.PrimitiveDataType;
import scrs.ShibbolethAuth.Token;

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
            int courseCapacity, String term, int instructorID, String firstDay, String lastDay, String classBeginTime,
            String classEndTime, String weekDays, String location, String type, String prerequisite, String description,
            String department) throws SQLException, Exception {

        if (token.type != Token.RoleType.BOTH && token.type != Token.RoleType.ADMIN) {
            System.out.println("THIS IS NOT ADMIN");

            return false;

        }
        System.out.println("THIS IS ADMIN");

        DBCoordinator dbcoordinator = new DBCoordinator();

        String sqlCmd = null;
        sqlCmd = "INSERT INTO COURSE (ID, NAME, CREDITS,CAPACITY,TERM,FIRSTDAY, LASTDAY, CLASSBEGINTIME, CLASSENDTIME, ROUTINES, LOCATION, TYPE, PREREQUISITE, DESCRIPTION, DEPARTMENT) "
                + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        ArrayList<String> dataList = new ArrayList<String>();
        dataList.add(Integer.toString(courseID));
        dataList.add(courseName);
        dataList.add(Integer.toString(courseCredits));
        dataList.add(Integer.toString(courseCapacity));
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
        // insert into course table based on given attributes
        
            dbcoordinator.insertData(sqlCmd, dataList, typeList);
            System.out.println("ADMIN ADD CLASS TO COURSE TABLE SUCCESSFUL");

        

        sqlCmd = "INSERT INTO INSTRUCTORANDCOURSE (COURSEID, INSTRUCTORID) VALUES (?,?)";

        dataList = new ArrayList<String>();
        dataList.add(Integer.toString(courseID));
        dataList.add(Integer.toString(instructorID));

        typeList = new ArrayList<PrimitiveDataType>();
        typeList.add(PrimitiveDataType.INT);
        typeList.add(PrimitiveDataType.INT);
        // insert into instructorandcourse table(need instructor already in
        // database)
        
            dbcoordinator.insertData(sqlCmd, dataList, typeList);
            System.out.println("ADMIN ADD CLASS TO INSTRUCTORANDCOURSE TABLE SUCCESSFUL");
       

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
     * @throws Exception
     */
    public boolean adminDeleteClass(ShibbolethAuth.Token token, int courseID) throws SCRSException, Exception {
        if (token.type != Token.RoleType.BOTH && token.type != Token.RoleType.ADMIN) {
            System.out.println(new SCRSException("ACCOUNT TYPE FAILURE:THIS IS NOT ADMIN"));
            return false;
        }

        DBCoordinator dbcoordinator = new DBCoordinator();

        String sqlCmd = null;
        sqlCmd = "DELETE FROM COURSE WHERE ID = ?";
        ArrayList<String> dataList = new ArrayList<String>();
        dataList.add(Integer.toString(courseID));

        ArrayList<PrimitiveDataType> typeList = new ArrayList<PrimitiveDataType>();
        typeList.add(PrimitiveDataType.INT);

        // delete class from course table
        
            dbcoordinator.deleteData(sqlCmd, dataList, typeList);
            System.out.println("ADMIN DELETE CLASS FROM COURSE TABLE SUCCESSFUL");
        

        sqlCmd = null;
        sqlCmd = "DELETE FROM INSTRUCTORANDCOURSE WHERE COURSEID = ?";
        dataList = new ArrayList<String>();
        dataList.add(Integer.toString(courseID));

        typeList = new ArrayList<PrimitiveDataType>();
        typeList.add(PrimitiveDataType.INT);

        // delete course from instructorandcourse table
       
            dbcoordinator.deleteData(sqlCmd, dataList, typeList);
            System.out.println("ADMIN DELETE CLASS FROM INSTRUCTORANDCOURSE TABLE SUCCESSFUL");
       

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
     * @throws Exception
     */
    public boolean adminEditClass(ShibbolethAuth.Token token, int courseID, String courseName, int courseCredits,
            int instructorID, String firstDay, String lastDay, String classBeginTime, String classEndTime,
            String weekDays, String location, String type, String prerequisite, String description, String department)
                    throws SCRSException, Exception {

        if (token.type != Token.RoleType.BOTH && token.type != Token.RoleType.ADMIN) {
            System.out.println(new SCRSException("ACCOUNT TYPE FAILURE:THIS IS NOT ADMIN"));

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
        // edit class in course table
        
            dbcoordinator.updateData(sqlCmd, dataList, typeList);
            System.out.println("ADMIN EDIT CLASS IN COURSE TABLE SUCCESSFUL");
        

        sqlCmd = "UPDATE INSTRUCTORANDCOURSE SET INSTRUCTORID = ? WHERE COURSEID = ?";

        dataList = new ArrayList<String>();
        dataList.add(Integer.toString(instructorID));
        dataList.add(Integer.toString(courseID));

        typeList = new ArrayList<PrimitiveDataType>();
        typeList.add(PrimitiveDataType.INT);
        typeList.add(PrimitiveDataType.INT);
        // insert into instructorandcourse table(need instructor already in
        // database)
       
            dbcoordinator.updateData(sqlCmd, dataList, typeList);
            System.out.println("ADMIN EDIT CLASS IN INSTRUCTORANDCOURSE TABLE SUCCESSFUL");
        

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
     * @throws Exception
     */
    public boolean adminAddStudentToClass(ShibbolethAuth.Token token, int studentID, int courseID, String grading,
            String courseTerm) throws SQLException, Exception {

        if (token.type != Token.RoleType.BOTH && token.type != Token.RoleType.ADMIN) {
            System.out.println(new SCRSException("ACCOUNT TYPE FAILURE:THIS IS NOT ADMIN"));

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
        // add student into class
        
            dbcoordinator.insertData(sqlCmd, dataList, typeList);
            System.out.println("ADMIN ADD STUDENT TO CLASS SUCCESSFUL");
        

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
     * @throws Exception
     */
    public boolean adminEditStudentRegisteredClass(ShibbolethAuth.Token token, int studentID, int courseID,
            String grading, String courseTerm) throws SQLException, Exception {
        if (token.type != Token.RoleType.BOTH && token.type != Token.RoleType.ADMIN) {
            System.out.println(new SCRSException("ACCOUNT TYPE FAILURE:THIS IS NOT ADMIN"));

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

      
            dbcoordinator.updateData(sqlCmd, dataList, typeList);
            System.out.println("ADMIN EDIT STUDENT REGISTERED CLASS SUCCESSFUL");
        

        return true;

    }

    /**
     * admin drop student from studentandcourse table
     * 
     * @param token
     * @param studentID
     * @param courseID
     * @return
     * @throws SCRSException
     * @throws Exception
     */
    public boolean adminDropStudentRegisteredClass(ShibbolethAuth.Token token, int studentID, int courseID)
            throws SCRSException, Exception {
        if (token.type != Token.RoleType.BOTH && token.type != Token.RoleType.ADMIN) {
            System.out.println(new SCRSException("ACCOUNT TYPE FAILURE:THIS IS NOT ADMIN"));

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
       
            dbcoordinator.deleteData(sqlCmd, dataList, typeList);
            System.out.println("ADMIN DROP STUDENT REGISTERED CLASS FROM STUDENTANDCOURSE TABLE SUCCESSFUL");
       
        return true;

    }

}
