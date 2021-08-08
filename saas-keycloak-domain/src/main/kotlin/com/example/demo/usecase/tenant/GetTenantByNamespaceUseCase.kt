package com.example.demo.usecase.tenant

import com.example.demo.gateway.tenant.GetTenantByNamespaceGateway
import com.example.demo.model.TenantDomain
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.inject.Named

@Named
class GetTenantByNamespaceUseCase(private val getTenantByNamespaceGateway: GetTenantByNamespaceGateway) {

    private val log: Logger = LoggerFactory.getLogger(GetTenantByNamespaceUseCase::class.java)

    fun execute(namespace: String): TenantDomain {
        return getTenantByNamespaceGateway.execute(namespace)
    }
}
