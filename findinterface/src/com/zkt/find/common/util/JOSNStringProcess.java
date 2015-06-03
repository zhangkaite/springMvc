package com.zkt.find.common.util;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JOSNStringProcess {

	/**
	 * 
	 * @param list
	 * @return
	 */
	public static String toJOSNArray(List list) {
		JSONArray jsonArray = JSONArray.fromObject(list);
		return jsonArray.toString();
	}

	/**
	 * 
	 * @param object
	 * @return
	 */
	public static String toJOSNObjce(Object object) {
		JSONObject jsonObject = JSONObject.fromObject(object);
		return jsonObject.toString();
	}
}
