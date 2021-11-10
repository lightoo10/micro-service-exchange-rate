package org.bcp.banking.exchange.rate.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ExchangeRateRequestDTO {
    private String currencyFrom;
    private String currencyTo;
    private BigDecimal rate;
}
