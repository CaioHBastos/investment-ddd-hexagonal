package br.com.lab.impacta.investment.adapter.in.api;

import br.com.lab.impacta.investment.application.InvestmentApplication;
import br.com.lab.impacta.investment.application.dto.request.InvestmentRequest;
import br.com.lab.impacta.investment.application.dto.response.InvestmentResponse;
import io.opentracing.Span;
import io.opentracing.Tracer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/accounts")
public class InvestmentController {

    @Autowired
    private InvestmentApplication investmentApplication;

    @Autowired
    private Tracer tracer;

    @PostMapping("/{accountId}/investment")
    public ResponseEntity<InvestmentResponse> invest(
            @PathVariable long accountId,
            @RequestBody InvestmentRequest investmentRequest) {

        Span span = tracer.buildSpan("Iniciando o investimento com os dados da conta").start();
        InvestmentResponse investmentResponse = investmentApplication.invest(accountId, investmentRequest);

        span.setTag("http.status_code", 200);
        span.finish();
        return ResponseEntity.ok(investmentResponse);
    }
}
