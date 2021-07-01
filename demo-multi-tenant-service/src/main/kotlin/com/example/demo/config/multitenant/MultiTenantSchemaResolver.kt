package com.example.demo.config.multitenant

import org.hibernate.context.spi.CurrentTenantIdentifierResolver
import org.springframework.stereotype.Component

@Component
class MultiTenantSchemaResolver : CurrentTenantIdentifierResolver {

    override fun resolveCurrentTenantIdentifier(): String {
        return TenantContextHolder.getCurrentSchema()
    }

    override fun validateExistingCurrentSessions(): Boolean {
        return true
    }

}