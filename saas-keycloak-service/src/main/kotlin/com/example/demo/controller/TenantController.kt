package com.example.demo.controller

import com.example.demo.api.TenantApi
import com.example.demo.model.*
import com.example.demo.translator.TenantDomainToTenantResponseTranslator
import com.example.demo.translator.TenantRequestToTenantDomainTranslator
import com.example.demo.usecase.tenant.CreateTenantUseCase
import com.example.demo.usecase.tenant.DeleteAllTenantsUseCase
import com.example.demo.usecase.tenant.GetAllTenantsUseCase
import com.example.demo.usecase.tenant.GetTenantByNamespaceUseCase
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.data.domain.PageRequest
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.CompletableFuture
import java.util.concurrent.CompletionStage
import java.util.stream.Collectors.toList

@RestController
class TenantController(
    private val createTenantUseCase: CreateTenantUseCase,
    private val getTenantByNamespaceUseCase: GetTenantByNamespaceUseCase,
    private val getAllTenantsUseCase: GetAllTenantsUseCase,
    private val deleteAllTenantsUseCase: DeleteAllTenantsUseCase
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

    override fun getAllTenants(@RequestParam(defaultValue = "20") size: Int,
                               @RequestParam(defaultValue = "0") page: Int): List<TenantResponse> {

        val pageRequest = PageRequest.of(page, size)

        return getAllTenantsUseCase.execute(pageRequest).stream()
            .map { tenantDomain -> TenantDomainToTenantResponseTranslator().translate(tenantDomain) }
            .collect(toList())
    }

    override fun deleteAllTenants(): MessageResponse {
        deleteAllTenantsUseCase.execute()
        return MessageResponse(TenantHttpResponse.TENANT_DELETED.httpStatus, TenantHttpResponse.TENANT_DELETED.httpMessage)
    }

    override fun getNamespaceValidity(namespace: String): CompletionStage<TenantValidationResponse> {
        TODO("Not yet implemented")
    }
}
