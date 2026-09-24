package com.campus.model;
pulbic class ScholarshipStudent extend Student{
    private double  ScholarshipPercentage;

    public ScholarshipStudent(int studentid,String studentname,int age,String department,int[]marks,double ScholarshipPercentage){
      super(studentid,studentname,age,department,marks);
      this.ScholarshipPercentage = ScholarshipPercentage;  
    }
    //getters and setters
    public double getScholarshipPercentage(){
        
    }
}