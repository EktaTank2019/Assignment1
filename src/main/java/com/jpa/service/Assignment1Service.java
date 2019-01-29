/*
 *To change this license header, choose License Headers in Project Properties.
 *To change this template file, choose Tools | Templates
 *and open the template in the editor.
 */
package com.jpa.service;

import com.jpa.Entity.Customer1;
import com.jpa.Entity.Login;
import com.jpa.exception.AuthenticationException;
import com.jpa.exception.LoginInvalidException;
import com.jpa.repository.Assignment1Repository;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ekta Tank
 * @since 28 Jan, 2019
 * @version 1.0.0
 */
@Service
public class Assignment1Service {

	final private Assignment1Repository assignment1Repository;
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}

	@Autowired
	public Assignment1Service(Assignment1Repository assignment1Repository) {
		this.assignment1Repository = assignment1Repository;
	}

	public Customer1 doSignUP(Customer1 customer) {

		customer.setPassword(passwordEncoder().encode(customer.getPassword()));

		return assignment1Repository.save(customer);
	}

	public Map<String, Object> doLogin(Login customer) throws LoginInvalidException {
		Customer1 c = assignment1Repository.findByUserName(customer.getUserName());

		Map< String, Object> json = new HashMap<>();

		if (c == null) {
			throw  new LoginInvalidException("User name is Invalid");
		} else {

			boolean ans = passwordEncoder().matches(customer.getPassword(), c.getPassword());

			String JWTToken = JWTAuthentication.createJsonWebToken(customer.getUserName(), 1);
			if (ans == true) {
				json.put("token", JWTToken);
				json.put("user", c);

				return json;
			} else {
				throw new LoginInvalidException("Authentication Failure");
			}
		}

	}

	public Customer1 validateRequest(String authorization) throws AuthenticationException {

		Tokeninfo t = JWTAuthentication.verifyToken(authorization);
		return assignment1Repository.findByUserName(t.getUserId());
	}

}
