package com.example.demo.gateway.tenant

import com.example.demo.model.TenantDomain

interface GetTenantByUuidGateway {

    fun execute(uuid: String): TenantDomain
}
