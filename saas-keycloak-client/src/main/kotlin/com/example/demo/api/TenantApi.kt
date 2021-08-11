package com.example.demo.api

import com.example.demo.model.*
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.concurrent.CompletionStage
import javax.validation.Valid

@RequestMapping("/api/v1/")
@Api(tags = ["Tenant"])
interface TenantApi {

    @ApiOperation(value = "Create a tenant", notes = "Create a tenant")
    @ApiResponses(value = [ApiResponse(code = 201, message = "Created", response = MessageResponse::class),
        ApiResponse(code = 208, message = "Tenant already exist", response = MessageResponse::class),
        ApiResponse(code = 400, message = "Bad request", response = MessageResponse::class),
        ApiResponse(code = 500, message = "Internal error to create the tenant", response = MessageResponse::class)])
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/tenant")
    fun createTenant(@Valid @RequestBody tenantRequest: TenantRequest): CompletionStage<TenantResponse>

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/tenant/new/validate")
    fun getNewNamespaceValidity(namespace: String): CompletionStage<ObjectValidationResponse>

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/tenant/validate")
    fun getNamespaceValidity(namespace: String): CompletionStage<ObjectValidationResponse>

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/tenant")
    fun getTenant(namespace: String): CompletionStage<TenantResponse>

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/tenants")
    fun getAllTenants(size: Int, page: Int): List<TenantResponse>

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/tenants")
    fun deleteAllTenants(): MessageResponse
}
