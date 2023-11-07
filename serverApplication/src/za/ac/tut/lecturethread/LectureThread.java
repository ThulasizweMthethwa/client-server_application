package za.ac.tut.lecturethread;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import za.ac.tut.student.Student;
import za.ac.tut.studentmanager.StudentManager;

public class LectureThread implements Runnable {

    private StudentManager studMan;
    private DataOutputStream out;
    private DataInputStream in;

    public LectureThread() {
        studMan = null;
    }

    public LectureThread(StudentManager studMn, Socket connecter) throws IOException {
        this.studMan = studMn;
        out = new DataOutputStream(connecter.getOutputStream());
        in = new DataInputStream(connecter.getInputStream());
    }

    private String getUserOption() {

        String menu = "Choose an operation below\n"
                + "-----------------------------------------------\n"
                + "1 - To add student on class list\n"
                + "2 - Get a specific student on the list\n"
                + "3 - To update student marks\n"
                + "4 - To remove student on the list\n"
                + "5 - To get all student from the class list\n"
                + "6 - To exit the program.\n"
                + "\nYour option: ";
        //int option = new Scanner(System.in).nextInt();
        return menu;
    }

    private int getOption() throws IOException {
        String optionstr;
        out.writeUTF(getUserOption());
        out.flush();
        optionstr = in.readUTF();
        System.out.println(optionstr);

        int option = Integer.parseInt(optionstr);

        return option;
    }

    @Override
    public void run() {

        int option;
        try {
            option = getOption();
        } catch (IOException ex) {
            Logger.getLogger(LectureThread.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        while (option != 6) {
            //synchronized (this.studMan) {
                try {
                    if (option == 1) {
                        out.writeUTF("Enter student number: ");
                        out.flush();
                        int studentNum = Integer.parseInt(in.readUTF());
                        out.writeUTF("Enter student name: ");
                        out.flush();
                        String name = in.readUTF();
                        out.writeUTF("Enter student formative mark: ");
                        out.flush();
                        double formative = Double.parseDouble(in.readUTF());
                        out.writeUTF("Eter student summative: ");
                        out.flush();
                        double summative = Double.parseDouble(in.readUTF());

                        try {
                            this.studMan.addStudent(new Student(studentNum, name, formative, summative));
                        } catch (SQLException ex) {
                            System.err.println("Failed to add the student to the database...");
                        }
                    } else if (option == 3) {
                        out.writeUTF("Enter student number: ");
                        out.flush();
                        
                        int studNum = Integer.parseInt(in.readUTF());
                        //int studNum = new Scanner(System.in).nextInt();
                        out.writeUTF("Enter new student mark: ");
                        out.flush();
                        
                        double studentMark = Double.parseDouble(in.readUTF());
                        //double studentMark = new Scanner(System.in).nextDouble();

                        try {
                            studMan.updateStudentMark(studNum, studentMark);
                            out.writeUTF("Student mark is successfully updated");
                        } catch (SQLException ex) {
                            out.writeUTF("Failed to update the student on a database.");
                        }

                    } else if (option == 2) {
                        out.writeUTF("Enter student number: ");
                        out.flush();

                        String studentNumber = in.readUTF();

                        int studNum = Integer.parseInt(studentNumber);

                        try {
                            Student student = studMan.getStudent(studNum);
                            out.writeUTF(student.toString());
                            out.flush();

                        } catch (SQLException ex) {
                            ex.printStackTrace();
                            out.writeUTF("Failed to get the student on a database.");
                        }
                    } else if (option == 4) {
                        out.writeUTF("Enter student number: ");
                        out.flush();

                        String studNumInput = in.readUTF();
                        //int studNum = new Scanner(System.in).nextInt();
                        int studNum = Integer.parseInt(studNumInput);
                        try {
                            studMan.removeStudent(studNum);
                            out.writeUTF("Student removed");

                        } catch (SQLException ex) {
                            out.writeUTF("Failed to get the student on a database.");
                        }
                    } else if (option == 5) {

                        try {
                            List<Student> listOfStudents = studMan.dispalyClassList();
                            String studentList = "";
                            for (Student student : listOfStudents) {

                                studentList += student.toString() + "\n";
                            }
                            out.writeUTF(studentList);

                        } catch (SQLException ex) {
                            System.err.println("Failed to get the student on a database.");
                        }
                    } else if (option > 6 || option < 1) {
                        out.writeUTF("The option " + option + " is not in the requared range, please re enter the correct option");
                        //continue;
                    }
                   // studMan.notifyAll();
                    
                    option = getOption();
                    
                } catch (IOException ex) {
                    System.err.println("");
                    ex.printStackTrace();

                }
           // }
//            try {
//
//                option = getOption();
//            } catch (IOException ex) {
//                ex.printStackTrace();
//                return;
//            }
        }

    }

}
