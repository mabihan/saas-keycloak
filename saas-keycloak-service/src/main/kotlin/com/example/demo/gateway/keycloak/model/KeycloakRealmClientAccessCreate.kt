package com.example.demo.gateway.keycloak.model

data class KeycloakRealmClientAccessCreate(
    val view: Boolean,
    val configure: Boolean,
    val manage: Boolean
)
