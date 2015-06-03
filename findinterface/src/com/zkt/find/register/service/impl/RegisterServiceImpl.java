package com.zkt.find.register.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zkt.find.register.dao.IRegisterDao;
import com.zkt.find.register.service.IRegisterService;
@Service
@Transactional
public class RegisterServiceImpl implements IRegisterService {
	@Autowired
	private IRegisterDao dao;

	@Override
	public int usernameverify(String user_name) {
		// TODO Auto-generated method stub
		return dao.usernameverify(user_name);
	}


	@Override
	public String saveregisteruser(String user_name, String user_pass,
			String user_sex, String nickname, String introduction,
			String device_id, String user_pic, String user_address,
			String user_type, String user_lableList) {
		// TODO Auto-generated method stub
		
		return dao.saveregisteruser(user_name, user_pass, user_sex, nickname, introduction, device_id, user_pic, user_address, user_type, user_lableList);
	}

	

}
