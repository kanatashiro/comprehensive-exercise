package com.example.demo.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;


public class RegistForm {

	/**メールアドレス*/
	@NotBlank(message="メールアドレスを入力して下さい")
	@Email(message="メールアドレスの形式が不正です")
	private String user_email;
	private String unique_key;
	private String regist_date;
	 private int del_flg;

   
   
public int getDel_flg() {
		return del_flg;
	}
	public void setDel_flg(int del_flg) {
		this.del_flg = del_flg;
	}
public String getUnique_key() {
		return unique_key;
	}
	public void setUnique_key(String unique_key) {
		this.unique_key = unique_key;
	}
public String getUser_email() {
	return user_email;
}
public String getRegist_date() {
	return regist_date;
}
public void setRegist_date(String regist_date) {
	this.regist_date = regist_date;
}
public void setUser_email(String user_email) {
	this.user_email = user_email;

}
@Override
public String toString() {
	return "RegistForm [user_email=" + user_email + ", regist_date=" + "]";
}
   
   
   
   
   
}