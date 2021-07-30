package com.example.demo.config.multitenant

import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties
import org.springframework.orm.jpa.JpaVendorAdapter
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider
import org.hibernate.context.spi.CurrentTenantIdentifierResolver
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import java.util.HashMap
import org.hibernate.MultiTenancyStrategy
import com.example.demo.DemoCreateProductApplication
import org.hibernate.cfg.Environment
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource

@Configuration
class DatabaseConfig(private val jpaProperties: JpaProperties) {

    @Bean
    fun jpaVendorAdapter(): JpaVendorAdapter {
        return HibernateJpaVendorAdapter()
    }

    @Bean
    fun entityManagerFactory(dataSource: DataSource,
                             multiTenantConnectionProvider: MultiTenantConnectionProvider,
                             currentTenantIdentifierResolver: CurrentTenantIdentifierResolver): LocalContainerEntityManagerFactoryBean {
        val jpaPropertiesMap: MutableMap<String, Any?> = HashMap()
        jpaPropertiesMap.putAll(jpaProperties.properties)
        jpaPropertiesMap[Environment.MULTI_TENANT] = MultiTenancyStrategy.SCHEMA
        jpaPropertiesMap[Environment.MULTI_TENANT_CONNECTION_PROVIDER] = multiTenantConnectionProvider
        jpaPropertiesMap[Environment.MULTI_TENANT_IDENTIFIER_RESOLVER] = currentTenantIdentifierResolver

        val em = LocalContainerEntityManagerFactoryBean()
        em.dataSource = dataSource
        em.setPackagesToScan(DemoCreateProductApplication::class.java.getPackage().name)
        em.jpaVendorAdapter = jpaVendorAdapter()
        em.setJpaPropertyMap(jpaPropertiesMap)
        return em
    }

}