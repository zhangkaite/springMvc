package com.zkt.find.register.dao;


public interface IRegisterDao {

	int usernameverify(String  user_name);
	
	String saveregisteruser(String user_name,String user_pass,String user_sex,String nickname,String introduction,String machineID,String user_pic,String user_address,String user_type,String lableList);
}
