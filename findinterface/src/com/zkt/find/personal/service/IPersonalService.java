package com.zkt.find.personal.service;

import java.util.List;

import com.zkt.find.personal.entity.FanseEntity;
import com.zkt.find.personal.entity.FashionPerEntity;
import com.zkt.find.personal.entity.LableUserEntity;
import com.zkt.find.personal.entity.NoticeNewsEntity;
import com.zkt.find.personal.entity.PersonalEntity;
import com.zkt.find.personal.entity.PersonalGoodsEntity;
import com.zkt.find.personal.entity.PrivateChatEntity;
import com.zkt.find.personal.entity.PrivateChatUserEntity;

public interface IPersonalService {

	PersonalEntity  personalinfo(String user_id);
	
	public List<PersonalGoodsEntity> personalGoodsInfo(String user_id);
	
	public Boolean saveFeedInfo(String feed_info,String phone_number,String user_id,String feed_time);
	
	public List<FanseEntity> fanselist(String user_id);
	public Boolean savePrivateChat(String user_id,String other_user_id,String content,String content_time,String operation_type);
	public List<PrivateChatEntity> showPrivateChat(String user_id);
	public List<PrivateChatUserEntity> showPrivateChatDetail(String user_id,String other_user_id);
	public Boolean saveAddScore(String user_id,String lable_id,String find_id,String addscore_id);
	List<NoticeNewsEntity> getNoticeNews(String rec_user_id,String send_user_id,String type);
	public List<LableUserEntity> getUserByLable(String lable_name);
	public List<FashionPerEntity> showFashionPer(String user_id);
}
