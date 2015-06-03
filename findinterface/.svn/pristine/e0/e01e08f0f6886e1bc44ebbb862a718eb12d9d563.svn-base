package com.zkt.find.common.util;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

public class SessionContext {

	private static SessionContext instance;
	
	private HashMap<String, Object> mymap;

	private SessionContext() {
		mymap = new HashMap<String, Object>();
	}

	public static SessionContext getInstance() {
		if (instance == null) {
			instance = new SessionContext();
		}
		return instance;
	}

	public void AddSession(String key, HttpSession session) {
		mymap.put(key, session);
	}

	public void DelSession(String key) {
		HttpSession hs = (HttpSession) mymap.get(key);
		if (null != hs) {
			try {
				hs.invalidate();
			} catch (IllegalStateException e) {

			}
		}
		mymap.remove(key);

	}

	public HttpSession getSession(String key) {
		if (key == null)
			return null;
		return (HttpSession) mymap.get(key);
	}

}