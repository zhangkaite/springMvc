package com.zkt.find.goods.entity;

import java.io.File;
@SuppressWarnings("unused")
/**find 上传图片存储实体*/
public class FindPicEntity {

	private String id;
	private String find_id;
	private String pic_path;
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


	public String getPic_path() {
		return pic_path;
	}

	public void setPic_path(String pic_path) {
		this.pic_path = pic_path;
	}



	
	
}
