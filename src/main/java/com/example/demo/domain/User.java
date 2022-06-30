package com.example.demo.domain;

public class User {

	/**id(主キー)*/
	private Integer id;
	/**名前*/
	private String name;
	/**ふりがな*/
	private String ruby;
	
	private String mail_address;
	/**郵便番号*/
	private String zipcode;
	/**住所*/
	private String address;
	/**電話番号*/
	private String telephone;
	/**パスワード*/
	private String password;
	
	 private int del_flg;
	
	
	public String getMail_address() {
		return mail_address;
	}
	public void setMail_address(String mail_address) {
		this.mail_address = mail_address;
	}
	public int getDel_flg() {
		return del_flg;
	}
	public void setDel_flg(int del_flg) {
		this.del_flg = del_flg;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUser_email() {
		return mail_address;
	}
	public void setUser_email(String user_email) {
		this.mail_address = mail_address;
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
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", ruby=" + ruby + ", zipcode=" + zipcode + ", address=" + address
				+ ", telephone=" + telephone + ", password=" + password + "]";
	}
	
}
