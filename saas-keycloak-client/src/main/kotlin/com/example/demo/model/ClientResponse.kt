package com.example.demo.model

import io.swagger.annotations.ApiModelProperty

data class ClientResponse(
        @ApiModelProperty(required = true)
        val id: String,

        @ApiModelProperty(required = true)
        val clientId: String,

        @ApiModelProperty(required = true)
        val name: String,

        @ApiModelProperty(required = false)
        val rootUrl: String?,

        @ApiModelProperty(required = false)
        val baseUrl: String?,

)
