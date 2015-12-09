package scrs;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import org.junit.Test;
import scrs.*;
import scrs.ShibbolethAuth.Token;

public class TestAdminFunctionality {
    // test admin add class into course and instructorandcourse table
    @Test
    public void TestAdminAddClass() throws Exception {

        SCRS testScrs = new SCRSImpl();
        ShibbolethAuth sbAuth = new ShibbolethAuth();
        Token tokenGenerator = sbAuth.tokenGenerator("John196", "password");
        Token myTokenStu = ((SCRSImpl) testScrs).userLogin("YUWEI1005", "mypassword");
        System.out.println("CHECK FROM TESTS " + tokenGenerator.type);

        if (tokenGenerator != null) {

            System.out.println("ADMIN ADD CLASS START");

            assertEquals(true,
                    testScrs.adminAddClass(tokenGenerator, 888, "AdvancedDatabase", 1, 25, "Fall2015", 206,
                            "09/01/2014", "12/20/2014", "9:00", "10:30", "Tu,Th", "KHKH110", "Lecture", "No",
                            "Databases", "CS"));
            assertEquals(true,
                    testScrs.adminAddClass(tokenGenerator, 777, "AdvancedDatabase", 2, 25, "Fall2015", 206,
                            "09/01/2014", "12/20/2014", "9:00", "10:30", "Tu,Th", "KHKH110", "Lecture", "No",
                            "Databases", "CS"));
            assertEquals(false,
                    testScrs.adminAddClass(myTokenStu, 666, "AdvancedDatabase", 2, 25, "Fall2015", 206, "09/01/2014",
                            "12/20/2014", "9:00", "10:30", "Tu,Th", "KHKH110", "Lecture", "No", "Databases", "CS"));

        }
    }

    @Test
    public void TestAdminAddClassAllString() throws Exception {

        SCRS testScrs = new SCRSImpl();
        ShibbolethAuth sbAuth = new ShibbolethAuth();
        Token tokenGenerator = sbAuth.tokenGenerator("John196", "password");
        System.out.println("CHECK FROM TESTS " + tokenGenerator.type);

        if (tokenGenerator != null) {

            System.out.println("ADMIN ADD CLASS START");

            assertEquals(false,
                    testScrs.adminAddClass(tokenGenerator, 444, "AdvancedDatabase1", 2, 25, "Fall2015", 206,
                            "09/01/2014", "12/20/2014", "9:00", "10:30", "Tu,Th", "KHKH110", "Lecture", "No",
                            "Databases", "CS"));

        }
    }

    @Test
    public void TestBOTHAddClass() throws Exception {

        SCRS testScrs = new SCRSImpl();
        ShibbolethAuth sbAuth = new ShibbolethAuth();

        Token tokenGenerator = ((SCRSImpl) testScrs).userLogin("amy2000", "password");
        System.out.println("CHECK FROM TESTS " + tokenGenerator.type);

        if (tokenGenerator != null) {

            System.out.println("BOTH ADD CLASS START");

            assertEquals(false,
                    testScrs.adminAddClass(tokenGenerator, 555, "Advanced Database", 3, 25, "Fall2015", 206,
                            "09/01/2014", "12/20/2014", "9:00", "10:30", "Tu,Th", "KHKH110", "Lecture", "No",
                            "Databases", "CS"));

            System.out.println("BOTH ADD CLASS SUCCESSFUL");

        }
    }

    // test admin delete class from course table and instructorandcourse table
    @Test
    public void TestAdminDeleteClass() throws ClassNotFoundException, SQLException {
        SCRS testScrs = new SCRSImpl();
        ShibbolethAuth sbAuth = new ShibbolethAuth();
        Token tokenGenerator = sbAuth.tokenGenerator("John196", "password");
        System.out.println("usertype  " + tokenGenerator.type);

        if (tokenGenerator != null) {

            assertEquals(true, testScrs.adminDeleteClass(tokenGenerator, 777));

        }
    }

    // test admin can edit class info in course table
    @Test
    public void TestAdminEditClass() throws ClassNotFoundException, SQLException {
        SCRS testScrs = new SCRSImpl();
        ShibbolethAuth sbAuth = new ShibbolethAuth();
        Token tokenGenerator = sbAuth.tokenGenerator("John196", "password");
        if (tokenGenerator != null) {

            System.out.println("ADMIN EDIT CLASS START");

            assertEquals(true, testScrs.adminEditClass(tokenGenerator, 888, "Advanced Database", 3, 206, "09/01/2014",
                    "09/01/2015", "9:00", "10:30", "Tu,Th", "KHKH110", "Lecture", "No", "Databases", "CS"));

        }
    }

    // test admin can add student to class in studentandcourse table
    @Test
    public void TestAdminAddStudentToClass() throws ClassNotFoundException, SQLException {
        SCRS testScrs = new SCRSImpl();
        ShibbolethAuth sbAuth = new ShibbolethAuth();
        Token tokenGenerator = sbAuth.tokenGenerator("John196", "password");
        if (tokenGenerator != null) {

            System.out.println("ADMIN ADD STDUENTCLASS START");

            assertEquals(true, testScrs.adminAddStudentToClass(tokenGenerator, 1006, 888, "A-F", "2014Fall"));

        }
    }

    // test admin edit studentandcourse info
    @Test
    public void TestAdminEditStudentRegisteredClass() throws ClassNotFoundException, SQLException {
        SCRS testScrs = new SCRSImpl();
        ShibbolethAuth sbAuth = new ShibbolethAuth();
        Token tokenGenerator = sbAuth.tokenGenerator("John196", "password");
        if (tokenGenerator != null) {

            assertEquals(true,
                    testScrs.adminEditStudentRegisteredClass(tokenGenerator, 1006, 888, "S/N", "Spring 2015"));

        }
    }

    // test admin can drop student in studentandcourse table
    @Test
    public void TestAdminDropStudentRegisteredClass() throws ClassNotFoundException, SQLException {
        SCRS testScrs = new SCRSImpl();
        ShibbolethAuth sbAuth = new ShibbolethAuth();
        Token tokenGenerator = sbAuth.tokenGenerator("John196", "password");
        System.out.println(tokenGenerator.type);
        if (tokenGenerator != null) {

            assertEquals(true, testScrs.adminDropStudentRegisteredClass(tokenGenerator, 1006, 888));

        }
    }

}
