package com.example.demo.config.multitenant

import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider
import kotlin.Throws
import java.sql.SQLException
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.sql.Connection
import javax.sql.DataSource

@Component
class MultiTenantSchemaProvider(private val dataSource: DataSource) : MultiTenantConnectionProvider {

    private val log = LoggerFactory.getLogger(MultiTenantSchemaProvider::class.java)

    override fun isUnwrappableAs(unwrapType: Class<*>?): Boolean {
        return false
    }

    override fun <T> unwrap(unwrapType: Class<T>): T? {
        return null
    }

    @Throws(SQLException::class)
    override fun getAnyConnection(): Connection {
        return dataSource.connection
    }

    @Throws(SQLException::class)
    override fun releaseAnyConnection(connection: Connection) {
        connection.close()
    }

    @Throws(SQLException::class)
    override fun getConnection(tenantIdentifier: String): Connection {
        log.info("getConnection for {}", tenantIdentifier)
        val connection = anyConnection
        connection.catalog = tenantIdentifier
        connection.schema = tenantIdentifier
        log.debug("connection.catalog = {}, connection.schema = {}", connection.catalog, connection.schema)
        return connection
    }

    @Throws(SQLException::class)
    override fun releaseConnection(tenantIdentifier: String, connection: Connection) {
        releaseAnyConnection(connection)
    }

    override fun supportsAggressiveRelease(): Boolean {
        return true
    }

}