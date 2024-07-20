# database:mysqldb

## step 1. set mysql with testcontainers

![image](https://github.com/user-attachments/assets/81d5fb11-792c-4ec6-8def-e37c92f737e6)

## step 2. set transactional logging

> jpa 를 사용한다면 orm.jpa 설정을 추천 합니다.

### level.org.springframework.orm.jpa: DEBUG

![image](https://github.com/user-attachments/assets/3a2a5d75-5bf1-4802-9f1d-e427107521de)

### level.org.springframework.transaction.interceptor: TRACE

![image](https://github.com/user-attachments/assets/f577c61b-e645-46fa-bf1d-e1a7c23ed45e)

## step 3. set p6spy for logging

...

## step 4. set querydsl

...
