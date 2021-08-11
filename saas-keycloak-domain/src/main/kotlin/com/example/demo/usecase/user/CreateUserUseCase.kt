package com.example.demo.usecase.user

import com.example.demo.exception.tenant.TenantNotFoundException
import com.example.demo.exception.tenant.TenantUuidMalformedException
import com.example.demo.exception.user.UserAlreadyExistException
import com.example.demo.gateway.tenant.DoesTenantUuidExistGateway
import com.example.demo.gateway.keycloak.user.CreateUserGateway
import com.example.demo.gateway.keycloak.user.DoesUserExistGateway
import com.example.demo.gateway.tenant.GetTenantByUuidGateway
import com.example.demo.model.UserCreateDomain
import com.example.demo.model.UserDomain
import java.util.*
import javax.inject.Named

@Named
class CreateUserUseCase(private val doesTenantUuidExistGateway: DoesTenantUuidExistGateway,
                        private val getTenantByUuidGateway: GetTenantByUuidGateway,
                        private val doesUserExistGateway: DoesUserExistGateway,
                        private val createUserGateway: CreateUserGateway) {

    fun execute(tenantUuid: String, userCreateDomain: UserCreateDomain): UserDomain {

        try {
            val validUuid = UUID.fromString(tenantUuid)

            if (!doesTenantUuidExistGateway.execute(validUuid)) {
                throw TenantNotFoundException("Tenant $tenantUuid not found.")
            }

            if (doesUserExistGateway.executeForEmail(tenantUuid, userCreateDomain.email)) {
                throw UserAlreadyExistException("Email ${userCreateDomain.email} is already taken.")
            }

            if (doesUserExistGateway.executeForUsername(tenantUuid, userCreateDomain.username)) {
                throw UserAlreadyExistException("Username ${userCreateDomain.username} is already taken.")
            }

            val keycloakRealmName = this.getTenantByUuidGateway.execute(tenantUuid).keycloakRealm

            return createUserGateway.execute(keycloakRealmName, userCreateDomain)

        } catch (ex: IllegalArgumentException ) {
            throw TenantUuidMalformedException("$tenantUuid is not a valid UUID")
        }
    }
}
