package com.example.demo.controller;

import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jca.cci.RecordTypeNotSupportedException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.Regist;
import com.example.demo.domain.User;
import com.example.demo.form.RegistForm;
import com.example.demo.form.UserForm;
import com.example.demo.service.RegistService;
import com.example.demo.service.UserService;
import com.fasterxml.jackson.annotation.JacksonInject.Value;
import com.fasterxml.jackson.databind.ser.std.UUIDSerializer;

@Controller
@RequestMapping("/touroku")

public class UserController {

	@ModelAttribute
	private UserForm userForm() {
		return new UserForm();
	}

	@Autowired
	private HttpSession session;

	@Autowired
	private RegistService registService;

	@Autowired
	private UserService userService;

	@ModelAttribute
	private RegistForm registForm() {
		return new RegistForm();

	}

//--------登録画面に遷移

	@RequestMapping("/aaa")
	public String index4(String key) {
		session.setAttribute("unique_key", key);
		Regist urlkey = registService.findBykey(key);
		System.out.println(urlkey);
		LocalDateTime nowDate = LocalDateTime.now();
		if (urlkey != null) {
			LocalDateTime deadline = urlkey.getRegist_date().plusDays(1);
			if (nowDate.isBefore(deadline)) {
				session.setAttribute("mail_address", urlkey.getUser_email());
				return "user";

			} else {
				return "jaja";
			}
		}
		return "jaja";
	}

//--------------入力値チェック
	@RequestMapping("/tourokukannryou")
	public String insert(@Validated UserForm form, BindingResult result) {

//	User duplicateRegist = registService.findByEmail(RegistForm.());

		if (!form.getConfirmpassword().isEmpty() && !form.getPassword().isEmpty()
				&& !form.getPassword().equals(form.getConfirmpassword())) {
			result.rejectValue("confirmpassword", "", "パスワードが一致していません");
		}

		if (result.hasErrors()) {
			return "user";
		}

//---------------入力内容登録

		User user = new User();

		user.setName(form.getName());
		user.setRuby(form.getRuby());
		user.setMail_address(String.valueOf(session.getAttribute("mail_address")));
		user.setZipcode(form.getZipcode());
		user.setAddress(form.getAddress());
		user.setTelephone(form.getTelephone());
		user.setPassword(form.getPassword());
		user.setDel_flg(0);

		userService.insert(user);

		registService.update(user.getMail_address());

		return "redirect:/touroku/index3";
	}

	@RequestMapping("/index3")
	public String index3() {
		return "tourokukannryou";
	}

}
