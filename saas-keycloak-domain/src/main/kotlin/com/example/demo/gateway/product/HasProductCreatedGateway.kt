package com.example.demo.gateway.product

import com.example.demo.model.ProductDomain

interface HasProductCreatedGateway {

    fun execute(productDomain: ProductDomain): Boolean

}
