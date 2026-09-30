package com.campus.services;

import java.util.List;
import java.util.ArrayList;

public class StudentService {
    private static List<String> student = new ArrayList<>();
    //get student 
    public StudentService(){
        student.add("101 - Bill - Java ");
        student.add("102 - Steve - python");
        student.add("103 - Ram - C++");
  
    }
    public List<String> getStudents(){
        return student;
    }

//add student 
    public void addstudent(String name,String course){
        student.add(String.valueOf(student.size()+101) + " - " + name + " - " + course);
    }
}