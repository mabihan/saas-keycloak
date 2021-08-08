package com.example.demo.gateway.keycloak.realm

interface DeleteRealmGateway {

    fun execute(realm: String)

}
