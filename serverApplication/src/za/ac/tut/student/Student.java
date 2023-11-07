package za.ac.tut.student;

public class Student {
   private int studentNumber;
   private String studentName;
   private double formative;
   private double summative;

    public Student() {
    }

    public Student(int studentNumber, String studentName, double formative, double summative) {
        this.studentNumber = studentNumber;
        this.studentName = studentName;
        this.formative = formative;
        this.summative = summative;
    }

    public int getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(int studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public double getFormative() {
        return formative;
    }

    public void setFormative(double formative) {
        this.formative = formative;
    }

    public double getSummative() {
        return summative;
    }

    public void setSummative(double summative) {
        this.summative = summative;
    }

    @Override
    public String toString() {
        return "Student{" + "studentNumber=" + studentNumber + ", studentName=" + studentName + ", formative=" + formative + ", summative=" + summative + '}';
    }
   
   
}
