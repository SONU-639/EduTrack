package studentmanagementsystem;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Scanner;

public class AddStudent {

    public static void addStudent() {
    	   // Scanner object for user input
        Scanner sc= new Scanner(System.in);

        try {
        	   // Get database connection
            Connection con = DBConnection.getConnection();
            // Read student details
            System.out.print("Student ID: ");
            String id = sc.nextLine();

            System.out.print("Name: ");
            String name = sc.nextLine();

            System.out.print("Course: ");
            String course = sc.nextLine();

            System.out.print("Semester: ");
            int semester = Integer.parseInt(sc.nextLine());

            System.out.print("Grade: ");
            String grade = sc.nextLine();

            System.out.print("Email: ");
            String email = sc.nextLine();
            // SQL query to insert student
            String query =
                    "INSERT INTO students VALUES('" +
                            id + "','" +
                            name + "','" +
                            course + "'," +
                            semester + ",'" +
                            grade + "','" +
                            email + "')";

            Statement stmt =con.createStatement();

            stmt.executeUpdate(query);

            System.out.println("Student Added successfully");
            // Close database connection
            con.close();

        } catch(Exception e) {
            System.out.println(e);
            sc.close();
        }
        
    }
}