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

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
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
    public void 학번으로_검색했을때_올바른_학생정보를_리턴하는지_확인하는_테스트() { //최진영
        when(mockService.findByStudentId("201620987")).thenReturn(new Student("최진영", "201620987", 5, "software", 4.5));

        String mockName = mockService.findByStudentId("201620987").getName();
        String mockStudentId = mockService.findByStudentId("201620987").getStudentId();
        int mockCurrentSemester = mockService.findByStudentId("201620987").getCurrentSemester();
        String mockMajor = mockService.findByStudentId("201620987").getMajor();
        double mockGpa = mockService.findByStudentId("201620987").getGPA();

        assertThat(mockName, is("최진영"));
        assertThat(mockStudentId, is("201620987"));
        assertThat(mockCurrentSemester, is(5));
        assertThat(mockMajor, is("software"));
        assertThat(mockGpa, is(4.5));

    }

    @Test(expected = IllegalArgumentException.class)
    public void 학번_검색_시_빈값을_입력하면_익셉션_발생_테스트() { //최진영

        when(mockService.findByStudentId(" ")).thenThrow(new IllegalArgumentException());
        mockService.findByStudentId(" ");

    }

    @Test
    public void 학번으로_검색_시_해당_함수가_한_번_실행되면_패스() { //최진영

        when(mockService.findByStudentId(anyString())).thenReturn(new Student("최진영", "201620987", 5, "software", 4.5));
        Student student = mockService.findByStudentId(anyString());
        verify(mockRepository, times(1)).findByStudentId(anyString());

    }

    @Test(expected = RuntimeException.class)
    public void 학번이_9자리가_아니면_익셉션_발생_테스트(){
        when(mockService.findByStudentId("123456789")).thenReturn(anyObject());
        when(mockService.findByStudentId("1234567891")).thenThrow(RuntimeException.class);
        mockService.findByStudentId("123456789");
        mockService.findByStudentId("1234567891");
    }




}
