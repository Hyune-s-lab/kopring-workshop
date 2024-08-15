# test:template

## WebTestSupport

```kotlin
@ActiveProfiles(value = ["test"])
@TestConstructor(autowireMode = AutowireMode.ALL)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
abstract class WebTestSupport 
```

## ScenarioTestSupport

```kotlin
@ActiveProfiles(value = ["test"])
@TestMethodOrder(MethodOrderer.DisplayName::class)
@TestInstance(Lifecycle.PER_CLASS)
abstract class ScenarioTestSupport
```

![image](https://github.com/user-attachments/assets/8c5ce47d-8c0d-4355-b383-4157a65c8153)
