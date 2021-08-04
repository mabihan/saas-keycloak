package com.example.demo.gateway

import com.example.demo.model.TenantDomain
import org.springframework.data.domain.Pageable

interface GetAllTenantsGateway {

    fun execute(pageable: Pageable): List<TenantDomain>
}
