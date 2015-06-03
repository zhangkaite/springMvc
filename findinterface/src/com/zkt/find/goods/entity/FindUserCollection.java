package com.zkt.find.goods.entity;


public class FindUserCollection {

	private String id;
	/** 收藏发现的文章 */
	private String find_id;
	/** 收藏用户ID */
	private String user_id;
	/** 收藏时间 */
	private String collection_time;
	/** 收藏者用户昵称 */
	private String nickname;
	/** 收藏者用户头像url */
	private String user_pic;
	/** 收藏者用户个性化签名 */
	private String introduction;

	private String findPicUrls;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFind_id() {
		return find_id;
	}

	public void setFind_id(String find_id) {
		this.find_id = find_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getCollection_time() {
		return collection_time;
	}

	public void setCollection_time(String collection_time) {
		this.collection_time = collection_time;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getUser_pic() {
		return user_pic;
	}

	public void setUser_pic(String user_pic) {
		this.user_pic = user_pic;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getFindPicUrls() {
		return findPicUrls;
	}

	public void setFindPicUrls(String findPicUrls) {
		this.findPicUrls = findPicUrls;
	}

	

}
