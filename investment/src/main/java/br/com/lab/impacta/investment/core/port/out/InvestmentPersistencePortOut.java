package br.com.lab.impacta.investment.core.port.out;

import br.com.lab.impacta.investment.core.domain.response.InvestmentDomainResponse;

public interface InvestmentPersistencePortOut {

    InvestmentDomainResponse save(InvestmentDomainResponse investmentDomainResponse);
}
