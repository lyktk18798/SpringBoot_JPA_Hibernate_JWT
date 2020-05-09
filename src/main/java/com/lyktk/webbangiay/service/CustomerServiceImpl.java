package com.lyktk.webbangiay.service;

import com.lyktk.webbangiay.domain.Customer;
import com.lyktk.webbangiay.domain.User;
import com.lyktk.webbangiay.repository.CustomerRepository;
import com.lyktk.webbangiay.utils.Constant;
import com.lyktk.webbangiay.utils.DateTimeUtils;
import com.lyktk.webbangiay.utils.StringUtils;
import com.lyktk.webbangiay.utils.exceptionHandler.LogicException;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
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

    @Autowired
    private JavaMailSender javaMailSender;

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

    @Override
    public void forgotPass(String email) {
        Customer customer = customerRepository.findByEmail(email)
                .orElseThrow(() -> new LogicException(HttpStatus.NOT_FOUND, email + " not exist!! Must try another email!"));

        String password = RandomStringUtils.randomAlphanumeric(Constant.NUMBER_OF_PASSWORD);
        customer.setPassword(passwordEncoder.encode(password));
        sendMail(customer.getEmail(), password);

        customerRepository.save(customer);
    }

    private void sendMail(String emailTo, String password) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(emailTo);

        msg.setSubject("Bitis Hunter store");
        msg.setText("You've just registered member in Bitis Hunter store" +
                "\n We'll provide password default to login in our website" +
                "\n Your password is "+password+ "\nThank you very much to visit our website!");


        javaMailSender.send(msg);
    }
}
