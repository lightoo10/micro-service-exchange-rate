package org.bcp.banking.exchange.rate.mapper;

import io.reactivex.functions.Function;
import org.bcp.banking.exchange.rate.dto.*;
import org.bcp.banking.exchange.rate.model.ExchangeRate;
import org.springframework.beans.BeanUtils;

import java.math.RoundingMode;
import java.util.Date;
import java.util.Objects;

import static org.bcp.banking.exchange.rate.util.Constants.PICKING_ERROR;

public class ExchangeRateMapper {

    public static ConvertCurrencyResponseDTO toConvertCurrencyResponseDTO(ExchangeRate exchangeRate, ConvertCurrencyRequestDTO convertCurrencyRequestDTO) {
        return ConvertCurrencyResponseDTO.builder()
                .originCurrency(exchangeRate.getCurrencyFrom())
                .destinationCurrency(exchangeRate.getCurrencyTo())
                .rate(exchangeRate.getRate())
                .convertedAmount(convertCurrencyRequestDTO.getAmount().multiply(exchangeRate.getRate()).setScale(2, RoundingMode.UP))
                .build();
    }

    public static ExchangeRate toExchangeRateDTO(ExchangeRateRequestDTO exchangeRateDTO) {
        return ExchangeRate.builder()
                .currencyFrom(exchangeRateDTO.getCurrencyFrom())
                .currencyTo(exchangeRateDTO.getCurrencyTo())
                .rate(exchangeRateDTO.getRate())
                .createdAt(new Date())
                .active(true)
                .build();
    }

    public static Function<ExchangeRate, ExchangeRateResponseDTO> toExchangeRateResponseDTO = exchangeRate -> {
        ExchangeRateResponseDTO exchangeRateResponseDTO = new ExchangeRateResponseDTO();
        BeanUtils.copyProperties(exchangeRate, exchangeRateResponseDTO);
        return exchangeRateResponseDTO;
    };

    public static MessageDTO toResponseErrorSynchronization(Exception exception) {
        return MessageDTO.builder()
                .id(EnumMessage.APPLICATION_ERROR.getValue())
                .message(PICKING_ERROR.concat(Objects.nonNull(exception.getMessage()) ? exception.getMessage() : ""))
                .build();
    }
}
