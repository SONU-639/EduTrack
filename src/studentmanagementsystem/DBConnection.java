
package studentmanagementsystem;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    // Database URL
    // localhost = MySQL running on same computer
    // 3306 = default MySQL port number
    // students_schema = database name

    static String url = "jdbc:mysql://localhost:3306/students_schema";
    static String username = "root";
    static String password = "6So5i@#!U-63";

    public static Connection getConnection() {
    	  // Method used by all classes to establish connection
        try {
            return DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }
}