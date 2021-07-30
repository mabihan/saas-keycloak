package com.example.demo.gateway.database.translator

import com.example.demo.gateway.database.model.TenantDB
import com.example.demo.model.TenantDomain
import java.time.LocalDateTime

class TenantDBToTenantDomainTranslator {

    fun translate(tenantDB: TenantDB): TenantDomain {
        return TenantDomain(tenantDB.id, tenantDB.namespace, "todo", LocalDateTime.now(), LocalDateTime.now());
    }

}
