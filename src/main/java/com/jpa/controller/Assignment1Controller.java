/*
 *To change this license header, choose License Headers in Project Properties.
 *To change this template file, choose Tools | Templates
 *and open the template in the editor.
 */
package com.jpa.controller;

import com.jpa.Entity.Customer1;
import com.jpa.Entity.Login;
import com.jpa.exception.AuthenticationException;
import com.jpa.exception.LoginInvalidException;
import com.jpa.service.Assignment1Service;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Ekta Tank
 * @since 28 Jan, 2019
 * @version 1.0.0
 */
@RestController
public class Assignment1Controller {

	private Assignment1Service assignment1Service;

	@Autowired
	public Assignment1Controller(Assignment1Service assignment1Service) {
		this.assignment1Service = assignment1Service;

	}

	@PostMapping("/rest/SignUP")
	public Customer1 doSignUp(@Valid @RequestBody Customer1 customer) {

		return assignment1Service.doSignUP(customer);

	}

	@PostMapping("/rest/login")
	public Map<String, Object> doLogin(@RequestBody Login customer) throws LoginInvalidException {

		return assignment1Service.doLogin(customer);

	}

	@GetMapping("/rest/viewProfile")
	public Customer1 viewProfile(HttpServletRequest httpRequest) throws AuthenticationException {

		String authorization = httpRequest.getHeader("Authorization");

		return assignment1Service.validateRequest(authorization);

	}

}
