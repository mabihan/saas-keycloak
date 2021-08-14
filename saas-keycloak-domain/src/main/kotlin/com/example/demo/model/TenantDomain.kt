package com.example.demo.model

import java.time.ZoneOffset

data class TenantDomain(
        val uuid: String,
        val namespace: String,
        var schemaName: String,
        var keycloakRealm: String,
        val timeZone: ZoneOffset,
        val clients: List<ClientDomain>
)
