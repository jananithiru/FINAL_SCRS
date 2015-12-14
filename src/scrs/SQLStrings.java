package scrs;

import java.util.ArrayList;
import java.util.List;

public class SQLStrings {

    /**
     * Method used to generate sql string for selecting data from the student
     * table.
     * 
     * @param studentID
     * @return
     */
    public static String selectAllFromStudent(int studentID) {
        // TODO: Come up with this string escaped corrected
        String sqlStr = "select * from student where id=" + studentID;
        return sqlStr;
    }

    /**
     * Method used to generate sql string for selecting data from the admin
     * table.
     * 
     * @param adminID
     * @return
     */
    public static String selectAllFromAdmin(int adminID) {
        // TODO: Come up with this string escaped corrected
        String sqlStr = "select * from administrator where id=" + adminID;
        return sqlStr;
    }

    /**
     * Method used to generate sql string for selecting data from the course
     * table. Required fields are courseID, location, and term. The rest of the
     * fields are optional. If an optional field is not to be queried for, the
     * value should be inputted as null.
     * 
     * @param courseID
     * @param courseName
     * @param location
     *            contains location of the course (e.g. 'East Bank KH3-301')
     * @param term
     *            term the course is offered (e.g. 'Spring2015')
     * @param department
     *            e.g. 'CS'
     * @param classType
     *            Lecture or Seminar
     * @param instructorID
     * @return
     */
    public static String selectAllFromCourse(int courseID, String courseName, String location, String term,
            String department, String classType) {
        // query instr
        // get set
        // check

        String sqlStr = "select * FROM course c";
        
        sqlStr += " WHERE c.id = " + courseID;

        sqlStr += " AND c.location = '" + location + "'";
        
        sqlStr += " AND c.term = '" + term + "'";
            
        if (department != null) {
            sqlStr += " AND c.department = '" + department + "'";
        }
        if (courseName != null) {
            sqlStr += " AND c.name = '" + courseName + "'";
        }
        if (classType != null) {
            sqlStr += " AND c.type = '" + classType + "'";
        }
        sqlStr += ";";
        return sqlStr;
    }

    /**
     * Method used to generate sql string for querying for a students
     * registration history.
     * 
     * @param studentID
     * @return
     */
    public static String selectHistoryFromStudentAndCourse(int studentID) {
        String sqlStr = "select * FROM studentandcourse WHERE studentid = " + studentID + ";";
        return sqlStr;
    }
}
