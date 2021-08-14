package com.example.demo.usecase.tenant

import com.example.demo.gateway.tenant.GetTenantByUuidGateway
import com.example.demo.model.TenantDomain
import javax.inject.Named

@Named
class GetTenantByUuidUseCase(private val getTenantByUuidGateway: GetTenantByUuidGateway) {

    fun execute(uuid: String): TenantDomain {
        return getTenantByUuidGateway.execute(uuid)
    }
}
