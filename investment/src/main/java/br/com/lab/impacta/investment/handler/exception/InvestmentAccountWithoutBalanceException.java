package br.com.lab.impacta.investment.handler.exception;

public class InvestmentAccountWithoutBalanceException extends RuntimeException {
    private String description;

    public String getDescription(){
        return description;
    }

    public InvestmentAccountWithoutBalanceException(){}

    public InvestmentAccountWithoutBalanceException(String message, String description) {
        super(message);

        this.description = description;
    }
}
