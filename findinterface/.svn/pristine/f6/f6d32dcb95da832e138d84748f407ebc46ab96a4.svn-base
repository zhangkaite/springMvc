package com.zkt.find.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ResourceBundle;


public class HttpConnect {
	private static HttpConnect instance;
	
	public static HttpConnect getInstance() {
		if (instance == null) {
			instance = new HttpConnect();
		}
		return instance;
	}
	
	public String HttpConnectUtil(String params) throws IOException 
	{
		ResourceBundle bundle = ResourceBundle.getBundle("resources");
		String permsystem_interface_url = bundle.getString("permsystem_interface_url");
		URL url = new URL(permsystem_interface_url);
		HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
		urlConnection.setDoOutput(true);// URL 连接可用于输入和/或输出
		urlConnection.setDoInput(true);// URL 连接可用于输入和/或输出
		urlConnection.setUseCaches(false);
		OutputStreamWriter osw = new OutputStreamWriter(urlConnection.getOutputStream());
		// 设置包体
		osw.write(params);
		osw.flush();
		osw.close();
		// 取得返回包体
		InputStream in = urlConnection.getInputStream();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in, "utf-8"));// 取得返回内容
		String line = bufferedReader.readLine();
		return line;
	}
}
