package org.bcp.banking.exchange.rate.exceptions;

public class ExchangeRateNotFoundException extends RuntimeException {

    public ExchangeRateNotFoundException(String message) {
        super("Invalid exchange rate" + message);
    }

}
