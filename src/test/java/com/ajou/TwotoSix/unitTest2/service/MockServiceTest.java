package com.ajou.TwotoSix.unitTest2.service;

import com.ajou.TwotoSix.unitTest2.domain.Student;
import com.ajou.TwotoSix.unitTest2.repository.MockRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MockServiceTest {

    @Mock
    private MockRepository mockRepository;

    @InjectMocks
    private MockService mockService;

    private List<Student> students = new ArrayList<>();

    @Test
    public void GPA가_한번설정되고_기준미달일때_false인지_확인(){ //김도연
        Student student = mock(Student.class);
        student.setGPA(3.49);
        verify(student, times(1)).setGPA(anyDouble());
        boolean result = mockService.ScholarshipVaild(student);
        assertTrue( result == false);

    }



}
