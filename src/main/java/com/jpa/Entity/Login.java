/*
 *To change this license header, choose License Headers in Project Properties.
 *To change this template file, choose Tools | Templates
 *and open the template in the editor.
 */

package com.jpa.Entity;

import javax.validation.constraints.NotBlank;

/**
 *
 * @author Ekta Tank
 * @since 29 Jan, 2019
 * @version 1.0.0
 */
public class Login {

	@NotBlank
	private String userName;
	@NotBlank
	private String password;

	public Login() {
	}
	

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public static class Builder {

		private String userName;
		private String password;

		private Builder() {
		}

		public Builder userName(final String value) {
			this.userName = value;
			return this;
		}

		public Builder password(final String value) {
			this.password = value;
			return this;
		}

		public Login build() {
			return new com.jpa.Entity.Login(userName, password);
		}
	}

	public static Login.Builder builder() {
		return new Login.Builder();
	}

	private Login(final String userName, final String password) {
		this.userName = userName;
		this.password = password;
	}
	
	
	
	
}
