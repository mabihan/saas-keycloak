package com.example.demo.gateway.product

import com.example.demo.model.ProductDomain

interface CreateProductGateway {

    fun execute(productDomain: ProductDomain)

}
