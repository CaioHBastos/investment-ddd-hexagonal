package br.com.lab.impacta.investment.core.facade.impl;

import br.com.lab.impacta.investment.adapter.out.http.AccountClient;
import br.com.lab.impacta.investment.core.facade.AccountFacade;
import br.com.lab.impacta.investment.core.facade.dto.DebitAccountRequest;
import br.com.lab.impacta.investment.core.facade.valueObject.AccountBalanceVO;
import br.com.lab.impacta.investment.core.facade.valueObject.DebitAccountVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccountFacadeImpl implements AccountFacade {

    private final AccountClient accountClient;

    @Override
    public AccountBalanceVO getAccountBalanceById(Long accountId) {
        AccountBalanceVO accountBalanceVO = accountClient.getAccountBalanceById(accountId);

        return accountBalanceVO;
    }

    @Override
    public boolean debitAccount(Long accountId, Double valueOfInvestment) {
        DebitAccountVO debitAccountVO = accountClient.debitAccount(accountId, new DebitAccountRequest(valueOfInvestment));

        return  debitAccountVO.isDebited();
    }
}
