package scrs;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import scrs.ShibbolethAuth.Token;

public class MainRunner {

    public static void main(String[] args) {

        // INITIALIZATION
        SCRS testScrs = new SCRSImpl();

        // LOGIN
        ShibbolethAuth sbAuth = new ShibbolethAuth();
        Token myToken = ((SCRSImpl) testScrs).userLogin("YUWEI1005", "mypassword");

        if (myToken != null) {
            List<ArrayList<String>> testResult = testScrs.queryStudentPersonalData(myToken, myToken.id);
            Iterator<ArrayList<String>> printIter = testResult.iterator();
            while (printIter.hasNext()) {
                Iterator<String> printInnerIter = printIter.next().iterator();
                while (printInnerIter.hasNext()) {
                    System.out.print(printInnerIter.next() + "\t");
                }
            }
        }

        // Add Class
        if (myToken != null) {
            // where to judge the token type??? inside the function or in
            // the test
            assertEquals(true, testScrs.studentAddClass(myToken, 888, "A-F", "Fall2015"));

        }

    }

}
