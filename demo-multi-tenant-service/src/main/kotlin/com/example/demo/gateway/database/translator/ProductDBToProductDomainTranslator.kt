package com.example.demo.gateway.database.translator

import com.example.demo.gateway.database.model.ProductDB
import com.example.demo.model.ProductDomain

class ProductDBToProductDomainTranslator {

    fun translate(productDB: ProductDB): ProductDomain {
        return ProductDomain(productDB.id, productDB.companyName, productDB.branchOfficeName, productDB.productName,
                productDB.unitType, productDB.active, productDB.createdDate, productDB.updatedDate)
    }

}