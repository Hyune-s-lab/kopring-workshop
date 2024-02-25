# logging-basic

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

### `run exception` api 실행시

> preHandle -> afterCompletion -> error!  
> -> preHandle -> postHandle -> afterCompletion

- 로직 처리 중 예외가 발생하기에 `postHandle` 는 실행되지 않습니다.
- 하지만 그 직후 또 한번 사이클이 돌아가는 이유는
    - 명시적인 예외 처리를 하지 않았기에 `BasicErrorController` 가 호출되기 때문 입니다.

## step 2. add RestControllerAdvice

### `run exception` api 실행시

> preHandle -> error! handled -> afterCompletion

- `RestControllerAdvice` 에서 handling 되기에 `BasicErrorController` 가 호출되지 않습니다.
- api 호출에 대한 예외 처리를 한 곳에서 할 수 있습니다.
- 응답을 표준화할 수 있습니다. `ErrorResponse` 

## step 3. handle request body

> HttpServletRequest is an interface which exposes getInputStream()  method to read the body. By default, the data from
> this InputStream can be read only once.
> - [Reading HttpServletRequest Multiple Times in Spring](https://www.baeldung.com/spring-reading-httpservletrequest-multiple-times)

- `HttpServletRequest` 의 body 는 한번 읽으면 더 이상 읽을 수 없습니다.
  - uri, method, header 는 여러번 읽을 수 있습니다.
- body 는 길이가 클 수 있음으로 예외가 발생했을 때만 logging 하는 것을 권장 합니다.
