package com.ajou.TwotoSix.unitTest2.repository;


import com.ajou.TwotoSix.unitTest2.domain.Student;

public interface MockRepository {

    Student findBystudentId(String studentId);

}
