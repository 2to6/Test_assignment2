package com.ajou.TwotoSix.unitTest2.service;

import com.ajou.TwotoSix.unitTest2.domain.Student;
import com.ajou.TwotoSix.unitTest2.repository.MockRepository;

import java.util.List;
import java.util.stream.Collectors;

public class MockService {

    private MockRepository mockRepository;


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
