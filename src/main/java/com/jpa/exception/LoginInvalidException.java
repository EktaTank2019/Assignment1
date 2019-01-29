/*
 *To change this license header, choose License Headers in Project Properties.
 *To change this template file, choose Tools | Templates
 *and open the template in the editor.
 */

package com.jpa.exception;

/**
 *
 * @author Ekta Tank
 * @since 29 Jan, 2019
 * @version 1.0.0
 */
@SuppressWarnings("serial")
public class LoginInvalidException  extends RuntimeException{

	private String msg;
	
	public LoginInvalidException(String msg) {
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
}
