package com.example.kopringworkshop.scenario1.model

enum class Service(
    val termVersions: Set<TermVersion>
) {
    A001(setOf(TermVersion.V20240701, TermVersion.V20240705)),
    ;

    enum class TermVersion {
        V20240701,
        V20240705
    }
}
