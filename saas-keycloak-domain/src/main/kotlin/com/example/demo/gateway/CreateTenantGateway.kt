package com.example.demo.gateway

import com.example.demo.model.TenantDomain

interface CreateTenantGateway {

    fun execute(tenantDomain: TenantDomain)

}
