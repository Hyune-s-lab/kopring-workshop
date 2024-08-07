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

## step 2. init member 100만명 - using jdbc bulk insert

![image](https://github.com/user-attachments/assets/5b4de84d-446f-4648-acbb-9df8a797c701)

- jdbc.bulkUpdate 를 사용하여 멤버 생성 및 약관 동의 처리
    - 5만명 멤버 생성 328 ms - 96% 감소
    - 3만명 약관 동의 442 ms - 96% 감소

![image](https://github.com/user-attachments/assets/8aba00c0-4d2d-4d57-b59f-97751c9b3c9e)

- 100만명 멤버 생성 5678 ms
- 60만명 약관 동의 8599 ms

## step 3. 기존 약관에 동의된 유저들에게 알림

> 빠른 테스트를 위해 전체 1만명을 기준으로 진행 합니다.

![image](https://github.com/user-attachments/assets/c224cd63-54fe-4be1-81ed-ae4fc42be740)

- 1만명 중 6천명에게 알림 전송: 5121 ms
- coroutine count 는 default 인 64 를 기반으로 실행

![image](https://github.com/user-attachments/assets/378dcb52-c143-4e6e-8231-02678e5b58ed)

- 1만명 중 6천명에게 알림 전송: 364 ms
- coroutine limitedParallelism 적용
    - `Dispatchers.IO.limitedParallelism(10)`
    - 가벼운 job 에만 사용되어야 하며 충분한 테스트가 필요한 설정

## step n. quartz scheduler, spring batch

...
