package scrs;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

import scrs.ShibbolethAuth.Token;

public class TestRegHistory {

    @Test
    public void testQueryRegHistory() {

        // INITIALIZATION
        SCRS testScrs = new SCRSImpl();

        // LOGIN
        Token myTokenAdmin = ((SCRSImpl) testScrs).userLogin("John196", "password");
        testScrs.adminAddClass(myTokenAdmin, 777, "Programming", 4, 30, "Spring2015", 206, "02/01/2015",
                "06/01/2015", "9:00", "10:30", "Tu,Th", "KH2150", "Lecture", "No", "Description", "CS");
        Token myTokenStu = ((SCRSImpl) testScrs).userLogin("YUWEI1005", "mypassword");

        //Add some registration history
        testScrs.studentAddClass(myTokenStu, 777, "A-F", "Spring2015");

        List<ArrayList<String>> testResult = testScrs.queryStudentRegistrationHistory(myTokenStu, 1005);

        assertNotNull(testResult);
        assertEquals(5, testResult.get(0).size());

        //clean up
        testScrs.studentDropClass(myTokenStu, 777);
        testScrs.adminDeleteClass(myTokenAdmin, 777);

    }

    @Test
    public void testQueryRegHistoryBadAccess() {

        // INITIALIZATION
        SCRS testScrs = new SCRSImpl();

        // LOGIN
        Token myTokenStu = ((SCRSImpl) testScrs).userLogin("YUWEI1005", "mypassword");

        List<ArrayList<String>> testResult = testScrs.queryStudentRegistrationHistory(myTokenStu, 999);

        assertNotNull(testResult);
        assertEquals(0, testResult.size());

    }
    
    @Test
    public void testQueryRegHistoryBadData() {

        // INITIALIZATION
        SCRS testScrs = new SCRSImpl();

        // LOGIN
        Token myTokenAdmin = ((SCRSImpl) testScrs).userLogin("John196", "password");

        List<ArrayList<String>> testResult = testScrs.queryStudentRegistrationHistory(myTokenAdmin, 0);

        assertNotNull(testResult);
        assertEquals(0, testResult.size());
    }

    @Test
    public void testQueryRegHistoryMissingStudent() {

        // INITIALIZATION
        SCRS testScrs = new SCRSImpl();

        // LOGIN
        Token myTokenAdmin = ((SCRSImpl) testScrs).userLogin("John196", "password");
        List<ArrayList<String>> testResult = testScrs.queryStudentRegistrationHistory(myTokenAdmin, 100);

        assertNotNull(testResult);
        assertEquals(0, testResult.size());

    }
}
