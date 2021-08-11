package com.example.demo.usecase.tenant

import com.example.demo.gateway.tenant.GetAllTenantsGateway
import com.example.demo.model.TenantDomain
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Pageable
import javax.inject.Named

@Named
class GetAllTenantsUseCase(private val getAllTenantsGateway: GetAllTenantsGateway) {

    fun execute(pageable: Pageable): List<TenantDomain> {
        return getAllTenantsGateway.execute(pageable)
    }
}
