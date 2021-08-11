package com.example.demo.model

import io.swagger.annotations.ApiModelProperty

data class TenantResponse(
        @ApiModelProperty(required = true)
        val uuid: String,

        @ApiModelProperty(required = true)
        val namespace: String,

        @ApiModelProperty(required = true)
        val keycloakRealm: String,

        @ApiModelProperty(required = true)
        val timeZone: String,
)
