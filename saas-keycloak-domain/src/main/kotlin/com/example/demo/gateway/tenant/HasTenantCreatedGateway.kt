package com.example.demo.gateway.tenant

import com.example.demo.model.TenantDomain

interface HasTenantCreatedGateway {

    fun execute(tenantNamespace: String): Boolean

}
