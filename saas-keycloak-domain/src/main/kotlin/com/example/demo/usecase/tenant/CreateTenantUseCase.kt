package com.example.demo.usecase.tenant

import com.example.demo.exception.AlreadyExistException
import com.example.demo.gateway.keycloak.client.CreateClientGateway
import com.example.demo.gateway.keycloak.realm.CreateRealmGateway
import com.example.demo.gateway.tenant.CreateTenantGateway
import com.example.demo.gateway.tenant.HasTenantCreatedGateway
import com.example.demo.model.TenantCreateDomain
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.inject.Named

@Named
class CreateTenantUseCase(private val hasTenantCreatedGateway: HasTenantCreatedGateway,
                          private val createTenantGateway: CreateTenantGateway,
                          private val createRealmGateway: CreateRealmGateway,
                          private val createClientGateway: CreateClientGateway ) {

    private val log: Logger = LoggerFactory.getLogger(CreateTenantUseCase::class.java)

    fun execute(tenantCreateDomain: TenantCreateDomain) {
        if (hasTenantCreatedGateway.execute(tenantCreateDomain.namespace)) {
            log.info("Tenant already exist")
            throw AlreadyExistException("Tenant already exist")
        }
        createRealmGateway.execute(tenantCreateDomain)
        createClientGateway.execute(tenantCreateDomain.sanitizedNamespace)
        createTenantGateway.execute(tenantCreateDomain)
    }
}
