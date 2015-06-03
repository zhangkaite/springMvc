package com.zkt.find.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p> Title: [CBOX后台管理系统1.0]</p>
 * <p> Description: [参数验证]</p>
 * <p> Created on 2014-8-6</p>
 * <p> Copyright: Copyright (c) 2008</p>
 * <p> Company: 东软集团股份有限公司</p>
 * @author [孙久增] - [sunjz@neusoft.com]
 * @version 1.0
 */
public class ParamsValid {
	
	private static Pattern pattern;
	
	private static Matcher matcher;
	
	/**
	 * <p>Discription:[验证日期格式是否正确]</p>
	 * Created on 2014-8-6
	 * @author: [孙久增] - [sunjz@neusoft.com] 
	 * @update: [日期YYYY-MM-DD] [更改人姓名]
	 */
	public static boolean validDate(String date){
		date = date == null ? "" : date;
		String reg = "[0-9]{4}-[0-9]{2}-[0-9]{2}";
		pattern = Pattern.compile(reg);
		matcher = pattern.matcher(date);
		boolean flag= matcher.matches();
		return flag;
	}
	
	/**
	 * <p>Discription:[验证版本号]</p>
	 * Created on 2014-8-6
	 * @author: [孙久增] - [sunjz@neusoft.com] 
	 * @update: [日期YYYY-MM-DD] [更改人姓名]
	 */
	public static boolean validVersion(String date){
		date = date == null ? "" : date;
		String reg = "^[0-9]+.[0-9]+.[0-9]+.[0-9]+$";
		pattern = Pattern.compile(reg);
		matcher = pattern.matcher(date);
		boolean flag= matcher.matches();
		return flag;
	}
	
	/**
	 * <p>Discription:[SQL注入验证]</p>
	 * Created on 2014-8-6
	 * @author: [孙久增] - [sunjz@neusoft.com] 
	 * @update: [日期YYYY-MM-DD] [更改人姓名]
	 */
	public static String replaceAll(String str){
		str = str == null ? "" : str;
		String reg = "[;=']";
		pattern = Pattern.compile(reg);
		matcher = pattern.matcher(str);
		return matcher.replaceAll("").trim();
	}
	
	/**
	 * <p>Discription:[链接注入]</p>
	 * Created on 2014-8-6
	 * @author: [孙久增] - [sunjz@neusoft.com] 
	 * @update: [日期YYYY-MM-DD] [更改人姓名]
	 */
	public static String replaceHtml(String str){
		str = str == null ? "" : str;
		String reg = "<.+?>";
		pattern = Pattern.compile(reg, Pattern.DOTALL);
		matcher = pattern.matcher(str);
		return matcher.replaceAll("").trim();
	}
	
	/**
	 * <p>Discription:[通过框架钓鱼注入]</p>
	 * Created on 2014-8-6
	 * @author: [孙久增] - [sunjz@neusoft.com] 
	 * @update: [日期YYYY-MM-DD] [更改人姓名]
	 */
	public static String replaceUrl(String str){
		str = str == null ? "" : str;
		String reg = "(http://|https://){1}[\\w\\.\\-/:]+";
		pattern = Pattern.compile(reg, Pattern.DOTALL);
		matcher = pattern.matcher(str);
		return matcher.replaceAll("").trim();
	}
	
	/**
	 * <p>Discription:[SQL注入验证]</p>
	 * Created on 2014-8-6
	 * @author: [孙久增] - [sunjz@neusoft.com] 
	 * @update: [日期YYYY-MM-DD] [更改人姓名]
	 */
	public static String replaceSpecChars(String str){
		str = str == null ? "" : str;
		String reg = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
		pattern = Pattern.compile(reg);
		matcher = pattern.matcher(str);
		return matcher.replaceAll("").trim();
	}
	
}
