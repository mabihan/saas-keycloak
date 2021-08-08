package com.example.demo.usecase.user

import com.example.demo.gateway.user.GetAllUsersGateway
import com.example.demo.model.UserDomain
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import javax.inject.Named

@Named
class GetAllUsersUseCase(private val getAllUsersGateway: GetAllUsersGateway) {

    fun execute(tenantNamespace: String, pageable: Pageable): Page<UserDomain> {
        return this.getAllUsersGateway.execute(tenantNamespace, pageable)
    }
}
