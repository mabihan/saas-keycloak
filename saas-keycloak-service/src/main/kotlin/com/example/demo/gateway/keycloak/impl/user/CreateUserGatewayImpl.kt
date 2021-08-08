package com.example.demo.gateway.keycloak.impl.user

import com.example.demo.exception.InternalErrorException
import com.example.demo.exception.user.UserAlreadyExistException
import com.example.demo.gateway.keycloak.repository.RealmRepository
import com.example.demo.gateway.keycloak.repository.UserRepository
import com.example.demo.gateway.keycloak.translator.UserDomainToKeycloakUserCreateTranslator
import com.example.demo.gateway.user.CreateUserGateway
import com.example.demo.model.UserCreateDomain
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.reactive.function.client.WebClientResponseException
import javax.inject.Named

@Named
class CreateUserGatewayImpl(private val userRepository: UserRepository): CreateUserGateway {

    override fun execute(tenantNamespace: String, userCreateDomain: UserCreateDomain) {

        val keycloakUserId =  this.userRepository.save(tenantNamespace, UserDomainToKeycloakUserCreateTranslator()
            .translate(userCreateDomain))

        if (keycloakUserId.isNotEmpty()) {
            this.userRepository.sendVerificationEmail(tenantNamespace, keycloakUserId)
        } else {
            throw InternalErrorException("Internal error while creating user ${userCreateDomain.username}")
        }
    }
}
