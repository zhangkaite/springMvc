package com.zkt.find.goods.dao.impl;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.zkt.find.common.util.Base64Util;
import com.zkt.find.common.util.ProtertiesUtil;
import com.zkt.find.goods.dao.IFindDao;
import com.zkt.find.goods.entity.AdminRecommend;
import com.zkt.find.goods.entity.FindCommentsEntity;
import com.zkt.find.goods.entity.FindEntity;
import com.zkt.find.goods.entity.FindPicEntity;
import com.zkt.find.goods.entity.FindUserCollection;
import com.zkt.find.goods.entity.FindWantUserEntity;
import com.zkt.find.goods.entity.UserAttentionEntiy;
import com.zkt.find.goods.entity.UserLableEntity;

@Repository
public class FindDaoImpl implements IFindDao {

	@Autowired
	@Qualifier("jdbcTemplate1")
	private JdbcTemplate jdbcWrite;

	@Autowired
	@Qualifier("jdbcTemplate2")
	private JdbcTemplate jdbcRead;

	private static Logger logger = LogManager.getLogger(FindDaoImpl.class);
	// 声明表名
	private final String find_table = "t_user_finds";
	private final String t_finds_pic = "t_finds_pic";
	private final String t_find_selectedlables = "t_find_selectedlables";
	private final String t_user = "t_user";
	private final String t_user_wants = "t_user_wants";
	private final String t_user_lable = "t_user_lable";
	private final String t_find_comment = "t_find_comment";
	private final String t_user_attention = "t_user_attention";
	private final String t_user_collection = "t_user_collection";

	private final String t_user_news = "t_user_news";

	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	SimpleDateFormat sdfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Override
	/***
	 * 存储图片
	 * 存储选择的达人标签
	 * 存储图片标签
	 */
	public boolean savePublish(String user_id, String find_desc, String find_attribute, String find_lables, String[] find_pics,
			String[] user_lables) {
		logger.info("===============================保存发现信息开始==================================");
		String uuid = UUID.randomUUID().toString();
		// 获取配置文件的路径
		String imagPath = ProtertiesUtil.getValue("context.properties", "imagePath");
		// 文件夹生成规则 /mnt/disk3_1/20150503/2015050314381212user_id1.jpg
		// 保存发现图片
		for (int i = 0; i < find_pics.length; i++) {
			String find_pic = find_pics[i];
			String filePath = imagPath + File.separator + sdf.format(new Date());
			String fileName = sdf.format(new Date())+ user_id + i + ".jpg";
			Base64Util.GenerateImage(find_pic, filePath, fileName);
			String sql = "insert into " + t_finds_pic + "(pic_path,submit_time,find_id) values(?,?,?)";
			Object[] params = new Object[] { filePath + File.separator + fileName, sdfs.format(new Date()), uuid };
			logger.info("发现保存图片数据sql：" + sql + "参数值:" + filePath + File.separator + fileName + "," + sdfs.format(new Date())
					+ "," + uuid);
			this.jdbcWrite.update(sql, params);
		}

		// 保存选择标签
		for (int i = 0; i < user_lables.length; i++) {
			String user_lable_id = user_lables[i];
			String sql = "insert into " + t_find_selectedlables + "(find_id,lable_id) values(?,?)";
			Object[] params = new Object[] { uuid, user_lable_id };
			logger.info("发现保存标签数据sql：" + sql + "参数值:" + uuid + "," + user_lable_id);
			this.jdbcWrite.update(sql, params);
		}

		// 保存发现数据
		String sql = "insert into " + find_table
				+ "(id,user_id,find_desc,find_attribute,find_lable,publish_time) values(?,?,?,?,?,?)";
		Object[] params = new Object[] { uuid, user_id, find_desc, find_attribute, find_lables, sdfs.format(new Date()) };
		logger.info("发现保存数据sql：" + sql + "参数值:" + uuid + "," + user_id + "," + find_desc + "," + find_attribute + ","
				+ find_lables + "," + sdfs.format(new Date()));
		int num = this.jdbcWrite.update(sql, params);
		if (num > 0) {
			return true;
		}
		logger.info("===============================保存发现信息结束==================================");
		return false;
	}

