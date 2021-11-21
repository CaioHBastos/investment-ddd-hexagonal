package br.com.lab.impacta.investment.handler.exception;

public class InvestmentAccountWithoutBalanceForProductPrivateException extends RuntimeException {
    private String description;

    public String getDescription(){
        return description;
    }

    public InvestmentAccountWithoutBalanceForProductPrivateException(){}

    public InvestmentAccountWithoutBalanceForProductPrivateException(String message, String description) {
        super(message);

        this.description = description;
    }
}
