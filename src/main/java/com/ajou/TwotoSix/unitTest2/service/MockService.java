package com.ajou.TwotoSix.unitTest2.service;

import com.ajou.TwotoSix.unitTest2.domain.Student;

public class MockService {

    public Student addStudent(String name, String studentId, int currentSemester, String major, float GPA){
        Student addStudent = new Student(name, studentId, currentSemester, major, GPA);
        return addStudent;
    }

    public boolean ScholarshipVaild(Student student){
        if(student.getGPA() >= 3.5) return true;
        return false;
    }
}
