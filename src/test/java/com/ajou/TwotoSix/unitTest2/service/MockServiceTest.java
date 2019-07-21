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
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MockServiceTest {

    @Mock
    private MockRepository mockRepository;

    @InjectMocks
    private MockService mockService;

    private List<Student> students = new ArrayList<>();

    @Test
    public void GPA가_기준미달일때_false인지_테스트(){ //김도연
        Student student = mock(Student.class);
        when(student.getGPA()).thenReturn(3.49);
        boolean result = mockService.ScholarshipVaild(student);
        assertTrue( result == false);
    }

    @Test
    public void GPA가_기준충족일때_true인지_테스트() { //김도연
        Student student = mock(Student.class);
        when(student.getGPA()).thenReturn(3.5);
        boolean result = mockService.ScholarshipVaild(student);
        assertTrue(result == true);
    }

    @Test
    public void GPA_업데이트후_결과반영이_정상작동하는지_테스트(){ //김도연
        Student student = mockService.addStudent("김도연","201720711",5,"Software", 3.2);
        //given 선행조건 - GAP업데이트
        given(mockRepository.updateGPAByStudentId("201720711",3.7)).willReturn(3.7);
        //when 수행 - 장학금 기준 검사
        boolean result = mockService.ScholarshipVaild(student);
        System.out.println(result);
        //then 결과
        //verify(mockRepository, atLeast(1)).updateGPA(any(),anyDouble());
        //assertTrue(result == true);

    }


}
