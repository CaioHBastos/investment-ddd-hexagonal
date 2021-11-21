package br.com.lab.impacta.investment.core.domain.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDomainResponse {

    private Long id;
    private String name;
    private Double minimumValueForInvestment;
    private boolean privateInvestment;
}

