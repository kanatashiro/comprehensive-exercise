package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Regist;
import com.example.demo.domain.User;
import com.example.demo.form.UserForm;
import com.example.demo.repository.UserRepository;


@Service
@Transactional
public class UserService {
	

	@Autowired
	private UserRepository userRepository;
	
	
	
	public void insert(User user) {
		userRepository.insert(user);
		
	}	
	public List<User> findByEmail(String mail_address) {
		return userRepository.findByEmail(mail_address);
	

}
	
	
	
	

}
