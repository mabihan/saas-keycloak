package com.example.demo.gateway.keycloak.user

import com.example.demo.model.UserCreateDomain

interface DoesUserExistGateway {

    fun execute(tenantNamespace: String, userCreateDomain: UserCreateDomain): Boolean
    fun execute(tenantNamespace: String, username: String?, email: String?): Boolean
}
