package org.bcp.banking.exchange.rate.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class ExchangeRateResponseDTO {
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private Date createdAt;
    private String currencyFrom;
    private String currencyTo;
    private BigDecimal rate;
    private Boolean active;
}
