# logging-basic

![image](https://github.com/Hyune-s-lab/kopring-workshop/assets/55722186/d779ab3c-da30-4035-8579-8d90e46d8902)

### how to run?

- http://localhost:8080/swagger-ui/index.html#/
- TestUtilController.http

## step 1. HandlerInterceptor

> HandlerInterceptors are part of the Spring MVC framework and sit between the DispatcherServlet and our Controllers.
>
> - [Introduction to Spring MVC HandlerInterceptor](https://www.baeldung.com/spring-mvc-handlerinterceptor)
> - [HandlerInterceptors vs. Filters in Spring MVC](https://www.baeldung.com/spring-mvc-handlerinterceptor-vs-filter)

- `preHandle -> postHandle -> afterCompletion` 과정을 통해 로깅을 할 수 있습니다.
- request, response 접근도 가능 합니다.

### call RunException api

> preHandle -> afterCompletion -> error!  
> -> preHandle -> postHandle -> afterCompletion

- 로직 처리 중 예외가 발생하기에 `postHandle` 는 실행되지 않습니다.
- 하지만 그 직후 또 한번 사이클이 돌아가는 이유는
    - 명시적인 예외 처리를 하지 않았기에 `BasicErrorController` 가 호출되기 때문 입니다.

## step 2. add RestControllerAdvice

### call RunException api

> preHandle -> error! handled -> afterCompletion

- `RestControllerAdvice` 에서 handling 되기에 `BasicErrorController` 가 호출되지 않습니다.
- api 호출에 대한 예외 처리를 한 곳에서 할 수 있습니다.
- 응답을 표준화할 수 있습니다. `ErrorResponse`

## step 3. handle request body

> HttpServletRequest is an interface which exposes getInputStream()  method to read the body.  
> By default, the data from this InputStream can be read only once.
> - [Reading HttpServletRequest Multiple Times in Spring](https://www.baeldung.com/spring-reading-httpservletrequest-multiple-times)

- `HttpServletRequest` 의 body 는 한번 읽으면 더 이상 읽을 수 없습니다.
    - uri, method, header 는 여러번 읽을 수 있습니다.
- body 는 길이가 클 수 있음으로 예외가 발생했을 때만 logging 하는 것을 권장 합니다.

## step 4. using MDC

- `Mapped Diagnostic Context` 는 같은 thread 내에서만 공유됩니다.
- traceId 등을 로깅할 수 있습니다.

## step 5. measure response duration

- filter 를 통해 request 단위의 측정이 가능 합니다.
    - 필요에 따라 slow api 를 감지할 수도 있습니다.
- 더 표준적인 방법은 metrics 를 활용하는 것 입니다.
    - [Metrics for Your Spring REST API](https://www.baeldung.com/spring-rest-api-metrics)

## step 6. using MDC with async

> - [@Async 비동기 멀티스레드 사용법](https://cano721.tistory.com/208)
> - [Creating Asynchronous Methods
    ](https://spring.io/guides/gs/async-method)
> - [표준 예외 처리에서 로깅까지 (2)](https://hyune-c.tistory.com/20)

### call RunException api - async 만 설정

![image](https://github.com/Hyune-s-lab/kopring-workshop/assets/55722186/b88dc8e6-3d6b-412f-9ca6-855dbbf76565)

- 실무에서는 async 전용 thread pool 을 설정하는 것을 권장 합니다.
    - default `SimpleAsyncTaskExecutor`

### call RunException api - CustomTaskDecorator 설정

![image](https://github.com/Hyune-s-lab/kopring-workshop/assets/55722186/92c8190c-1148-4778-9820-82af2903a517)

## step 7. using MDC with coroutine

### call RunException api - coroutine 만 설정

![image](https://github.com/Hyune-s-lab/kopring-workshop/assets/55722186/458d49b2-faf5-414a-b6b3-1c6632128a96)
