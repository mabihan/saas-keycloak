package com.example.demo.gateway.database.impl.product

import com.example.demo.exception.InternalErrorException
import com.example.demo.gateway.product.HasProductCreatedGateway
import com.example.demo.gateway.database.repository.ProductRepository
import com.example.demo.model.ProductDomain
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.lang.Exception
import javax.inject.Named

@Named
class HasProductCreatedGatewayImpl(private val productRepository: ProductRepository) : HasProductCreatedGateway {

    private val log: Logger = LoggerFactory.getLogger(HasProductCreatedGatewayImpl::class.java)

    override fun execute(productDomain: ProductDomain): Boolean {
        try {
            return productRepository.existsByCompanyNameAndBranchOfficeNameAndProductNameAndUnitTypeAndActive(productDomain.companyName,
                    productDomain.branchOfficeName, productDomain.productName, productDomain.unitType, true)
        } catch (ex: Exception) {
            log.error("Internal error to find the product", ex)
            throw InternalErrorException("Internal error to find the product: " + ex.message)
        }
    }

}
