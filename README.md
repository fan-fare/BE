### Fanfare 프로젝트

---


<br><br>
## 프로젝트 개요

Fanfare는 사용자들이 생일 축하 메시지를 남길 수 있는 웹 사이트입니다. <br>
생일을 맞은 사용자를 위해, 누구나 케이크에 메시지를 남길 수 있습니다. <br>
그동안 받아온 메세지는 생일 당일부터 공개되어 조회할 수 있으며, 이후 7일 동안만 메시지를 보관할 수 있습니다. <br>

<br><br>
## 백엔드 설계 및 개발 기간
약 10일 (백엔드 1명, 허은정)

<br><br>
## 프로젝트 사이트 바로가기
https://www.happybday.cc/
<br><br>

## 기술 스택
| **카테고리**        | **스택**                                                                                                                                  |
|---------------------|------------------------------------------------------------------------------------------------------------------------------------------|
| **IDE**             | IntelliJ                                                                                                                                  |
| **Language**        | Java 17                                                                                                                                   |
| **Framework**       | Spring Boot 3.3.2, Gradle                                                                                                                 |
| **Authentication**  | Spring Security 6, JSON Web Tokens                                                                                                        |
| **ORM**             | Spring Data JPA                                                                                                                           |
| **Database**        | MySQL                                                                                                                                     |
| **External**        | AWS EC2, AWS RDS                                                                                                                          |
| **Other Tools**     | Notion, Discord, Postman, Figma                                                                                                           |

<br><br>
## 주요 기능

- **회원 관리**: 회원가입, 로그인, 아이디 중복 확인 기능
- **생일 축하 메시지 관리**: 모든 사용자에 대해 메시지 작성 기능, 생일자에 대해 조회, 삭제 기능
- **케이크 관리**: 사용자별 생일 케이크와 연동된 메시지 조회

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
