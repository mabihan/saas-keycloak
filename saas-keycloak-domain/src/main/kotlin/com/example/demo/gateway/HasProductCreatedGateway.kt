package com.example.demo.gateway

import com.example.demo.model.ProductDomain

interface HasProductCreatedGateway {

    fun execute(productDomain: ProductDomain): Boolean

}