package com.ajou.TwotoSix.unitTest2.service;

import com.ajou.TwotoSix.unitTest2.domain.Student;
import com.ajou.TwotoSix.unitTest2.repository.MockRepository;

import java.util.List;
import java.util.stream.Collectors;

public class MockService {
    private final MockRepository mockRepository;

    public MockService(MockRepository mockRepository) {
        this.mockRepository = mockRepository;
    }


    public Student findByStudentId(String studentId){
        Student student = mockRepository.findByStudentId(studentId);
        return student;
    }

    public double updateGPAByStudentId(String studentId, float updatedGPA) {
        Student student = findByStudentId(studentId);
        student.setGPA(updatedGPA);
        return student.getGPA();
    }
  
    public Student addStudent(String name, String studentId, int currentSemester, String major, float GPA){
        Student addStudent = new Student(name, studentId, currentSemester, major, GPA);
        return addStudent;
    }

    public boolean ScholarshipVaild(Student student){
        if(student.getGPA() >= 3.5) return true;
        return false;
    }


    public Student findByName(String name) {
        Student studentName = mockRepository.findByName(name);
        return studentName;
    }

    public void deleteStudent(String name) {
        Student deleteStudentName = findByName(name);
        mockRepository.deleteStudent(deleteStudentName);
    }

    public List<Student> searchStudentByName(List<Student> studentList, String filterByName) {
        return studentList.stream()
                .filter(student -> student.getName().equals(filterByName))
                .collect(Collectors.toList());
    }
}
