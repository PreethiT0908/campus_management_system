package com.campus.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.campus.model.Student;
import com.campus.services.StudentService;

@WebServlet("/students")
public class StudentServlet extends HttpServlet {

    private final StudentService studentService = new StudentService();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws IOException, ServletException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "list": {
                var students = studentService.getStudents();
                request.setAttribute("students", students);
                RequestDispatcher listDispatcher = request.getRequestDispatcher("/student.jsp");
                listDispatcher.forward(request, response);
                break;
            }
            case "edit": {
                int id = Integer.parseInt(request.getParameter("id"));
                Student student = studentService.getStudentById(id);
                request.setAttribute("student", student);
                RequestDispatcher editDispatcher = request.getRequestDispatcher("/update-student.jsp");
                editDispatcher.forward(request, response);
                break;
            }
            case "delete": {
                int id = Integer.parseInt(request.getParameter("id"));
                studentService.deleteStudent(id);
                response.sendRedirect(request.getContextPath() + "/students");
                break;
            }
            default: {
                response.sendRedirect(request.getContextPath() + "/students");
                break;
            }
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws IOException, ServletException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "add";
        }

        switch (action) {
            case "add": {
                String name = request.getParameter("name");
                String department = request.getParameter("department");
                int age = Integer.parseInt(request.getParameter("age"));
                studentService.addStudent(name, department, age);
                response.sendRedirect(request.getContextPath() + "/students");
                break;
            }
            case "update": {
                int id = Integer.parseInt(request.getParameter("id"));
                String name = request.getParameter("name");
                String department = request.getParameter("department");
                int age = Integer.parseInt(request.getParameter("age"));
                studentService.updateStudent(id, name, department, age);
                response.sendRedirect(request.getContextPath() + "/students");
                break;
            }
            case "delete": {
                int id = Integer.parseInt(request.getParameter("id"));
                studentService.deleteStudent(id);
                response.sendRedirect(request.getContextPath() + "/students");
                break;
            }
            default: {
                response.sendRedirect(request.getContextPath() + "/students");
                break;
            }
        }
    }

    @Override
    public void doPut(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String department = request.getParameter("department");
        int age = Integer.parseInt(request.getParameter("age"));
        studentService.updateStudent(id, name, department, age);
        response.sendRedirect(request.getContextPath() + "/students");
    }

    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        studentService.deleteStudent(id);
        response.sendRedirect(request.getContextPath() + "/students");
    }
}