package com.example.demo.gateway.tenant

interface DoesTenantNamespaceExistGateway {

    fun execute(namespace: String): Boolean

}
