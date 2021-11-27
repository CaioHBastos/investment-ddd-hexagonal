package br.com.lab.impacta.investment.adapter.out.http;

import br.com.lab.impacta.investment.core.facade.dto.DebitAccountRequest;
import br.com.lab.impacta.investment.core.facade.valueObject.AccountBalanceVO;
import br.com.lab.impacta.investment.core.facade.valueObject.DebitAccountVO;
import io.opentracing.Span;
import io.opentracing.Tracer;
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

    @Autowired
    private Tracer tracer;

    @Override
    public AccountBalanceVO getAccountBalanceById(Long accountId) {

        Span span = tracer.buildSpan("Iniciando a comunicação com o serviço de conta para buscar os valores").start();

        AccountBalanceVO accountBalanceVO = webClient.get()
                .uri("http://localhost:8081/api/v1/accounts/" + accountId + "/balance")
                .retrieve()
                .bodyToMono(AccountBalanceVO.class).block();

        span.setTag("http.status_code", 200);
        span.finish();

        return accountBalanceVO;
    }

    @Override
    public DebitAccountVO debitAccount(Long accountId, DebitAccountRequest debitAccountRequest) {

        Span span = tracer.buildSpan(
                "Iniciando a comunicação com o serviço de conta para realizar o debito do investimento").start();

        DebitAccountVO debitAccountVO = webClient.post()
                .uri("http://localhost:8081/api/v1/accounts/" + accountId + "/debit")
                .body(Mono.just(debitAccountRequest), DebitAccountRequest.class)
                .retrieve()
                .bodyToMono(DebitAccountVO.class).block();

        span.setTag("http.status_code", 200);
        span.finish();

        return debitAccountVO;
    }
}
