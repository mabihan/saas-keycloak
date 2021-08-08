package com.example.demo.translator

import com.example.demo.model.TenantDomain
import com.example.demo.model.TenantResponse

class TenantDomainToTenantResponseTranslator {

    fun translate(tenantDomain: TenantDomain): TenantResponse {
        return TenantResponse(
            uuid = tenantDomain.uuid,
            namespace = tenantDomain.namespace,
            keycloakRealm = tenantDomain.keycloakRealm,
            timeZone = tenantDomain.timeZone.toString()
        )
    }

}
