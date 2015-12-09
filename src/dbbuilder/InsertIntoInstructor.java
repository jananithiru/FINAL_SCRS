package dbbuilder;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import scrs.Constants;
import scrs.DBCoordinator;
import scrs.Constants.PrimitiveDataType;

public class InsertIntoInstructor {

    public static void insertShibbolethauth(int id, String X500Account, String X500Password, int userId,
            String userType) {

        DBCoordinator dbCoordinator = new DBCoordinator();

        String sqlStr = "INSERT INTO SHIBBOLETHAUTH VALUES(?,?,?,?,?)";

        ArrayList<String> dataList = new ArrayList<String>();

        dataList.add(Integer.toString(id));

        dataList.add(X500Account);

        dataList.add(X500Password);

        dataList.add(Integer.toString(userId));

        dataList.add(userType);

        ArrayList<PrimitiveDataType> typeList = new ArrayList<Constants.PrimitiveDataType>();

        typeList.add(PrimitiveDataType.INT);

        typeList.add(PrimitiveDataType.STRING);

        typeList.add(PrimitiveDataType.STRING);

        typeList.add(PrimitiveDataType.INT);

        typeList.add(PrimitiveDataType.STRING);

        try {

            dbCoordinator.insertData(sqlStr, dataList, typeList);

        } catch (ClassNotFoundException e) {

            e.printStackTrace();

        } catch (SQLException e) {

            e.printStackTrace();

        } catch (ParseException e) {

            e.printStackTrace();

        }
    }

    public static void addInstructorIntoLoginTables(int id, String firstName, String lastName, String date,
            String gender, String title, String department) {

        insertShibbolethauth(id + 10, firstName + Integer.toString(id), "password", id, "ADMIN");

        DBCoordinator dbCoordinator = new DBCoordinator();

        String sqlStr = "INSERT INTO INSTRUCTOR " + "(ID, FIRSTNAME, LASTNAME, DATEOFBIRTH, GENDER, TITLE, DEPARTMENT) "
                + "VALUES (?, ?, ?, ?, ?,?, ?);";

        ArrayList<String> dataList = new ArrayList<String>();
        dataList.add(Integer.toString(id + 10));
        dataList.add(firstName);
        dataList.add(lastName);
        dataList.add(date);
        dataList.add(gender);
        dataList.add(title);
        dataList.add(department);

        ArrayList<PrimitiveDataType> typeList = new ArrayList<Constants.PrimitiveDataType>();
        typeList.add(PrimitiveDataType.INT);
        typeList.add(PrimitiveDataType.STRING);
        typeList.add(PrimitiveDataType.STRING);
        typeList.add(PrimitiveDataType.DATE);
        typeList.add(PrimitiveDataType.STRING);
        typeList.add(PrimitiveDataType.STRING);
        typeList.add(PrimitiveDataType.STRING);

        try {
            dbCoordinator.insertData(sqlStr, dataList, typeList);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("insert data done!!");
    }

    public static void main(String args[]) {
        DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        int userId = 196;
        addInstructorIntoLoginTables(userId, "Gu", "Bruce", "09/01/1994", "Male", "Professor", "CS");
        SelectFromAdministrator.selectAll();
    }

}
