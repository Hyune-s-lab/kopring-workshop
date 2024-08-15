- 대부분의 모듈은 kotlin + spring + web application 베이스로 구성되어 있습니다.
- 대부분의 모듈은 독립 실행이 가능 합니다. (local profile)
- step 별로 commit 을 참고하시면 좋습니다.

# web

### [exception](https://github.com/Hyune-s-lab/kopring-workshop/tree/main/web/exception)

- step 1. RestControllerAdvice
- step 2. handle CustomException

# logging

### [basic](https://github.com/Hyune-s-lab/kopring-workshop/tree/main/logging/basic)

- step 1. HandlerInterceptor
- step 2. add RestControllerAdvice
- step 3. handle request body
- step 4. using MDC
- step 5. measure response duration
- step 6. using MDC with async
- step 7. using MDC with coroutine

### [logback](https://github.com/Hyune-s-lab/kopring-workshop/tree/main/logging/logback)

- step 1. separate env
- step 2. create logback.xml
- step 3. add error appender with JsonLayout
- step 4. using MDC

### [datadog](https://github.com/Hyune-s-lab/kopring-workshop/tree/main/logging/datadog)

- step 1. boot with datadog agent
- step 2. using coroutine

# database

### [mongodb](https://github.com/Hyune-s-lab/kopring-workshop/tree/main/db/mongodb)

- step 0. set mongodb with local docker
- step 1. repository save & find
- step 2. set testcontainers for test

### [mysqldb](https://github.com/Hyune-s-lab/kopring-workshop/tree/main/db/mysqldb)

- step 1. set mysql with testcontainers
- step 2. set transactional logging
- step 3. set p6spy for logging
- step 4. set querydsl

# batch

### [scenario1](https://github.com/Hyune-s-lab/kopring-workshop/tree/main/batch/scenario1)

- step 1. init member 5만명 - using jpa
- step 2. init member 100만명 - using jdbc multiline insert
- step 3. 기존 약관에 동의된 유저들에게 알림
- step n. quartz scheduler, spring batch ...

# test

### [testcontainers](https://github.com/Hyune-s-lab/kopring-workshop/tree/main/test/testcontainers)

- step 1. apply mysql testcontainers to local, test profile
- step 2. apply redis testcontainers to local, test profile
- step 3. apply kafka testcontainers to local, test profile

### [template](https://github.com/Hyune-s-lab/kopring-workshop/tree/main/test/template)

- WebTestSupport
- ScenarioTestSupport

# external-api

### [wiremock](https://github.com/Hyune-s-lab/kopring-workshop/tree/main/external-api/wiremock)

- step 1. external api call with openfeign
- step 2. change external api end-point to wiremock at test profile
- step 3. test read-timeout
