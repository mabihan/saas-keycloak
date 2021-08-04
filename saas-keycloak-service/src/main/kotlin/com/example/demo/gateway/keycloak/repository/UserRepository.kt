package com.example.demo.gateway.keycloak.repository

import com.example.demo.gateway.keycloak.model.KeycloakUser
import org.springframework.stereotype.Repository

@Repository
interface UserRepository {

    fun save(user: KeycloakUser): KeycloakUser
}
