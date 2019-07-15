package com.ajou.TwotoSix.unitTest2.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Subject {
    private String subjectName; //과목 이름
    private String professorName; //교수 이름
    private int numberOfStudents; //수강 인원
}
