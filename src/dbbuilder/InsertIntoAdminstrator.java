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

public class InsertIntoAdminstrator {

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

            // TODO Auto-generated catch block

            e.printStackTrace();

        } catch (SQLException e) {

            // TODO Auto-generated catch block

            e.printStackTrace();

        } catch (ParseException e) {

            // TODO Auto-generated catch block

            e.printStackTrace();

        }
    }

    //
    // public static void main(String args[]) {
    // DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
    // try {
    // addAdminIntoLoginTables(111, "John", "Mollberg",
    // new java.sql.Date((format.parse("08/24/1994").getTime())), "male", "CS");
    // } catch (ParseException e) {
    // // TODO Auto-generated catch block
    // e.printStackTrace();
    // }
    // }
    //

    public static void addAdminIntoLoginTables(int id, String firstName, String lastName, String date, String gender,
            String department) {

        insertShibbolethauth(id + 1, firstName + Integer.toString(id), "password", id, "ADMIN");

        DBCoordinator dbCoordinator = new DBCoordinator();

        String sqlStr = "INSERT INTO ADMINISTRATOR " + "(ID, FIRSTNAME, LASTNAME, DATEOFBIRTH, GENDER, DEPARTMENT) "
                + "VALUES (?, ?, ?, ?, ?, ?);";

        ArrayList<String> dataList = new ArrayList<String>();
        dataList.add(Integer.toString(id));
        dataList.add(firstName);
        dataList.add(lastName);
        dataList.add(date);
        dataList.add(gender);
        dataList.add(department);

        ArrayList<PrimitiveDataType> typeList = new ArrayList<Constants.PrimitiveDataType>();
        typeList.add(PrimitiveDataType.INT);
        typeList.add(PrimitiveDataType.STRING);
        typeList.add(PrimitiveDataType.STRING);
        typeList.add(PrimitiveDataType.DATE);
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

    public static void addBothIntoLoginTables(int id, String firstName, String lastName, String date, String gender,
            String department, String type, int credits) {

        insertShibbolethauth(id + 1, firstName + Integer.toString(id), "password", id, "BOTH");

        DBCoordinator dbCoordinator = new DBCoordinator();

        String sqlStr = "INSERT INTO ADMINISTRATOR " + "(ID, FIRSTNAME, LASTNAME, DATEOFBIRTH, GENDER, DEPARTMENT) "
                + "VALUES (?, ?, ?, ?, ?, ?);";

        ArrayList<String> dataList = new ArrayList<String>();
        dataList.add(Integer.toString(id));
        dataList.add(firstName);
        dataList.add(lastName);
        dataList.add(date);
        dataList.add(gender);
        dataList.add(department);

        ArrayList<PrimitiveDataType> typeList = new ArrayList<Constants.PrimitiveDataType>();
        typeList.add(PrimitiveDataType.INT);
        typeList.add(PrimitiveDataType.STRING);
        typeList.add(PrimitiveDataType.STRING);
        typeList.add(PrimitiveDataType.DATE);
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

        sqlStr = "INSERT INTO STUDENT " + "(ID, FIRSTNAME, LASTNAME, DATEOFBIRTH, TYPE, GENDER, CREDITS, DEPARTMENT) "
                + "VALUES (?, ?, ?, ?, ?,?, ?,?);";

        dataList = new ArrayList<String>();
        dataList.add(Integer.toString(id));
        dataList.add(firstName);
        dataList.add(lastName);
        dataList.add(date);
        dataList.add(type);
        dataList.add(gender);
        dataList.add(Integer.toString(credits));
        dataList.add(department);

        typeList = new ArrayList<Constants.PrimitiveDataType>();
        typeList.add(PrimitiveDataType.INT);
        typeList.add(PrimitiveDataType.STRING);
        typeList.add(PrimitiveDataType.STRING);
        typeList.add(PrimitiveDataType.DATE);
        typeList.add(PrimitiveDataType.STRING);
        typeList.add(PrimitiveDataType.STRING);
        typeList.add(PrimitiveDataType.INT);
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
        addAdminIntoLoginTables(userId, "John", "Mollberg", "08/24/1994", "Male", "CS");
        int bothId = 2000;
        addBothIntoLoginTables(bothId, "amy", "wang", "08/24/1991", "Female", "CS", "Master", 10);

        SelectFromAdministrator.selectAll();
    }

}
