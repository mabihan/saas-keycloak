package com.example.demo.usecase.tenant

import com.example.demo.gateway.tenant.GetTenantByNamespaceGateway
import com.example.demo.gateway.tenant.HasTenantCreatedGateway
import com.example.demo.model.TenantDomain
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.inject.Named

@Named
class GetTenantNamespaceAvailabilityUseCase(private val hasTenantCreatedGateway: HasTenantCreatedGateway) {

    private val log: Logger = LoggerFactory.getLogger(GetTenantNamespaceAvailabilityUseCase::class.java)

    fun execute(namespace: String): Boolean {
        return !hasTenantCreatedGateway.execute(namespace)
    }
}
