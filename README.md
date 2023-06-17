# SpringBoot API
기초데이터베이스 과제 Project
SpringBoot , JDBC드라이버를 사용하여 RESTful API서버개발

# 과제

https://apl.hongik.ac.kr/lecture/dbms 에는 박사과정 (PhD Students), 석사과정 (Master Students), 학부과정 (Undergraduate Students) 들이 있음

학생들은 HTML list로 열거되어 있으며, 새로운 학생들이 추가 또는 삭제되지만, 열거의 형식은 바뀌지 않음. 

열거된 정보는 모두 영어로 되어 있음을 전제함

웹 서버는 다음의 질의를 처리할 수 있는 API가 구현되어야 함



전제조건: 

- 서버는 localhost에서 구동

- 서버 포트는 8082

- PostgreSQL 포트는 5432

- database이름은 hongik

- table이름은 students 이며, 각 attribute는 다음과 같이 설정됨

- sid integer, primary key

- name varchar (100)

- email varchar (100)

- degree varchar (100)

- graduation integer

- PostgreSQL 사용자는 postgres, password는 1234

(1) 특정 이름을 가진 학생의 학위 유형 질의

 - GET Request

 - Endpoint /students/degree

 - Request 매개변수는 "name"

 - Response 형식 <Name> : < Degree >



예) Michael Jordan : PhD



 - 학생이 존재하지 않은 경우, "No such student"라는 에러 메세지를 반환하도록 함

 - 동명의 학생이 여러명 존재하는 경우, "There are multiple students with the same name. Please provide an email address instead. 



(2) 특정 이름을 가진 학생의 이메일 질의

 - GET Request

 - Endpoint /students/email

 - Request 매개변수는 "name"

 - Response 형식 <Name> : < 이메일 >



예) Michael Jordan : jordan@nike.com



 - 학생이 존재하지 않은 경우, "No such student"라는 에러 메세지를 반환하도록 함

 - 동명의 학생이 여러명 존재하는 경우, "There are multiple students with the same name. Please contact the administrator by phone.



(3) 학위별 학생의 수 반환

 - GET Request

 - Endpoint: /students/stat

 - Request 매개변수는 "degree"

 - Response 형식 <Degree> : < Count >



예) Number of Master's student : 5



(4) 신규 학생의 등록

  - PUT Request

  - Endpoint: /students/register

  - Request 매개변수는 "name", "email" 과 "graduation"

  - Response 형식

    "Registration successful", insert가 정상적으로 이루어졌을 때

    "Already registered", 동일인을 다시 등록하려고 할 때
