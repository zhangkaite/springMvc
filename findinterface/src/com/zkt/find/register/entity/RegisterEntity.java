package com.zkt.find.register.entity;

import java.util.Date;

public class RegisterEntity {

	//id
	private String user_id;
	//user名称
	private String user_name;
	//user密码
	private String user_pass;
	//创建时间
	private Date user_createTime;
	public Date getUser_createTime() {
		return user_createTime;
	}
	public void setUser_createTime(Date user_createTime) {
		this.user_createTime = user_createTime;
	}
	//性别  0女  1男
	private int user_sex;
	//昵称
	private String nickname;
	//描述
	private String introduction;
	//设备id
	private String device_id;
	//图片
	private String user_pic;
	//地址
	private String user_address;
	//用户类别  1普通 2微信  3 微博
	private int user_type;
	//达人标签
	private String user_lableList;
	
	public String getUser_lableList() {
		return user_lableList;
	}
	public void setUser_lableList(String user_lableList) {
		this.user_lableList = user_lableList;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_pass() {
		return user_pass;
	}
	public void setUser_pass(String user_pass) {
		this.user_pass = user_pass;
	}
	public int getUser_sex() {
		return user_sex;
	}
	public void setUser_sex(int user_sex) {
		this.user_sex = user_sex;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public String getDevice_id() {
		return device_id;
	}
	public void setDevice_id(String device_id) {
		this.device_id = device_id;
	}
	public String getUser_pic() {
		return user_pic;
	}
	public void setUser_pic(String user_pic) {
		this.user_pic = user_pic;
	}
	public String getUser_address() {
		return user_address;
	}
	public void setUser_address(String user_address) {
		this.user_address = user_address;
	}
	public int getUser_type() {
		return user_type;
	}
	public void setUser_type(int user_type) {
		this.user_type = user_type;
	}
	
	
}
