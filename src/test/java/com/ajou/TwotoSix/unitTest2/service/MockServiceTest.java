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
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;

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
    public void 학번이_9자리가_아니면_익셉션_발생_테스트(){ //최진영
        when(mockService.findByStudentId("123456789")).thenReturn(anyObject());
        when(mockService.findByStudentId("1234567891")).thenThrow(RuntimeException.class);
        mockService.findByStudentId("123456789");
        mockService.findByStudentId("1234567891");
    }

    @Test
    public void findByStudentId_getter_테스트(){ //

       Student student = mock(Student.class);

        student.getStudentId();
        student.getName();
        student.getGPA();
        student.getCurrentSemester();
        student.getMajor();

        verify(student, timeout(200).atLeastOnce()).getStudentId();
        verify(student, timeout(200).atLeastOnce()).getName();
        verify(student, timeout(200).atLeastOnce()).getGPA();
        verify(student, timeout(200).atLeastOnce()).getCurrentSemester();
        verify(student, timeout(200).atLeastOnce()).getMajor();

    }


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

    @Test
    public void 객체에서_학생이름을_가져오는_로직이_두번실행됐으면_패스(){         //이안규
        Student student = mock(Student.class);
        student.getName();
        student.getName();
        verify(student, times(2)).getName();
    }


    @Test
    public void 이름으로_검색하면_학생정보를_리턴하고_1번이하로_호출되었는지_검증하고_학생이름확인(){           //이안규
        //given
        given(mockRepository.findByName("이안규")).willReturn(new Student("이안규","201421155",7,"Mathematics",3.6));
        //when
        Student student = mockRepository.findByName("이안규");
        //then
        verify(mockRepository,atLeast(1)).findByName(anyString());
        assertThat(student.getName(),is("이안규"));
    }

    @Test
    public void 학생이_장학금을_받을수있는지_판단후_검증및확인(){                //이안규
        Student student = mockService.addStudent("이안규","201421155",7,"Mathematics",4.0);
        //given
        given(mockRepository.ScholarshipVaild(student)).willReturn(true);
        //when
        boolean scholashipValid = mockRepository.ScholarshipVaild(student);
        //then
        verify(mockRepository, atLeast(1)).ScholarshipVaild(any());
        assertThat(scholashipValid,is(true));
    }

    @Test
    public void 학생정보를추가하고_원하던정보대로_추가되었는지확인(){           //이안규
        Student student = mockService.addStudent("이안규","201421155",7,"Mathematics",4.0);
        assertThat(student.getName(),is("이안규"));
        assertThat(student.getStudentId(),is("201421155"));
        assertThat(student.getCurrentSemester(),is(7));
        assertThat(student.getMajor(),is("Mathematics"));
        assertThat(student.getGPA(),is(4.0));
    }
      
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
        student = mockService.updateGPA(student,3.7);
        //when 수행 - 장학금 기준 검사
        boolean result = mockService.ScholarshipVaild(student);
        //then 결과
        verify(mockService,times(1)).updateGPA(any(), anyDouble());
        assertTrue(result == true);
    }
}
