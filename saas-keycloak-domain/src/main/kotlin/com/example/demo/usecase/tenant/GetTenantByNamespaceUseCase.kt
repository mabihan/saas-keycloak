package com.example.demo.usecase.tenant

import com.example.demo.gateway.tenant.GetTenantByNamespaceGateway
import com.example.demo.model.TenantDomain
import javax.inject.Named

@Named
class GetTenantByNamespaceUseCase(private val getTenantByNamespaceGateway: GetTenantByNamespaceGateway) {

    fun execute(namespace: String): TenantDomain {
        return getTenantByNamespaceGateway.execute(namespace)
    }
}
