package com.zkt.find.goods.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zkt.find.goods.dao.IFindDao;
import com.zkt.find.goods.entity.AdminRecommend;
import com.zkt.find.goods.entity.FindCommentsEntity;
import com.zkt.find.goods.entity.FindEntity;
import com.zkt.find.goods.entity.FindPicEntity;
import com.zkt.find.goods.entity.FindUserCollection;
import com.zkt.find.goods.entity.FindWantUserEntity;
import com.zkt.find.goods.entity.UserAttentionEntiy;
import com.zkt.find.goods.entity.UserLableEntity;
import com.zkt.find.goods.service.IFindService;

@Service
@Transactional
public class FindServiceImpl implements IFindService {
	
	@Autowired
	private IFindDao dao;

	@Override
	public boolean savePublish(String user_id, String find_desc, String find_attribute, String find_lables, String[] find_pics,
			String[] user_lables) {
		// TODO Auto-generated method stub
		return dao.savePublish(user_id, find_desc, find_attribute, find_lables, find_pics, user_lables);
	}

	@Override
	public List<FindEntity> getFindList(String page_no, String page_size) {
		// TODO Auto-generated method stub
		return dao.getFindList(page_no,page_size);
	}

	@Override
	public List<FindWantUserEntity> getWantUserInfo(FindEntity findEntity) {
		// TODO Auto-generated method stub
		return dao.getWantUserInfo(findEntity);
	}

	@Override
	public List<FindPicEntity> getPicList(FindEntity findEntity) {
		// TODO Auto-generated method stub
		return dao.getPicList(findEntity);
	}

	@Override
	public List<UserLableEntity> getUserLables(String find_id) {
		// TODO Auto-generated method stub
		return dao.getUserLables(find_id);
	}

	@Override
	public List<FindCommentsEntity> getComments(String find_id) {
		// TODO Auto-generated method stub
		return dao.getComments(find_id);
	}

	@Override
	public boolean insertAttentionData(String user_id, String attention_user_id) {
		// TODO Auto-generated method stub
		return dao.insertAttentionData(user_id,attention_user_id);
	}

	@Override
	public boolean userWant(String user_id, String find_id) {
		// TODO Auto-generated method stub
		return dao.userWant(user_id,find_id);
	}

	@Override
	public List<FindWantUserEntity> showUserWantList(String find_id, String page_no, String page_size) {
		// TODO Auto-generated method stub
		return dao.showUserWantList(find_id,page_no,page_size);
	}

	@Override
	public boolean UserComment(String find_id, String comment_user_id, String comment_content,String user_id) {
		// TODO Auto-generated method stub
		return dao.UserComment(find_id,comment_user_id,comment_content,user_id);
	}

	@Override
	public List<FindCommentsEntity> ShowFindCommnets(String find_id, String page_no, String page_size) {
		// TODO Auto-generated method stub
		return dao.ShowFindCommnets(find_id,page_no,page_size);
	}

	@Override
	public boolean UserCollection(String find_id, String user_id) {
		// TODO Auto-generated method stub
		return dao.UserCollection(find_id,user_id);
	}

	@Override
	public boolean delUserCollection(String find_id, String user_id) {
		// TODO Auto-generated method stub
		return dao.delUserCollection(find_id,user_id);
	}

	@Override
	public List<FindUserCollection> ShowUserCollection(String user_id, String page_no, String page_size) {
		// TODO Auto-generated method stub
		return dao.ShowUserCollection(user_id,page_no,page_size);
	}

	@Override
	public String getPicUrl(String find_id) {
		// TODO Auto-generated method stub
		return dao.getPicUrl(find_id);
	}

	@Override
	public List<UserAttentionEntiy> showMyAttention(String user_id, String page_no, String page_size) {
		// TODO Auto-generated method stub
		return dao.showMyAttention(user_id,page_no,page_size);
	}

	@Override
	public List<FindEntity> getUserFindList(String user_id, String lable_id, String page_no, String page_size) {
		// TODO Auto-generated method stub
		return dao.getUserFindList(user_id,lable_id,page_no,page_size);
	}

	@Override
	public List<AdminRecommend> getAdminRecommend() {
		// TODO Auto-generated method stub
		return dao.getAdminRecommend();
	}

	@Override
	public boolean isCollection(String find_id, String user_id) {
		// TODO Auto-generated method stub
		return dao.isCollection(find_id,user_id);
	}



}
