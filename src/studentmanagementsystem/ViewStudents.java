package studentmanagementsystem;

import java.sql.*;
import java.util.Scanner;

public class ViewStudents {

    public static void viewStudents() {
    	 Scanner sc = new Scanner(System.in);
        try {
        	 
            // Connect to database
            Connection con =DBConnection.getConnection();

            // Create SQL statement
            Statement stmt =con.createStatement();

            // Fetch all student records
            ResultSet rs = stmt.executeQuery( "SELECT * FROM students");

            // Display heading
            System.out.println("ID\tNAME\tCOURSE\tSEM\tGRADE\tEMAIL");

            // Read records one by one
            while (rs.next()) {

                System.out.println(
                        rs.getString("student_id")
                                + "\t"
                                + rs.getString("name")
                                + "\t"
                                + rs.getString("course")
                                + "\t"
                                + rs.getInt("semester")
                                + "\t"
                                + rs.getDouble("grade")
                                + "\t"
                                + rs.getString("email"));
            }

            // Close connection
            con.close();

        } catch (Exception e) {
            System.out.println(e);
            sc.close();
            
        }
    }
}