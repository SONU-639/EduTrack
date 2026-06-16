package studentmanagementsystem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.io.FileWriter;
//*
//* PURPOSE:
//* Export all student records from MySQL database
//* into a text file.
//*
//* OUTPUT FILE:
//* StudentData.txt

public class ExportData {

    public static void exportData() {

        try {

            Connection con =
                    DBConnection.getConnection();
            // SQL statement
            Statement stmt =
                    con.createStatement();
            // Fetch all records
            ResultSet rs =
                    stmt.executeQuery(
                            "SELECT * FROM students");
            // Create output file
            FileWriter fw =
                    new FileWriter("StudentData.txt");
            // File heading
            fw.write("STUDENT MANAGEMENT SYSTEM\n");
            fw.write("=========================================\n");
            fw.write("ID\tNAME\tCOURSE\tSEM\tGRADE\tEMAIL\n");
            fw.write("=========================================\n");
            // Write records into file
            while(rs.next()) {

                fw.write(
                        rs.getString("student_id") + "\t" +
                        rs.getString("name") + "\t" +
                        rs.getString("course") + "\t" +
                        rs.getInt("semester") + "\t" +
                        rs.getString("grade") + "\t" +
                        rs.getString("email") + "\n"
                );
            }
            // Save file
            fw.close();
            con.close();

            System.out.println("Data Exported Successfully!");
            System.out.println("File Created: StudentData.txt");

        } catch(Exception e) {

            System.out.println(e);
        }
    }
}