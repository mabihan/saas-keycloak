package com.example.demo.gateway.tenant

import com.example.demo.model.TenantDomain

interface GetTenantByNamespaceGateway {

    fun execute(namespace: String): TenantDomain
}
