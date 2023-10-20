package coms363;
import java.sql.*;


public class Query {

    public static Connection connect = null;

    public static void main(String[] args) {

        try {
            //Set up connection parameters
            String userName = "coms363";
            String password = "password";
            String dbServer = "jdbc:mysql://localhost:3306/sys";
     
            //Set up connection
            connect = DriverManager.getConnection(dbServer, userName, password);
            System.out.println("Connection established!");
        } catch (Exception e) {
            System.out.println("Connection failed");
        }

        Statement stmt = null;
        try{
            ResultSet resultSet = null;
            String sqlQuery = "";
            String outputString = "Query 1: ";

            //query 1
            sqlQuery = "SELECT c.number AS course_number, c.name AS course_name, AVG(r.grade) AS average_grade " +
                    "FROM courses c " +
                    "INNER JOIN register r ON c.number = r.course_number " +
                    "GROUP BY c.number, c.name";
            stmt = connect.createStatement();
            resultSet = stmt.executeQuery(sqlQuery);
            while(resultSet.next())
            {
                outputString += "Course Names : "+resultSet.getString("course_name") + "....\t";
                outputString += "Course Numbers : "+resultSet.getInt("course_number") + "....\t";
                outputString += "Averages : "+resultSet.getInt("average_grade") + "....\t";
            }
            System.out.println(outputString);

            //query 2
            outputString = "Query 2: ";
            sqlQuery = "SELECT COUNT(DISTINCT s.snum) AS female_las_students_count " +
                    "FROM students s " +
                    "INNER JOIN major m ON s.snum = m.snum " +
                    "INNER JOIN degrees d ON m.name = d.name AND m.level = d.level AND d.department_code IN (SELECT code FROM departments WHERE college = 'LAS') " +
                    "WHERE s.gender = 'F' " +
                    "UNION " +
                    "SELECT COUNT(DISTINCT s.snum) AS female_las_students_count " +
                    "FROM students s " +
                    "INNER JOIN minor mi ON s.snum = mi.snum " +
                    "INNER JOIN degrees d ON mi.name = d.name AND mi.level = d.level AND d.department_code IN (SELECT code FROM departments WHERE college = 'LAS') " +
                    "WHERE s.gender = 'F'";
            resultSet = stmt.executeQuery(sqlQuery);
            while(resultSet.next())
            {
                outputString += "Count of Female LAS Students: "+resultSet.getInt("female_las_students_count") + "....";
                
            }
            System.out.println(outputString);

            //query 3
            outputString = "Query 3: Degrees with More Male Students: ";
            sqlQuery = "SELECT d.name AS degree_name " +
                    "FROM degrees d " +
                    "WHERE " +
                    "(SELECT COUNT(*) FROM major m INNER JOIN students s ON m.snum = s.snum WHERE m.name = d.name AND m.level = d.level AND s.gender = 'M') " +
                    ">" +
                    "(SELECT COUNT(*) FROM minor mi INNER JOIN students s ON mi.snum = s.snum WHERE mi.name = d.name AND mi.level = d.level AND s.gender = 'F')";
            resultSet = stmt.executeQuery(sqlQuery);
            
            while(resultSet.next())
            {
                outputString +=resultSet.getString("degree_name") + "..\t";
            }
            System.out.println(outputString);

            //query 4
            outputString = "Query 4: ";
            sqlQuery = "SELECT m.name AS major_name " +
                    "FROM major m " +
                    "INNER JOIN students s ON m.snum = s.snum " +
                    "WHERE s.dob = (SELECT MIN(dob) FROM students)";
            resultSet = stmt.executeQuery(sqlQuery);
            while(resultSet.next())
            {
                outputString += "Major of the Youngest Student: "+resultSet.getString("major_name") + "....";
               
            }
            System.out.println(outputString);


        } catch (SQLException ex) {
            ex.printStackTrace();
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
