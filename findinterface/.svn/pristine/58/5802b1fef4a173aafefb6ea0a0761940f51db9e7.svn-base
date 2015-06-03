package com.zkt.find.common.dao.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.AbstractLobCreatingPreparedStatementCallback;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.jdbc.support.lob.LobCreator;
import org.springframework.jdbc.support.lob.LobHandler;
import org.springframework.stereotype.Repository;

import com.zkt.find.common.dao.ICommonDao;

/**
 * <p> Title: [CBOX后台管理系统2.0]</p>
 * <p> Description: [公共资源]</p>
 * <p> Created on Mar 1, 2012</p>
 * <p> Copyright: Copyright (c) 2008</p>
 * <p> Company: 东软软件股份有限公司</p>
 * @author [齐星] - [qi_x@neusoft.com]
 * @version 1.0
 */
@Repository
public class CommonDaoImpl implements ICommonDao {

	@Autowired
	@Qualifier("jdbcTemplate1")
	private JdbcTemplate jdbcWrite;

	@Autowired
	@Qualifier("jdbcTemplate2")
	private JdbcTemplate jdbcRead;

	private LobHandler lobHandler = new DefaultLobHandler();

	/**
	 * <p>Discription:[得到分页总数]</p>
	 * Created on Mar 3, 2012
	 * @author:　[齐星] - [qi_x@neusoft.com]
	 * @update:[日期YYYY-MM-DD] [更改人姓名]
	 */
	@Override
	public List<String> getTotalPage(String everypage, String tablename, String condition) {
		List<String> list = new ArrayList<String>();
		String sql = "";
		String total = "";
		String totalpage = "";
		if (null == everypage || "".equals(everypage)) {
			sql = "select count(*) from " + tablename;
			
			int result = jdbcRead.queryForInt(sql);
			// int result = jdbc.query(sql, BeanPropertyRowMapper.newInstance(CBOX_USERINFO.class)).size();
			total = String.valueOf(result);
			totalpage = "1";
		} else {
			int pagecount = Integer.parseInt(everypage);
			if ("".equals(tablename)) {
				
				sql = "select count(*) from "  + condition;
			}else {
				sql = "select count(*) from " + tablename + condition;
			}
			
			int result = jdbcRead.queryForInt(sql);
			// int result = jdbc.query(sql, BeanPropertyRowMapper.newInstance(CBOX_USERINFO.class)).size();
			total = String.valueOf(result);
			if (result % pagecount == 0) {
				totalpage = String.valueOf(result / pagecount);
			} else {
				totalpage = String.valueOf(result / pagecount + 1);
			}
		}
		list.add(total);
		list.add(totalpage);
		return list;
	}
	
	
	/**
	 * 重写分页
	 */
	
	@Override
	public List<String> getTotalPages(String everypage, String sql) {
		List<String> list = new ArrayList<String>();
		
		String total = "";
		String totalpage = "";
		System.out.println("getTotalPages everypage:"+everypage);
		if (null == everypage || "".equals(everypage)) {
			System.out.println("getTotalPages sql:"+sql);
			int result = jdbcRead.queryForInt(sql);
			total = String.valueOf(result);
			totalpage = "1";
		} else {
			int pagecount = Integer.parseInt(everypage);
			String sqls="select count(*) from (";
			int result = jdbcRead.queryForInt(sqls+sql+")");
			// int result = jdbc.query(sql, BeanPropertyRowMapper.newInstance(CBOX_USERINFO.class)).size();
			total = String.valueOf(result);
			if (result % pagecount == 0) {
				totalpage = String.valueOf(result / pagecount);
			} else {
				totalpage = String.valueOf(result / pagecount + 1);
			}
		}
		list.add(total);
		list.add(totalpage);
		return list;
	}
	
	
	
	
	
	

