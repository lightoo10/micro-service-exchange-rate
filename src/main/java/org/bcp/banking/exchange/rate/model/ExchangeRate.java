package org.bcp.banking.exchange.rate.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "exchange_rate")
public class ExchangeRate {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "currency_from")
    private String currencyFrom;

    @Column(name = "currency_to")
    private String currencyTo;

    @Column(name = "rate")
    private BigDecimal rate;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "active")
    private Boolean active;
}
