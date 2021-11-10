package org.bcp.banking.exchange.rate.repository;

import org.bcp.banking.exchange.rate.model.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, String> {
    ExchangeRate findByCurrencyFromAndCurrencyToAndActive(String currencyFrom, String currencyTo, Boolean active);

    @Modifying
    @Transactional
    @Query("update ExchangeRate er set er.active = false where er.currencyFrom = ?1 and er.currencyTo = ?2")
    void updateExchangeRateActive(String currencyFrom, String currencyTo);
}
