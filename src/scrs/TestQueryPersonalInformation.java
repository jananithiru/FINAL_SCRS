package scrs;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import scrs.*;
import scrs.ShibbolethAuth.Token;
import scrs.SCRSException;

public class TestQueryPersonalInformation {

    @Test
    public void testDisplayStudentDetails() {

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

    }

    @Test
    public void testDisplayInvalidUserType() {

        // INITIALIZATION

        SCRS testScrs = new SCRSImpl();

        ShibbolethAuth sbAuth = new ShibbolethAuth();
        Token myToken = ((SCRSImpl) testScrs).userLogin("John196", "password");

        if (myToken != null) //
        {
            List<ArrayList<String>> testResult = testScrs.queryStudentPersonalData(myToken, myToken.id);

            assertEquals(null, testResult);
            // Iterator<ArrayList<String>> printIter = testResult.iterator();
            // while (printIter.hasNext()) {
            // Iterator<String> printInnerIter = printIter.next().iterator();
            // while (printInnerIter.hasNext()) {
            // // metaRes.add(innerIter.next().toString());
            // System.out.print(printInnerIter.next() + "\t");
            // }
            // }
        }
    }

    @Test // (expected = SCRSException.class)
    public void testDisplayInvalidLoginCredentials() {

        // INITIALIZATION

        SCRS testScrs = new SCRSImpl();

        ShibbolethAuth sbAuth = new ShibbolethAuth();
        Token myToken = ((SCRSImpl) testScrs).userLogin("bob999!", "mypassword");

        if (myToken != null) //
        {
            List<ArrayList<String>> testResult = testScrs.queryStudentPersonalData(myToken, myToken.id);
            // List<ArrayList<String>> testResult =
            // testScrs.queryStudentPersonalData(myToken, myToken.id);
            assertEquals(null, testResult);
            // assertEquals(null, testResult);
            // Iterator<ArrayList<String>> printIter = testResult.iterator();
            // while (printIter.hasNext()) {
            // Iterator<String> printInnerIter = printIter.next().iterator();
            // while (printInnerIter.hasNext()) {
            // // metaRes.add(innerIter.next().toString());
            // System.out.print(printInnerIter.next() + "\t");
            // }
            // }
        }
    }

    @Test
    public void testDisplayValidAdminDetails() {

        SCRS testScrs = new SCRSImpl();

        ShibbolethAuth sbAuth = new ShibbolethAuth();
        Token myToken = ((SCRSImpl) testScrs).userLogin("John196", "password");

        if (myToken != null) //
        {
            List<ArrayList<String>> testResult = testScrs.queryAdminPersonalData(myToken);
            // assertEquals(null, testResult);

            Iterator<ArrayList<String>> printIter = testResult.iterator();
            while (printIter.hasNext()) {
                Iterator<String> printInnerIter = printIter.next().iterator();
                while (printInnerIter.hasNext()) {
                    System.out.print(printInnerIter.next() + "\t");
                }
            }
        }
    }
}
