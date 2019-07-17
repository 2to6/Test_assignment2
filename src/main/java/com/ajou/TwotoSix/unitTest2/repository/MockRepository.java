package com.ajou.TwotoSix.unitTest2.repository;


import com.ajou.TwotoSix.unitTest2.domain.Student;

public interface MockRepository {

    float updateGPA(Student student, float updatedGPA);

    Student findByStudentId(String studentId);
}
