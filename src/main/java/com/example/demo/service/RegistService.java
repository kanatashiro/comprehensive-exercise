package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Regist;
import com.example.demo.repository.RegistRepository;



@Service
@Transactional
public class RegistService {
	
	@Autowired
	private RegistRepository registRepository;
	
	
	public void insert(Regist Address) {
		registRepository.insert(Address);;
		
		
	}
	
	public List<Regist> findByEmail(String user_email) {
		return  registRepository.findByEmail(user_email);
	

}
	public Regist findBykey(String unique_key) {
		return  registRepository.findBykey(unique_key);
	
}

}

