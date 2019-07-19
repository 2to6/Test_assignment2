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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class MockServiceTest {

    @Mock
    private MockRepository mockRepository;

    @InjectMocks
    private MockService mockService;


    private List<Student> students = new ArrayList<>();

    @Test
    public void 객체에서_이름을_가져오는_로직이_두번실행됐으면_패스(){
        Student student = mock(Student.class);
        student.getName();
        student.getName();
        verify(student, times(2)).getName();
    }


    @Test
    public void 이름으로_검색하면_학생정보를_리턴하고_1번이하로_호출되었는지_검증(){
        //given
        given(mockRepository.findByName("이안규")).willReturn(new Student("이안규","201421155",7,"Mathematics",3.6));
        //when
        Student student = mockRepository.findByName("이안규");
        //then
        verify(mockRepository,atLeast(1)).findByName(anyString());
        assertThat(student.getName(),is("이안규"));
    }

    @Test
    public void 학생이_장학금을_받을수있는지_판단후_검증(){
        Student student = mockService.addStudent("이안규","201421155",7,"Mathematics",4.0);
        //given
        given(mockRepository.ScholarshipVaild(student)).willReturn(true);
        //when
        boolean scholashipValid = mockRepository.ScholarshipVaild(student);
        //then
        verify(mockRepository, atLeast(1)).ScholarshipVaild(any());
    }

    @Test
    public void 학점업데이트를했을때원하는값으로업데이트된값을반환하는지검증(){
        //given
        given(mockRepository.updateGPA("201421155",4.5)).willReturn(4.5);
        //when
        double modifiedGPA = mockRepository.updateGPA("201421155",4.5);
        //then
        verify(mockRepository, atLeast(1)).updateGPA(anyString(),anyDouble());
        assertThat(modifiedGPA,is(4.5));
    }
}
