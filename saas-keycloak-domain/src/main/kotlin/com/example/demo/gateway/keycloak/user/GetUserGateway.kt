package com.example.demo.gateway.keycloak.user

import com.example.demo.model.UserDomain

interface GetUserGateway {

    fun execute(tenantUuid: String, id: String): UserDomain

}
