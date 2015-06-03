package com.zkt.find.login.dao.impl;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.zkt.find.login.dao.ILoginDao;
import com.zkt.find.register.entity.RegisterEntity;
@Repository
public class LoginDaoImpl implements ILoginDao {

	private static Logger logger = LogManager.getLogger(ILoginDao.class);
	private static String TABLE_NAME = "t_user";
	@Autowired
	@Qualifier("jdbcTemplate1")
	private JdbcTemplate jdbcWrite;

	@Autowired
	@Qualifier("jdbcTemplate2")
	private JdbcTemplate jdbcRead;

	@Override
	public List<RegisterEntity> userNoExit(String user_name) {
		// TODO Auto-generated method stub
		Boolean flag = true;
		String sql = "select user_id,user_name,user_pass,"
				+ "user_createTime,user_sex,nickname,"
				+ "introduction,device_id,user_pic,user_address,"
				+ "user_type,user_lableList from t_user"
				+ TABLE_NAME + " where user_name = '"+user_name+"' ";
		logger.info("sql:" + sql + "  参数值:" + user_name);
		return jdbcRead.query(sql,BeanPropertyRowMapper.newInstance(RegisterEntity.class));
	}

	@Override
	public Boolean updatePass(String new_old_pass, String user_id) {
		// TODO Auto-generated method stub
		 Boolean flag=true;
		 String sql = "update  " + TABLE_NAME
	                + " set user_pass=? where user_id=? ";
	        logger.info("用户注修改密码sql：" + sql + "参数值:" + new_old_pass + "," + user_id);
	        Object[] params = new Object[] { new_old_pass, user_id };
	        int num = this.jdbcWrite.update(sql, params);
	        if (num > 0)
	        {
	            return true;
	        }
	        return false;
	}

}
