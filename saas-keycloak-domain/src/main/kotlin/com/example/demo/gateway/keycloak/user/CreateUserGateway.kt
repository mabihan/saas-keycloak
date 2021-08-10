package com.example.demo.gateway.keycloak.user

import com.example.demo.model.UserCreateDomain
import com.example.demo.model.UserDomain

interface CreateUserGateway {

    fun execute(tenantNamespace: String, userCreateDomain: UserCreateDomain): UserDomain

}
