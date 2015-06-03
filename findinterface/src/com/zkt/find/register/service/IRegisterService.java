package com.zkt.find.register.service;


public interface IRegisterService {

	public	int usernameverify(String user_name);
	
	public String saveregisteruser(String user_name,String user_pass,String user_sex,String nickname,String introduction,String device_id,String user_pic,String user_address,String user_type,String user_lableList);
}