	/**
	 * <p>Discription:[取得table名称和查询条件]</p>
	 * Created on Apr 9, 2012
	 * @author: [齐星] - [qi_x@neusoft.com]
	 * @update: [日期YYYY-MM-DD] [更改人姓名]
	 */
	public List<String> getTableInfo(String[] collections) {
		List<String> list = new ArrayList<String>();
		String tablename = collections[0] + " t ";
		String orderby_column = "";
		String orderby_type = "";
		String condition = " where 1=1 ";
		List<String> conditionTypeList = new ArrayList<String>();
		conditionTypeList.add("=");
		conditionTypeList.add(">=");
		conditionTypeList.add("<=");
		conditionTypeList.add(">");
		conditionTypeList.add("<");
		if (collections.length > 1) {
			int collectionslength = collections.length;
			// 判断是否存在排序字段，如果存在去的排序字段和排序类型
			if ("orderby_column".equals(collections[collectionslength - 3])) {
				orderby_column = collections[collectionslength - 1];
				orderby_type = collections[collectionslength - 2];
				// 去除排序字段
				collectionslength = collectionslength - 3;
			}
			// 循环条件
			for (int i = 1; i < collectionslength; i = i + 3) {
				// 如果条件字段部位空串，条件有效
				if (!"".equals(collections[i + 2])) {
					// 由于数据一部分来源于抓取，一部分来源于录入，录入部分将&,>,<,\替换成html编码，但抓取部分数据中的保持原样
					// 将条件中的转义字符替换valueNow为未转成html编码的格式
					String valueNow = collections[i + 2].replaceAll("の", "&");
					valueNow = valueNow.replaceAll("ぃ", "\"");
					valueNow = valueNow.replaceAll("△", "");
					valueNow = valueNow.replaceAll("☆", "/");
					// valueCheck为转成html编码的格式
					String valueCheck = collections[i + 2].replaceAll("の", "&");
					valueCheck = valueCheck.replaceAll("ぃ", "\"");
					valueCheck = valueCheck.replaceAll("☆", "/");
					// 如果条件的值中含有以下字符，转成html编码
					if (valueCheck.indexOf(">") != -1 || valueCheck.indexOf("<") != -1
							|| valueCheck.indexOf("\"") != -1 || valueCheck.indexOf("&") != -1) {
						valueCheck = valueCheck.replaceAll("&", "&amp;");
						valueCheck = valueCheck.replaceAll(">", "&gt;");
						valueCheck = valueCheck.replaceAll("<", "&lt;");
						valueCheck = valueCheck.replaceAll("\"", "&quot;");
						// 如果条件为like，
						if ("like".equals(collections[i + 1])) {
							// 首先匹配原数据，再匹配新数据
							condition = condition + " and (";
							condition = condition + collections[i] + " " + collections[i + 1] + " "
									+ this.getEscapeStr(valueNow);
							condition = condition + " or ";
							condition = condition + collections[i] + " " + collections[i + 1] + " "
									+ this.getEscapeStr(valueCheck) + ")";

							if (valueNow.indexOf("&") != -1) {
								// 如果条件中本身含有&，并含有<号，则不匹配转成的html代码
								if (valueNow.indexOf("<") == -1) {
									condition = condition + " and ";
									condition = condition + collections[i] + " not " + collections[i + 1] + "'%&lt;%'";
								}
								// 如果条件中本身含有&，并含有>号，则不匹配转成的html代码
								if (valueNow.indexOf(">") == -1) {
									condition = condition + " and ";
									condition = condition + collections[i] + " not " + collections[i + 1] + "'%&gt;%'";
								}
								// 如果条件中本身含有&，并含有\号，则不匹配转成的html代码
								if (valueNow.indexOf("\"") == -1) {
									condition = condition + " and ";
									condition = condition + collections[i] + " not " + collections[i + 1]
											+ "'%&quot;%'";
								}
							}
						}
						// 如果条件不是like，直接匹配库中值即可。
						if (conditionTypeList.contains(collections[i + 1])) {
							condition = condition + " and (";
							condition = condition + collections[i] + " " + collections[i + 1] + "'" + valueNow + "'";
							condition = condition + " or ";
							condition = condition + collections[i] + " " + collections[i + 1] + "'" + valueCheck + "')";
						}
					} else {
						// 如果不存在符号，直接匹配库中值即可
						if ("instr".equals(collections[i + 1])) {
							condition = condition + " and ";
							condition = condition + "instr(" + collections[i] + ",'" + valueNow + "')>0";
						}
						if ("like".equals(collections[i + 1])) {
							condition = condition + " and ";
							condition = condition + collections[i] + " " + collections[i + 1]
									+ this.getEscapeStr(valueNow);
						}
						if ("likes".equals(collections[i + 1])) {
							condition = condition + " or ";
							condition = condition + collections[i] + " " +" like "
									+ this.getEscapeStr(valueNow);
						}
						
						if (conditionTypeList.contains(collections[i + 1])) {
							condition = condition + " and ";
							if (">=".equals(collections[i + 1]) || "<=".equals(collections[i + 1])) {
								condition = condition + collections[i] + " " + collections[i + 1] + "to_date('"
										+ valueNow + "','yyyy-mm-dd') ";
							} else
								condition = condition + collections[i] + " " + collections[i + 1] + "'" + valueNow
										+ "'";
						}
					}
				}
			}
			if (!"".equals(orderby_column)) {
				condition = condition + " order by " + orderby_column + " " + orderby_type;
			}
		}
		list.add(tablename);
		list.add(condition);
		return list;
	}

