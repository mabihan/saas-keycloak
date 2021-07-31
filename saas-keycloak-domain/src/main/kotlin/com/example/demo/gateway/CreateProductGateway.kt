package com.example.demo.gateway

import com.example.demo.model.ProductDomain

interface CreateProductGateway {

    fun execute(productDomain: ProductDomain)

}