package com.example.demo.gateway.tenant

import com.example.demo.model.TenantDomain

interface CreateTenantGateway {

    fun execute(tenantDomain: TenantDomain)

}
