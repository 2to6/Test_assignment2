package com.ajou.TwotoSix.unitTest2.service;

import com.ajou.TwotoSix.unitTest2.domain.Student;
import com.ajou.TwotoSix.unitTest2.repository.MockRepository;
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
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MockServiceTest {

    @Mock
    private MockRepository mockRepository;

    @InjectMocks
    private MockService mockService;

    //mock()
    @Test
    public void mock메소드_사용했을때_notNull이면_pass(){     //최지원
        Student student = mock(Student.class);
        assertTrue( student != null);
    }

    //@Mock
    @Mock
    Student student;
    @Test
    public void mock어노테이션_사용했을때_notNull이면_pass(){   //최지원
        MockitoAnnotations.initMocks(this);
        //MockitoAnnotations.initMocks(this)를 이용하면 Mockito 어노테이션이 선언된 변수들은 객체를 만들어 낸다.
        assertTrue(student != null);
    }

    //when()
    @Test
    public void when_thenReturn_사용_updateGPAByStudentId_실행했을때_4리턴하면_pass(){  //최지원
        mockService = mock(MockService.class);
        when(mockService.updateGPAByStudentId(anyString(),anyDouble())).thenReturn(4.0);
        assertTrue(mockService.updateGPAByStudentId(anyString(),anyDouble()) == 4.0);
    }

    //when() 2nd
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

    //doThrow() : 예외 던지기
    //eq() : 특정 값을 넣기
    @Test(expected = IllegalArgumentException.class)
    public void doThrow_사용했을떄_IllegalArgumentException_throw하면_pass(){  //최지원
        mockService = mock(MockService.class);
        doThrow(new IllegalArgumentException()).when(mockService).updateGPAByStudentId(eq("201420997"),anyDouble());
        //mockService.updateGPAByStudentId(anyString(),4.3);  //fail
        mockService.updateGPAByStudentId("201420997",4.4);
    }

    //doNothing() : void로 선언된 메소드에 when()을 걸고 싶을 때 사용
    @Test
    public void doNothing_사용했을때_아무메소드도_실행안됐으면_pass(){   //최지원
        Student student = mock(Student.class);
        doNothing().when(student).setGPA(anyDouble());
        student.setGPA(4.3);
        verify(student).setGPA(anyDouble());
        System.out.println("GPA::"+student.getGPA());
    }

    //verify() : 해당 구문이 호출 되었는지를 체크.
    //단순한 호출뿐만 아니라 횟수나 타임아웃 시간까지 지정해서 체크 가능
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

    //@InjectMocks: 클래스 내부에 다른 클래스를 포함하는 경우에 사용
    //이 다른 클래스로 로직을 점검해야 한다면 외부에서 주입할 수 있는 setter 메소드나 생성자를 구현하지 않아도
    //@InjectMocks annotations을 사용하여 @Mock이나 @Spy 어노테이션이 붙은 mock 객체를 자신의 멤버 클래스와 일치하여
    //주입시킨다.

    @Test   //BDD 스타일
    public void BDD_스타일로_updateGPAByStudentId_메소드_Test(){
        //given : 선행 조건 기술
        given(mockRepository.updateGPAByStudentId(eq("201420997"),eq(4.0))).willReturn(new Student("CJW","201420997",6,"Software",4.0).getGPA());
        //when : 기능 수행
        double GPAofStudent = mockRepository.updateGPAByStudentId("201420997",4.0);
        //then : 결과 확인
        verify(mockRepository, times(1)).updateGPAByStudentId("201420997",4.0);
        assertThat(GPAofStudent, is(4.0));
    }
}
