package com.example.demo.gateway.tenant

import com.example.demo.model.TenantCreateDomain
import com.example.demo.model.TenantDomain

interface CreateTenantGateway {

    fun execute(tenantCreateDomain: TenantCreateDomain): TenantDomain

}
