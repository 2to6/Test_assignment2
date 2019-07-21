package com.ajou.TwotoSix.unitTest2.service;

import com.ajou.TwotoSix.unitTest2.domain.Student;
import com.ajou.TwotoSix.unitTest2.repository.MockRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

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


    private List<Student> students = new ArrayList<>();


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
