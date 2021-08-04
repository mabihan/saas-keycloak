package com.example.demo.model

enum class TenantHttpResponse(val httpStatus: Int, val httpMessage: String) {

    TENANT_CREATED(201, "created"),
    TENANT_DELETED(200, "deleted"),
    TENANT_UPDATED(202, "updated"),
    TENANT_ALREADY_EXIST(208, "already exist"),
    TENANT_NOT_FOUND(404, "not found"),
    TENANT_BAD_REQUEST(400, "bad request"),
    TENANT_INTERNAL_ERROR(500, "internal error")

}
