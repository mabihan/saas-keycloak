package com.example.demo.gateway.tenant

interface HasTenantCreatedGateway {

    fun execute(tenantNamespace: String): Boolean

}
