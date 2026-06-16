package studentmanagementsystem;

import java.util.Scanner;

public class MainMenu {
  
 // Main method starts program execution
    public static void main(String[] args) {
    	// Scanner object used throughout the project
    	  
    	Scanner sc = new Scanner(System.in);
        int choice = 0;

        while(choice != 7) {

            System.out.println("\n====== EDUTRACK SYSTEM ======");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Search Student");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Export Data");
            System.out.println("7. Exit");
            System.out.print("Enter Choice: ");

            String input = sc.nextLine();

            try {
                choice = Integer.parseInt(input);

                switch(choice) {

                    case 1:
                        AddStudent.addStudent();
                        break;

                    case 2:
                        ViewStudents.viewStudents();
                        break;

                    case 3:
                        SearchStudent.searchStudent();
                        break;

                    case 4:
                        UpdateStudent.updateStudent();
                        break;

                    case 5:
                        DeleteStudent.deleteStudent();
                        break;

                    case 6:
                        ExportData.exportData();
                        break;

                    case 7:
                        System.out.println("Thank You For Using EduTrack!");
                        break;

                    default:
                        System.out.println("❌ Invalid Choice! Please enter 1-7.");
                }

            } catch(NumberFormatException e) {
                System.out.println("❌ Invalid Input! Please enter numbers only.");
            }
        }
        sc.close();   }
   
}