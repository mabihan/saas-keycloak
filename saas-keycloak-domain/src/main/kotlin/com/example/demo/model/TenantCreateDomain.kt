package com.example.demo.model

import java.time.ZoneOffset

data class TenantCreateDomain(
        val namespace: String,
        var sanitizedNamespace: String,
        val timeZone: ZoneOffset,
)
