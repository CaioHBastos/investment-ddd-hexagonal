package br.com.lab.impacta.investment.core.facade.dto;

import lombok.Data;

@Data
public class DebitAccountRequest {
    private Double valueOfDebit;

    public DebitAccountRequest() {}

    public DebitAccountRequest(Double valueOfDebit) {
        this.valueOfDebit = valueOfDebit;
    }
}
