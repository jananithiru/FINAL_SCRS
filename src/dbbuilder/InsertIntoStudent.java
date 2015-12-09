package dbbuilder;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import scrs.Constants;
import scrs.DBCoordinator;
import scrs.Constants.PrimitiveDataType;

public class InsertIntoStudent {
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

        // STUDENT
        // String sqlStudent01 = "INSERT INTO STUDENT (ID, FIRSTNAME, LASTNAME,
        // DATEOFBIRTH,TYPE,GENDER,ADVISOR,CREDITS,DEPARTMENT) "
        // + "VALUES (888, 'Alice1', 'Liddell', 09/01/2014 ,'Master', 'Female',
        // 'Mad Hatter', 1, 'CS');";

        System.out.println("Records created successfully");
    }

    public static void insertStudent(int id, String firstName, String lastName, String dateofBirth, String type,
            String gender, String advisor, int credits, String department) {
        insertShibbolethauth(id + 1, firstName + id, "mypassword", id, "STUDENT");
        DBCoordinator dbCoordinator = new DBCoordinator();
        String sqlStr = "INSERT INTO STUDENT VALUES(?,?,?,?,?,?,?,?,?)";

        ArrayList<String> dataList = new ArrayList<String>();
        dataList.add(Integer.toString(id));
        dataList.add(firstName);
        dataList.add(lastName);
        dataList.add(dateofBirth);
        dataList.add(type);
        dataList.add(gender);
        dataList.add(advisor);
        dataList.add(Integer.toString(credits));
        dataList.add(department);

        ArrayList<PrimitiveDataType> typeList = new ArrayList<Constants.PrimitiveDataType>();
        typeList.add(PrimitiveDataType.INT);
        typeList.add(PrimitiveDataType.STRING);
        typeList.add(PrimitiveDataType.STRING);
        typeList.add(PrimitiveDataType.DATE);
        typeList.add(PrimitiveDataType.STRING);
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

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        insertStudent(1005, "YUWEI", "WANG", "08/02/1991", "Master", "Female", "Jake", 3, "CS");

    }

}
