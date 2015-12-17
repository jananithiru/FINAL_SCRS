package scrs;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import org.junit.Test;
import scrs.ShibbolethAuth.Token;

public class TestAdminFunctionality {

    // Description: test admin add class into course and instructorandcourse
    // table
    // Input:arguments of adminAddClass()
    // Output: return true
    @Test
    public void TestAdminAddClass() throws Exception {

        SCRS testScrs = new SCRSImpl();
        ShibbolethAuth sbAuth = new ShibbolethAuth();
        Token tokenGenerator = sbAuth.tokenGenerator("John196", "password");
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

        }
    }

    // Description: test student can't add class
    // Input:arguments of adminAddClass()
    // Output: return false, incorrect user
    @Test
    public void TestStudentCantAddClass() throws Exception {

        SCRS testScrs = new SCRSImpl();
        Token myTokenStu = ((SCRSImpl) testScrs).userLogin("YUWEI1005", "mypassword");

        if (myTokenStu != null) {

            assertEquals(false,
                    testScrs.adminAddClass(myTokenStu, 666, "AdvancedDatabase", 2, 25, "Fall2015", 206, "09/01/2014",
                            "12/20/2014", "9:00", "10:30", "Tu,Th", "KHKH110", "Lecture", "No", "Databases", "CS"));

        }
    }

    // Description: test admin can't add class which does not have all strings
    // Input:arguments of adminAddClass()
    // Output: return false, throw SQLException
    @Test
    public void TestAdminCantAddClassNameNotWithAllString() throws Exception {

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

    // Description: test BOTH type can add class
    // Input:arguments of adminAddClass()
    // Output: return true
    @Test
    public void TestBOTHCanAddClass() throws Exception {

        SCRS testScrs = new SCRSImpl();
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

    // Description: test undefined type can't add class
    // Input:arguments of adminAddClass()
    // Output: return false, incorrect user
    @Test
    public void TestUNDEFINEDCantAddClass() throws Exception {

        SCRS testScrs = new SCRSImpl();
        Token tokenGenerator = ((SCRSImpl) testScrs).userLogin("amy2001", "password");
        System.out.println("CHECK FROM TESTS " + tokenGenerator.type);

        if (tokenGenerator != null) {

            assertEquals(false,
                    testScrs.adminAddClass(tokenGenerator, 555, "Advanced Database", 3, 25, "Fall2015", 206,
                            "09/01/2014", "12/20/2014", "9:00", "10:30", "Tu,Th", "KHKH110", "Lecture", "No",
                            "Databases", "CS"));

        }
    }

    // Description: test admin delete class from course table and
    // instructorandcourse table
    // Input:arguments of adminDeleteClass()
    // Output: return true
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

    // Description: test student can't delete class
    // Input:arguments of adminDeleteClass()
    // Output: return false, incorrect user
    @Test
    public void TestStudentCantDeleteClass() throws ClassNotFoundException, SQLException {
        SCRS testScrs = new SCRSImpl();
        Token tokenGenerator = ((SCRSImpl) testScrs).userLogin("amy2001", "password");
        System.out.println("CHECK FROM TESTS " + tokenGenerator.type);

        if (tokenGenerator != null) {

            assertEquals(false, testScrs.adminDeleteClass(tokenGenerator, 777));

        }
    }

    // Description: test admin can edit class info in course table
    // Input:arguments of adminEditClass()
    // Output: return true
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

    // Description: test student can't edit class
    // Input:arguments of adminEditClass()
    // Output: return false, incorrect user type
    @Test
    public void TestStudentCantEditClass() throws ClassNotFoundException, SQLException {
        SCRS testScrs = new SCRSImpl();
        Token tokenGenerator = ((SCRSImpl) testScrs).userLogin("amy2001", "password");
        System.out.println("CHECK FROM TESTS " + tokenGenerator.type);
        if (tokenGenerator != null) {

            assertEquals(false, testScrs.adminEditClass(tokenGenerator, 888, "Advanced Database", 3, 206, "09/01/2014",
                    "09/01/2015", "9:00", "10:30", "Tu,Th", "KHKH110", "Lecture", "No", "Databases", "CS"));

        }
    }

    // Description: test admin can add student to class in studentandcourse
    // table
    // Input:arguments of adminAddStudentToClass()
    // Output: return true
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

    // Description: test student can't add student to class
    // Input:arguments of adminAddStudentToClass()
    // Output: return false, incorrect user
    @Test
    public void TestStudentCantAddStudentToClass() throws ClassNotFoundException, SQLException {
        SCRS testScrs = new SCRSImpl();
        Token tokenGenerator = ((SCRSImpl) testScrs).userLogin("amy2001", "password");
        System.out.println("CHECK FROM TESTS " + tokenGenerator.type);
        if (tokenGenerator != null) {

            assertEquals(false, testScrs.adminAddStudentToClass(tokenGenerator, 1006, 888, "A-F", "2014Fall"));

        }
    }

    // Description: test admin edit studentandcourse info
    // Input:arguments of adminEditStudentRegisteredClass()
    // Output: return true
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

    // Description: test student can't edits student registered class
    // Input:arguments of adminEditStudentRegisteredClass()
    // Output: return false, incorrect user
    @Test
    public void TestStudentCantEditStudentRegisteredClass() throws ClassNotFoundException, SQLException {
        SCRS testScrs = new SCRSImpl();
        Token myTokenStu = ((SCRSImpl) testScrs).userLogin("YUWEI1005", "mypassword");
        if (myTokenStu != null) {

            assertEquals(false, testScrs.adminEditStudentRegisteredClass(myTokenStu, 1006, 888, "S/N", "Spring 2015"));

        }
    }

    // Description: test admin can drop student in studentandcourse table
    // Input:arguments of adminDropStudentRegisteredClass()
    // Output: return true
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

    // Description: test student can't drop registered class
    // Input:arguments of adminDropStudentRegisteredClass()
    // Output: return false, incorrect user
    @Test
    public void TestStudentCantDropStudentRegisteredClass() throws ClassNotFoundException, SQLException {
        SCRS testScrs = new SCRSImpl();
        Token myTokenStu = ((SCRSImpl) testScrs).userLogin("YUWEI1005", "mypassword");
        System.out.println(myTokenStu.type);
        if (myTokenStu != null) {

            assertEquals(false, testScrs.adminDropStudentRegisteredClass(myTokenStu, 1006, 888));

        }
    }

}
