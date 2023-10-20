package coms363;
import java.sql.*;


public class DropTables {
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
            stmt = connect.createStatement();

            stmt.addBatch("DROP TABLE `sys`.`courses`, `sys`.`degrees`, `sys`.`departments`, " +
                    "`sys`.`minor`, `sys`.`major`, `sys`.`register`, `sys`.`students`;");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        try{
            stmt.executeBatch();
            System.out.println("All Tables Dropped");
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
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