package com.example.demo.controller;


import java.util.List;
import java.util.UUID;

import javax.mail.Address;
import javax.mail.Session;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.Regist;
import com.example.demo.domain.User;
import com.example.demo.form.RegistForm;
import com.example.demo.service.RegistService;
import com.example.demo.service.UserService;



@Controller
@RequestMapping("/regist")
public class RegistController {
	
//----メール登録画面
	
@RequestMapping("")
public String index() {	
	return "regist";	
}

@Autowired
private MailSender mailSender;

@Autowired
private RegistService registService;

@Autowired
private UserService userService;



@ModelAttribute
public RegistForm setUpForm() {
	return new RegistForm();
}

//-----メールアドレス入力値チェック
//------入力値エラーのため、入力登録画面に遷移

@RequestMapping("/mailkannryou")
public String registConfirm(@Validated RegistForm form,BindingResult result ) {
	
List<Regist> urlList = registService.findByEmail(form.getUser_email());
List<User>  userList = userService.findByEmail(form.getUser_email());
if(urlList != null){
	result.rejectValue("user_email", null,"24時間内にURLを発行済みです");
	return "regist";
}
if(userList != null) {
	result.rejectValue("user_email", null,"既にメールアドレスが登録されています。");
	return "regist";
	
}
if(result.hasErrors()){
return "regist";
}else{

String unique_key = UUID.randomUUID().toString();
SimpleMailMessage msg = new SimpleMailMessage();

try {
	msg.setFrom("tourokukannryou@mail.com");
	msg.setSubject("ユーザー登録用のURLの送付");
	msg.setTo(form.getUser_email());
	msg.setText("http://localhost:8080/touroku/?key=" + unique_key);
	mailSender.send(msg);
	Regist regist = new Regist();
	regist.setUnique_key(unique_key);
	regist.setUser_email(form.getUser_email());
	regist.setDel_flg(0);
	registService.insert(regist);
} catch (MailException e) {
	e.printStackTrace();
}	
}
return "redirect:/regist/index2";
}
@RequestMapping("/index2")
public String index2() {
	return "sousinnkannryou";
}
}







