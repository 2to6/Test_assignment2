package com.ajou.TwotoSix.unitTest2.repository;

import com.ajou.TwotoSix.unitTest2.domain.Student;

public interface MockRepository {


    Student findBystudentId(String studentId);

    double updateGPAByStudentId(String StudentId, double updatedGPA);

    Student findByStudentId(String studentId);

    boolean ScholarshipVaild(Student student);

    Student findByName(String name);

    void deleteStudent(Student deleteStudentName);
}
