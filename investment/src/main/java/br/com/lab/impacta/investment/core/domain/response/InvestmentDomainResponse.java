package br.com.lab.impacta.investment.core.domain.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class InvestmentDomainResponse {

    private Long id;
    private Long productId;
    private Long accountId;
    private Double value;
    private Date date;
    private boolean privateInvestment;

    public InvestmentDomainResponse() {}

    public InvestmentDomainResponse(Long productId, Long accountId, Double value) {
        this.productId = productId;
        this.accountId = accountId;
        this.value = value;
    }

    public boolean sufficientBalanceForInvestment(Double accountBalance) {
        return this.value < accountBalance;
    }

    public boolean verifyProductPrivateOrDefaultForInvestment(Double accountBalance, ProductDomainResponse product) {
        if (!product.isPrivateInvestment()){
            this.privateInvestment = false;

            return true;
        }

        if (product.isPrivateInvestment() && (accountBalance >= product.getMinimumValueForInvestment())){
            this.privateInvestment = true;

            return true;
        }

        return  false;
    }
}
