package com.example.demo.gateway

import com.example.demo.model.TenantDomain

interface CreateRealmGateway {

    fun execute(tenantDomain: TenantDomain)

}
