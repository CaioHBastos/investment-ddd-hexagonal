package br.com.lab.impacta.investment.service.impl;

import br.com.lab.impacta.investment.handler.exception.InvestmentAccountIsNotDebitException;
import br.com.lab.impacta.investment.handler.exception.InvestmentAccountWithoutBalanceException;
import br.com.lab.impacta.investment.handler.exception.InvestmentAccountWithoutBalanceForProductPrivateException;
import br.com.lab.impacta.investment.handler.exception.InvestmentProductDontExistException;
import br.com.lab.impacta.investment.model.Investment;
import br.com.lab.impacta.investment.model.Product;
import br.com.lab.impacta.investment.repository.InvestmentRepository;
import br.com.lab.impacta.investment.repository.ProductRepository;
import br.com.lab.impacta.investment.service.InvestmentService;
import br.com.lab.impacta.investment.service.facade.AccountFacade;
import br.com.lab.impacta.investment.service.facade.valueObject.AccountBalanceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InvestmentServiceImpl implements InvestmentService {

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

    @Autowired
    private InvestmentRepository investmentRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private AccountFacade accountFacade;

    @Override
    public Investment invest(Long productId, Long accountId, Double valueInvestment) {
        Optional<Product> product = productRepository.findById(productId);

        if (product.isEmpty())
            throw new InvestmentProductDontExistException(messageExceptionProductDontExists,
                    descriptionExceptionProductDontExists);

        Investment investment = new Investment(productId, accountId, valueInvestment);

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

        investmentRepository.save(investment);

        return investment;
    }
}
