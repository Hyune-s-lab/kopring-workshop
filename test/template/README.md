# test:template

## WebTestSupport

```kotlin
@ActiveProfiles(value = ["test"])
@TestConstructor(autowireMode = AutowireMode.ALL)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
abstract class WebTestSupport 
```
