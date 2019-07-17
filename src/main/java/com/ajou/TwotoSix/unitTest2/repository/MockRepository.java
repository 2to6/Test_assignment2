package com.ajou.TwotoSix.unitTest2.repository;


import com.ajou.TwotoSix.unitTest2.domain.Student;

public interface MockRepository {


    Student findByName(String name);

    void deleteStudent(Student deleteStudentName);
}
