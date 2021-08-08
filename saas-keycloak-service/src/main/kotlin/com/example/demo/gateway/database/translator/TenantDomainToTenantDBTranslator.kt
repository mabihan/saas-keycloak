package com.example.demo.gateway.database.translator

import com.example.demo.gateway.database.model.TenantDB
import com.example.demo.model.TenantCreateDomain

class TenantDomainToTenantDBTranslator {

    fun translate(tenantCreateDomain: TenantCreateDomain): TenantDB {
        return TenantDB(
            namespace = tenantCreateDomain.namespace,
            keycloakRealm = tenantCreateDomain.sanitizedNamespace,
            schemaName = tenantCreateDomain.sanitizedNamespace,
            timeZone = tenantCreateDomain.timeZone.toString())
    }

}
