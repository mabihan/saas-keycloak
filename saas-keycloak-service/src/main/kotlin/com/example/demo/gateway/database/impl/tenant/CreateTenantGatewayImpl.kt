package com.example.demo.gateway.database.impl.tenant

import com.example.demo.exception.InternalErrorException
import com.example.demo.gateway.tenant.CreateTenantGateway
import com.example.demo.gateway.database.repository.TenantRepository
import com.example.demo.gateway.database.translator.TenantDBToTenantDomainTranslator
import com.example.demo.gateway.database.translator.TenantDomainToTenantDBTranslator
import com.example.demo.model.TenantCreateDomain
import com.example.demo.model.TenantDomain
import liquibase.integration.spring.SpringLiquibase
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.orm.jpa.EntityManagerFactoryInfo
import org.springframework.transaction.annotation.Isolation
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import javax.inject.Named
import javax.persistence.EntityManager

@Named
@Transactional(readOnly = false, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
class CreateTenantGatewayImpl(  private val tenantRepository: TenantRepository,
                                private val entityManager: EntityManager): CreateTenantGateway {

    private val log: Logger = LoggerFactory.getLogger(CreateTenantGatewayImpl::class.java)

    /**
     * Create a tenant in the master database and create a specific schema
     * TODO : Improve error handling when creating schema and running liquibase
     */
    override fun execute(tenantCreateDomain: TenantCreateDomain): TenantDomain {
        try {
            val tenantDb = tenantRepository.save(TenantDomainToTenantDBTranslator().translate(tenantCreateDomain))

            val query = entityManager.createNativeQuery("CREATE SCHEMA " + tenantDb.schemaName + ";")
            query.executeUpdate()

            val info = entityManager.entityManagerFactory as EntityManagerFactoryInfo
            val dS = info.dataSource

            val liquibase = SpringLiquibase()
            liquibase.dataSource = dS
            liquibase.changeLog = "classpath:/db/changelog/db.changelog-tenants.yaml"
            liquibase.defaultSchema = tenantDb.schemaName
            liquibase.setShouldRun(true)

            liquibase.afterPropertiesSet()

            return TenantDBToTenantDomainTranslator().translate(tenantDb)

        } catch (ex: Exception) {
            log.error("Internal error to create the tenant", ex)
            throw InternalErrorException("Internal error to create the tenant: " + ex.message)
        }
    }

}
