package br.com.lab.impacta.investment.adapter.out.persistence.mapper;

import br.com.lab.impacta.investment.adapter.out.persistence.entity.Product;
import br.com.lab.impacta.investment.core.domain.response.ProductDomainResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductPersistenceMapper {

    public static Optional<ProductDomainResponse> toDomainResponse(Optional<Product> product) {
        if (product.isEmpty()) {
            return Optional.empty();
        }

        ProductDomainResponse productDomainResponse = new ProductDomainResponse();
        productDomainResponse.setId(product.get().getId());
        productDomainResponse.setName(product.get().getName());
        productDomainResponse.setPrivateInvestment(product.get().isPrivateInvestment());
        productDomainResponse.setMinimumValueForInvestment(product.get().getMinimumValueForInvestment());

        return Optional.of(productDomainResponse);
    }
}
