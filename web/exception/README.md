# web-exception

### how to run?

- http://localhost:8080/swagger-ui/index.html#/
- /src/test/http-request/TestUtilController.http

## step 1. RestControllerAdvice

- servlet request 는 하나의 thread 로 처리 됩니다.
    - `RestControllerAdvice` 는 하나의 request 에서 에외가 발생하는 경우 global handling 을 해줍니다.
- `@Rest~~` annotation 을 사용한다면 annotation 기반의 응답 객체 조작을 추천 합니다.
    - `ResponseEntity` 를 직접 조작하는 방법도 있습니다.

### `RestControllerAdvice` 에서 handling 되지 못한 exception 은?

- default 로직에서 잡히긴 하지만 권장하지 않습니다.
- `@ExceptionHandler(Exception::class)` 선언으로 최상위 예외 처리를 권장 합니다.
