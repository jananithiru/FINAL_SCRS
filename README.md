# FINAL_SCRS


How to test admin functions

The admin has seven methods. The adminAddClass(), adminDeleteClass(), adminEditClas(), adminAddStudengToClass(), adminEditStudentRegisterClass(),adminDropStudentRegisteredClass()


We also have insertIntoAdmin, insertIntoStudent, insertIntoInstructor and setup db SQLiteJDBC.

We also have selectfromAdmin, selectfromStudent, selectfromCourse, selectfromStudentAndCourse, selectfromInstructorAndCourse, selectfromInstructor

Before we test admin functions, we need first run SQLiteJDBC to set up the database. It will delete all existing tables and create ne empty tables.
After that, we need to insert admin and insert instructor(because admin add class need to search the existing instructor id based on instructor last name )


Then, we can run testAdminAddClass(), it will add two class 777 and 888. When we finish the addclass, we can selectfromCourse and selectfromInstructorAndCourse, we can see the inserted class.


Then, we can run testAdminDeleteClass(), it will delete class 777. We can check it by running selectfromCourse and selectfromInstructorAndCourse


Then, we can run testAdminEditClass(), it will edit class 888 from credit 1 to 2. Then we can check again.


Then, we can run testAdminAddStudentToClass(), it will add a student to a specific class. You can check by run selectfromStudentAndCourse()


Then, we can run testAdminEditStudentRegisteredClass(), it will change the "A-F" to "S/N". We can check from StudentAndCourse table


Then, we can run testAdminDropStudentRegisteredClass(), it will drop this class. You can check the StudentAndCourse is empty, now.

Until now, all admin functions are tested. 
