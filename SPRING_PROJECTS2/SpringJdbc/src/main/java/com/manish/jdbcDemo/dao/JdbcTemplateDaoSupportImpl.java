package com.manish.jdbcDemo.dao;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class JdbcTemplateDaoSupportImpl extends JdbcDaoSupport {
	
	public int getCircleCount(){
		
		String sql="select count(*) from circle";
		return getJdbcTemplate().queryForObject(sql,Integer.class);
//		OR
//		return jdbcTemplate.querForInt(sql);		
		
	}

}
