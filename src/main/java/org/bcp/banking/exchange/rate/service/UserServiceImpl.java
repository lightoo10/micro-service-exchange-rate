package org.bcp.banking.exchange.rate.service;

import io.reactivex.Observable;
import io.reactivex.Single;
import lombok.extern.slf4j.Slf4j;
import org.bcp.banking.exchange.rate.auth.JwtTokenUtil;
import org.bcp.banking.exchange.rate.dto.*;
import org.bcp.banking.exchange.rate.model.ExchangeRate;
import org.bcp.banking.exchange.rate.repository.ExchangeRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.bcp.banking.exchange.rate.mapper.ExchangeRateMapper.*;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public UserResponseDTO authenticate(UserRequestDTO userRequestDTO) {
        return UserResponseDTO.builder()
                .username(userRequestDTO.getUsername())
                .token(jwtTokenUtil.getJWTToken(userRequestDTO.getUsername()))
                .build();
    }
}
