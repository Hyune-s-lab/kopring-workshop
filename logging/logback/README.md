# logging-logback

### how to run?

- http://localhost:8080/swagger-ui/index.html#/
- TestUtilController.http

### introduction

> - [Spring/Logback - 초보개발자 긍.응.성](https://ckddn9496.tistory.com/category/Spring/Logback)
> - [A Guide To Logback](https://www.baeldung.com/logback)
> - [Creating a Custom Logback Appender](https://www.baeldung.com/custom-logback-appender)

| Level     | Description                                                                                                        |
|-----------|--------------------------------------------------------------------------------------------------------------------|
| **ERROR** | - 시스템에서 예외가 발생한 경우<br>- 원하는 동작을 할 수 없는 경우<br>- 시스템이 예기치 않게 종료되는 상황은 Fatal 도                                        |
| **WARN**  | - 예외가 발생했지만, 진행에 방해가 되지 않는 경우 (인텐드 무시)<br>- 작동에는 문제가 없지만, Side effect 등 다른 예외가 발생할 여지가 있을 때                        |
| **INFO**  | - 시스템 Lifecycle 상 각 단계적으로 발생하는 이벤트<br>예: 시스템 초기화, Singleton 객체 초기화<br>- 외부 연계 등 정보의 교류가 필요한 경우 (로그 appender 사용 가능) |
| **DEBUG** | - 기본 Level (프로젝트마다 유사 DEBUG Level 로)<br>- 학습 호흡의 흐름을 파악하기 위해                                                       |
| **TRACE** | - 데이터의 변화를 파악하기 위해 (IDE 디버그 대용)<br>- Loop, Multi-threading 환경에서의 Race condition 파악 등                               |

## step 1. separate env

> resources/application.yml

- local, dev, prod 환경 분리

## step 2. create logback.xml

> resources/logback-spring.xml
> 1. define `spring property, reserved property`
> 2. define `pattern` as `property`
> 3. set `appender` with properties similar to `encoder`
> 4. injection `appender` to `springProfile env`

### 주요 속성 사용법

> define `spring property, reserved property`

- `springProperty` application.yml 에 정의된 값을 주입받을 수 있습니다.
- `reserved property` contextName, logDir, logFile, logLevel 등이 있습니다.
    - yml 에 정의 후 `springProperty` 를 통해 주입받는 것을 추천 합니다.

> define `pattern` as `property`

- `property` 복잡한 pattern 을 정의하고 관리 합니다.

> set `appender` with properties similar to `encoder`

- `appender` filter 등으로 기준을 설정하고, encoder 를 통해 log 를 출력 합니다.
    - 주로 사용되는 appender 로는 ConsoleAppender, FileAppender 등이 있습니다.

> injection appender

- `springProfile` 에 정의된 환경별로 다른 appender 를 주입할 수 있습니다.

### 환경별로 다른 appender 설정

> 이번 step 에서는 json layout 의 error logging 이 되지 않습니다.

- `prod` INFO_JSON_APPENDER
- `dev` INFO_JSON_APPENDER
- `local` ALL_CONSOLE_APPENDER

## step 3. add error appender with JsonLayout

- 별도의 pattern 없이도 json 형태의 log 를 출력할 수 있습니다.

```json
{
  "timestamp": "2024-02-25T23:40:12.696",
  "level": "ERROR",
  "thread": "http-nio-8080-exec-1",
  "logger": "com.example.kopringworkshop.logback.support.ApiControllerAdvice",
  "message": "...",
  "context": "logging-logback"
}
```

- JsonLayout 을 사용하기 위해서는 dependency 추가가 필요 합니다.

``` 
// logback
val logbackVersion: String by project
implementation("ch.qos.logback.contrib:logback-json-classic:${logbackVersion}")
implementation("ch.qos.logback.contrib:logback-jackson:${logbackVersion}") 
```
