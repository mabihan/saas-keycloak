package com.example.demo.gateway.realm

import com.example.demo.model.TenantDomain

interface CreateRealmGateway {

    fun execute(tenantDomain: TenantDomain)

}
