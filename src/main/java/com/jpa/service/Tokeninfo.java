/*
 *To change this license header, choose License Headers in Project Properties.
 *To change this template file, choose Tools | Templates
 *and open the template in the editor.
 */
package com.jpa.service;

import org.joda.time.DateTime;

/**
 *
 * @author Ekta Tank
 * @since 29 Jan, 2019
 * @version 1.0.0
 */
public class Tokeninfo {

	private String userId;
	private DateTime issued;
	private DateTime expires;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public DateTime getIssued() {
		return issued;
	}

	public void setIssued(DateTime issued) {
		this.issued = issued;
	}

	public DateTime getExpires() {
		return expires;
	}

	public void setExpires(DateTime expires) {
		this.expires = expires;
	}
}
