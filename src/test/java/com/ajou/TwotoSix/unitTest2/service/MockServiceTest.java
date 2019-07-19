package com.ajou.TwotoSix.unitTest2.service;

import com.ajou.TwotoSix.unitTest2.domain.Student;
import com.ajou.TwotoSix.unitTest2.repository.MockRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
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

//    @Mock
//    private MockRepository mockRepository;
//
//    @InjectMocks
//    private MockService mockService;

    //mock()
    @Test
    public void mock_사용_간단한예제(){
        Student student = mock(Student.class);
        assertTrue( student != null);
    }

    //@Mock
    @Mock
    Student student;
    @Test
    public void mock_사용_간단예제2(){
        MockitoAnnotations.initMocks(this);
        //MockitoAnnotations.initMocks(this)를 이용하면 Mockito 어노테이션이 선언된 변수들은 객체를 만들어 낸다.
        assertTrue(student != null);
    }

    //when()
    @Test
    public void when_example(){
        student = mock(Student.class);
        when(student.getName()).thenReturn("CJW");
        when(student.getCurrentSemester()).thenReturn(6);
        assertTrue("CJW".equals(student.getName()));
        assertTrue(6 == student.getCurrentSemester());
    }

    //when() 2nd
    @Test
    public void when_2_example(){
        MockService mockService = mock(MockService.class);
        when(mockService.getList(anyString(),anyInt()))
                .thenReturn(
                        new ArrayList<String>(){
                            {this.add("CJW"); this.add("CJM");}
                        }
                );
        assertTrue(mockService.getList(anyString(),anyInt())!=null);
    }

    //https://jdm.kr/blog/222

    //doThrow() : 예외 던지기
    //eq() : 특정 값을 넣기
    @Test(expected = IllegalArgumentException.class)
    public void doThrow_example(){
        Student student = mock(Student.class);
        doThrow(new IllegalArgumentException()).when(student).setName(eq("CJW"));
        String name = "CJW";
        student.setName(name);
    }

    //doNothing() : void로 선언된 메소드에 when()을 걸고 싶을 때 사용
    @Test
    public void doNothing_example(){
        Student student = mock(Student.class);
        doNothing().when(student).setName(anyString());
        student.setName("CJW");
        verify(student).setName(anyString());
        System.out.println("Name::"+student.getName());
    }

    //verify() : 해당 구문이 호출 되었는지를 체크.
    //단순한 호출뿐만 아니라 횟수나 타임아웃 시간까지 지정해서 체크 가능
    @Test
    public void verify_example(){
        Student student = mock(Student.class);
        String name = "CJW";
        student.setName(name);
        //n번 호출했는지 체크
        verify(student, times(1)).setName(anyString()); //success
        //호출 안했는지 체크
        verify(student, never()).getName();     //success
        verify(student, never()).setName(eq("CJM"));    //success
        verify(student, never()).setName(eq("CJW"));    //fail
        //최소 1번 이상 호출했는지 체크
        verify(student,atLeastOnce()).setName(anyString());     //success
        //2번 이하 호출했는지 체크
        verify(student, atMost(2)).setName(anyString());      //success
        //2번 이상 호출했는지 체크
        verify(student,atLeast(2)).setName(anyString());      //fail
        //지정된 시간(millis)안으로 메소드를 호출했는지 체크
        verify(student,timeout(100)).setName(anyString());      //success
        //지정된 시간안으로 1번 이상 메소드를 호출했는지 체크
        verify(student,timeout(100).atLeastOnce()).setName(anyString());        //success
    }

    //@InjectMocks: 클래스 내부에 다른 클래스를 포함하는 경우에 사용
    //이 다른 클래스로 로직을 점검해야 한다면 외부에서 주입할 수 있는 setter 메소드나 생성자를 구현하지 않아도
    //@InjectMocks annotations을 사용하여 @Mock이나 @Spy 어노테이션이 붙은 mock 객체를 자신의 멤버 클래스와 일치하여
    //주입시킨다.

    @Mock
    MockRepository mockRepository;

    @InjectMocks
    MockService mockService;

    @Test   //BDD 스타일
    public void example_InjectMocks(){
        //given : 선행 조건 기술
        given(mockRepository.findByName(eq("CJW"))).willReturn(new Student("CJW","201420997",6,"Software",3.6));
        //when : 기능 수행
        Student student = mockService.findByName("CJW");
        student = mockService.findByName("CJW");
        //then : 결과 확인
        verify(mockRepository, times(2)).findByName("CJW");
        assertThat(student.getGPA(), is(3.6));

        when(mockRepository.findByName("CJW")).thenReturn(new Student("CJM","201712345",5,"sooeui",3.5));
        assertTrue(mockService.findBystudentId("201712345") == new Student("CJM","201712345",5,"sooeui",3.5));
    }

    //@Spy : @Spy로 선언된 mock 객체는 mock method(stub)를 별도로 만들지 않는다면 실제 method가 호출된다.
    //이건 어떻게 쓰는지 모르겠다 ㅠㅠ
//    @Test
//    public void example_spy(){
//        MockService mockService = spy(MockService.class);
//        doReturn("CJW").when(student).getName();
//        assertTrue(student.getName() == "CJW");
//    }



}
