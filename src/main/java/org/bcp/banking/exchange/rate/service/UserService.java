package org.bcp.banking.exchange.rate.service;

import org.bcp.banking.exchange.rate.dto.UserRequestDTO;
import org.bcp.banking.exchange.rate.dto.UserResponseDTO;

public interface UserService {
    UserResponseDTO authenticate(UserRequestDTO userRequestDTO);
}
