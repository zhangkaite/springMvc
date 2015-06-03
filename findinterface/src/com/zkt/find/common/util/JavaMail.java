package com.zkt.find.common.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class JavaMail {
	
	
	private static Properties props = new Properties();

	private static Session session = Session.getInstance(props);
	

	public static void setProps(String host){
		
		// Props中配置的参数同发送邮件有关（仅创建邮件时可以不设置）
				props.setProperty("mail.transport.protocol", "smtp");
				props.setProperty("mail.host", host);
				props.setProperty("mail.smtp.auth", "true");// 请求认证
	}
	

	public static MimeMessage createMessage(String subject,String sendMailAccount,String recMailAccount) {
		// 创建MimeMessage对象:代表一封邮件
		MimeMessage message = new MimeMessage(session);
		// 设置邮件头
		try {
			message.setFrom(new InternetAddress(sendMailAccount));
			message.setRecipients(Message.RecipientType.TO, recMailAccount);
			message.setSubject(subject);
			return message;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	 public static void sendMail(Message message,String SENDMAIL_ACCOUNT,String SENDMAIL_PASSWD){
	        try {
	            Transport ts = session.getTransport();
	            ts.connect(SENDMAIL_ACCOUNT,SENDMAIL_PASSWD);
	            ts.sendMessage(message, message.getAllRecipients());
	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        }
	    }

	 public static String readValue(String filePath,String key) {
		  Properties props = new Properties();
		        try {
		         InputStream in = new BufferedInputStream (new FileInputStream(filePath));
		         props.load(in);
		         String value = props.getProperty (key);
		            System.out.println(key+value);
		            return value;
		        } catch (Exception e) {
		         e.printStackTrace();
		         return null;
		        }
		 }

}
