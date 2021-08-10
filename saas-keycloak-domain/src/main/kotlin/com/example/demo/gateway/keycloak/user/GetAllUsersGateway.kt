package com.example.demo.gateway.keycloak.user

import com.example.demo.model.UserDomain
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface GetAllUsersGateway {

    fun execute(tenantUuid: String, pageable: Pageable): Page<UserDomain>

}
