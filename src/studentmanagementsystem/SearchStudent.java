package studentmanagementsystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

/*
 * =====================================================
 * MODULE : SEARCH STUDENT
 *
 * PURPOSE:
 * Search and display a student's details using
 * Student ID.
 * =====================================================
 */

public class SearchStudent {
    public static void searchStudent() {
    	   // Scanner for user input
        Scanner sc = new Scanner(System.in);

        try {

            // Establish database connection
            Connection con = DBConnection.getConnection();

            // Ask user for Student ID
            System.out.print("Enter Student ID : ");
            String id = sc.nextLine();

            // SQL query to search student
            String query =
                    "SELECT * FROM students WHERE student_id=?";

            // Create PreparedStatement
            PreparedStatement ps =
                    con.prepareStatement(query);

            // Set Student ID
            ps.setString(1, id);

            // Execute query
            ResultSet rs = ps.executeQuery();

            // Check if student exists
            if(rs.next()) {

                System.out.println("\nStudent Found");

                System.out.println("ID : "
                        + rs.getString("student_id"));

                System.out.println("Name : "
                        + rs.getString("name"));

                System.out.println("Course : "
                        + rs.getString("course"));

                System.out.println("Semester : "
                        + rs.getInt("semester"));

                System.out.println("Grade : "
                        + rs.getString("grade"));

                System.out.println("Email : "
                        + rs.getString("email"));

            } else {

                System.out.println("Student Not Found");
            }

            // Close connection
            con.close();

        } catch(Exception e) {
            System.out.println(e);
        }
      
    }
}