	@Override
	public List<FindEntity> getFindList(String page_no, String page_size) {
		String sql = "select t1.*,t2.nickname,t2.user_pic,t2.introduction from " + find_table + " t1 ," + t_user
				+ " t2 where t1.user_id=t2.user_id order by t1.publish_time desc limit " + (Integer.parseInt(page_no) - 1)
				* Integer.parseInt(page_size) + "," + (Integer.parseInt(page_no)) * Integer.parseInt(page_size);
		logger.info("查询发现信息分页sql：" + sql);
		return jdbcRead.query(sql, BeanPropertyRowMapper.newInstance(FindEntity.class));
	}

	@Override
	public List<FindWantUserEntity> getWantUserInfo(FindEntity findEntity) {
		String sql = "select t1.*,t2.nickname,t2.user_pic,t2.introduction from " + t_finds_pic + " t1," + t_user
				+ " t2 where t1.user_id=t2.user_id and t1.user_id='" + findEntity.getUser_id() + "'";
		// Object[] params = new Object[] { findEntity.getUser_id()};
		logger.info("发现想要用户数据sql：" + sql);
		return jdbcRead.query(sql, BeanPropertyRowMapper.newInstance(FindWantUserEntity.class));
	}

	@Override
	public List<FindPicEntity> getPicList(FindEntity findEntity) {
		String sql = "select * from " + t_finds_pic + " where find_id='" + findEntity.getId() + "'";
		// Object[] params = new Object[] { findEntity.getUser_id()};
		logger.info("发现图片数据sql：" + sql);
		return jdbcRead.query(sql, BeanPropertyRowMapper.newInstance(FindPicEntity.class));
	}

	@Override
	public List<UserLableEntity> getUserLables(String find_id) {
		String sql = "select t1.* from " + t_user_lable + " t1," + t_find_selectedlables
				+ " t2 where t1.id=t2.lable_id and t2.find_id='" + find_id + "'";
		logger.info("发现用户选择的标签数据sql：" + sql);
		return jdbcRead.query(sql, BeanPropertyRowMapper.newInstance(UserLableEntity.class));
	}

	@Override
	public List<FindCommentsEntity> getComments(String find_id) {
		String sql = "select t1.*,t2.nickname,t2.user_pic,t2.introduction from " + t_find_comment + " t1," + t_user
				+ " t2 where t1.comment_user_id=t2.user_id and t1.find_id='" + find_id + "'";
		logger.info("发现用户评论数据sql：" + sql);
		return jdbcRead.query(sql, BeanPropertyRowMapper.newInstance(FindCommentsEntity.class));
	}

