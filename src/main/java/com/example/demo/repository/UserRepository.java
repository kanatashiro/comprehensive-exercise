package com.example.demo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Regist;
import com.example.demo.domain.User;
import com.example.demo.form.UserForm;

@Repository
public class UserRepository {

@Autowired
private NamedParameterJdbcTemplate template;

//@Autowired
//private PasswordEncoder passwordEncoder;

private static final RowMapper<User> USER_ROW_MAPPER  = (rs, i) -> {
	User user = new User();
	user.setId(rs.getInt("id"));
	user.setName(rs.getString("name"));
	user.setRuby(rs.getString("ruby"));
	user.setUser_email(rs.getString("mail_address"));
	user.setPassword(rs.getString("password"));
	user.setZipcode(rs.getString("zipcode"));
	user.setAddress(rs.getString("address"));
	user.setTelephone(rs.getString("telephone"));
	return user;
};

//データ情報を挿入する

public void insert(User user) {
	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	user.setPassword(passwordEncoder.encode(user.getPassword()));
	SqlParameterSource param = new BeanPropertySqlParameterSource(user);
	String sql = "INSERT INTO users(name, ruby, mail_address,zipcode,address,telephone,password,del_flg)"
			+ " VALUES(:name,:ruby,:mail_address,:zipcode,:address,:telephone,:password,:del_flg);";
	template.update(sql, param);
}



public List<User> findByEmail(String mail_address) {
	String sql = "SELECT * from users WHERE mail_address=:mail_address ";
	SqlParameterSource param = new MapSqlParameterSource().addValue("mail_address", mail_address);
	 List<User> emailRegist = template.query(sql, param,USER_ROW_MAPPER);
		if(emailRegist.size() == 0) {
			return null;
		}
		return emailRegist;
	
	}




	




}