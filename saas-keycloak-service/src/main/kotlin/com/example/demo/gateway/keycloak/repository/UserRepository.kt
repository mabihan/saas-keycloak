package com.example.demo.gateway.keycloak.repository

import com.example.demo.gateway.keycloak.model.KeycloakUser
import com.example.demo.gateway.keycloak.model.KeycloakUserCreate
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository

@Repository
interface UserRepository {

    fun save(realm: String, userToCreate: KeycloakUserCreate): String

    fun sendVerificationEmail(realm: String, id: String)

    fun search(realm: String, search: String): List<KeycloakUser>

    fun findById(realm: String, id: String): KeycloakUser?

    fun findByUsername(realm: String, username: String, pageable: Pageable): Page<KeycloakUser>

    fun findByEmail(realm: String, email: String, pageable: Pageable): Page<KeycloakUser>

    fun findAll(realm: String, pageable: Pageable): Page<KeycloakUser>
}
