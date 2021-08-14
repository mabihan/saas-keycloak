package com.example.demo.translator

import com.example.demo.model.TenantDomain
import com.example.demo.model.TenantResponse
import java.util.stream.Collectors

class TenantDomainToTenantResponseTranslator {

    fun translate(tenantDomain: TenantDomain): TenantResponse {
        return TenantResponse(
            uuid = tenantDomain.uuid,
            namespace = tenantDomain.namespace,
            keycloakRealm = tenantDomain.keycloakRealm,
            timeZone = tenantDomain.timeZone.toString(),
            clients = tenantDomain.clients.stream()
                .map { clientDomain -> ClientDomainToClientResponseTranslator().translate(clientDomain) }
                .collect(Collectors.toList())
        )
    }

}
