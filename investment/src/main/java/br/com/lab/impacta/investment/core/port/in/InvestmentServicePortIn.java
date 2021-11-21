package br.com.lab.impacta.investment.core.port.in;

import br.com.lab.impacta.investment.core.domain.response.InvestmentDomainResponse;

public interface InvestmentServicePortIn {
    InvestmentDomainResponse invest(Long productId, Long accountId, Double valueInvestment);
}
