package org.bcp.banking.exchange.rate.exceptions;

public class JwtNotFoundException extends RuntimeException {

    public JwtNotFoundException(String message) {
        super("Invalid Token" + message);
    }

}
