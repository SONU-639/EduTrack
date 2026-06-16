package studentmanagementsystem;

import java.sql.*;
import java.util.Scanner;

public class DeleteStudent {
//	 * PURPOSE:
//		 * Remove a student record from database.
    public static void deleteStudent() {

        Scanner sc =new Scanner(System.in);

        try {
        	 // Database connection
            Connection con = DBConnection.getConnection();
            // Ask Student ID
            System.out.print("Student ID: ");

            String id =sc.nextLine();

            Statement stmt =con.createStatement();

            stmt.executeUpdate("DELETE FROM students WHERE student_id='" + id + "'");

            System.out.println("Deleted successfully");

            con.close();

        } catch(Exception e) {

            System.out.println(e);
           sc.close();
        }
    }
}
