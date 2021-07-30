package com.example.demo.gateway

import com.example.demo.model.TenantDomain

interface GetTenantByNamespaceGateway {

    fun execute(namespace: String): TenantDomain
}
