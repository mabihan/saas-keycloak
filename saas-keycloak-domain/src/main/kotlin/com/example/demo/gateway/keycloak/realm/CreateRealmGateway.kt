package com.example.demo.gateway.keycloak.realm

import com.example.demo.model.TenantDomain

interface CreateRealmGateway {

    fun execute(tenantDomain: TenantDomain)

}
