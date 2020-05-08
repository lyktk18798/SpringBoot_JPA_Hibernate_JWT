package com.lyktk.webbangiay.service;

import com.lyktk.webbangiay.domain.Customer;
import com.lyktk.webbangiay.repository.CustomerRepository;
import com.lyktk.webbangiay.utils.DateTimeUtils;
import com.lyktk.webbangiay.utils.StringUtils;
import com.lyktk.webbangiay.utils.exceptionHandler.LogicException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        customerRepository.save(customer);
    }

    @Override
    public Customer login(String email, String password) {
        Customer customer = customerRepository.findByEmail(email)
                .orElseThrow(() -> new LogicException(HttpStatus.NOT_FOUND, "Email or password is invalid! Let try again"));
        if(!passwordEncoder.matches(password, customer.getPassword())) {
            throw new LogicException(HttpStatus.NOT_FOUND, "Email or password is invalid! Let try again");
        }
        return customer;
    }
}
