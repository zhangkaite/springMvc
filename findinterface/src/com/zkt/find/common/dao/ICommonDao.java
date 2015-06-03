package com.zkt.find.common.dao;

import java.util.List;
import java.util.Map;

/**
 * <p> Title: [CBOX后台管理系统2.0]</p>
 * <p> Description: [公共资源]</p>
 * <p> Created on Mar 1, 2012</p>
 * <p> Copyright: Copyright (c) 2008</p>
 * <p> Company: 东软软件股份有限公司</p>
 * @author [齐星] - [qi_x@neusoft.com]
 * @version 1.0
 */

public interface ICommonDao {

	/**
	 * <p>Discription:[得到分页总数]</p>
	 * Created on Mar 3, 2012
	 * @author: [齐星] - [qi_x@neusoft.com] 
	 * @update: [日期YYYY-MM-DD] [更改人姓名]
	 */
	public List<String> getTotalPage(String everypage, String tablename, String condition);

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
	 * <p>Discription:[取得ids之外的排序]</p>
	 * Created on May 22, 2012
	 * @author: [齐星] - [qi_x@neusoft.com] 
	 * @update: [日期YYYY-MM-DD] [更改人姓名]
	 */
	public List<Map<String, Object>> getOrderList(String tablename, String ordercolumn, String ids, String idname,
			String[] collections);

	/**
	 * <p>Discription:[取得该表最大排序]</p>
	 * Created on May 22, 2012
	 * @author: [齐星] - [qi_x@neusoft.com] 
	 * @update: [日期YYYY-MM-DD] [更改人姓名]
	 */
	public String getOrderMax(String tablename, String ordercolumn, String[] collections);
	
	/**
	 * <p>Discription:[删除排序]</p>
	 * Created on May 22, 2012
	 * @author: [齐星] - [qi_x@neusoft.com] 
	 * @update: [日期YYYY-MM-DD] [更改人姓名]
	 */
	public void deleteOrder(String tablename, String ordercolumn, String thisOrder, String[] collections);

	
	
	
	public List<String> getTotalPages(String everypage, String sql);
}
