package com.example.demo.gateway.keycloak.model

import kotlin.String

// https://github.com/IBM/cloud-native-starter/blob/master/security/IKS/quarkus-realm.json

data class KeycloakRealm(
    val id: String,
    val realm: String,
    val smtpServer: KeycloakSmtpServer
)
