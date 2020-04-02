package com.lyktk.webbangiay.service;

import com.lyktk.webbangiay.domain.Customer;

import java.util.List;

/**
 * 
 * @author Lyktk
 *
 */
public interface CustomerService {

    public List<Customer> findAllCustomers(String email, String phone);

    void save(Customer customer);
}

