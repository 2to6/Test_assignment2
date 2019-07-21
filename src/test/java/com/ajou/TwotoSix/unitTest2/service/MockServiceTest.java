package com.ajou.TwotoSix.unitTest2.service;

import com.ajou.TwotoSix.unitTest2.domain.Student;
import com.ajou.TwotoSix.unitTest2.repository.MockRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MockServiceTest {

    @Mock
    private MockRepository mockRepository;

    @InjectMocks
    private MockService mockService;

    @Test
    public void mock메소드_사용했을때_notNull이면_pass(){     //최지원
        Student student = mock(Student.class);
        assertTrue( student != null);
    }

    @Mock
    Student student;
    @Test
    public void mock어노테이션_사용했을때_notNull이면_pass(){   //최지원
        MockitoAnnotations.initMocks(this);
        //MockitoAnnotations.initMocks(this)를 이용하면 Mockito 어노테이션이 선언된 변수들은 객체를 만들어 낸다.
        assertTrue(student != null);
    }

    @Test
    public void when_thenReturn_사용_updateGPAByStudentId_실행했을때_4리턴하면_pass(){  //최지원
        mockService = mock(MockService.class);
        when(mockService.updateGPAByStudentId(anyString(),anyDouble())).thenReturn(4.0);
        assertTrue(mockService.updateGPAByStudentId(anyString(),anyDouble()) == 4.0);
    }

    @Test
    public void when_thenReturn_사용_List만들었을떄_getList하고_NotNull이면_pass(){    //최지원
        mockService = mock(MockService.class);
        when(mockService.getList(anyString(),anyInt()))
                .thenReturn(
                        new ArrayList<String>(){
                            {this.add("CJW"); this.add("CJM");}
                        }
                );
        assertTrue(mockService.getList(anyString(),anyInt())!=null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void doThrow_사용했을떄_IllegalArgumentException_throw하면_pass(){  //최지원
        mockService = mock(MockService.class);
        doThrow(new IllegalArgumentException()).when(mockService).updateGPAByStudentId(eq("201420997"),anyDouble());
        //mockService.updateGPAByStudentId(anyString(),4.3);  //fail
        mockService.updateGPAByStudentId("201420997",4.4);
    }

    @Test
    public void doNothing_사용했을때_아무메소드도_실행안됐으면_pass(){   //최지원
        Student student = mock(Student.class);
        doNothing().when(student).setGPA(anyDouble());
        student.setGPA(4.3);
        verify(student).setGPA(anyDouble());
        System.out.println("GPA::"+student.getGPA());
    }

    @Test
    public void verify사용하여_currentSemester관련_메소드들이_조건에맞게_실행되었으면_pass(){ //최지원
        Student student = mock(Student.class);
        int currentSemester = 6;
        student.setCurrentSemester(currentSemester);
        //n번 호출했는지 체크
        verify(student, times(1)).setCurrentSemester(anyInt()); //success
        //호출 안했는지 체크
        verify(student, never()).getCurrentSemester();     //success
        verify(student, never()).setCurrentSemester(eq(10));    //success
        verify(student, never()).setCurrentSemester(eq(6));    //fail
        //최소 1번 이상 호출했는지 체크
        verify(student,atLeastOnce()).setCurrentSemester(anyInt());     //success
        //2번 이하 호출했는지 체크
        verify(student, atMost(2)).setCurrentSemester(anyInt());      //success
        //2번 이상 호출했는지 체크
        verify(student,atLeast(2)).setCurrentSemester(anyInt());      //fail
        //지정된 시간(millis)안으로 메소드를 호출했는지 체크
        verify(student,timeout(100)).setCurrentSemester(anyInt());      //success
        //지정된 시간안으로 1번 이상 메소드를 호출했는지 체크
        verify(student,timeout(100).atLeastOnce()).setCurrentSemester(anyInt());        //success
    }

    @Test   //BDD 스타일
    public void BDD_스타일로_updateGPAByStudentId_메소드_Test(){     //
        //given : 선행 조건 기술
        given(mockRepository.updateGPAByStudentId(eq("201420997"),eq(4.0))).willReturn(new Student("CJW","201420997",6,"Software",4.0).getGPA());
        //when : 기능 수행
        double GPAofStudent = mockRepository.updateGPAByStudentId("201420997",4.0);
        //then : 결과 확인
        verify(mockRepository, times(1)).updateGPAByStudentId("201420997",4.0);
        assertThat(GPAofStudent, is(4.0));

    @Before
    public void setUp() {
        //학생 객체를 생성
        Student minsu = new Student("박민수",
                "200012345", 6, "수학과", 3.1);
        Student jeongho = new Student("정종호",
                "203045785", 7, "건축학과", 3.4);
        Student minchul = new Student("김민철",
                "213458121", 4, "물리학과", 2.9);
        Student seongwon = new Student("서성원",
                "201487531", 5, "국방디지털학과", 3.2);
        Student taesun = new Student("전태선",
                "215491531", 3, "사이버보안학과", 2.7);


        students.add(minsu);
        students.add(jeongho);
        students.add(minchul);
        students.add(seongwon);
        students.add(taesun);
    }


    @Test
    public void 박민수라는_학생을_찾아_가짜객체를_생성하고_기존의_객체의_이름과_일치하면_리스트에서_삭제하고_리스트의_사이즈를_검사() { //원동욱
        when(mockService.findByName("박민수")).thenReturn(new Student("박민수",
                "200012345", 6, "수학과", 3.1));
        String studentName = mockService.findByName("박민수").getName();

        if (students.get(0).getName().equals(studentName)) {
            students.remove(0);
        }
        assertThat(students.size(), is(1));
    }


    @Test
    public void 특정글자를_포함하는_학생들을_찾아_만족하는_학생들을_모아_새로_리스트를_생성한후_제대로_만들어졌는지_검사() { //원동욱

        //Stream 사용
        //민을 검색했을 경우, 2명이 민을 포함한 이름을 가지고 있기에 사이즈가 2인 리스트가 만들어져야한다.
        List<Student> newListingStudents = mockService.searchStudentByName(students,"민");
        assertThat(newListingStudents.size(), is(2));
    }
}
