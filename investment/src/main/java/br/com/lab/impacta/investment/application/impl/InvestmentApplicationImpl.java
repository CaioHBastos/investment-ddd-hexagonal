package br.com.lab.impacta.investment.application.impl;

import br.com.lab.impacta.investment.application.InvestmentApplication;
import br.com.lab.impacta.investment.application.adapter.InvestmentAdapter;
import br.com.lab.impacta.investment.application.dto.request.InvestmentRequest;
import br.com.lab.impacta.investment.application.dto.response.InvestmentResponse;
import br.com.lab.impacta.investment.core.domain.response.InvestmentDomainResponse;
import br.com.lab.impacta.investment.core.port.in.InvestmentServicePortIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InvestmentApplicationImpl implements InvestmentApplication {

    @Autowired
    private InvestmentServicePortIn investmentService;

    @Override
    public InvestmentResponse invest(Long accountId, InvestmentRequest investmentRequest) {
        InvestmentDomainResponse investment = investmentService.invest(investmentRequest.getProductId(), accountId,
                investmentRequest.getValue());

        return InvestmentAdapter.toDtoInvestment(investment);
    }
}
