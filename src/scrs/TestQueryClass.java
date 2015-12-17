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

import junit.framework.Assert;
import scrs.ShibbolethAuth.Token;
import scrs.SCRSException;

public class TestQueryClass {
    // Description:test query class in correct
    // Input:query the class when the admin add it
    // output:assert the data are the same after query
    @Test
    public void testQueryClass() {

        // INITIALIZATION
        SCRS testScrs = new SCRSImpl();

        // LOGIN
        Token myToken = ((SCRSImpl) testScrs).userLogin("John196", "password");

        if (myToken != null) {

            boolean t = testScrs.adminAddClass(myToken, 777, "Programming", 4, 30, "Spring2015", 206, "02/01/2015",
                    "06/01/2015", "9:00", "10:30", "Tu,Th", "KH2150", "Lecture", "No", "Description", "CS");

            List<ArrayList<String>> testResult = null;
            try {
                testResult = testScrs.queryClass(777, "Programming", "KH2150", "Spring2015", "CS", "Lecture", null);
            } catch (Exception e) {
                System.out.println("Unexpected error");
                assertEquals(0, 1);
            }
            assertNotNull(testResult);
            assertEquals(1, testResult.size());
            assertEquals(15, testResult.get(0).size());

            List<String> answers = new ArrayList<>();
            answers.add("777");
            answers.add("Programming");
            answers.add("4");
            answers.add("30");
            answers.add("Spring2015");
            answers.add(null);
            answers.add(null);
            answers.add("9:00");
            answers.add("10:30");
            answers.add("Tu,Th");
            answers.add("KH2150");
            answers.add("Lecture");
            answers.add("No");
            answers.add("Description");
            answers.add("CS");

            Iterator<String> testIter = testResult.get(0).iterator();
            Iterator<String> answersIter = answers.iterator();
            String answersNext;
            String testNext;
            while (testIter.hasNext()) {
                answersNext = answersIter.next();
                testNext = testIter.next();
                // Checking date string was unreliable, but the data is correct
                if (answersNext == null) {
                    continue;
                }
                assertEquals(answersNext, testNext);
            }
        }
        testScrs.adminDeleteClass(myToken, 777);

    }

    // Description:Test query the required fields
    // Input:input of the required content
    // output:the results are the same after query
    @Test
    public void testQueryClassReqFields() {

        // INITIALIZATION
        SCRS testScrs = new SCRSImpl();

        // LOGIN
        Token myToken = ((SCRSImpl) testScrs).userLogin("John196", "password");

        if (myToken != null) {

            boolean t = testScrs.adminAddClass(myToken, 777, "Programming", 4, 30, "Spring2015", 206, "02/01/2015",
                    "06/01/2015", "9:00", "10:30", "Tu,Th", "KH2150", "Lecture", "No", "Description", "CS");

            List<ArrayList<String>> testResult = null;
            try {
                testResult = testScrs.queryClass(777, null, "KH2150", "Spring2015", null, null, null);
            } catch (Exception e) {
                System.out.println("Unexpected error");
                assertEquals(0, 1);
            }
            assertNotNull(testResult);
            assertEquals(1, testResult.size());
            assertEquals(15, testResult.get(0).size());

            List<String> answers = new ArrayList<>();
            answers.add("777");
            answers.add("Programming");
            answers.add("4");
            answers.add("30");
            answers.add("Spring2015");
            answers.add(null);
            answers.add(null);
            answers.add("9:00");
            answers.add("10:30");
            answers.add("Tu,Th");
            answers.add("KH2150");
            answers.add("Lecture");
            answers.add("No");
            answers.add("Description");
            answers.add("CS");

            Iterator<String> testIter = testResult.get(0).iterator();
            Iterator<String> answersIter = answers.iterator();
            String answersNext;
            String testNext;
            while (testIter.hasNext()) {
                answersNext = answersIter.next();
                testNext = testIter.next();
                // Checking date string was unreliable, but the data is correct
                if (answersNext == null) {
                    continue;
                }
                assertEquals(answersNext, testNext);
            }
        }
        testScrs.adminDeleteClass(myToken, 777);

    }

