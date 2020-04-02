package com.lyktk.webbangiay.service;

import com.lyktk.webbangiay.domain.Customer;
import com.lyktk.webbangiay.repository.CustomerRepository;
import com.lyktk.webbangiay.utils.DateTimeUtils;
import com.lyktk.webbangiay.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<Customer> findAllCustomers(String email, String phone) {
        return customerRepository.findByEmailLikeAndAndPhoneLike("%"+email.toUpperCase()+"%",
                                                                "%"+phone.toUpperCase()+"%");
    }

    @Override
    public void save(Customer customer) {
        customer.setDateRegistered(new Date());
        customer.setPassword(passwordEncoder.encode(StringUtils.genPass()));
        customerRepository.save(customer);
    }
}
