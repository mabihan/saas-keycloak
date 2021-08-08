package com.example.demo.gateway.keycloak.realm

import com.example.demo.model.TenantCreateDomain

interface CreateRealmGateway {

    fun execute(tenantCreateDomain: TenantCreateDomain)

}
