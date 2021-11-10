package org.bcp.banking.exchange.rate.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ConvertCurrencyRequestDTO {
    private BigDecimal amount;
    private String originCurrency;
    private String destinationCurrency;
}
