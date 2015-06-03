package com.zkt.find.register.dao.impl;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.zkt.find.common.util.Base64Util;
import com.zkt.find.common.util.MD5;
import com.zkt.find.common.util.ProtertiesUtil;
import com.zkt.find.register.dao.IRegisterDao;

@Repository
public class RegisterDaoImpl implements IRegisterDao{

	 private static Logger logger = LogManager.getLogger(RegisterDaoImpl.class);
	 private static String TABLE_NAME="t_user";
	 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	 SimpleDateFormat sdfs = new SimpleDateFormat("yyyyMMddHHmmss");
	 @Autowired
	    @Qualifier("jdbcTemplate1")
	    private JdbcTemplate jdbcWrite;

	    @Autowired
	    @Qualifier("jdbcTemplate2")
	    private JdbcTemplate jdbcRead;
	    
	/* (non-Javadoc)
	 * @see com.zkt.find.register.dao.IRegisterDao#usernameverify(java.lang.String)
	 */
	@Override
	public int usernameverify(String user_name) {
		// TODO Auto-generated method stub
		String sql = "select count(1) from " + TABLE_NAME + " where user_name = ? ";
		logger.info("sql:"+sql+ "  参数值:"+user_name);
		int num = jdbcRead.queryForInt(sql, user_name);
		 return num;
	}

	@Override
	public String saveregisteruser(String user_name, String user_pass,
			String user_sex, String nickname, String introduction,
			String machineID, String user_pic, String user_address,
			String user_type, String lableList) {
		// TODO Auto-generated method stub
		String uuid = UUID.randomUUID().toString();
		logger.info("============================保存注册信息===========================");
		// 获取配置文件的路径
		String imagPath = ProtertiesUtil.getValue("context.properties", "imagePath");
		String filePath = imagPath + File.separator + sdf.format(new Date());
		String fileName = sdfs.format(new Date()) + user_name   + ".jpg";
		Base64Util.GenerateImage(user_pic, filePath, fileName);
		String sql="insert into "+TABLE_NAME+" (user_id,user_name,user_pass,user_createTime,user_sex,nickname,"
				+ "introduction,device_id,user_pic,user_address,user_type,user_lableList)"
				+ " values(?,?,?,?,?,?,?,?,?,?,?,?)";
		MD5 md5=new MD5();
		Object[] params = new Object[]{uuid,user_name,md5.getMD5ofStr(user_pass),sdf.format(new Date()),user_sex,nickname,introduction
				,machineID, filePath + File.separator + fileName,user_address,user_type,lableList};
		int num= this.jdbcWrite.update(sql, params);
		
		if(lableList.contains(";"))
		{
			String[] lableListstr=lableList.split(";");
			for(int count=0;count<lableListstr.length;count++)
			{
				String lablesql="insert into  t_user_lable (user_id,lable_name,lable_count) values(?,?,?)";
				Object[] lableparams = new Object[]{uuid,lableListstr[count],0};
				this.jdbcWrite.update(lablesql,lableparams);
			}
		}
		if (num > 0) {
			return uuid;
		}
		logger.info("===============================保存注册信息结束==================================");
		return null;
		
	}


}
