package br.com.lab.impacta.investment.adapter.out.persistence.impl;

import br.com.lab.impacta.investment.adapter.out.persistence.entity.Product;
import br.com.lab.impacta.investment.adapter.out.persistence.mapper.ProductPersistenceMapper;
import br.com.lab.impacta.investment.adapter.out.persistence.repository.ProductRepository;
import br.com.lab.impacta.investment.core.domain.response.ProductDomainResponse;
import br.com.lab.impacta.investment.core.port.out.ProductPersistencePortOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ProductPersistenceImpl implements ProductPersistencePortOut {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Optional<ProductDomainResponse> findById(Long productId) {
        Optional<Product> product = productRepository.findById(productId);
        Optional<ProductDomainResponse> productDomainResponse = ProductPersistenceMapper.toDomainResponse(product);

        return productDomainResponse;
    }
}
