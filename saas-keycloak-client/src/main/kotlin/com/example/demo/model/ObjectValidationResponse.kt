package com.example.demo.model

import io.swagger.annotations.ApiModelProperty

data class ObjectValidationResponse(
        @ApiModelProperty(required = true)
        val valid: Boolean,

        @ApiModelProperty(required = true)
        val message: String,

        @ApiModelProperty(required = true)
        val error: String,
)
