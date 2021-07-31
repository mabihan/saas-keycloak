package com.example.demo.controller

import com.example.demo.api.TenantApi
import com.example.demo.model.*
import com.example.demo.translator.TenantDomainToTenantResponseTranslator
import com.example.demo.translator.TenantRequestToTenantDomainTranslator
import com.example.demo.usecase.CreateTenantUseCase
import com.example.demo.usecase.GetTenantByNamespaceUseCase
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.RestController
import java.security.Principal
import java.util.concurrent.CompletableFuture
import java.util.concurrent.CompletionStage

@RestController
class TenantController(
    private val createTenantUseCase: CreateTenantUseCase,
    private val getTenantByNamespaceUseCase: GetTenantByNamespaceUseCase
    ) : TenantApi {

    private val log: Logger = LoggerFactory.getLogger(TenantController::class.java)

    override fun createTenant(tenantRequest: TenantRequest): MessageResponse {
        log.info("Tenant API starting to creating the tenant: {}", tenantRequest.namespace)

        val tenantDomain = TenantRequestToTenantDomainTranslator().translate(tenantRequest)

        createTenantUseCase.execute(tenantDomain)

        return MessageResponse(TenantHttpResponse.TENANT_CREATED.httpStatus, TenantHttpResponse.TENANT_CREATED.httpMessage)
    }

    override fun getTenant(namespace: String): CompletionStage<TenantResponse> {
        return CompletableFuture
            .supplyAsync { getTenantByNamespaceUseCase.execute(namespace) }
            .thenApplyAsync { TenantDomainToTenantResponseTranslator().translate(it) }
    }

    override fun getAllTenants(): Array<TenantResponse> {
        TODO("Not yet implemented")
    }
}
