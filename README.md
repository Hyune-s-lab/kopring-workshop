# kopring-workshop

- 대부분의 모듈은 kotlin + spring + web application 베이스로 구성되어 있습니다.
- 대부분의 모듈은 독립 실행이 가능 합니다.
- step 별로 commit 을 참고하시면 좋습니다.

## logging

### [basic](https://github.com/Hyune-s-lab/kopring-workshop/tree/main/logging/basic)

- step 1. HandlerInterceptor
- step 2. add RestControllerAdvice
- step 3. handle request body
- step 4. using MDC
- step 5. measure response duration

### [logback](https://github.com/Hyune-s-lab/kopring-workshop/tree/main/logging/logback)

- step 1. separate env
- step 2. create logback.xml
- step 3. add error appender with JsonLayout
- step 4. using MDC

### [datadog](https://github.com/Hyune-s-lab/kopring-workshop/tree/main/logging/datadog)

- step 1. boot with datadog agent
