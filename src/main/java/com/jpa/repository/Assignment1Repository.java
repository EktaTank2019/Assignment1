/*
 *To change this license header, choose License Headers in Project Properties.
 *To change this template file, choose Tools | Templates
 *and open the template in the editor.
 */

package com.jpa.repository;

import com.jpa.Entity.Customer1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



/**
 *
 * @author Ekta Tank
 * @since 28 Jan, 2019
 * @version 1.0.0
 */
@Repository
public interface  Assignment1Repository  extends JpaRepository<Customer1, String> {
Customer1 findByUserName(String userName);
}
