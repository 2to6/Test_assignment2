package com.ajou.TwotoSix.unitTest2.domain;


import lombok.AllArgsConstructor;
import lombok.Data;


@AllArgsConstructor
@Data
public class Student {
    private String name;
    private String studentId;
    private int currentSemester;
    private String major;
    private double GPA;
}
