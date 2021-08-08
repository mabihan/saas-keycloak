package com.example.demo.gateway.keycloak.impl.user

import com.example.demo.exception.tenant.TenantNotFoudException
import com.example.demo.gateway.database.repository.TenantRepository
import com.example.demo.gateway.keycloak.repository.UserRepository
import com.example.demo.gateway.keycloak.user.DoesUserExistGateway
import com.example.demo.model.UserCreateDomain
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.data.domain.Pageable
import javax.inject.Named

@Named
class DoesUserExistGatewayImpl(private val userRepository: UserRepository,
                               private val tenantRepository: TenantRepository): DoesUserExistGateway {

    override fun execute(tenantNamespace: String, userCreateDomain: UserCreateDomain): Boolean {

        try {
            var userAlreadyExist = false

            val tenantDb = this.tenantRepository.findByNamespace(tenantNamespace)

            val keycloakUserByEmail = this.userRepository.findByEmail(
                tenantDb.namespace,
                userCreateDomain.email,
                Pageable.ofSize(1)
            )

            val keycloakUserByUsername = this.userRepository.findByUsername(
                tenantDb.namespace,
                userCreateDomain.username,
                Pageable.ofSize(1)
            )

            if (keycloakUserByEmail.totalElements > 0) {
                userAlreadyExist = true
            }

            if (keycloakUserByUsername.totalElements > 0) {
                userAlreadyExist = true
            }

            return userAlreadyExist
        } catch (ex: EmptyResultDataAccessException) {
            throw TenantNotFoudException("Tenant $tenantNamespace not found." )
        }
    }


    override fun execute(tenantNamespace: String, username: String?, email: String?): Boolean {

        var userAlreadyExist = false

        try {
            val tenantDb = this.tenantRepository.findByNamespace(tenantNamespace)

            if (email != null) {
                if (email.isNotEmpty()) {
                    val keycloakUserByEmail = this.userRepository.findByEmail(
                        tenantDb.namespace,
                        email,
                        Pageable.ofSize(1)
                    )

                    if (keycloakUserByEmail.totalElements > 0) {
                        userAlreadyExist = true
                    }
                }
            }


            if (username != null) {
                if (username.isNotEmpty()) {
                    val keycloakUserByEmail = this.userRepository.findByEmail(
                        tenantDb.namespace,
                        username,
                        Pageable.ofSize(1)
                    )

                    if (keycloakUserByEmail.totalElements > 0) {
                        userAlreadyExist = true
                    }
                }
            }

            return userAlreadyExist

        } catch (ex: EmptyResultDataAccessException) {
            throw TenantNotFoudException("Tenant $tenantNamespace not found." )
        }
    }
}
