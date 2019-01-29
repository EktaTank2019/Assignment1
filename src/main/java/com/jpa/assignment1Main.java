/*
 *To change this license header, choose License Headers in Project Properties.
 *To change this template file, choose Tools | Templates
 *and open the template in the editor.
 */
package com.jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


/**
 *
 * @author Ekta Tank
 * @since 28 Jan, 2019
 * @version 1.0.0
 */
@SpringBootApplication
@EntityScan("com.jpa.Entity")
@ComponentScan("com.jpa")
@EnableJpaRepositories("com.jpa.repository")


public class assignment1Main {

	@Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	public static void main(String[] arg) {
		SpringApplication.run(assignment1Main.class, arg);
	}

}
