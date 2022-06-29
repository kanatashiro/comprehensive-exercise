package com.example.demo.repository;

import java.util.List;

import javax.management.loading.PrivateClassLoader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Regist;
import com.example.demo.domain.User;

@Repository
public class RegistRepository {
	@Autowired
	private NamedParameterJdbcTemplate template;

	private static final RowMapper<Regist> REGIST_ROW_MAPPER = (rs, i) -> {
		Regist regist = new Regist();
		regist.setUser_email(rs.getString("user_email"));
		regist.setRegist_date(rs.getTimestamp("regist_date").toLocalDateTime());
		regist.setUnique_key(rs.getString("unique_key"));
		regist.setDel_flg(rs.getInt("del_flg"));

		return regist;
	};

	public void insert(Regist regist) {
		String insertSql = "insert into regist_url(user_email,unique_key,del_flg)VALUES(:user_email,:unique_key,:del_flg) ;";
		SqlParameterSource param = new BeanPropertySqlParameterSource(regist);
		template.update(insertSql, param);
	}

	public List<Regist> findByEmail(String user_email) {
		String sql = "SELECT * from regist_url WHERE user_email=:user_email and regist_date +cast('1 days' as interval)>now();";
		SqlParameterSource param = new MapSqlParameterSource().addValue("user_email", user_email);
		List<Regist> emailRegist = template.query(sql, param, REGIST_ROW_MAPPER);
		if (emailRegist.size() == 0) {
			return null;
		}
		return emailRegist;
	}

	public Regist findBykey(String unique_key) {
		String sql = "SELECT user_email, regist_date, unique_key, del_flg from regist_url WHERE unique_key=:unique_key and del_flg=0;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("unique_key", unique_key);
		try {
			return template.queryForObject(sql, param, REGIST_ROW_MAPPER);
		} catch (DataAccessException e) {
			return null;

		}
	}
		
		public void update(String user_email) {
			String updatesql = "UPDATE regist_url SET del_flg = 1 WHERE user_email=:user_email;";
			SqlParameterSource param = new MapSqlParameterSource().addValue("user_email", user_email);
			template.update(updatesql, param);

	}
}
