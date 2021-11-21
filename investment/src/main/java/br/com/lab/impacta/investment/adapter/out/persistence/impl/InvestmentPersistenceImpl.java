package br.com.lab.impacta.investment.adapter.out.persistence.impl;

import br.com.lab.impacta.investment.adapter.out.persistence.entity.Investment;
import br.com.lab.impacta.investment.adapter.out.persistence.mapper.InvestmentPersistenceMapper;
import br.com.lab.impacta.investment.adapter.out.persistence.repository.InvestmentRepository;
import br.com.lab.impacta.investment.core.domain.response.InvestmentDomainResponse;
import br.com.lab.impacta.investment.core.port.out.InvestmentPersistencePortOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class InvestmentPersistenceImpl implements InvestmentPersistencePortOut {

    @Autowired
    private InvestmentRepository investmentRepository;

    @Transactional
    @Override
    public InvestmentDomainResponse save(InvestmentDomainResponse investmentDomainResponse) {
        Investment investment = InvestmentPersistenceMapper.toEntity(investmentDomainResponse);
        Investment investmented = investmentRepository.save(investment);
        return InvestmentPersistenceMapper.toDomainResponse(investmented);
    }
}
