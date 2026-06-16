package studentmanagementswing;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class StudentManagementSwing extends JFrame {

    // Database Connection Details
    String url = "jdbc:mysql://localhost:3306/students_schema";
    String username = "root";
    String password = "6So5i@#!U-63";

    JTextField txtId, txtName, txtCourse, txtSemester, txtGrade, txtEmail;
    JTable table;
    DefaultTableModel model;

    public StudentManagementSwing() {

        setTitle("EduTrack - Student Management System");
        setSize(1000, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Main Panel
        JPanel panel = new JPanel(new BorderLayout());

        // ==========================
        // TOP TITLE
        // ==========================
        JLabel title = new JLabel("EDUTRACK STUDENT MANAGEMENT SYSTEM",
                JLabel.CENTER);

        title.setFont(new Font("Arial", Font.BOLD, 24));

        panel.add(title, BorderLayout.NORTH);

        // ==========================
        // FORM PANEL
        // ==========================
        JPanel form = new JPanel(new GridLayout(6, 2, 10, 10));

        form.add(new JLabel("Student ID"));
        txtId = new JTextField();
        form.add(txtId);

        form.add(new JLabel("Name"));
        txtName = new JTextField();
        form.add(txtName);

        form.add(new JLabel("Course"));
        txtCourse = new JTextField();
        form.add(txtCourse);

        form.add(new JLabel("Semester"));
        txtSemester = new JTextField();
        form.add(txtSemester);

        form.add(new JLabel("Grade"));
        txtGrade = new JTextField();
        form.add(txtGrade);

        form.add(new JLabel("Email"));
        txtEmail = new JTextField();
        form.add(txtEmail);

        panel.add(form, BorderLayout.WEST);

        // ==========================
        // TABLE
        // ==========================
        model = new DefaultTableModel();

        model.addColumn("ID");
        model.addColumn("Name");
        model.addColumn("Course");
        model.addColumn("Semester");
        model.addColumn("Grade");
        model.addColumn("Email");

        table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);

        panel.add(scrollPane, BorderLayout.CENTER);

        // ==========================
        // BUTTONS
        // ==========================
        JPanel buttons = new JPanel();

        JButton addBtn = new JButton("Add");
        JButton viewBtn = new JButton("View");
        JButton searchBtn = new JButton("Search");
        JButton updateBtn = new JButton("Update");
        JButton deleteBtn = new JButton("Delete");
        JButton clearBtn = new JButton("Clear");

        buttons.add(addBtn);
        buttons.add(viewBtn);
        buttons.add(searchBtn);
        buttons.add(updateBtn);
        buttons.add(deleteBtn);
        buttons.add(clearBtn);

        panel.add(buttons, BorderLayout.SOUTH);

        // ==========================
        // BUTTON ACTIONS
        // ==========================

        addBtn.addActionListener(e -> addStudent());

        viewBtn.addActionListener(e -> loadStudents());

        searchBtn.addActionListener(e -> searchStudent());

        updateBtn.addActionListener(e -> updateStudent());

        deleteBtn.addActionListener(e -> deleteStudent());

        clearBtn.addActionListener(e -> clearFields());

        add(panel);

        setVisible(true);
    }

    // Database Connection
    private Connection getConnection() throws Exception {

        return DriverManager.getConnection(
                url,
                username,
                password
        );
    }

    // Add Student
    private void addStudent() {

        try {

            Connection con = getConnection();

            String query =
                    "INSERT INTO students VALUES(?,?,?,?,?,?)";

            PreparedStatement ps =
                    con.prepareStatement(query);

            ps.setString(1, txtId.getText());
            ps.setString(2, txtName.getText());
            ps.setString(3, txtCourse.getText());
            ps.setInt(4,
                    Integer.parseInt(txtSemester.getText()));
            ps.setString(5, txtGrade.getText());
            ps.setString(6, txtEmail.getText());

            ps.executeUpdate();

            JOptionPane.showMessageDialog(this,
                    "Student Added Successfully");

            loadStudents();

            con.close();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(this,
                    e.getMessage());
        }
    }

    // View Students
    private void loadStudents() {

        try {

            model.setRowCount(0);

            Connection con = getConnection();

            Statement st = con.createStatement();

            ResultSet rs =
                    st.executeQuery("SELECT * FROM students");

            while (rs.next()) {

                model.addRow(new Object[] {

                        rs.getString("student_id"),
                        rs.getString("name"),
                        rs.getString("course"),
                        rs.getInt("semester"),
                        rs.getString("grade"),
                        rs.getString("email")

                });
            }

            con.close();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(this,
                    e.getMessage());
        }
    }

    // Search Student
    private void searchStudent() {

        try {

            Connection con = getConnection();

            String query =
                    "SELECT * FROM students WHERE student_id=?";

            PreparedStatement ps =
                    con.prepareStatement(query);

            ps.setString(1, txtId.getText());

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                txtName.setText(rs.getString("name"));
                txtCourse.setText(rs.getString("course"));
                txtSemester.setText(
                        String.valueOf(rs.getInt("semester")));
                txtGrade.setText(rs.getString("grade"));
                txtEmail.setText(rs.getString("email"));

            } else {

                JOptionPane.showMessageDialog(this,
                        "Student Not Found");
            }

            con.close();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(this,
                    e.getMessage());
        }
    }

    // Update Student
    private void updateStudent() {

        try {

            Connection con = getConnection();

            String query =
                    "UPDATE students SET name=?,course=?,semester=?,grade=?,email=? WHERE student_id=?";

            PreparedStatement ps =
                    con.prepareStatement(query);

            ps.setString(1, txtName.getText());
            ps.setString(2, txtCourse.getText());
            ps.setInt(3,
                    Integer.parseInt(txtSemester.getText()));
            ps.setString(4, txtGrade.getText());
            ps.setString(5, txtEmail.getText());
            ps.setString(6, txtId.getText());

            ps.executeUpdate();

            JOptionPane.showMessageDialog(this,
                    "Student Updated Successfully");

            loadStudents();

            con.close();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(this,
                    e.getMessage());
        }
    }

    // Delete Student
    private void deleteStudent() {

        try {

            Connection con = getConnection();

            String query =
                    "DELETE FROM students WHERE student_id=?";

            PreparedStatement ps =
                    con.prepareStatement(query);

            ps.setString(1, txtId.getText());

            ps.executeUpdate();

            JOptionPane.showMessageDialog(this,
                    "Student Deleted Successfully");

            loadStudents();

            con.close();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(this,
                    e.getMessage());
        }
    }

    // Clear Fields
    private void clearFields() {

        txtId.setText("");
        txtName.setText("");
        txtCourse.setText("");
        txtSemester.setText("");
        txtGrade.setText("");
        txtEmail.setText("");
    }

    // Main Method
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() ->
                new StudentManagementSwing());
    }
}