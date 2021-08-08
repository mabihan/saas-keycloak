package com.example.demo.gateway.tenant

import com.example.demo.model.TenantCreateDomain

interface CreateTenantGateway {

    fun execute(tenantCreateDomain: TenantCreateDomain)

}
