package com.example.demo.gateway.keycloak.model

import kotlin.String

// https://github.com/IBM/cloud-native-starter/blob/master/security/IKS/quarkus-realm.json

data class KeycloakRealmCreate(
    val id: String,
    val realm: String,
    val displayName: String,
    val smtpServer: KeycloakSmtpServer
)
