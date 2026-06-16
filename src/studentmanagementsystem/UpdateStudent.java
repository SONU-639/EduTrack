package studentmanagementsystem;

import java.sql.*;
import java.util.Scanner;

/*
 * UpdateStudent Class
 * -------------------
 * Updates student information except Student ID.
 */

public class UpdateStudent {

    public static void updateStudent() {
    	 Scanner sc = new Scanner(System.in);
        try {

            // Database connection
            Connection con =DBConnection.getConnection();
            // Student to update
            System.out.print("Enter Student ID : ");

            String id =
                    sc.nextLine();
            // Show update options

            System.out.println(
                    "\n1. Name");
            System.out.println(
                    "2. Course");
            System.out.println(
                    "3. Semester");
            System.out.println(
                    "4. Grade");
            System.out.println(
                    "5. Email");

            System.out.print( "Choose Field : ");

            int choice =
                    sc.nextInt();

            sc.nextLine();
            // New value
            System.out.print( "Enter New Value : ");

            String value = sc.nextLine();

            String column = "";

            // Determine column to update
            switch(choice) {

                case 1:
                    column = "name";
                    break;

                case 2:
                    column = "course";
                    break;

                case 3:
                    column = "semester";
                    break;

                case 4:
                    column = "grade";
                    break;

                case 5:
                    column = "email";
                    break;
            }
            // Dynamic update query
            String query =
                    "UPDATE students SET "
                    + column +
                    "=? WHERE student_id=?";

            PreparedStatement ps =
                    con.prepareStatement(query);

            ps.setString(1, value);
            ps.setString(2, id);

            ps.executeUpdate();

            System.out.println(
                    "Record Updated Successfully");

            con.close();

        } catch(Exception e) {

            System.out.println(e);
            sc.close();
        }
    }
}