package br.com.lab.impacta.investment.core.facade;

import br.com.lab.impacta.investment.core.facade.valueObject.AccountBalanceVO;

public interface AccountFacade {
    AccountBalanceVO getAccountBalanceById(Long accountId);

    boolean debitAccount(Long accountId, Double valueOfInvestment);
}
