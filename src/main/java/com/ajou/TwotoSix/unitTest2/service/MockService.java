package com.ajou.TwotoSix.unitTest2.service;

import com.ajou.TwotoSix.unitTest2.domain.Student;
import com.ajou.TwotoSix.unitTest2.repository.MockRepository;

public class MockService {
    private final MockRepository mockRepository;

    public MockService(MockRepository mockRepository) {
        this.mockRepository = mockRepository;
    }

    public float updateGPAByStudentId(String studentId, float updatedGPA) {
        Student student = findBystudentId(studentId);
        student.setGPA(updatedGPA);
        return student.getGPA();
    }

    public Student findBystudentId(String studentId) {
        Student student = mockRepository.findByStudentId(studentId);
        return student;
    }
  
    public Student addStudent(String name, String studentId, int currentSemester, String major, float GPA){
        Student addStudent = new Student(name, studentId, currentSemester, major, GPA);
        return addStudent;
    }

    public boolean ScholarshipVaild(Student student){
        if(student.getGPA() >= 3.5) return true;
        return false;
    }
}