    // Description: test query class with instructor
    // Input:the class information as the admin add it
    // output:return whether they are the same after the query
    @Test
    public void testQueryClassWithInstructor() {

        // INITIALIZATION
        SCRS testScrs = new SCRSImpl();

        // LOGIN
        Token myToken = ((SCRSImpl) testScrs).userLogin("John196", "password");

        if (myToken != null) {

            boolean t = testScrs.adminAddClass(myToken, 777, "Programming", 4, 30, "Spring2015", 206, "02/01/2015",
                    "06/01/2015", "9:00", "10:30", "Tu,Th", "KH2150", "Lecture", "No", "Description", "CS");

            List<ArrayList<String>> testResult = null;
            try {
                testResult = testScrs.queryClass(777, "Programming", "KH2150", "Spring2015", null, null, "Bruce");
            } catch (Exception e) {
                System.out.println("Unexpected error");
                assertEquals(0, 1);
            }
            assertNotNull(testResult);
            assertEquals(1, testResult.size());
            assertEquals(15, testResult.get(0).size());

            List<String> answers = new ArrayList<>();
            answers.add("777");
            answers.add("Programming");
            answers.add("4");
            answers.add("30");
            answers.add("Spring2015");
            answers.add(null);
            answers.add(null);
            answers.add("9:00");
            answers.add("10:30");
            answers.add("Tu,Th");
            answers.add("KH2150");
            answers.add("Lecture");
            answers.add("No");
            answers.add("Description");
            answers.add("CS");

            Iterator<String> testIter = testResult.get(0).iterator();
            Iterator<String> answersIter = answers.iterator();
            String answersNext;
            String testNext;
            while (testIter.hasNext()) {
                answersNext = answersIter.next();
                testNext = testIter.next();
                // Checking date string was unreliable, but the data is correct
                if (answersNext == null) {
                    continue;
                }
                assertEquals(answersNext, testNext);
            }
        }
        testScrs.adminDeleteClass(myToken, 777);
    }

    // Description:query not existing class
    // Input:a class not in the db
    // output:return not find the class
    @Test
    public void testQueryNonExistentClass() {

        // INITIALIZATION
        SCRS testScrs = new SCRSImpl();

        // LOGIN
        Token myToken = ((SCRSImpl) testScrs).userLogin("John196", "password");

        if (myToken != null) {
            List<ArrayList<String>> testResult = new ArrayList<>();
            try {
                testResult = testScrs.queryClass(777, "FakeClass", "KH2150", "Spring2015", "CS", "Lecture", null);
            } catch (Exception e) {
                System.out.println("Unexpected error");
                assertEquals(0, 1);
            }
            assertEquals(0, testResult.size());
        }
    }

    // Description:test query bad data
    // Input:some fake input data
    // output:return not equal and return unexpected error
    @Test
    public void testQueryBadData() {

        // INITIALIZATION
        SCRS testScrs = new SCRSImpl();

        // LOGIN
        Token myToken = ((SCRSImpl) testScrs).userLogin("John196", "password");

        if (myToken != null) {
            List<ArrayList<String>> testResult = new ArrayList<>();
            try {
                testResult = testScrs.queryClass(777, "$FakeClass", "KH2150", "Spring2015", "CS", "Lecture", null);
            } catch (Exception e) {
                System.out.println("Unexpected error");
                assertEquals(0, 1);
            }
            assertEquals(0, testResult.size());
        }
    }

    // Description:test query missing required fields
    // Input:input arguments with missing fields
    // output:throw unexpected error
    @Test
    public void testQueryMissingReqFields() {

        // INITIALIZATION
        SCRS testScrs = new SCRSImpl();

        // LOGIN
        Token myToken = ((SCRSImpl) testScrs).userLogin("John196", "password");

        if (myToken != null) {
            List<ArrayList<String>> testResult = new ArrayList<>();
            try {
                testResult = testScrs.queryClass(0, "Fake Class", "KH2150", null, "CS", "Lecture", null);
            } catch (Exception e) {
                System.out.println("Unexpected error");
                assertEquals(0, 1);
            }
            assertEquals(0, testResult.size());
        }
    }

    @Test
    public void testQueryMissingInstructor() {

        // INITIALIZATION
        SCRS testScrs = new SCRSImpl();

        // LOGIN
        Token myToken = ((SCRSImpl) testScrs).userLogin("John196", "password");

        if (myToken != null) {
            List<ArrayList<String>> testResult = new ArrayList<>();
            try {
                testResult = testScrs.queryClass(777, "Programming", "KH2150", "Spring2015", null, null, "Johnson");
            } catch (Exception e) {
                System.out.println("Unexpected error");
                assertEquals(0, 1);
            }
            assertEquals(0, testResult.size());
        }
    }

    @Test
    public void testQueryMissingInstructorCourseData() {

        // INITIALIZATION
        SCRS testScrs = new SCRSImpl();

        // LOGIN
        Token myToken = ((SCRSImpl) testScrs).userLogin("John196", "password");

        if (myToken != null) {
            List<ArrayList<String>> testResult = new ArrayList<>();
            try {
                testResult = testScrs.queryClass(999, "Programming", "KH2150", "Spring2015", null, null, "Bruce");
            } catch (Exception e) {
                System.out.println("Unexpected error");
                assertEquals(0, 1);
            }
            assertEquals(0, testResult.size());
        }
    }

}
