package com.example.demo.usecase

import com.example.demo.exception.AlreadyExistException
import com.example.demo.gateway.CreateTenantGateway
import com.example.demo.gateway.HasTenantCreatedGateway
import com.example.demo.model.TenantDomain
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.inject.Named

@Named
class CreateTenantUseCase(private val hasTenantCreatedGateway: HasTenantCreatedGateway,
                          private val createTenantGateway: CreateTenantGateway) {

    private val log: Logger = LoggerFactory.getLogger(CreateTenantUseCase::class.java)

    fun execute(tenantDomain: TenantDomain) {
        if (hasTenantCreatedGateway.execute(tenantDomain)) {
            log.info("Tenant already exist")
            throw AlreadyExistException("Tenant already exist")
        }

        createTenantGateway.execute(tenantDomain)
    }

}
