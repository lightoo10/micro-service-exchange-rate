package org.bcp.banking.exchange.rate.controller;

import org.bcp.banking.exchange.rate.dto.UserRequestDTO;
import org.bcp.banking.exchange.rate.dto.UserResponseDTO;
import org.bcp.banking.exchange.rate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/login")
	public UserResponseDTO authenticate(@RequestBody UserRequestDTO userRequestDTO) {
		return userService.authenticate(userRequestDTO);
	}
}