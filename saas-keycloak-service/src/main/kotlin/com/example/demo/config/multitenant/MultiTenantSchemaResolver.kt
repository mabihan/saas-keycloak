package com.example.demo.config.multitenant

import org.hibernate.context.spi.CurrentTenantIdentifierResolver
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class MultiTenantSchemaResolver : CurrentTenantIdentifierResolver {

    @Value("\${spring.liquibase.default-schema}")
    val defaultSchema = ""

    override fun resolveCurrentTenantIdentifier(): String {
        return TenantContextHolder.getCurrentSchema(defaultSchema)
    }

    override fun validateExistingCurrentSessions(): Boolean {
        return true
    }

}