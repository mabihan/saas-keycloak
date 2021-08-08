package com.example.demo.gateway.user

import com.example.demo.model.UserCreateDomain

interface CreateUserGateway {

    fun execute(tenantNamespace: String, userCreateDomain: UserCreateDomain)

}
