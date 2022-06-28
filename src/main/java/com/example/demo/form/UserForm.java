package com.example.demo.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserForm {
	/**id(主キー)*/
	private Integer id;
	/**名前*/
	@NotBlank(message="名前を入力して下さい")
	private String name;
	
	/**ふりがな*/
	@NotBlank(message="ふりがなを入力して下さい")
	private String ruby;
	
	private String mail_address;
	
	public String getMail_address() {
		return mail_address;
	}
	public void setMail_address(String mail_address) {
		this.mail_address = mail_address;
	}
	/**郵便番号*/
	@NotBlank(message="郵便番号を入力して下さい")
	@Pattern(regexp="^[0-9]{3}-[0-9]{4}$", message="郵便番号はXXX-XXXXの形式で入力してください")
	private String zipcode;
	/**住所*/
	@NotBlank(message="住所を入力して下さい")
	private String address;
	/**電話番号*/
	@NotBlank(message="電話番号を入力して下さい")
	@Pattern(regexp="^[0-9]{3}-[0-9]{4}-[0-9]{4}$", message="電話番号はXXX-XXXX-XXXXの形式で入力してください")
	private String telephone;
	/**パスワード*/
	@NotBlank(message="パスワードを入力して下さい")
	@Size(min=8, max=16, message="パスワードは８文字以上１６文字以内で設定してください")
	private String password;
	/**確認用パスワード*/
	@NotBlank(message="パスワードを入力して下さい")
	@Size(min=8, max=16, message="パスワードは８文字以上１６文字以内で設定してください")
	private String confirmpassword;
	

	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRuby() {
		return ruby;
	}
	public void setRuby(String ruby) {
		this.ruby = ruby;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmpassword() {
		return confirmpassword;
	}
	public void setConfirmpassword(String confirmpassword) {
		this.confirmpassword = confirmpassword;
	}
	@Override
	public String toString() {
		return "UserForm [id=" + id + ", name=" + name + ", ruby=" + ruby + ", zipcode=" + zipcode + ", address="
				+ address + ", telephone=" + telephone + ", password=" + password + ", confirmpassword="
				+ confirmpassword + "]";
	}

	
	
}