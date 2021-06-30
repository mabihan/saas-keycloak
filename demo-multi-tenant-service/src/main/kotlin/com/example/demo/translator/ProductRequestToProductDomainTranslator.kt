package com.example.demo.translator

import com.example.demo.model.ProductDomain
import com.example.demo.model.ProductRequest
import java.time.LocalDateTime

class ProductRequestToProductDomainTranslator {

    fun translate(productRequest: ProductRequest, companyName: String, branchOfficeName: String): ProductDomain {
        return ProductDomain(companyName = companyName, branchOfficeName = branchOfficeName, productName = productRequest.productName,
                unitType = productRequest.unitType, active = true, createdDate = LocalDateTime.now())
    }

}