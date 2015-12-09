package scrs;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import scrs.ShibbolethAuth.Token;

public class SampleProgram {

    public static void main(String[] args) {

        // INITIALIZATION
        SCRS testScrs = new SCRSImpl();

        // LOGIN
        ShibbolethAuth sbAuth = new ShibbolethAuth();
        Token myToken = ((SCRSImpl) testScrs).userLogin("YUWEI1005", "mypassword");

        if (myToken != null) {
            assertEquals(null, testScrs.queryStudentPersonalData(myToken, myToken.id));
        }

        // Add Class
        if (myToken != null) {
            assertEquals(false, testScrs.studentAddClass(myToken, 888, "A-F", "Fall2015"));
        }

    }

}
