package br.com.lab.impacta.investment.core.facade.valueObject;

import lombok.Data;

@Data
public class AccountBalanceVO {
    private Long accountId;

    private Double balance;
}
