package br.com.lab.impacta.investment.handler;

import br.com.lab.impacta.investment.handler.exception.*;
import io.opentracing.Span;
import io.opentracing.Tracer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class ControllerExceptionHandler {

    @Autowired
    private Tracer tracer;

    @ExceptionHandler(InvestmentProductDontExistException.class)
    public ResponseEntity<ErrorMessageResponse> errorProductDontExists(InvestmentProductDontExistException ex) {
        Span span = tracer.buildSpan("Não existe produto cadastrado").start();
        ErrorMessageResponse message = new ErrorMessageResponse(
                HttpStatus.NOT_FOUND.value(),
                new Date(),
                ex.getMessage(),
                ex.getDescription());

        span.setTag("http.status_code", 404);
        span.finish();
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvestmentAccountWithoutBalanceException.class)
    public ResponseEntity<ErrorMessageResponse> errorWithoutBalance(InvestmentAccountWithoutBalanceException ex) {
        Span span = tracer.buildSpan("Não existe valores na conta.").start();
        ErrorMessageResponse message = new ErrorMessageResponse(
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                ex.getMessage(),
                ex.getDescription());

        span.setTag("http.status_code", 400);
        span.finish();
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvestmentAccountWithoutBalanceForProductPrivateException.class)
    public ResponseEntity<ErrorMessageResponse> errorWithoutBalanceForPrivate(InvestmentAccountWithoutBalanceForProductPrivateException ex) {
        Span span = tracer.buildSpan("Erro para com o valor e o tipo de produto.").start();
        ErrorMessageResponse message = new ErrorMessageResponse(
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                ex.getMessage(),
                ex.getDescription());

        span.setTag("http.status_code", 400);
        span.finish();
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvestmentAccountIsNotDebitException.class)
    public ResponseEntity<ErrorMessageResponse> errorWithoutBalanceForPrivate(InvestmentAccountIsNotDebitException ex) {
        Span span = tracer.buildSpan("Erro para com o valor e o tipo de debito").start();
        ErrorMessageResponse message = new ErrorMessageResponse(
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                ex.getMessage(),
                ex.getDescription());

        span.setTag("http.status_code", 400);
        span.finish();
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorMessageResponse> errorGeneral(RuntimeException ex) {
        Span span = tracer.buildSpan("Erro genérico da aplicação").start();
        ErrorMessageResponse message = new ErrorMessageResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                new Date(),
                ex.getMessage(),
                "Não foi possível processar sua requisição.");

        span.setTag("http.status_code", 500);
        span.finish();
        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
