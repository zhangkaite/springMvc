package com.zkt.find.personal.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zkt.find.personal.dao.IPersonalDao;
import com.zkt.find.personal.entity.FanseEntity;
import com.zkt.find.personal.entity.FashionPerEntity;
import com.zkt.find.personal.entity.LableUserEntity;
import com.zkt.find.personal.entity.NoticeNewsEntity;
import com.zkt.find.personal.entity.PersonalEntity;
import com.zkt.find.personal.entity.PersonalGoodsEntity;
import com.zkt.find.personal.entity.PrivateChatEntity;
import com.zkt.find.personal.entity.PrivateChatUserEntity;
import com.zkt.find.personal.service.IPersonalService;
@Service
@Transactional
public class PersonalServiceImpl implements IPersonalService{
	@Autowired
	private IPersonalDao dao;
	@Override
	public PersonalEntity personalinfo(String user_id) {
		// TODO Auto-generated method stub
		return dao.personalinfo(user_id);
	}

	public List<PersonalGoodsEntity> personalGoodsInfo(String user_id)
	{
		return dao.persongoodsinfo(user_id); 
	}

	@Override
	public Boolean saveFeedInfo(String feed_info, String phone_number,
			String user_id, String feed_time) {
		// TODO Auto-generated method stub
		return dao.saveFeedInfo(feed_info, phone_number, user_id, feed_time);
	}

	@Override
	public List<FanseEntity> fanselist(String user_id) {
		// TODO Auto-generated method stub
		return dao.fanselist(user_id);
	}

	@Override
	public Boolean savePrivateChat(String user_id, String other_user_id,
			String content, String content_time, String operation_type) {
		// TODO Auto-generated method stub
		return dao.savePrivateChat(user_id, other_user_id, content, content_time, operation_type);
	}

	@Override
	public List<PrivateChatEntity> showPrivateChat(String user_id) {
		// TODO Auto-generated method stub
		return dao.showPrivateChat(user_id);
	}

	@Override
	public List<PrivateChatUserEntity> showPrivateChatDetail(String user_id,
			String other_user_id) {
		// TODO Auto-generated method stub
		return dao.showPrivateChatDetail(user_id,other_user_id);
	}
	@Override
	public Boolean saveAddScore(String user_id,String lable_id,String find_id,String addscore_id)
	{
		return dao.saveAddScore(user_id,lable_id,find_id,addscore_id);
	}

	@Override
	public List<NoticeNewsEntity> getNoticeNews(String rec_user_id,
			String send_user_id, String type) {
		// TODO Auto-generated method stub
		return dao.getNoticeNews(rec_user_id, send_user_id, type);
	}

	@Override
	public List<LableUserEntity> getUserByLable(String lable_name) {
		// TODO Auto-generated method stub
		return dao.getUserByLable(lable_name);
	}

	@Override
	public List<FashionPerEntity> showFashionPer(String user_id) {
		// TODO Auto-generated method stub
		return dao.showFashionPer(user_id);
	}
}
