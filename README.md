# custom-rest-client-core
Multi Module 에서 타 도메인의 서비스를 이용할 시 발생하는 `순환참조`를 예방하고 효율적인 유지보수를 위한 모듈 샘플

## ☝️간단 도메인으로 구성
- **학교**: 학교 이름과 총 학생 수가 있습니다.
- **학생**: 다니고 있는 학교의 id와 이름이 존재합니다.
```mermaid
erDiagram
SCHOOL {
BIGINT id PK
VARCHAR name
BIGINT studentCnt
}

STUDENT {
BIGINT id PK
BIGINT schoolId FK
VARCHAR name
}
```

## ⬢ Hexagonal Architecture를 기반한 모듈 참조 그래프
```mermaid
graph LR

%% main-runner
    main[main-runner]

%% school 도메인 구조
    school_domain[school-domain] --> school_api[school-api]
    school_api --> school_app[school-application]
    school_app --> school_rdb[school-rdb-adapter]
    school_app --> school_webmvc[school-webmvc-adapter]

%% student 도메인 구조
    student_domain[student-domain] --> student_api[student-api]
    student_api --> student_app[student-application]
    student_app --> student_rdb[student-rdb-adapter]
    student_app --> student_webmvc[student-webmvc-adapter]

%% 공통 도메인 참조
    school_api --> school[school]
    school_app --> school
    school_rdb --> school
    school_webmvc --> school

    student_api --> student[student]
    student_app --> student
    student_rdb --> student
    student_webmvc --> student
    
%% main-runner
    school --> main
    student --> main
```

## 🚨타 도메인 수정을 위한 비즈니스 요구사항 발생

- 요구사항: 해당 학교에 학생이 등록 시, 그 학교의 총 학생 수를 증가시킨다.
- 비즈니스 코드 탐색
  - student service 탐색
  ```java
  public class StudentCommandService implements StudentCreateUseCase {
    
    private final StudentCommandRepositoryPort StudentCommandRepositoryPort;
    
     /*
       🚨 학생 등록 시, 해당 학교에 전첸 학생 수를 증가시키는 요구사항 추가
     */  
    @Override
    public Student createStudent(Student student) {
        return StudentCommandRepositoryPort.save(student);
    }
  }

  ```
  - school port 탐색
  ```java
   public interface SchoolCommandRepositoryPort { 
    School save(School school);

    School updateStudentCnt(Long id);
  }
  ```

### ❌ 해결방안 1

- student-application은 school-application을 의존하여 SchoolCommandRepositoryPort 호출
- 코드 예시
  ```java
  public class StudentCommandService implements StudentCreateUseCase {

  private final StudentCommandRepositoryPort StudentCommandRepositoryPort;
  // ❌ 해결방안 1
  private final SchoolCommandRepositoryPort schoolCommandRepositoryPort;

  /*
  🚨 학생 등록 시, 해당 학교에 전첸 학생 수를 증가시키는 요구사항 추가
  */
  @Override
  public Student createStudent(Student student) {
        var newStudent =  StudentCommandRepositoryPort.save(student);
        
        //  ❌ 해결방안 1
        // 학교 총 학생 수 업데이트 로직 호출
        schoolCommandRepositoryPort.updateStudentCnt(newStudent.getSchoolId());
        
        return newStudent;
  }
  }
  ```
- 😩문제점 발생
  - 현재는 student-application이 school-application을 의존하지만, 변화하는 요구사항에 shcool-application이 student-application을 의존할수도 있습니다.
     - **‼ 모듈 순환 참조 에러 발생**
```mermaid
graph LR

%% 모듈 정의
    school_app[school-application]
    student_app[student-application]

%% 순환 참조
    student_app --> school_app
    school_app --> student_app

%% ⚠️ 에러 표시 (점선 라벨로 표현)
    student_app -.-> school_app
    school_app -.-> student_app
    school_app -. 🔁 Circular Dependency Detected! .-> student_app

```

  - 모듈 순환 참조를 방지하기 위해 도메인과 도메인이 상하 관계를 맺는 제약적인 사항이 발생합니다.
     - 모듈간 활용 자유도 저하 -> **서비스 한계점 발생**

### 🛎️ 해결방안 2

- student의 driven 모듈은 RestClient를 응용하여 school driving 모듈에게 HTTP 요청