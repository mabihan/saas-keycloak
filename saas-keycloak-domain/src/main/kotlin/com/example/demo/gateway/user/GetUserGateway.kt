package com.example.demo.gateway.user

import com.example.demo.model.UserDomain

interface GetUserGateway {

    fun execute(tenantNamespace: String, id: String): UserDomain

}
