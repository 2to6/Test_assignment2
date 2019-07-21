# 실전코딩 2조 Software Testing 두번째 과제 

## 조원
이안규, 원동욱, 최진영, 최지원, 김도연 

총 5명

## 사용된 Dependencies & APIs
- Junit : version 4.12
- Hamcrest : version 1.3
- Lombok : version 1.16.10
- Stream API

## 설명
주제 - 학생정보검색 프로그램

메소드
  findByStudentId -> 학생번호로 학생의 정보를 찾는다.
  updateGPAByStudentId -> 학생의 학점을 업데이트 하고 업데이트 된 학점을 반환한다.
  addStudent -> 학생의 정보를 추가한다.
  ScholarshipValid -> 학생의 장학 선정여부를 알려준다.
  findByName -> 학생의이름으로 학생의 정보를 찾는다.
  searchStudentByName -> 리스트에서 이름을 필터링한다.

각 테스트마다 어떤 사람이 코드를 작성해는지 주석으로 이름을 적어 처리함.

예시)
```
    @Test
    public void 특정글자를_포함하는_학생들을_찾아_만족하는_학생들을_모아_새로_리스트를_생성한후_제대로_만들어졌는지_검사() { //원동욱
        //Stream 사용
        //민을 검색했을 경우, 2명이 민을 포함한 이름을 가지고 있기에 사이즈가 2인 리스트가 만들어져야한다.
        List<Student> newListingStudents = mockService.searchStudentByName(students,"민");
        assertThat(newListingStudents.size(), is(2));
    }
```
