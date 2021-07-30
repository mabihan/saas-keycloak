package com.example.demo.gateway.database.translator

import com.example.demo.gateway.database.model.TenantDB
import com.example.demo.model.TenantDomain

class TenantDomainToTenantDBTranslator {

    fun translate(tenantDomain: TenantDomain): TenantDB {
        return TenantDB(tenantDomain.id, tenantDomain.namespace, this.sanitizeSchemaName(tenantDomain.namespace))
    }

    /**
     * Sanitize a string which will be used as schema name. Remove non-alphanumeric characters.
     * @return sanitized string
     */
    private fun sanitizeSchemaName(dirty: String): String {
        return "schema_" + dirty.replace("[^a-zA-Z0-9]", "")
    }
}
