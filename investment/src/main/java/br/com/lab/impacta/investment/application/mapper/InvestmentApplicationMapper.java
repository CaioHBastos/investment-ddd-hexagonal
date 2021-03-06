package br.com.lab.impacta.investment.application.mapper;

import br.com.lab.impacta.investment.application.dto.response.InvestmentResponse;
import br.com.lab.impacta.investment.core.domain.response.InvestmentDomainResponse;

public class InvestmentApplicationMapper {

    public static InvestmentResponse toDtoInvestment(InvestmentDomainResponse investment) {
        return new InvestmentResponse(investment.getId(), investment.getValue(), investment.getDate());
    }
}
