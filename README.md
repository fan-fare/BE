### Fanfare 프로젝트

---


<br><br>
## 프로젝트 개요

Fanfare는 사용자들이 생일 축하 메시지를 남길 수 있는 웹 사이트입니다. <br>
생일을 맞은 사용자를 위해, 누구나 케이크에 메시지를 남길 수 있습니다. <br>
그동안 받아온 메세지는 생일 당일부터 공개되어 조회할 수 있으며, 이후 7일 동안만 메시지를 보관할 수 있습니다. <br>
(개발자 : 프론트1, 백엔드1) </br>


<br><br>
## 프로젝트 사이트 바로가기
https://www.happybday.cc/

<br><br>
## 테스트 계정
https://www.happybday.cc/6e4e4b48-78f5-405c-a60c-2f6f3774bcc6 <br>
아이디 : test1234 <br>
비밀번호 : test1234 <br>
누구나 메세지를 남길 수 있습니다. 
메세지 조회는 로그인한 사용자만 생일 당일부터 7일간 가능합니다. 
<br><br>

## 실제 사용자 실행 화면
<details>
    <summary> 보기 </summary> 
    <img src="https://github.com/user-attachments/assets/a73b4011-9586-4f0a-8474-f8f637e6d7a0" alt="home" style="width: 4cm;" /> &nbsp;
    <img src="https://github.com/user-attachments/assets/6a79282b-0314-46f0-8e89-dbd1910f832d" alt="createmessage" style="width: 4cm;" /> &nbsp;
    <img src="https://github.com/user-attachments/assets/21f03789-27b0-4075-b690-62dbca700c0d" alt="messages" style="width: 4cm;" /> &nbsp;
    <img src="https://github.com/user-attachments/assets/955d83bb-8206-4373-aee4-7e409a47b137" alt="messages" style="width: 4cm;" /> &nbsp;
</details>

<br><br>

## 기술 스택
| **카테고리**        | **스택**                                                                                                                                  |
|---------------------|------------------------------------------------------------------------------------------------------------------------------------------|
| **IDE**             | IntelliJ                                                                                                                                  |
| **Language**        | Java 17                                                                                                                                   |
| **Framework**       | Spring Boot 3.3.2, Gradle                                                                                                                 |
| **Authentication**  | Spring Security 6                                                                                                  |
| **ORM**             | Spring Data JPA                                                                                                                           |
| **Scheduling**      | Spring Scheduler                                                                                                                          |
| **Database**        | MySQL                                                                                                                                     |
| **Deploy**          | AWS EC2, AWS RDS                                                                                                                          |



<br><br>
## 주요 기능

- **회원 관리**: 회원가입, 로그인, 아이디 중복 확인 기능
- **생일 축하 메시지 관리**: 모든 사용자에 대해 메시지 작성 기능, 생일자에 대해 조회, 삭제 기능
- **케이크 관리**: 사용자별 생일 케이크와 연동된 메시지 조회
- **자동 메시지 및 회원 삭제**: 스프링 스케줄러를 사용하여 생일 7일 이후 해당 사용자의 메시지와 회원 엔티티 자동 삭제 기능

<br><br>
## 디렉토리 구조

```
fanfare
├── common
│   ├── exception     
│   ├── response      
│   └── security      
├── controller       
├── domain           
├── dto
│   ├── cake         
│   ├── member       
│   ├── message     
│   └── security    
├── repository      
└── service         
    └── security    
```
<br><br>
## ERD (Entity Relationship Diagram)

<img width="300" alt="image" src="https://github.com/user-attachments/assets/c754b48f-2c50-4d9f-b2a4-df18accb5870">
