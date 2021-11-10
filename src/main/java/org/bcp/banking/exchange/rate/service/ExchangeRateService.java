package org.bcp.banking.exchange.rate.service;

import io.reactivex.Observable;
import io.reactivex.Single;
import org.bcp.banking.exchange.rate.dto.ConvertCurrencyRequestDTO;
import org.bcp.banking.exchange.rate.dto.ConvertCurrencyResponseDTO;
import org.bcp.banking.exchange.rate.dto.ExchangeRateRequestDTO;
import org.bcp.banking.exchange.rate.dto.ExchangeRateResponseDTO;

public interface ExchangeRateService {
    Observable<ExchangeRateResponseDTO> getExchangeRates();
    Single<ExchangeRateResponseDTO> saveExchangeRate(ExchangeRateRequestDTO exchangeRateRequestDTO);
    Single<ConvertCurrencyResponseDTO> convertCurrency(ConvertCurrencyRequestDTO convertCurrencyRequestDTO);
}
