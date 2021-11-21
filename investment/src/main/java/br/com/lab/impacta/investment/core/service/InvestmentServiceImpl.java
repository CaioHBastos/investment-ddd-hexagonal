package br.com.lab.impacta.investment.core.service;

import br.com.lab.impacta.investment.core.domain.response.InvestmentDomainResponse;
import br.com.lab.impacta.investment.core.domain.response.ProductDomainResponse;
import br.com.lab.impacta.investment.core.facade.AccountFacade;
import br.com.lab.impacta.investment.core.facade.valueObject.AccountBalanceVO;
import br.com.lab.impacta.investment.core.port.in.InvestmentServicePortIn;
import br.com.lab.impacta.investment.core.port.out.InvestmentPersistencePortOut;
import br.com.lab.impacta.investment.core.port.out.ProductPersistencePortOut;
import br.com.lab.impacta.investment.handler.exception.InvestmentAccountIsNotDebitException;
import br.com.lab.impacta.investment.handler.exception.InvestmentAccountWithoutBalanceException;
import br.com.lab.impacta.investment.handler.exception.InvestmentAccountWithoutBalanceForProductPrivateException;
import br.com.lab.impacta.investment.handler.exception.InvestmentProductDontExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class InvestmentServiceImpl implements InvestmentServicePortIn {

    private final ProductPersistencePortOut productPersistencePortOut;
    private final InvestmentPersistencePortOut investmentPersistencePortOut;
    private final AccountFacade accountFacade;

    @Value("${lab.investment.exceptions.product-dont-exists-message}")
    private String messageExceptionProductDontExists;

    @Value("${lab.investment.exceptions.product-dont-exists-description}")
    private String descriptionExceptionProductDontExists;

    @Value("${lab.investment.exceptions.account-without-balance-message}")
    private String messageExceptionAccountWithoutBalance;

    @Value("${lab.investment.exceptions.account-without-balance-description}")
    private String descriptionExceptionAccountWithoutBalance;

    @Value("${lab.investment.exceptions.account-without-balance-for-product-private-message}")
    private String messageExceptionAccountWithoutBalanceForProductPrivate;

    @Value("${lab.investment.exceptions.account-without-balance-for-product-private-description}")
    private String descriptionExceptionAccountWithoutBalanceForProductPrivate;

    @Value("${lab.investment.exceptions.account-is-not-debited-message}")
    private String messageExceptionAccountIsNotDebited;

    @Value("${lab.investment.exceptions.account-is-not-debited-description}")
    private String descriptionExceptionAccountIsNotDebited;

    @Override
    public InvestmentDomainResponse invest(Long productId, Long accountId, Double valueInvestment) {
        Optional<ProductDomainResponse> product = productPersistencePortOut.findById(productId);

        if (product.isEmpty())
            throw new InvestmentProductDontExistException(messageExceptionProductDontExists,
                    descriptionExceptionProductDontExists);

        InvestmentDomainResponse investment = new InvestmentDomainResponse(productId, accountId, valueInvestment);

        AccountBalanceVO accountBalanceVO = accountFacade.getAccountBalanceById(accountId);

        if (!investment.sufficientBalanceForInvestment(accountBalanceVO.getBalance()))
            throw new InvestmentAccountWithoutBalanceException(messageExceptionAccountWithoutBalance,
                    descriptionExceptionAccountWithoutBalance);

        if (!investment.verifyProductPrivateOrDefaultForInvestment(accountBalanceVO.getBalance(), product.get()))
            throw new InvestmentAccountWithoutBalanceForProductPrivateException(messageExceptionAccountWithoutBalanceForProductPrivate,
                    descriptionExceptionAccountWithoutBalanceForProductPrivate);

        boolean isDebited = accountFacade.debitAccount(accountId, valueInvestment);

        if (!isDebited)
            throw new InvestmentAccountIsNotDebitException(messageExceptionAccountIsNotDebited,
                    descriptionExceptionAccountIsNotDebited);

        InvestmentDomainResponse investmentedDomainResponse = investmentPersistencePortOut.save(investment);

        return investmentedDomainResponse;
    }
}
