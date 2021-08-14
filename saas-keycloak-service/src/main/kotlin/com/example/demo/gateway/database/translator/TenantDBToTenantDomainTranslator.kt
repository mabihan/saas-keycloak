package com.example.demo.gateway.database.translator

import com.example.demo.gateway.database.model.TenantDB
import com.example.demo.model.ClientDomain
import com.example.demo.model.TenantDomain
import java.time.ZoneOffset

class TenantDBToTenantDomainTranslator {

    fun translate(tenantDB: TenantDB, clients: List<ClientDomain>): TenantDomain {
        return TenantDomain(
            uuid = tenantDB.uuid.toString(),
            namespace = tenantDB.namespace,
            schemaName = tenantDB.schemaName,
            keycloakRealm = tenantDB.keycloakRealm,
            timeZone = ZoneOffset.of(tenantDB.timeZone),
            clients = clients
        )
    }

}