	/**
	 * <p>Discription:[处理sql中的特殊字符]</p>
	 * Created on May 22, 2012
	 * @author: [齐星] - [qi_x@neusoft.com]
	 * @update: [日期YYYY-MM-DD] [更改人姓名]
	 */
	public String getEscapeStr(String value) {
		value = value.replaceAll("'", "''");
		value = value.replaceAll("あ", ",");
		if (value.indexOf("%") != -1 || value.indexOf("_") != -1) {
			value = value.replaceAll("%", "/%");
			value = value.replaceAll("_", "/_");
			return "'%" + value + "%' escape '/'";
		} else {
			return "'%" + value + "%'";
		}
	}

	/**
	 * <p>Discription:[保存blob]</p>
	 * Created on Apr 17, 2012
	 * @author: [齐星] - [qi_x@neusoft.com]
	 * @update: [日期YYYY-MM-DD] [更改人姓名]
	 */
	public void saveBlob(String tablename, String columnname, String id, String idvalue, String path) {
		String sql = "update " + tablename + " set " + columnname + " = ? where " + id + "= '" + idvalue + "'";
		try {
			final File blobIn = new File(path);
			final InputStream blobIs = new FileInputStream(blobIn);
			jdbcWrite.execute(sql, new AbstractLobCreatingPreparedStatementCallback(lobHandler) {

				protected void setValues(PreparedStatement ps, LobCreator lobCreator) throws SQLException {
					lobCreator.setBlobAsBinaryStream(ps, 1, blobIs, (int) blobIn.length());
				}
			});
			blobIs.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * <p>Discription:[取得ids之外的排序]</p>
	 * Created on May 22, 2012
	 * @author: [齐星] - [qi_x@neusoft.com]
	 * @update: [日期YYYY-MM-DD] [更改人姓名]
	 */
	public List<Map<String, Object>> getOrderList(String tablename, String ordercolumn, String ids, String idname,
			String[] collections) {
		String sql = "";
		if ("".equals(collections[0])) {
			sql = "select " + ordercolumn + " from " + tablename + " where " + idname + " not in (" + ids + ")";
		} else {
			String collection = "";
			for (int i = 0; i < collections.length; i = i + 2) {
				collection += " and " + collections[i] + "='" + collections[i + 1] + "'";
			}
			sql = "select " + ordercolumn + " from " + tablename + " where " + idname + " not in (" + ids + ") "
					+ collection;
		}

		List<Map<String, Object>> list = jdbcRead.queryForList(sql);
		return list;
	}

	/**
	 * <p>Discription:[取得该表最大排序]</p>
	 * Created on May 22, 2012
	 * @author: [齐星] - [qi_x@neusoft.com]
	 * @update: [日期YYYY-MM-DD] [更改人姓名]
	 */
	public String getOrderMax(String tablename, String ordercolumn, String[] collections) {
		String sql = "";
		if ("".equals(collections[0])) {
			sql = "select Max(to_number(" + ordercolumn + ")) from " + tablename;
		} else {
			String collection = "";
			for (int i = 0; i < collections.length; i = i + 2) {
				collection += " and " + collections[i] + "='" + collections[i + 1] + "'";
			}
			sql = "select Max(to_number(" + ordercolumn + ")) from " + tablename + " where 1=1 "
					+ collection;
		}
		int max = jdbcRead.queryForInt(sql);
		return String.valueOf(max + 1);
	}

	/**
	 * <p>Discription:[删除排序]</p>
	 * Created on May 22, 2012
	 * @author: [齐星] - [qi_x@neusoft.com]
	 * @update: [日期YYYY-MM-DD] [更改人姓名]
	 */
	public void deleteOrder(String tablename, String ordercolumn, String thisOrder, String[] collections) {
		String sql = "";
		if ("".equals(collections[0])) {
			sql = "update " + tablename + " set " + ordercolumn + "=" + ordercolumn + "-1 where " + ordercolumn + ">"
					+ thisOrder;
		} else {
			String collection = "";
			for (int i = 0; i < collections.length; i = i + 2) {
				collection += " and " + collections[i] + "='" + collections[i + 1] + "'";
			}
			sql = "update " + tablename + " set " + ordercolumn + "=" + ordercolumn + "-1 where " + ordercolumn + ">"
					+ thisOrder + collection;
		}
		jdbcWrite.update(sql);
	}


}
