package com.zkt.find.login.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zkt.find.login.dao.ILoginDao;
import com.zkt.find.login.service.ILoginService;
import com.zkt.find.register.entity.RegisterEntity;
@Service
@Transactional
public class LoginServiceImpl  implements ILoginService {
	@Autowired
	private ILoginDao dao;
	@Override
	public List<RegisterEntity> userNoExit(String user_name) {
		// TODO Auto-generated method stub
		return dao.userNoExit(user_name);
	}
	@Override
	public Boolean updatePass(String new_old_pass, String user_id) {
		// TODO Auto-generated method stub
		return dao.updatePass(new_old_pass,user_id);
	}

}
