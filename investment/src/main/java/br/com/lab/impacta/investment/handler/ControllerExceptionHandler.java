package br.com.lab.impacta.investment.handler;

import br.com.lab.impacta.investment.handler.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(InvestmentProductDontExistException.class)
    public ResponseEntity<ErrorMessageResponse> errorProductDontExists(InvestmentProductDontExistException ex) {
        ErrorMessageResponse message = new ErrorMessageResponse(
                HttpStatus.NOT_FOUND.value(),
                new Date(),
                ex.getMessage(),
                ex.getDescription());

        return new ResponseEntity<ErrorMessageResponse>(message, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvestmentAccountWithoutBalanceException.class)
    public ResponseEntity<ErrorMessageResponse> errorWithoutBalance(InvestmentAccountWithoutBalanceException ex) {
        ErrorMessageResponse message = new ErrorMessageResponse(
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                ex.getMessage(),
                ex.getDescription());

        return new ResponseEntity<ErrorMessageResponse>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvestmentAccountWithoutBalanceForProductPrivateException.class)
    public ResponseEntity<ErrorMessageResponse> errorWithoutBalanceForPrivate(InvestmentAccountWithoutBalanceForProductPrivateException ex) {
        ErrorMessageResponse message = new ErrorMessageResponse(
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                ex.getMessage(),
                ex.getDescription());

        return new ResponseEntity<ErrorMessageResponse>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvestmentAccountIsNotDebitException.class)
    public ResponseEntity<ErrorMessageResponse> errorWithoutBalanceForPrivate(InvestmentAccountIsNotDebitException ex) {
        ErrorMessageResponse message = new ErrorMessageResponse(
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                ex.getMessage(),
                ex.getDescription());

        return new ResponseEntity<ErrorMessageResponse>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorMessageResponse> errorGeneral(RuntimeException ex) {
        ErrorMessageResponse message = new ErrorMessageResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                new Date(),
                ex.getMessage(),
                "Não foi possível processar sua requisição.");

        return new ResponseEntity<ErrorMessageResponse>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
