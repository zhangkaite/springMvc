package com.zkt.find.goods.entity;

public class UserAttentionEntiy {
	private String id;
	/** 用户ID */
	private String user_id;
	/** 被关注用户ID */
	private String attention_user_id;
	/** 关注时间 */
	private String attention_time;
	/** 关注类型 0 已关注 1 已互相关注 */
	private String attention_type;
	/** 被关注者用户昵称 */
	private String nickname;
	/** 被关注者用户头像url */
	private String user_pic;
	/** 被关注者用户个性化签名 */
	private String introduction;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getAttention_user_id() {
		return attention_user_id;
	}

	public void setAttention_user_id(String attention_user_id) {
		this.attention_user_id = attention_user_id;
	}

	public String getAttention_time() {
		return attention_time;
	}

	public void setAttention_time(String attention_time) {
		this.attention_time = attention_time;
	}

	public String getAttention_type() {
		return attention_type;
	}

	public void setAttention_type(String attention_type) {
		this.attention_type = attention_type;
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

}
