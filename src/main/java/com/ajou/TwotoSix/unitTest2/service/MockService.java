package com.ajou.TwotoSix.unitTest2.service;

import com.ajou.TwotoSix.unitTest2.domain.Student;

public class MockService {
    public boolean ScholarshipVaild(Student student){
        if(student.getGPA() >= 3.5) return true;
        return false;
    }
}
