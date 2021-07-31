package com.example.demo.config.multitenant

import com.example.demo.gateway.database.model.TenantDB
import com.example.demo.gateway.database.repository.TenantRepository
import liquibase.integration.spring.MultiTenantSpringLiquibase
import liquibase.integration.spring.SpringLiquibase
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import java.util.stream.Collectors
import javax.sql.DataSource

@Component
class LiquibaseConfig {

    @Value("\${spring.liquibase.default-schema}")
    val defaultSchema = ""

    @Bean
    fun liquibase(dataSource: DataSource): SpringLiquibase {
        val liquibase = SpringLiquibase()
        liquibase.dataSource = dataSource
        liquibase.changeLog = "classpath:/db/changelog/db.changelog-core.yaml"
        liquibase.defaultSchema = defaultSchema
        liquibase.setShouldRun(true)
        return liquibase
    }

    @Bean
    fun liquibaseMultiTenant(dataSource: DataSource, tenantRepository: TenantRepository): MultiTenantSpringLiquibase {
        val liquibase = MultiTenantSpringLiquibase()
        liquibase.dataSource = dataSource
        liquibase.changeLog = "classpath:/db/changelog/db.changelog-tenants.yaml"
        val schemas = tenantRepository.findAll().stream().map(TenantDB::schema).collect(Collectors.toList())
        liquibase.schemas = schemas
        liquibase.isShouldRun = true
        return liquibase
    }
}
