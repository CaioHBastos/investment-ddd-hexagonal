package br.com.lab.impacta.investment.adapter.out.http;

import br.com.lab.impacta.investment.core.facade.dto.DebitAccountRequest;
import br.com.lab.impacta.investment.core.facade.valueObject.AccountBalanceVO;
import br.com.lab.impacta.investment.core.facade.valueObject.DebitAccountVO;

public interface AccountClient {

    AccountBalanceVO getAccountBalanceById(Long accountId);

    DebitAccountVO debitAccount(Long accountId, DebitAccountRequest debitAccountRequest);
}
