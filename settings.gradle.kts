rootProject.name = "kopring-workshop"

include(
    "web:exception",

    "logging:basic",
    "logging:logback",
    "logging:datadog",

    "db:mongodb",

    "common",
)
