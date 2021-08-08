package com.example.demo.usecase.tenant

import com.example.demo.gateway.tenant.HasTenantCreatedGateway
import javax.inject.Named

@Named
class GetTenantNamespaceValidityUseCase(private val hasTenantCreatedGateway: HasTenantCreatedGateway) {

    fun execute(namespace: String): Boolean {
        return !hasTenantCreatedGateway.execute(namespace)
    }
}
