package com.example.demo.gateway.keycloak.impl.user

import com.example.demo.exception.tenant.TenantUuidMalformedException
import com.example.demo.gateway.database.repository.TenantRepository
import com.example.demo.gateway.keycloak.repository.UserRepository
import com.example.demo.gateway.keycloak.user.DoesUserExistGateway
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.data.domain.Pageable
import java.util.*
import javax.inject.Named

@Named
class DoesUserExistGatewayImpl(private val userRepository: UserRepository,
                               private val tenantRepository: TenantRepository): DoesUserExistGateway {

    override fun executeForEmail(tenantUuid: String, email: String): Boolean {
        var userAlreadyExist = false

        try {
            val validUuid = UUID.fromString(tenantUuid)

            try {

                val tenantDb = this.tenantRepository.findByUuid(validUuid)

                val keycloakUserByEmail = this.userRepository.findByEmail(
                    tenantDb.namespace,
                    email,
                    Pageable.ofSize(1)
                )

                if (keycloakUserByEmail.totalElements > 0) {
                    userAlreadyExist = true
                }

                return userAlreadyExist

            } catch (ex: EmptyResultDataAccessException) {
                return false
            }
        } catch (ex: IllegalArgumentException ) {
            throw TenantUuidMalformedException("$tenantUuid is not a valid UUID")
        }
    }

    override fun executeForUuid(tenantUuid: String, uuid: String): Boolean {

        try {
            val tenantDb = this.tenantRepository.findByNamespace(tenantUuid)

            try {
                this.userRepository.findById(
                    tenantDb.namespace,
                    uuid
                )
            } catch (ex: EmptyResultDataAccessException) {
                return false
            }

            return true

        } catch (ex: EmptyResultDataAccessException) {
            return false
        }
    }

    override fun executeForUsername(tenantUuid: String, username: String): Boolean {
        var userAlreadyExist = false

        try {
            val tenantDb = this.tenantRepository.findByNamespace(tenantUuid)

            val keycloakUserByEmail = this.userRepository.findByUsername(
                tenantDb.namespace,
                username,
                Pageable.ofSize(1)
            )

            if (keycloakUserByEmail.totalElements > 0) {
                userAlreadyExist = true
            }

            return userAlreadyExist

        } catch (ex: EmptyResultDataAccessException) {
            return false
        }
    }
}
