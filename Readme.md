# 백엔드 서버

- Messaging : RabbitMQ  
- DB : Mysql 8.0.34 ==> 변경 postgres 13-alpine
- Spring boot 3.1.1  

### Spring Security 임시 계정(deprecated)
id : admin  
password : password  

### 빌드 방법

- 테스트는 제외하고 빌드해야함

gradle build -x test

### 특이사항

- application.yml은 gitignore처리 하였으므로 별도 문의 필요  
(AWS 계정으로 노출 방지를 위함)