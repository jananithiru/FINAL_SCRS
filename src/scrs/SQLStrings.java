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
     * @param instrCoursesListStr
     * @return
     */
    public static String selectAllFromCourse(int courseID, String courseName, String location, String term,
            String department, String classType, List<String> instrCoursesListStr) {
        String sqlStr = "select * FROM course c";
        if (instrCoursesListStr != null) {
            sqlStr += " JOIN instructorandcourse ic on c.ID = ic.courseID";
        }

        sqlStr += " WHERE c.id = " + courseID;

        if (location != null) {
            sqlStr += " AND c.location = '" + location + "'";
        }
        if (term != null) {
            sqlStr += " AND c.term = '" + term + "'";
        }
        if (department != null) {
            sqlStr += " AND c.department = '" + department + "'";
        }
        if (courseName != null) {
            sqlStr += " AND c.name = '" + courseName + "'";
        }
        if (classType != null) {
            sqlStr += " AND c.type = '" + classType + "'";
        }
        if (instrCoursesListStr != null) {
            sqlStr += " AND ic.instructorId = " + instrCoursesListStr;
        }
        sqlStr += ";";
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
    public static String selectAllFromCourse2(int courseID, String courseName, String location, String term,
            String department, String classType, List<String> instructorCourses) {
        // query instr
        // get set
        // check

        String sqlStr = "select * FROM course c";
        if (instructorCourses != null) {
            String setStr = "(";
            int size = instructorCourses.size();
            for (int i = 0; i < size; i++) {
                setStr += instructorCourses.get(i);
                if (i < size - 1) {
                    setStr += ",";
                }
            }
            setStr += ")";
            sqlStr += " WHERE c.id in " + setStr;
        } else {
            sqlStr += " WHERE c.id = " + courseID;
        }

        if (location != null) {
            sqlStr += " AND c.location = '" + location + "'";
        }
        if (term != null) {
            sqlStr += " AND c.term = '" + term + "'";
        }
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
