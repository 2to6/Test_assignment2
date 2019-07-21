package com.ajou.TwotoSix.unitTest2.service;

import com.ajou.TwotoSix.unitTest2.domain.Student;
import com.ajou.TwotoSix.unitTest2.repository.MockRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MockService {
    private final MockRepository mockRepository;

    public MockService(MockRepository mockRepository) {
        this.mockRepository = mockRepository;
    }


    public Student findBystudentId(String studentId){
        Student student = mockRepository.findBystudentId(studentId);
        return student;
    }

    public double updateGPAByStudentId(String studentId, double updatedGPA) {
        Student student = findBystudentId(studentId);
        student.setGPA(updatedGPA);
        return student.getGPA();
    }

    //When() example 2
    public List<String> getList(String name, int age){
        List<String> result = new ArrayList<>();
        //do something code
        return result;
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


    public List<Student> searchStudentByName(List<Student> studentList, String filterByName) {
        return studentList.stream()
                .filter(student -> student.getName().contains(filterByName))
                .collect(Collectors.toList());
    }
}
