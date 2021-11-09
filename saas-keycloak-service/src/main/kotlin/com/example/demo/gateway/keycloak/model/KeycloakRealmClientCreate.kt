package com.example.demo.gateway.keycloak.model

data class KeycloakRealmClientCreate(
    val name: String,
    val redirectUris: List<String>,
    val webOrigins: List<String>,
    val publicClient: Boolean,
)
