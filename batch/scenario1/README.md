# batch:scenario1

> n 명의 유저가 이용하는 플랫폼에는 A001 서비스가 존재 합니다.  
> A001 서비스는 유저 중 일부인 k 명이 약관에 동의 후 사용 하고 있었습니다.
>
> 어느 날 A001 서비스의 약관이 변경 예고 되었습니다.  
> 이에 따라 A001 서비스를 사용한 유저들에게 약관 변경 동의 알림을 보내려고 합니다.

## step 1. init member 5만명 - using jpa

![image](https://github.com/user-attachments/assets/a84e6173-543a-4882-8c62-bd6db64daadc)

- jpa 를 사용하여 모두 단일 쿼리로 처리
    - 5만명 멤버 생성 10417 ms
    - 3만명 약관 동의 13090 ms
