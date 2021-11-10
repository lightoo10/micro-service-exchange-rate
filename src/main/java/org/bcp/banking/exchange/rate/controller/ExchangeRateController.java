package org.bcp.banking.exchange.rate.controller;

import io.reactivex.Observable;
import io.reactivex.Single;
import org.bcp.banking.exchange.rate.config.Secured;
import org.bcp.banking.exchange.rate.dto.ConvertCurrencyRequestDTO;
import org.bcp.banking.exchange.rate.dto.ConvertCurrencyResponseDTO;
import org.bcp.banking.exchange.rate.dto.ExchangeRateRequestDTO;
import org.bcp.banking.exchange.rate.dto.ExchangeRateResponseDTO;
import org.bcp.banking.exchange.rate.service.ExchangeRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/exchange-rate/v1")
public class ExchangeRateController {

    @Autowired
    private ExchangeRateService exchangeRateService;

    @Secured
    @GetMapping
    public Observable<ExchangeRateResponseDTO> getExchangeRate() {
        return exchangeRateService.getExchangeRates();
    }

    @Secured
    @PostMapping
    public Single<ExchangeRateResponseDTO> saveExchangeRate(@RequestBody ExchangeRateRequestDTO exchangeRateRequestDTO) {
        return exchangeRateService.saveExchangeRate(exchangeRateRequestDTO);
    }

    @Secured
    @PostMapping("/convert")
    public Single<ConvertCurrencyResponseDTO> convertCurrency(@RequestBody ConvertCurrencyRequestDTO convertCurrencyRequestDTO) {
        return exchangeRateService.convertCurrency(convertCurrencyRequestDTO);
    }
}
