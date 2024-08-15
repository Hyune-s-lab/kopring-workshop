rootProject.name = "kopring-workshop"

include(
    "web:exception",

    "logging:basic",
    "logging:logback",
    "logging:datadog",

    "db:mongodb",
    "db:mysqldb",

    "batch:scenario1",

    "test:testcontainers",
    "test:template",

    "external-api:wiremock",

    "common",
)
