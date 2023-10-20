package coms363;
import java.sql.*;

public class CreateTables {

	public static Connection connect = null;
	
    public static void main(String[] args){

        try {
            //Set up connection parameters
            String userName = "coms363";
            String password = "password";
            String dbServer = "jdbc:mysql://localhost:3306/sys";
            
            //Set up connection
            connect = DriverManager.getConnection(dbServer,userName,password);
            System.out.println("Connection established!");
        } catch(Exception e) {
            System.out.println("Connection failed");
        }

        Statement stmt = null;
        try {
            stmt = connect.createStatement();
            stmt.addBatch("CREATE TABLE Students(\r\n" +
                    "   snum integer NOT NULL,\r\n"+
                    "   ssn integer,\r\n"+
                    "   name varchar(10),\r\n"+
                    "   gender varchar(1),\r\n"+
                    "   dob datetime,\r\n"+
                    "   c_addr varchar(20),\r\n"+
                    "   c_phone varchar(10),\r\n"+
                    "   p_addr varchar(20),\r\n"+
                    "   p_phone varchar(10),\r\n"+
                    "   primary key (ssn),\r\n"+
                    "   unique key (snum)"+ ");\r\n"
            );
                    stmt.addBatch("CREATE TABLE Departments(\r\n"+
                    "code integer,\r\n"+
                    "name varchar(50) NOT NULL,\r\n"+
                    "phone varchar(10),\r\n"+
                    "college varchar(20),\r\n"+
                    "primary key (code),\r\n"+
                    "unique key (name)\r\n"+
            ");\r\n");

            stmt.addBatch("CREATE TABLE Degrees(\r\n"+
                    "name varchar(50),\r\n"+
                    "level varchar(5),\r\n"+
                    "department_code integer,\r\n"+
                    "primary key (name,level),\r\n"+
                    "foreign key (department_code) references Departments(code)\r\n"+
            ");\r\n");

            stmt.addBatch("CREATE TABLE Courses(\r\n"+
                    "number integer,\r\n"+
                    "name varchar(50),\r\n"+
                    "description varchar(50),\r\n"+
                    "credithours integer,\r\n"+
                    "level varchar(20),\r\n"+
                    "department_code integer,\r\n"+
                    "primary key (number),\r\n"+
                    "foreign key (department_code) references Departments(code)\r\n"+
            ");\r\n");

            stmt.addBatch("CREATE TABLE Register(\r\n"+
                    "snum integer,\r\n"+
                    "course_number integer,\r\n"+
                    "regtime varchar(20),\r\n"+
                    "grade integer,\r\n"+
                    "primary key (snum, course_number),\r\n"+
                    "foreign key (snum) references Students(snum),\r\n"+
                    "foreign key (course_number) references Courses(number)\r\n"+
            ");\r\n");

           stmt.addBatch("CREATE TABLE Major(\r\n"+
                    "snum integer,\r\n"+
                    "name varchar(50),\r\n"+
                    "level varchar(5),\r\n"+
                    "primary key (snum,name,level),\r\n"+
                    "foreign key (snum) references Students(snum),\r\n"+
                    "foreign key (name,level) references Degrees(name,level)\r\n"+
            ");\r\n");

           stmt.addBatch("CREATE TABLE Minor(\r\n"+
                    "snum integer,\r\n"+
                    "name varchar(50),\r\n"+
                    "level varchar(5),\r\n"+
                    "primary key (snum,name,level),\r\n"+
                    "foreign key (snum) references Students(snum),\r\n"+
                    "foreign key (name,level) references Degrees(name,level)\r\n"+
            ");\r\n");

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {

            stmt.executeBatch();
            System.out.println("Tables Created");
        } catch (SQLException e) {
            
            e.printStackTrace();
        } finally {
            try {
                // Close connection
                if (stmt != null) {
                    stmt.close();
                }
                if (connect != null) {
                    connect.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
