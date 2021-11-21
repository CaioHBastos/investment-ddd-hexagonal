package br.com.lab.impacta.investment.core.port.out;

import br.com.lab.impacta.investment.core.domain.response.ProductDomainResponse;

import java.util.Optional;

public interface ProductPersistencePortOut {

    Optional<ProductDomainResponse> findById(Long productId);

}
