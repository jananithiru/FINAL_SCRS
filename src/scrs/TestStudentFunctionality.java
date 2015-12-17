package scrs;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import scrs.*;
import scrs.ShibbolethAuth.Token;

/**
 * @author Yuwei Wang
 * Junit tests for student functionalities
 *
 */
public class TestStudentFunctionality {
    
    @Test
    public void TestStudentAddClass() {
        SCRS testScrs = new SCRSImpl();
        // ShibbolethAuth sbAuth = new ShibbolethAuth();

        // Add Class
        Token myTokenAdmin = ((SCRSImpl) testScrs).userLogin("John196", "password");
        Token myTokenStu = ((SCRSImpl) testScrs).userLogin("YUWEI1005", "mypassword");

        // Add Class
        if (myTokenAdmin != null && myTokenStu != null) {
            // Testcase: Token type is admin, function should return false
            assertEquals(false, testScrs.studentAddClass(myTokenAdmin, 888, "A-F", "Fall2015"));
            // Testcase: Good case: tokentype is student, course id is 888 which
            // is in the database
            // Grade basis is A-F and the term is Fall 2015, the function should
            // return true
            assertEquals(true, testScrs.studentAddClass(myTokenStu, 888, "A-F", "Fall2015"));
            // Testcase: student leaves the grade basis empty, the function
            // should return false
            assertEquals(false, testScrs.studentAddClass(myTokenStu, 888, null, "Fall2015"));
            // TestCase: student leaves the term empty, the function should
            // return false
            assertEquals(false, testScrs.studentAddClass(myTokenStu, 888, "A-F", null));
            // Testcase: The student tries to add a course with course id 5566,
            // which is not in the database
            // the function should return false
            assertEquals(true, testScrs.studentAddClass(myTokenStu, 5566, "A-F", "Fall2015"));
            //testcase: when out of timeframe, the student tries to add one class, the function should return false 
            assertEquals(false, testScrs.studentAddClass(myTokenStu, 888, "A-F", "Fall2014"));

        }
    }

    @Test
    public void TestStudentDropClass() {
        SCRS testScrs = new SCRSImpl();
        // ShibbolethAuth sbAuth = new ShibbolethAuth();
        Token myTokenAdmin = ((SCRSImpl) testScrs).userLogin("John196", "password");
        Token myTokenStu = ((SCRSImpl) testScrs).userLogin("YUWEI1005", "mypassword");
        
        if (myTokenAdmin != null && myTokenStu != null) {
            //testcase: good case, the student wants to drop course 888, which is in his record(studentAndCourse table)
            //the function should return true
            assertEquals(true, testScrs.studentDropClass(myTokenStu, 888));
            //testcase: the student drops the course 88800 which is not in his record
            //the function should return false
            assertEquals(false, testScrs.studentDropClass(myTokenStu, 88800));
            //testcase: the token type is admin, the function should return false 
            assertEquals(false, testScrs.studentDropClass(myTokenAdmin, 888));
            //testcase: student tries to drop course 666 which is 2014fall term, which is out of timeframe
            //the function should return false 
            assertEquals(false, testScrs.studentDropClass(myTokenStu, 666));
            

        }
    }

    @Test
    public void testStudentEditClass() {
        SCRS testScrs = new SCRSImpl();
        Token myTokenAdmin = ((SCRSImpl) testScrs).userLogin("John196", "password");
        Token myTokenStu = ((SCRSImpl) testScrs).userLogin("YUWEI1005", "mypassword");
        if (myTokenAdmin != null && myTokenStu != null) {
            //testcase: good case: the studentmodify the grad basis into A-F for course 888, which is in his record  
            assertEquals(true, testScrs.studentEditClass(myTokenStu, 888, "A-F", "Fall2015"));
            //testcase: out of time frame: student tries to change the grade basis into A-F for course 666
            assertEquals(false, testScrs.studentEditClass(myTokenStu, 666, "A-F", "Spring2014"));
            //testcase: the student did not give the course grade basis, the function should return false
            assertEquals(false, testScrs.studentEditClass(myTokenStu, 666, null, "Fall2015"));
            //test case: the student leave the course term null, the function should return false
            assertEquals(false, testScrs.studentEditClass(myTokenStu, 888, "A-F", null));
            //testcase: the token type is admin, the function should return false
            assertEquals(false, testScrs.studentEditClass(myTokenAdmin, 888, "A-F", "Fall2015"));
            //the student tries to modify the course which is not in his record(StudentAndCourse TABLE)
            assertEquals(false, testScrs.studentEditClass(myTokenStu, 9990000, "A-F", "Fall2015"));
            //The grade basis is not a good format
            assertEquals(false, testScrs.studentEditClass(myTokenStu, 888, "A-P", "Fall2015"));
           
        }
    }

}
