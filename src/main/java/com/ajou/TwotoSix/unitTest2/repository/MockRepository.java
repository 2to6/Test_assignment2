package com.ajou.TwotoSix.unitTest2.repository;

import com.ajou.TwotoSix.unitTest2.domain.Subject;

import java.util.List;

public interface MockRepository {

    List<Subject> findAllSubject();

    Subject findBySubjectName(String subjectName);

    Subject findByProfessorName();

}
