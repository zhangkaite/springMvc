package com.zkt.find.common.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;

public abstract class BaseDao {

	@Autowired
	@Qualifier("jdbcTemplate1")
	private JdbcTemplate jdbcWrite;

	@Autowired
	@Qualifier("jdbcTemplate2")
	private JdbcTemplate jdbcRead;

	public JdbcTemplate getJdbcWrite() {
		return jdbcWrite;
	}

	public void setJdbcWrite(JdbcTemplate jdbcWrite) {
		this.jdbcWrite = jdbcWrite;
	}

	public JdbcTemplate getJdbcRead() {
		return jdbcRead;
	}

	public void setJdbcRead(JdbcTemplate jdbcRead) {
		this.jdbcRead = jdbcRead;
	}
	
}
