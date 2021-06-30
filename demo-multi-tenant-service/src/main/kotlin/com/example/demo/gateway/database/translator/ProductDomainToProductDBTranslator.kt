package com.example.demo.gateway.database.translator

import com.example.demo.gateway.database.model.ProductDB
import com.example.demo.model.ProductDomain

class ProductDomainToProductDBTranslator {

    fun translate(productDomain: ProductDomain): ProductDB {
        return ProductDB(productDomain.id, productDomain.companyName, productDomain.branchOfficeName, productDomain.productName,
                productDomain.unitType, productDomain.active, productDomain.createdDate, productDomain.updatedDate)
    }

}