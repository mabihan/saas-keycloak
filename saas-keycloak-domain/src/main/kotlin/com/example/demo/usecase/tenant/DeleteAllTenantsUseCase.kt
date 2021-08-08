package com.example.demo.usecase.tenant

import com.example.demo.gateway.tenant.DeleteAllTenantsGateway
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.inject.Named

@Named
class DeleteAllTenantsUseCase(private val deleteAllTenantsGateway: DeleteAllTenantsGateway) {

    private val log: Logger = LoggerFactory.getLogger(DeleteAllTenantsUseCase::class.java)

    fun execute() {
        return deleteAllTenantsGateway.execute()
    }
}
