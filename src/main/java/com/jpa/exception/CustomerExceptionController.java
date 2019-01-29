/*
 *To change this license header, choose License Headers in Project Properties.
 *To change this template file, choose Tools | Templates
 *and open the template in the editor.
 */
package com.jpa.exception;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 *
 * @author Ekta Tank
 * @since 24 Jan, 2019
 * @version 1.0.0
 *
 */
@RestControllerAdvice
public class CustomerExceptionController {

	@ExceptionHandler(value = CustomerException.class)
	public String exception(CustomerException customerException) {
		return "Exception";
	}

	@ExceptionHandler(value = LoginInvalidException.class)
	public ResponseEntity<String> invalidLoginexception(LoginInvalidException loginInvalidException) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(loginInvalidException.getMsg());
	}

	@ExceptionHandler(value = AuthenticationException.class)
	public ResponseEntity<String> Authenticationexception(AuthenticationException authenticationException) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(authenticationException.getMsg());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ArrayList<String> validationError(MethodArgumentNotValidException ex) {

		BindingResult result = ex.getBindingResult();
		final List<FieldError> fieldErrors = result.getFieldErrors();
		ArrayList<String> errors = new ArrayList<>();
		for (int i = 0; i < fieldErrors.size(); i++) {
			String msj;
			msj = "Error Field:  " +fieldErrors.get(i).getField()+ "    Default Message:   "+fieldErrors.get(i).getDefaultMessage();

			errors.add(msj);

		}

		return errors;
	}
}
