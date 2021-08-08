package com.example.demo.model

import com.fasterxml.jackson.annotation.JsonFormat
import io.swagger.annotations.ApiModelProperty
import java.time.LocalDateTime

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
