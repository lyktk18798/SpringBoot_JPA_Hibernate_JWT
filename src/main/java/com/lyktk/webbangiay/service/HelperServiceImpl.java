package com.lyktk.webbangiay.service;

import com.lyktk.webbangiay.domain.*;
import com.lyktk.webbangiay.repository.*;
import com.lyktk.webbangiay.utils.Constant;
import com.lyktk.webbangiay.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class HelperServiceImpl implements HelperService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private ProducerRepository producerRepository;
    @Autowired
    private ColorRepository colorRepository;
    @Autowired
    private ProductGroupRepository productGroupRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public List<Category> findAllCategory() {
        return categoryRepository.findAllByStatusIs(Constant.ACTIVE);
    }

    @Override
    public List<Role> findAllRole() {
        return roleRepository.findAll();
    }

    @Override
    public List<Producer> findAllProducer() {
        return producerRepository.findAllByStatusIs(Constant.ACTIVE);
    }

    @Override
    public List<Color> findAllColors() {
        return colorRepository.findAllByStatusIs(Constant.ACTIVE);
    }

    @Override
    public List<ProductGroup> findAllProductGroups() {
        return productGroupRepository.findAllByStatusIs(Constant.ACTIVE);
    }

    @Override
    public void sendMail(String emailTo) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo("cuanho1871998@gmail.com");

        msg.setSubject("Testing from Spring Boot");
        msg.setText("Hello World \n Spring Boot Email");

        javaMailSender.send(msg);
    }
}
