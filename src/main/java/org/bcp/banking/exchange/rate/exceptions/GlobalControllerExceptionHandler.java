package org.bcp.banking.exchange.rate.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.bcp.banking.exchange.rate.mapper.ExchangeRateMapper.toResponseErrorSynchronization;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
@Slf4j
public class GlobalControllerExceptionHandler {

    @ExceptionHandler(value = JwtNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ResponseEntity<?> notFound(JwtNotFoundException dadException) {
        log.info("Error occurred: jwt not found");
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(toResponseErrorSynchronization(dadException));
    }

    @ExceptionHandler(value = RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResponseEntity<?> handleErrorGeneric(RuntimeException dadException) {
        log.info("Error occurred in exchange-rate: "+dadException);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(toResponseErrorSynchronization(dadException));
    }
}
