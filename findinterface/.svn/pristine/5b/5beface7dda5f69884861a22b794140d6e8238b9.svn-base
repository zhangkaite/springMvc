package com.zkt.find.common.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;

import com.zkt.find.common.dao.ICommonDao;
import com.zkt.find.common.service.ICommonService;

/**
 * <p> Title: [CBOX后台管理系统2.0]</p>
 * <p> Description: [公共资源]</p>
 * <p> Created on Mar 20, 2012</p>
 * <p> Copyright: Copyright (c) 2008</p>
 * <p> Company: 东软集团股份有限公司</p>
 * @author [齐星] - [qi_x@neusoft.com]
 * @version 1.0
 */
@Service
public class CommonServiceImpl implements ICommonService,ServletContextAware {

	@Autowired
	private ICommonDao dao;

	private ServletContext servletContext;

	public void setServletContext(ServletContext servletContext) { // 实现接口中的setServletContext方法
		this.servletContext = servletContext;
	}

	private static Map<String, String[]> originMap = new HashMap<String, String[]>();
	static {
		originMap.put("港台", new String[] { "台湾", "香港", "港台" });
		originMap.put("大陆", new String[] { "中国", "内地", "大陆" });
		originMap.put("美国", new String[] { "美国" });
		originMap.put("英国", new String[] { "英国" });
		originMap.put("韩国", new String[] { "韩国" });
		originMap.put("日本", new String[] { "日本" });
		originMap.put("法国", new String[] { "法国" });
		originMap.put("其他", new String[] { "其他" });
	}

	/**
	 * <p>Discription:[得到当前登录用户id]</p>
	 * Created on Mar 20, 2012
	 * @author:　[齐星] - [qi_x@neusoft.com]
	 * @update:[日期YYYY-MM-DD] [更改人姓名]
	 */
	@Override
	public String getCurrentUserId(HttpServletRequest request) {
		Object user_id = request.getSession().getAttribute("user_id");
		
		if (user_id != null) {
			return user_id.toString();
		}
		
		return "";
	}

	/**
	 * <p>Discription:[得到当前登录用户]</p>
	 * Created on Mar 20, 2012
	 * @author:　[齐星] - [qi_x@neusoft.com]
	 * @update:[日期YYYY-MM-DD] [更改人姓名]
	 */
	@Override
	public String getCurrentUser(HttpServletRequest request) {
		Object username = request.getSession().getAttribute("user_nickname");
		
		if (username != null) {
			return username.toString();
		}
		
		return "";
	}

	/**
	 * <p>Discription:[取得当前时间]</p>
	 * Created on May 6, 2012
	 * @author: [齐星] - [qi_x@neusoft.com] 
	 * @update: [日期YYYY-MM-DD] [更改人姓名]
	 */
	public Date getCurrentDate() {
		Date date = new Date(System.currentTimeMillis());
		return date;
	}

	/**
	 * <p>Discription:[获取basepath]</p>
	 * Created on May 9, 2012
	 * @author: [齐星] - [qi_x@neusoft.com] 
	 * @update: [日期YYYY-MM-DD] [更改人姓名]
	 */
	public String getBasePath(HttpServletRequest request) {
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
		return basePath;
	}

	/**
	 * <p>Discription:[取得分页总数]</p>
	 * Created on Mar 2, 2012
	 * @author:　[齐星] - [qi_x@neusoft.com]
	 * @update:[日期YYYY-MM-DD] [更改人姓名]
	 * 页面首次请求 
	 */
	@Override
	public List<String> getTotalPage(String everypage, String[] collections,String othercollections) {
		String condition ="";
		if (collections[0].contains("without")) {
			for (int i = 0; i < collections.length; i++) {
				condition+=collections[i]+",";
			}
			condition=condition.substring(0, condition.length()-1);
			condition=condition.replace("without", " ");
			
			return dao.getTotalPage(everypage, "", condition);
		}else {
			List<String> list = dao.getTableInfo(collections);
			String tablename = list.get(0);
			 condition = list.get(1);
			 if (null!=othercollections&&!"".equals(othercollections)&&!"null".equals(othercollections)&&othercollections.contains("without")) {
					condition+=othercollections.replace("without", "");
				}
			return dao.getTotalPage(everypage, tablename, condition);
		}
		
	}
	
	/**
	 * 重写分页
	 */
	@Override
	public List<String> getTotalPages(String everypage, String sql) {
		return dao.getTotalPages(everypage, sql);
	}
	
	/**
	 * <p>Discription:[取得table名称和查询条件]</p>
	 * Created on Apr 9, 2012
	 * @author: [齐星] - [qi_x@neusoft.com] 
	 * @update: [日期YYYY-MM-DD] [更改人姓名]
	 */
	public List<String> getTableInfo(String[] collections) {
		return dao.getTableInfo(collections);
	}

