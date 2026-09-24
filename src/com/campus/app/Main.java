package com.campus.app;
import com.campus.model.Student;
import com.campus.service.StudentService;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the student ID: ");
        int studentid = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter the student name: ");
        String studentname = sc.nextLine();

        System.out.print("Enter the student age: ");
        int age = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter the student department: ");
        String department = sc.nextLine();

        System.out.print("Enter the number of subjects: ");
        int n = sc.nextInt();

        int[] marks = new int[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Enter mark for subject " + (i + 1) + ": ");
            marks[i] = sc.nextInt();
        }

        Student student = new Student(
                studentid,
                studentname,
                age,
                department,
                marks
        );

        student.displayStudentInfo(true);

        student.displayStudentCount();

        StudentService studentService = new StudentService();

        studentService.displayReportCard(student);

        sc.close();
    }
}

