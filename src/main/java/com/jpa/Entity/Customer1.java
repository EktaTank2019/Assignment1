/*
 *To change this license header, choose License Headers in Project Properties.
 *To change this template file, choose Tools | Templates
 *and open the template in the editor.
 */
package com.jpa.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

/**
 *
 * @author Ekta Tank
 * @since 28 Jan, 2019
 * @version 1.0.0
 */
@Entity

public class Customer1 {

	@Id
	@NotBlank
	private String userName;

	@NotBlank
	private String name;
	private String accountType;
	private String accountNumber;

	@NotBlank
	private String password;

	public Customer1() {
	}

	private Customer1(Builder build) {

		this.name = build.name;
		this.accountType = build.accountType;
		this.accountNumber = build.accountNumber;
		this.userName = build.userName;
		this.password = build.password;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public static class Builder {

		private String id;
		private String name;
		private String accountType;
		private String accountNumber;
		private String userName;
		private String password;

		private Builder() {
		}

		public Builder id(final String value) {
			this.id = value;
			return this;
		}

		public Builder name(final String value) {
			this.name = value;
			return this;
		}

		public Builder accountType(final String value) {
			this.accountType = value;
			return this;
		}

		public Builder accountNumber(final String value) {
			this.accountNumber = value;
			return this;
		}

		public Builder userName(final String value) {
			this.userName = value;
			return this;
		}

		public Builder password(final String value) {
			this.password = value;
			return this;
		}

		public Customer1 build() {
			return new Customer1(this);
		}
	}

	public static Customer1.Builder builder() {
		return new Customer1.Builder();
	}

}