	/**
	 * <p>Discription:[保存blob]</p>
	 * Created on Apr 17, 2012
	 * @author: [齐星] - [qi_x@neusoft.com] 
	 * @update: [日期YYYY-MM-DD] [更改人姓名]
	 */
	@Override
	public void saveBlob(String tablename, String columnname, String id, String idvalue, String path) {
		dao.saveBlob(tablename, columnname, id, idvalue, path);
	}

	/**
	 * <p>Discription:[文件上传]</p>
	 * Created on Apr 17, 2012
	 * @author:　[齐星] - [qi_x@neusoft.com]
	 * @update:[日期YYYY-MM-DD] [更改人姓名]
	 */
	public synchronized String upload(String url, MultipartFile file) throws IOException {
		String rtuValue = "1";
		if (null != file && !file.isEmpty()) {
			String path = this.servletContext.getRealPath(url); // 上传路径
			File file1 = new File(path);
			if (!file1.exists() && !file1.isDirectory()) {
				file1.mkdir();
			}
			byte[] bytes = file.getBytes();
			String ename = getExtensionName(file.getOriginalFilename());
			String filename = String.valueOf(System.currentTimeMillis()) + getRandomNum(5);
			filename = filename + "." + ename;
			rtuValue = url + filename;
			FileOutputStream fos = new FileOutputStream(path + "/" + filename); // 上传到web服务器
			fos.write(bytes); // 写入文件
			fos.close();
		}
		return rtuValue;
	}

	/**
	 * <p>Discription:[删除文件]</p>
	 * Created on Apr 17, 2012
	 * @author:　[齐星] - [qi_x@neusoft.com]
	 * @update:[日期YYYY-MM-DD] [更改人姓名]
	 */
	public boolean deleteFile(String filename) throws Exception {
		String path = this.servletContext.getRealPath("/");
		File file = new File(path + filename);
		if (file.exists()) {
			file.delete();
		}
		return true;
	}

	/**
	 * <p>Discription:[获取文件扩展名]</p>
	 * Created on Apr 17, 2012
	 * @author: [齐星] - [qi_x@neusoft.com] 
	 * @update: [日期YYYY-MM-DD] [更改人姓名]
	 */
	public static String getExtensionName(String filename) {
		if ((filename != null) && (filename.length() > 0)) {
			int dot = filename.lastIndexOf('.');
			if ((dot > -1) && (dot < (filename.length() - 1))) {
				return filename.substring(dot + 1);
			}
		}
		return filename;
	}

	/**
	 * <p>Discription:[取得n位数字或字母]</p>
	 * Created on Apr 24, 2012
	 * @author: [齐星] - [qi_x@neusoft.com] 
	 * @update: [日期YYYY-MM-DD] [更改人姓名]
	 */
	public static String getRandomNum(int num) {
		Random rd = new Random(); // 创建随机对象
		String n = "";
		int rdGet; // 取得随机数
		do {
			rdGet = Math.abs(rd.nextInt()) % 10 + 48; // 产生48到57的随机数(0-9的键位值)
			// rdGet=Math.abs(rd.nextInt())%26+97; //产生97到122的随机数(a-z的键位值)
			char num1 = (char) rdGet;
			String dd = Character.toString(num1);
			n += dd;

		} while (n.length() < 4);// 假如长度小于4

		return n; // 获取4位字母的序列
	}

