package br.com.lab.impacta.investment.adapter.out.http;

import br.com.lab.impacta.investment.core.facade.dto.DebitAccountRequest;
import br.com.lab.impacta.investment.core.facade.valueObject.AccountBalanceVO;
import br.com.lab.impacta.investment.core.facade.valueObject.DebitAccountVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class AccountClientImpl implements AccountClient {

    WebClient webClient = WebClient.create();

    @Value("${lab.investment.paths.client-account-base-url}")
    private String urlAccount;

    @Value("${lab.investment.paths.client-account-debit-path-url}")
    private String urlDebit;

    @Override
    public AccountBalanceVO getAccountBalanceById(Long accountId) {
        return webClient.get()
                .uri("http://localhost:8081/api/v1/accounts/" + accountId + "/balance")
                .retrieve()
                .bodyToMono(AccountBalanceVO.class).block();
    }

    @Override
    public DebitAccountVO debitAccount(Long accountId, DebitAccountRequest debitAccountRequest) {
        return webClient.post()
                .uri("http://localhost:8081/api/v1/accounts/" + accountId + "/debit")
                .body(Mono.just(debitAccountRequest), DebitAccountRequest.class)
                .retrieve()
                .bodyToMono(DebitAccountVO.class).block();
    }
}
