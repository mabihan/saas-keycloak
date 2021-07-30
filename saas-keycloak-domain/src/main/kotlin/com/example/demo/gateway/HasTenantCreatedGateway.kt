package com.example.demo.gateway

import com.example.demo.model.TenantDomain

interface HasTenantCreatedGateway {

    fun execute(tenantDomain: TenantDomain): Boolean

}
