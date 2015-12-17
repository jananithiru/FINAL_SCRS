package scrs;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UtilMethods { // TODO: Rename this class after checking with people
    /**
     * This method is used to convert a list of ArrayLists of Objects to a list
     * of ArrayLists of Strings.
     * 
     * @param objList
     * @return A list of ArrayList of Strings.
     */
    public static List<ArrayList<String>> convertObjListToStringList(List<ArrayList<Object>> objList) {
        List<ArrayList<String>> result = new ArrayList<ArrayList<String>>();

        Iterator<ArrayList<Object>> outerIter = objList.iterator();
        while (outerIter.hasNext()) {
            ArrayList<String> metaRes = new ArrayList<String>();
            Iterator<Object> innerIter = outerIter.next().iterator();
            while (innerIter.hasNext()) {
                metaRes.add(innerIter.next().toString());
            }
            result.add(metaRes);
        }
        return result;
    }

    /**
     * This method is used to determine if some date is within the registration
     * time frame for some specific course.
     * 
     * @param currentDate
     *            contains the date of registration.
     * @param courseTerm
     *            contains the course term (e.g. "Spring2015") of the course you
     *            wish to check the time frame of.
     * @return a boolean value. True if it is within time frame, else false.
     */
    @SuppressWarnings("deprecation")
    public static boolean isInTimeFrame(Date currentDate, String courseTerm) {
        int startYear, endYear;
        int startMonth, endMonth;
        int startDay = 1;
        int endDay = 8; 

        // TODO init these
        int courseYear = Integer.valueOf((String) courseTerm.subSequence(courseTerm.length() - 4, courseTerm.length()));

        if (courseTerm.contains("Spring")) {
            startMonth = 12;
            startYear = courseYear - 1;
            endMonth = 2; 
            endYear = courseYear;
        } else {
            startMonth = 7;
            startYear = courseYear;
            endMonth = 9; 
            endYear = courseYear;
        }

        Date startDate = new Date(startYear, startMonth, startDay);
        Date endDate = new Date(endYear, endMonth, endDay);

        return (currentDate.after(startDate) && currentDate.before(endDate));
    }

    public static boolean isAlphaNumeric(String s) {
        String pattern = "^[a-zA-Z0-9]*$";
        return (s.matches(pattern));
    }

    public static boolean isString(String s) {
        String pattern = "^[a-zA-Z]*$";
        return (s.matches(pattern));
    }

}