	/**
	 * <p>Discription:[list去重]</p>
	 * Created on Apr 12, 2012
	 * @author: [齐星] - [qi_x@neusoft.com] 
	 * @update: [日期YYYY-MM-DD] [更改人姓名]
	 */
	public List removeRepeatList(List list) {
		Set set = new HashSet();
		List newList = new ArrayList();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Object element = iter.next();
			if (set.add(element))
				newList.add(element);
		}
		return newList;
	}

	/**
	 * <p>Discription:[数组去重]</p>
	 * Created on 2012-10-25
	 * @author: [齐星] - [qi_x@neusoft.com] 
	 * @update: [日期YYYY-MM-DD] [更改人姓名]
	 */
	public String[] removeRepeatArray(String[] arr) {
		Set<String> set = new HashSet<String>();
		for (int i = 0; i < arr.length; i++) {
			set.add(arr[i]);
		}
		String[] newStr = set.toArray(new String[1]);
		return newStr;
	}

	/**
	 * <p>Discription:[校验排序是否正确]</p>
	 * Created on May 22, 2012
	 * @author: [齐星] - [qi_x@neusoft.com] 
	 * @update: [日期YYYY-MM-DD] [更改人姓名]
	 */
	public String checkOrder(String[] idstr, String[] orderstr, String tablename, String ordercolumn, String idname,
			String[] collections) {
		String ids = "";
		for (int i = 0; i < idstr.length; i++) {
			ids += "'" + idstr[i] + "',";
		}
		ids = ids.substring(0, ids.length() - 1);
		// 将排序放入List
		List<Integer> orderlist = new ArrayList<Integer>();
		for (int i = 0; i < orderstr.length; i++) {
			orderlist.add(Integer.valueOf(orderstr[i]));
		}
		// 取得该表其他排序
		List<Map<String, Object>> list = dao.getOrderList(tablename, ordercolumn, ids, idname, collections);
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				orderlist.add(Integer.valueOf((String) list.get(i).get(ordercolumn)));
			}
		}
		// 判断里面是否含有重复排序
		List<String> listr = removeRepeatList(orderlist);
		if (listr.size() != orderlist.size()) {
			return "2";
		}
		// 判断排序是否正确
		Collections.sort(orderlist);
		for (int i = 0; i < orderlist.size(); i++) {
			if (orderlist.get(i) != i + 1) {
				return "3";
			}
		}
		return "1";
	}
	
	/**
	 * <p>Discription:[取得该表最大排序]</p>
	 * Created on May 22, 2012
	 * @author: [齐星] - [qi_x@neusoft.com] 
	 * @update: [日期YYYY-MM-DD] [更改人姓名]
	 */
	public String getOrderMax(String tablename, String ordercolumn, String[] collections) {
		return dao.getOrderMax(tablename, ordercolumn, collections);
	}
	
	/**
	 * <p>Discription:[删除排序]</p>
	 * Created on May 22, 2012
	 * @author: [齐星] - [qi_x@neusoft.com] 
	 * @update: [日期YYYY-MM-DD] [更改人姓名]
	 */
	public void deleteOrder(String tablename, String ordercolumn, String thisOrder, String[] collections) {
		dao.deleteOrder(tablename, ordercolumn, thisOrder, collections);
	}
	
	/**
	 * <p>Discription:[替换html标签]</p>
	 * Created on 2012-6-5
	 * @author: [齐星] - [qi_x@neusoft.com] 
	 * @update: [日期YYYY-MM-DD] [更改人姓名]
	 */
	public String replaceTag(String input) {
		if (!hasSpecialChars(input)) {
			return input;
		}
		StringBuffer filtered = new StringBuffer(input.length());
		char c;
		for (int i = 0; i <= input.length() - 1; i++) {
			c = input.charAt(i);
			switch (c) {
				case '<':
					filtered.append("&lt;");
					break;
				case '>':
					filtered.append("&gt;");
					break;
				case '"':
					filtered.append("&quot;");
					break;
				case '&':
					filtered.append("&amp;");
					break;
				default:
					filtered.append(c);
			}

		}
		return (filtered.toString());
	}

	/**
	 * <p>Discription:[转回html标签]</p>
	 * Created on 2012-6-6
	 * @author: [齐星] - [qi_x@neusoft.com] 
	 * @update: [日期YYYY-MM-DD] [更改人姓名]
	 */
	public String replaceHtml(String str) {
		if (null != str && "".equals(str)) {
			str = str.replaceAll("&lt;", "<");
			str = str.replaceAll("&gt;", ">");
			str = str.replaceAll("&quot;", "\"");
			str = str.replaceAll("&amp;", "&");
		}
		return str;
	}

	/**
	 * <p>Discription:[判断标记是否存在]</p>
	 * Created on 2012-6-5
	 * @author: [齐星] - [qi_x@neusoft.com] 
	 * @update: [日期YYYY-MM-DD] [更改人姓名]
	 */
	public boolean hasSpecialChars(String input) {
		boolean flag = false;
		if ((input != null) && (input.length() > 0)) {
			char c;
			for (int i = 0; i <= input.length() - 1; i++) {
				c = input.charAt(i);
				switch (c) {
					case '>':
						flag = true;
						break;
					case '<':
						flag = true;
						break;
					case '"':
						flag = true;
						break;
					case '&':
						flag = true;
						break;
				}
			}
		}
		return flag;
	}

	/**
	 * <p>Discription:[对比两个list是否相等]</p>
	 * Created on 2012-10-31
	 * @author: [齐星] - [qi_x@neusoft.com] 
	 * @update: [日期YYYY-MM-DD] [更改人姓名]
	 */
	public boolean compareList(List<String> a, List<String> b) {

		if (a.size() != b.size())
			return false;
		if (a.size() == 0 && b.size() == 0)
			return true;
		Collections.sort(a);
		Collections.sort(b);
		for (int i = 0; i < a.size(); i++) {
			if (!a.get(i).equals(b.get(i)))
				return false;
		}
		return true;
	}
}
