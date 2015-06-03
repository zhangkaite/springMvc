package com.zkt.find.goods.dao;

import java.util.List;

import com.zkt.find.goods.entity.AdminRecommend;
import com.zkt.find.goods.entity.FindCommentsEntity;
import com.zkt.find.goods.entity.FindEntity;
import com.zkt.find.goods.entity.FindPicEntity;
import com.zkt.find.goods.entity.FindUserCollection;
import com.zkt.find.goods.entity.FindWantUserEntity;
import com.zkt.find.goods.entity.UserAttentionEntiy;
import com.zkt.find.goods.entity.UserLableEntity;

public interface IFindDao {
	
	/**保存发布信息*/
	public boolean savePublish(String user_id, String find_desc, String find_attribute, String find_lables, String[] find_pics,
			String[] user_lables);

	public List<FindEntity> getFindList(String page_no, String page_size);

	public List<FindWantUserEntity> getWantUserInfo(FindEntity findEntity);

	public List<FindPicEntity> getPicList(FindEntity findEntity);

	public List<UserLableEntity> getUserLables(String find_id);

	public List<FindCommentsEntity> getComments(String find_id);

	public boolean insertAttentionData(String user_id, String attention_user_id);

	public boolean userWant(String user_id, String find_id);

	public List<FindWantUserEntity> showUserWantList(String find_id, String page_no, String page_size);

	public boolean UserComment(String find_id, String comment_user_id, String comment_content,String user_id);

	public List<FindCommentsEntity> ShowFindCommnets(String find_id, String page_no, String page_size);

	public boolean UserCollection(String find_id, String user_id);

	public boolean delUserCollection(String find_id, String user_id);

	public List<FindUserCollection> ShowUserCollection(String user_id, String page_no, String page_size);

	public String getPicUrl(String find_id);

	public List<UserAttentionEntiy> showMyAttention(String user_id, String page_no, String page_size);

	public List<FindEntity> getUserFindList(String user_id, String lable_id, String page_no, String page_size);

	public List<AdminRecommend> getAdminRecommend();

	public boolean isCollection(String find_id, String user_id);


}
