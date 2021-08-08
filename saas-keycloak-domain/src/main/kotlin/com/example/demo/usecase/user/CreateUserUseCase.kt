package com.example.demo.usecase.user

import com.example.demo.exception.AlreadyExistException
import com.example.demo.exception.tenant.TenantAlreadyExistException
import com.example.demo.exception.tenant.TenantNotFoudException
import com.example.demo.exception.user.UserAlreadyExistException
import com.example.demo.gateway.tenant.HasTenantCreatedGateway
import com.example.demo.gateway.user.CreateUserGateway
import com.example.demo.gateway.user.DoesUserExistGateway
import com.example.demo.model.UserCreateDomain
import com.example.demo.model.UserDomain
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.inject.Named

@Named
class CreateUserUseCase(private val hasTenantCreatedGateway: HasTenantCreatedGateway,
                        private val doesUserExistGateway: DoesUserExistGateway,
                        private val createUserGateway: CreateUserGateway) {

    private val log: Logger = LoggerFactory.getLogger(CreateUserUseCase::class.java)

    fun execute(tenantNamespace: String, userCreateDomain: UserCreateDomain) {

        if (!hasTenantCreatedGateway.execute(tenantNamespace)) {
            throw TenantNotFoudException("Tenant $tenantNamespace not found.")
        }

        if (doesUserExistGateway.execute(tenantNamespace, userCreateDomain)){
            log.info("User already exist")
            throw UserAlreadyExistException("User already exist")
        }

        createUserGateway.execute(tenantNamespace, userCreateDomain)
    }
}
