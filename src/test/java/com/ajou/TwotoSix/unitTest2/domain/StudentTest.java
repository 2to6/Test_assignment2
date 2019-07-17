package com.ajou.TwotoSix.unitTest2.domain;

import com.ajou.TwotoSix.unitTest2.repository.MockRepository;
import com.ajou.TwotoSix.unitTest2.service.MockService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StudentTest {
    @Mock
    private MockRepository mockRepository;

    @InjectMocks
    private MockService mockService;


    private List<Student> students = new ArrayList<>();


//
//    @Test
//    public void 학생이름을_이용해서_목록에서_학생을_삭제한다() {
//        Student student = mock(Student.class);
//        when(student.getName())
//    }

}