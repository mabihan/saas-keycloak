package com.example.demo.gateway.database.impl.product

import com.example.demo.exception.InternalErrorException
import com.example.demo.gateway.product.CreateProductGateway
import com.example.demo.gateway.database.repository.ProductRepository
import com.example.demo.gateway.database.translator.ProductDomainToProductDBTranslator
import com.example.demo.model.ProductDomain
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.inject.Named

@Named
class CreateProductGatewayImpl(private val productRepository: ProductRepository) : CreateProductGateway {

    private val log: Logger = LoggerFactory.getLogger(CreateProductGatewayImpl::class.java)

    override fun execute(productDomain: ProductDomain) {
        try {
            productRepository.save(ProductDomainToProductDBTranslator().translate(productDomain))
        } catch (ex: Exception) {
            log.error("Internal error to create the product", ex)
            throw InternalErrorException("Internal error to create the product: " + ex.message)
        }
    }

}
