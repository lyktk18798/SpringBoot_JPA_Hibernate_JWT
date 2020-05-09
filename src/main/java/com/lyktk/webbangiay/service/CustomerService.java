package com.lyktk.webbangiay.service;

import com.lyktk.webbangiay.domain.Customer;

import java.util.List;

/**
 * 
 * @author Lyktk
 *
 */
public interface CustomerService {

    List<Customer> findAllCustomers(String email, String phone);

    void save(Customer customer);

    Customer login(String email, String password);

    void forgotPass(String email);

}

