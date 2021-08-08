package com.example.demo.usecase.user

import com.example.demo.gateway.user.GetUserGateway
import com.example.demo.model.UserDomain
import javax.inject.Named

@Named
class GetUserUseCase(private val getUserGateway: GetUserGateway) {

    fun execute(tenantNamespace: String, id: String): UserDomain {
        return this.getUserGateway.execute(tenantNamespace, id)
    }
}
