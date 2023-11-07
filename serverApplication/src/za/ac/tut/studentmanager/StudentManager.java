package za.ac.tut.studentmanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import za.ac.tut.student.Student;

public class StudentManager {

    private Connection connect;
    private PreparedStatement prepared;
    private ResultSet result;

    public StudentManager(String dburl, String userName, String password) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connect = DriverManager.getConnection(dburl, userName, password);
    }

    public List<Student> dispalyClassList() throws SQLException {
        String sqlStatement = "SELECT * FROM student_table";
        prepared = connect.prepareStatement(sqlStatement);

        result = prepared.executeQuery();
        List<Student> listOfStudents = new ArrayList<>();
        Student student;

        while (result.next()) {
            int studNum = result.getInt(1);
            String name = result.getString(2);
            double fa = result.getDouble(3);
            double sa = result.getDouble(4);

            student = new Student(studNum, name, fa, sa);
            listOfStudents.add(student);
        }
        prepared.close();

        return listOfStudents;
    }

    public void updateStudentMark(int studentId, double studentMark) throws SQLException {
        String sqlUpdate = "UPDATE student_table SET formative = ? WHERE student_num = ?";

        prepared = connect.prepareStatement(sqlUpdate);
        prepared.setDouble(1, studentMark);
        prepared.setInt(2, studentId);
        prepared.executeUpdate();
        prepared.close();
    }

    public Student getStudent(int studId) throws SQLException {
        String sqlQuery = "SELECT * FROM student_table WHERE student_num = ?";

        prepared = connect.prepareStatement(sqlQuery);
        prepared.setInt(1, studId);

        result = prepared.executeQuery();

        Student student = new Student();
        if (result.next()) {
            int studNum = result.getInt(1);
            String name = result.getString(2);
            double fa = result.getDouble(3);
            double sa = result.getDouble(4);

            student = new Student(studNum, name, fa, sa);
        }
        prepared.close();

        return student;
    }

    public void addStudent(Student s) throws SQLException {
        String insert = "INSERT INTO student_table VALUES(?,?,?,?)";

        prepared = connect.prepareStatement(insert);
        prepared.setInt(1, s.getStudentNumber());
        prepared.setString(2, s.getStudentName());
        prepared.setDouble(3, s.getFormative());
        prepared.setDouble(4, s.getSummative());

        prepared.executeUpdate();
        prepared.close();

    }

    public void removeStudent(int studentId) throws SQLException {
        String removeStatement = "DELETE FROM student_table WHERE student_num = ?";

        prepared = connect.prepareStatement(removeStatement);
        prepared.setInt(1, studentId);
        prepared.executeUpdate();
        prepared.close();

    }
}
