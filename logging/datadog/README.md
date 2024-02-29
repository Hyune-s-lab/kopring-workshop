# logging-datadog

### how to run?

- http://localhost:8080/swagger-ui/index.html#/
- datadog/src/test/http-request/TestUtilController.http

## 1. boot with datadog agent

### add dependency

- datadog traceId 를 가져오기 위해 추가

```
val datadogApiVersion: String by project
implementation("com.datadoghq:dd-trace-api:${datadogApiVersion}")
```

### add VM options

![image](https://github.com/Hyune-s-lab/kopring-workshop/assets/55722186/36049daf-9642-41c1-bddb-80a45e08c1c2)

```shell
-javaagent:dd-java-agent.jar -Ddd.profiling.enabled=true -Ddd.service=logging-datadog -Ddd.env=dev -Duser.timezone=Asia/Seoul -Ddd.logs.injection=true
```

### call RunException api

```http request
POST http://localhost:8080/test-util/run-exception

HTTP/1.1 500 
Content-Type: application/json
Transfer-Encoding: chunked
Date: Wed, 28 Feb 2024 06:05:10 GMT
Connection: close

{
  "code": "500",
  "message": "call runException api: status=500",
  "timestamp": "2024-02-28T15:05:10.379+0900",
  "traceId": "3742834624401928357"
}
```
