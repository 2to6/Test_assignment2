package com.ajou.TwotoSix.unitTest2.service;

import com.ajou.TwotoSix.unitTest2.domain.Subject;
import com.ajou.TwotoSix.unitTest2.repository.MockRepository;

import java.util.List;

public class MockService {

    private final MockRepository mockRepository;

    public MockService(MockRepository mockRepository) {
        this.mockRepository = mockRepository;
    }


    public List<Subject> findAllSubject() {
        return mockRepository.findAllSubject();
    }

    public Subject findBySubjectName(String subjectName){
        Subject subject = mockRepository.findBySubjectName(subjectName);
        return subject;
    }

    public Subject findByProfessorName(){
        Subject subject = mockRepository.findByProfessorName();
        return subject;
    }

    public Subject updateProfessorNameBySubjectName(String subjectName, String professorName){
        Subject subject = findBySubjectName(subjectName);
        subject.setSubjectName(professorName);

        return subject;
    }

}
