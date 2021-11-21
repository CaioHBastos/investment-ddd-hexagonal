package br.com.lab.impacta.investment.adapter.out.persistence.mapper;

import br.com.lab.impacta.investment.adapter.out.persistence.entity.Investment;
import br.com.lab.impacta.investment.core.domain.response.InvestmentDomainResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class InvestmentPersistenceMapper {

    public static Investment toEntity(InvestmentDomainResponse investmentDomainResponse) {
        Investment investment = new Investment();
        investment.setProductId(investmentDomainResponse.getProductId());
        investment.setAccountId(investmentDomainResponse.getAccountId());
        investment.setValue(investmentDomainResponse.getValue());

        return investment;
    }

    public static InvestmentDomainResponse toDomainResponse(Investment investmented) {
        InvestmentDomainResponse investmentDomainResponse = new InvestmentDomainResponse();
        investmentDomainResponse.setId(investmented.getId());
        investmentDomainResponse.setValue(investmented.getValue());
        investmentDomainResponse.setDate(investmented.getDate());

        return investmentDomainResponse;
    }
}
