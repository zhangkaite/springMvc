package com.zkt.find.common.service;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

/**
 * <p> Title: [CBOX后台管理系统2.0]</p>
 * <p> Description: [公共资源]</p>
 * <p> Created on Mar 20, 2012</p>
 * <p> Copyright: Copyright (c) 2008</p>
 * <p> Company: 东软集团股份有限公司</p>
 * @author [齐星] - [qi_x@neusoft.com]
 * @version 1.0
 */

public interface ICommonService {

	/**
	 * <p>Discription:[得到当前登录用户id]</p>
	 * Created on Mar 20, 2012
	 * @author: [齐星] - [qi_x@neusoft.com] 
	 * @update: [日期YYYY-MM-DD] [更改人姓名]
	 */
	public String getCurrentUserId(HttpServletRequest request);

	/**
	 * <p>Discription:[得到当前登录用户]</p>
	 * Created on Mar 20, 2012
	 * @author: [齐星] - [qi_x@neusoft.com] 
	 * @update: [日期YYYY-MM-DD] [更改人姓名]
	 */
	public String getCurrentUser(HttpServletRequest request);

	/**
	 * <p>Discription:[取得当前时间]</p>
	 * Created on May 6, 2012
	 * @author: [齐星] - [qi_x@neusoft.com] 
	 * @update: [日期YYYY-MM-DD] [更改人姓名]
	 */
	public Date getCurrentDate();

	/**
	 * <p>Discription:[获取basepath]</p>
	 * Created on May 9, 2012
	 * @author: [齐星] - [qi_x@neusoft.com] 
	 * @update: [日期YYYY-MM-DD] [更改人姓名]
	 */
	public String getBasePath(HttpServletRequest request);

	/**
	 * <p>Discription:[取得分页总数]</p>
	 * Created on Mar 3, 2012
	 * @author: [齐星] - [qi_x@neusoft.com] 
	 * @update: [日期YYYY-MM-DD] [更改人姓名]
	 */
	public List<String> getTotalPage(String everypage, String[] collections,String othercollections);

	/**
	 * <p>Discription:[取得table名称和查询条件]</p>
	 * Created on Apr 9, 2012
	 * @author: [齐星] - [qi_x@neusoft.com] 
	 * @update: [日期YYYY-MM-DD] [更改人姓名]
	 */
	public List<String> getTableInfo(String[] collections);

	/**
	 * <p>Discription:[保存blob]</p>
	 * Created on Apr 17, 2012
	 * @author: [齐星] - [qi_x@neusoft.com] 
	 * @update: [日期YYYY-MM-DD] [更改人姓名]
	 */
	public void saveBlob(String tablename, String columnname, String id, String idvalue, String path);

	/**
	 * <p>Discription:[文件上传]</p>
	 * Created on Apr 17, 2012
	 * @author: [齐星] - [qi_x@neusoft.com] 
	 * @update: [日期YYYY-MM-DD] [更改人姓名]
	 */
	public String upload(String url, MultipartFile file) throws IOException;

	/**
	 * <p>Discription:[文件删除]</p>
	 * Created on Apr 17, 2012
	 * @author: [齐星] - [qi_x@neusoft.com] 
	 * @update: [日期YYYY-MM-DD] [更改人姓名]
	 */
	public boolean deleteFile(String filename) throws Exception;

	/**
	 * <p>Discription:[list去重]</p>
	 * Created on Apr 12, 2012
	 * @author: [齐星] - [qi_x@neusoft.com] 
	 * @update: [日期YYYY-MM-DD] [更改人姓名]
	 */
	public List removeRepeatList(List list);
	
	/**
	 * <p>Discription:[数组去重]</p>
	 * Created on 2012-10-25
	 * @author: [齐星] - [qi_x@neusoft.com] 
	 * @update: [日期YYYY-MM-DD] [更改人姓名]
	 */
	public String[] removeRepeatArray(String[] arr);

	/**
	 * <p>Discription:[校验排序是否正确]</p>
	 * Created on May 22, 2012
	 * @author: [齐星] - [qi_x@neusoft.com] 
	 * @update: [日期YYYY-MM-DD] [更改人姓名]
	 */
	public String checkOrder(String[] idstr, String[] orderstr, String tablename, String ordercolumn, String idname,
			String[] collections);

	/**
	 * <p>Discription:[取得该表最大排序]</p>
	 * Created on May 22, 2012
	 * @author: [齐星] - [qi_x@neusoft.com] 
	 * @update: [日期YYYY-MM-DD] [更改人姓名]
	 */
	public String getOrderMax(String tablename, String ordercolumn, String[] collections);
	
	/**
	 * <p>Discription:[删]</p>
	 * Created on May 22, 2012
	 * @author: [齐星] - [qi_x@neusoft.com] 
	 * @update: [日期YYYY-MM-DD] [更改人姓名]
	 */
	public void deleteOrder(String tablename, String ordercolumn, String thisOrder, String[] collections);
	
	/**
	 * <p>Discription:[替换html标签]</p>
	 * Created on 2012-6-5
	 * @author: [齐星] - [qi_x@neusoft.com] 
	 * @update: [日期YYYY-MM-DD] [更改人姓名]
	 */
	public String replaceTag(String str);
	
	/**
	 * <p>Discription:[转回html标签]</p>
	 * Created on 2012-6-6
	 * @author: [齐星] - [qi_x@neusoft.com] 
	 * @update: [日期YYYY-MM-DD] [更改人姓名]
	 */
	public String replaceHtml(String str);

	/**
	 * <p>Discription:[对比两个list是否相等]</p>
	 * Created on 2012-10-31
	 * @author: [齐星] - [qi_x@neusoft.com] 
	 * @update: [日期YYYY-MM-DD] [更改人姓名]
	 */
	public boolean compareList(List<String> a, List<String> b);
	
	
	
	/**
	 * 重写分页
	 */
	public List<String> getTotalPages(String everypage, String sql);

}
