package com.example.demo.model

import io.swagger.annotations.ApiModelProperty

data class TenantValidationResponse(
        @ApiModelProperty(required = true)
        val valid: Boolean,

        @ApiModelProperty(required = true)
        val message: String,
)
