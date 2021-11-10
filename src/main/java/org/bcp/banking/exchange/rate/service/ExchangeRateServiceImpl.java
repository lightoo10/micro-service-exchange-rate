package org.bcp.banking.exchange.rate.service;

import io.reactivex.Observable;
import io.reactivex.Single;
import lombok.extern.slf4j.Slf4j;
import org.bcp.banking.exchange.rate.dto.ConvertCurrencyRequestDTO;
import org.bcp.banking.exchange.rate.dto.ConvertCurrencyResponseDTO;
import org.bcp.banking.exchange.rate.dto.ExchangeRateRequestDTO;
import org.bcp.banking.exchange.rate.dto.ExchangeRateResponseDTO;
import org.bcp.banking.exchange.rate.exceptions.ExchangeRateNotFoundException;
import org.bcp.banking.exchange.rate.exceptions.JwtNotFoundException;
import org.bcp.banking.exchange.rate.model.ExchangeRate;
import org.bcp.banking.exchange.rate.repository.ExchangeRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

import static org.bcp.banking.exchange.rate.mapper.ExchangeRateMapper.*;

@Slf4j
@Service
public class ExchangeRateServiceImpl implements ExchangeRateService {

    @Autowired
    private ExchangeRateRepository exchangeRateRepository;

    @Override
    public Observable<ExchangeRateResponseDTO> getExchangeRates() {
        return Observable.just(exchangeRateRepository.findAll())
                .flatMap(Observable::fromIterable).map(toExchangeRateResponseDTO);
    }

    @Override
    @Transactional
    public Single<ExchangeRateResponseDTO> saveExchangeRate(ExchangeRateRequestDTO exchangeRateRequestDTO) {
        return Single.create(subscriber -> {
            exchangeRateRepository.updateExchangeRateActive(exchangeRateRequestDTO.getCurrencyFrom(), exchangeRateRequestDTO.getCurrencyTo());
            ExchangeRate exchangeRate = exchangeRateRepository.save(toExchangeRateDTO(exchangeRateRequestDTO));
            subscriber.onSuccess(toExchangeRateResponseDTO.apply(exchangeRate));
        });
    }

    @Override
    @Transactional
    public Single<ConvertCurrencyResponseDTO> convertCurrency(ConvertCurrencyRequestDTO convertCurrencyRequestDTO) {
        return Single.create(subscriber -> {
            Optional<ExchangeRate> exchangeRate = Optional.ofNullable(exchangeRateRepository.findByCurrencyFromAndCurrencyToAndActive(convertCurrencyRequestDTO.getOriginCurrency(), convertCurrencyRequestDTO.getDestinationCurrency(), true));
            if (exchangeRate.isEmpty()) {
                log.info("Exchange Rate is required");
                subscriber.onError(new JwtNotFoundException("Exchange Rate is required"));
            } else {
                log.info("Exchange Rate: {}", exchangeRate.get().getRate());
                subscriber.onSuccess(toConvertCurrencyResponseDTO(exchangeRate.get(), convertCurrencyRequestDTO));
            }
        });
    }
}
