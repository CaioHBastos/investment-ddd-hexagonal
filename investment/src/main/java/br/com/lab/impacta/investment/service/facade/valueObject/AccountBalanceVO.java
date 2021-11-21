package br.com.lab.impacta.investment.service.facade.valueObject;

import lombok.Data;

@Data
public class AccountBalanceVO {
    private Long accountId;

    private Double balance;
}
