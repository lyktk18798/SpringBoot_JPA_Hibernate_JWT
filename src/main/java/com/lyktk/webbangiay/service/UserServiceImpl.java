package com.lyktk.webbangiay.service;

import java.util.*;
import java.util.stream.Collectors;

import com.lyktk.webbangiay.domain.Role;
import com.lyktk.webbangiay.repository.RoleRepository;
import com.lyktk.webbangiay.utils.Constant;
import com.lyktk.webbangiay.utils.exceptionHandler.LogicException;
import org.hibernate.annotations.common.util.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.RandomStringUtils;

import com.lyktk.webbangiay.domain.User;
import com.lyktk.webbangiay.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JavaMailSender javaMailSender;


    @Override
	public Optional<User> getUserByUsername(String username) {
		if (StringHelper.isEmpty(username)) {
			return Optional.empty();
		}

		return Optional.of(new User());
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existsByUserName(String userName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean existsByEmail(String email) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<User> findAllUsers(String email, String fullname, String phonenumber, Integer role) {

	    List<Integer> roles = Arrays.asList(role);
	    if (role == Constant.ALL_ITEMS) {
	        List<Role> lstRoles= roleRepository.findAll();
	        roles= lstRoles.stream().map(Role::getId).collect(Collectors.toList());
        }
		return userRepository.findAllByEmailLikeAndAndFullnameLikeAndPhonenumberLikeAndRoleIdIn("%"+email.toUpperCase()+"%", "%"+fullname.toUpperCase()+"%", "%"+phonenumber.toUpperCase()+"%", roles);
	}

    @Override
    public void save(User u) {
	    if(u.getId() == null){
            u.setCreateBy(1);
            u.setCreateDate(new Date());
            String password = RandomStringUtils.randomAlphanumeric(Constant.NUMBER_OF_PASSWORD);
            u.setPassword(passwordEncoder.encode(password));
            sendMail(u.getEmail(), password);

        }else{
	        u.setUpdateBy(1);
            u.setUpdateDate(new Date());
        }
        userRepository.save(u);

    }

    @Override
    public void delete(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public void forgotPass(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new LogicException(HttpStatus.NOT_FOUND, email + " not exist!! Must try another email!"));

        String password = RandomStringUtils.randomAlphanumeric(Constant.NUMBER_OF_PASSWORD);
        user.setPassword(passwordEncoder.encode(password));
        sendMail(user.getEmail(), password);

        userRepository.save(user);
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
