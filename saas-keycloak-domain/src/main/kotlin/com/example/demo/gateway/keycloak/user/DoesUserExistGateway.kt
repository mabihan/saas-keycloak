package com.example.demo.gateway.keycloak.user

interface DoesUserExistGateway {

    fun executeForEmail(tenantUuid: String, email: String): Boolean
    fun executeForUuid(tenantUuid: String, uuid: String): Boolean
    fun executeForUsername(tenantUuid: String, username: String): Boolean
}