	@Override
	public boolean insertAttentionData(String user_id, String attention_user_id) {
		/**
		 * 第一步：查询当前被关注的用户是否已关注自己
		 * */

		String flagSql = "select count(1) from " + t_user_attention + " where user_id=? and attention_user_id=?";
		Object[] param = new Object[] { attention_user_id, user_id };
		logger.info("发现保存标签数据sql：" + flagSql + "参数值:" + attention_user_id + "," + user_id);
		int data = this.jdbcRead.queryForInt(flagSql, param);
		String flag = "";
		if (data > 0) {
			flag = "1";
		} else {
			flag = "0";
		}
		String sql = "insert into " + t_user_attention
				+ " (user_id,attention_user_id,attention_time,attention_type) values(?,?,?,?)";
		Object[] params = new Object[] { user_id, attention_user_id, sdfs.format(new Date()), flag };
		logger.info("发现保存数据sql：" + sql + "参数值:" + user_id + "," + attention_user_id + "," + sdfs.format(new Date()) + ", 1");
		int num = this.jdbcWrite.update(sql, params);
		if (num > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean userWant(String user_id, String find_id) {
		String sql = "insert into " + t_user_wants + "(user_id,finds_id,want_time) values(?,?,?) ";
		Object[] params = new Object[] { user_id, find_id, sdfs.format(new Date()) };
		logger.info("用户想要插入数据sql：" + sql + "参数值:" + user_id + "," + find_id + "," + sdfs.format(new Date()));
		int num = this.jdbcWrite.update(sql, params);
		if (num > 0) {
			return true;
		}
		return false;
	}

	@Override
	public List<FindWantUserEntity> showUserWantList(String find_id, String page_no, String page_size) {
		String sql = "select t1.*,t2.nickname,t2.user_pic,t2.introduction from " + t_user_wants + " t1," + t_user
				+ " t2 where t1.user_id=t2.user_id and t1.finds_id='" + find_id + "' order by t1.want_time desc limit "
				+ (Integer.parseInt(page_no) - 1) * Integer.parseInt(page_size) + "," + Integer.parseInt(page_no)
				* Integer.parseInt(page_size);
		logger.info("用户想要查询数据sql：" + sql);
		return jdbcRead.query(sql, BeanPropertyRowMapper.newInstance(FindWantUserEntity.class));
	}

	@Override
	public boolean UserComment(String find_id, String comment_user_id, String comment_content, String user_id) {

		/** 将评论插入到通知表里面 */
		String sql_info = "insert into " + t_user_news + " (rec_user_id,send_user_id,type,status,tine) values(?,?,?,?)";
		Object[] param_info = new Object[] { user_id, comment_user_id, '2', '0', sdfs.format(new Date()) };
		this.jdbcWrite.update(sql_info, param_info);

		String sql = "insert into " + t_find_comment + "(find_id,comment_user_id,comment_content,comment_time) values(?,?,?,?) ";
		Object[] params = new Object[] { find_id, comment_user_id, comment_content, sdfs.format(new Date()) };
		logger.info("用户想要插入数据sql：" + sql + "参数值:" + find_id + "," + comment_user_id + "," + comment_content + ","
				+ sdfs.format(new Date()));
		int num = this.jdbcWrite.update(sql, params);
		if (num > 0) {
			return true;
		}
		return false;
	}

	@Override
	public List<FindCommentsEntity> ShowFindCommnets(String find_id, String page_no, String page_size) {
		String sql = "select t1.*,t2.nickname,t2.user_pic,t2.introduction from " + t_find_comment + " t1," + t_user
				+ " t2 where t1.comment_user_id=t2.user_id and t1.finds_id='" + find_id
				+ "' order by t1.comment_time desc limit " + (Integer.parseInt(page_no) - 1) * Integer.parseInt(page_size) + ","
				+ Integer.parseInt(page_no) * Integer.parseInt(page_size);
		logger.info("用户想要查询数据sql：" + sql);
		return jdbcRead.query(sql, BeanPropertyRowMapper.newInstance(FindCommentsEntity.class));
	}

	@Override
	public boolean UserCollection(String find_id, String user_id) {
		String sql = "insert into " + t_user_collection + "(find_id,user_id,collection_time) values(?,?,?)";
		Object[] params = new Object[] { find_id, user_id, sdfs.format(new Date()) };
		logger.info("用户收藏想要插入数据sql：" + sql + "参数值:" + find_id + "," + user_id + "," + sdfs.format(new Date()));
		int num = this.jdbcWrite.update(sql, params);
		if (num > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean delUserCollection(String find_id, String user_id) {
		String sql = "delete from " + t_user_collection + " where find_id=? and user_id=?";
		Object[] params = new Object[] { find_id, user_id };
		logger.info("用户收藏想要删除数据sql：" + sql + "参数值:" + find_id + "," + user_id);
		int num = this.jdbcWrite.update(sql, params);
		if (num > 0) {
			return true;
		}
		return false;
	}

	@Override
	public List<FindUserCollection> ShowUserCollection(String user_id, String page_no, String page_size) {
		String sql = "select * from " + t_user_collection + " where user_id='" + user_id + "' limit "
				+ (Integer.parseInt(page_no) - 1) * Integer.parseInt(page_size) + "," + Integer.parseInt(page_no)
				* Integer.parseInt(page_size);
		logger.info("用户收藏分页查询数据sql：" + sql);
		return jdbcRead.query(sql, BeanPropertyRowMapper.newInstance(FindUserCollection.class));
	}

	@Override
	public String getPicUrl(String find_id) {
		String sql = "select * from " + t_finds_pic + " where find_id='" + find_id + "'";
		// Object[] params = new Object[] { findEntity.getUser_id()};
		logger.info("发现图片数据sql：" + sql);
		List<FindPicEntity> ls = jdbcRead.query(sql, BeanPropertyRowMapper.newInstance(FindPicEntity.class));
		if (ls.size() > 0) {
			return ls.get(0).getPic_path();
		} else {
			return "";
		}
	}

	@Override
	public List<UserAttentionEntiy> showMyAttention(String user_id, String page_no, String page_size) {
		String sql = "select * from " + t_user_attention + " where attention_user_id='" + user_id + "' limit "
				+ (Integer.parseInt(page_no) - 1) * Integer.parseInt(page_size) + "," + Integer.parseInt(page_no)
				* Integer.parseInt(page_size);
		logger.info("用户粉丝分页查询数据sql：" + sql);
		return jdbcRead.query(sql, BeanPropertyRowMapper.newInstance(UserAttentionEntiy.class));
	}

	@Override
	public List<FindEntity> getUserFindList(String user_id, String lable_id, String page_no, String page_size) {
		String sql = "";
		if (null == lable_id) {
			sql = "select t1.*,t2.nickname,t2.user_pic,t2.introduction from " + find_table + " t1 ," + t_user
					+ " t2 where t1.user_id=t2.user_id and t1.user_id='" + user_id + "' order by t1.publish_time desc limit "
					+ (Integer.parseInt(page_no) - 1) * Integer.parseInt(page_size) + "," + (Integer.parseInt(page_no))
					* Integer.parseInt(page_size);
		} else {

			sql = "select t1.*,t2.nickname,t2.user_pic,t2.introduction from " + find_table + " t1 ," + t_user + " t2 ,"
					+ t_user_lable + " t3  where t1.user_id=t2.user_id and t3.user_id=t2.user_id and t1.user_id='" + user_id
					+ "'  and  t3.id='" + lable_id + "' order by t1.publish_time desc limit " + (Integer.parseInt(page_no) - 1)
					* Integer.parseInt(page_size) + "," + (Integer.parseInt(page_no)) * Integer.parseInt(page_size);
		}
		logger.info("查询用户发现信息分页sql：" + sql);
		return jdbcRead.query(sql, BeanPropertyRowMapper.newInstance(FindEntity.class));
	}

	private final String t_admin_recommend = "t_admin_recommend";

	@Override
	public List<AdminRecommend> getAdminRecommend() {
		String sql = "select * from " + t_admin_recommend;
		return jdbcRead.query(sql, BeanPropertyRowMapper.newInstance(AdminRecommend.class));
	}

	@Override
	public boolean isCollection(String find_id, String user_id) {
		String sql="select count(1) from "+t_user_collection+" where find_id=? and user_id=? ";
		Object[] params = new Object[] { find_id, user_id};
		logger.info("查看收藏信息是否存在sql：" + sql + "参数值:" + find_id + "," + user_id );
		int num = this.jdbcWrite.update(sql, params);
		if (num > 0) {
			return true;
		}
		return false;
	}

}
