package com.example.demo.config.multitenant

import org.slf4j.LoggerFactory

class TenantContextHolder {

    companion object {
        private val log = LoggerFactory.getLogger(TenantContextHolder::class.java)
        const val defaultSchema = "master"
        private val currentSchema = ThreadLocal<String>()

        fun getCurrentSchema(): String {
            return currentSchema.get() ?: return defaultSchema
        }

        fun setCurrentSchema(schema: String?) {
            currentSchema.set(schema)
            log.info("Current schema = {}", schema)
        }

        fun setDefaultSchema() {
            setCurrentSchema(defaultSchema)
        }
    }

}