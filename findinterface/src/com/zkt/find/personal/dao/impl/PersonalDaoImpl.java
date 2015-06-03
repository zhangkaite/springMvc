package com.zkt.find.personal.dao.impl;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.zkt.find.personal.dao.IPersonalDao;
import com.zkt.find.personal.entity.FanseEntity;
import com.zkt.find.personal.entity.FashionPerEntity;
import com.zkt.find.personal.entity.LableUserEntity;
import com.zkt.find.personal.entity.NoticeNewsEntity;
import com.zkt.find.personal.entity.PersonalEntity;
import com.zkt.find.personal.entity.PersonalGoodsEntity;
import com.zkt.find.personal.entity.PrivateChatEntity;
import com.zkt.find.personal.entity.PrivateChatUserEntity;
@Repository
public class PersonalDaoImpl implements IPersonalDao{
	 private static Logger logger = LogManager.getLogger(PersonalDaoImpl.class);
	 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	 @Autowired
	    @Qualifier("jdbcTemplate1")
	    private JdbcTemplate jdbcWrite;

	    @Autowired
	    @Qualifier("jdbcTemplate2")
	    private JdbcTemplate jdbcRead;
	    
	@Override
	public PersonalEntity personalinfo(String user_id) {
		// TODO Auto-generated method stub
		String sql="select user_id,user_name,user_sex,nickname,introduction,user_pic,user_address,user_lableList,(select count(1) from t_user_finds  tf where tf.user_id='"+user_id+"') user_find_count,"
				+ "(select count(1) from t_user_attention  ta where ta.user_id='"+user_id+"') user_attention_count,"
				+ "(select count(1) from t_user_attention  ta where ta.attention_user_id='"+user_id+"') user_beattention_count"
				+ "from t_user where user_id='"+user_id+"'";
		 logger.info("执行sql"+ sql);
		 return (PersonalEntity) jdbcRead.query(sql,BeanPropertyRowMapper.newInstance(PersonalEntity.class));
	}

	@Override
	public List<PersonalGoodsEntity> persongoodsinfo(String user_id) {
		// TODO Auto-generated method stub
		String sql="select  tfc.pic_path as good_pic_url,tfc.id as good_id from t_user_finds tf ,t_finds_pic tfc where tf.id=tfc.find_id and tf.user_id='"+user_id+"'";
		logger.info("执行sql"+ sql);
		return  jdbcRead.query(sql, BeanPropertyRowMapper.newInstance(PersonalGoodsEntity.class));
	}

	@Override
	public Boolean saveFeedInfo(String feed_info, String phone_number,
			String user_id, String feed_time) {
		// TODO Auto-generated method stub
		Boolean flag=false;
		String sql="insert into t_user_feedback (user_id,feed_time,phone_number,feed_info) values (?,?,?,?)";
		Object[] params=new Object[]{user_id,sdf.format(feed_time),phone_number,feed_info};
		logger.info("sql:"  +sql+"参数"+ user_id+","+sdf.format(feed_time)+","+phone_number+","+feed_info);
		int num=jdbcWrite.update(sql,params);
		if(num>0)
		{
			flag=true;
		}
		else
		{
			flag=false;
		}
		return flag;
	}

	@Override
	public List<FanseEntity> fanselist(String user_id) {
		// TODO Auto-generated method stub
		String sql="select t.user_id,t.nickname,t.user_pic as user_pictrue, t.introduction,tu.attention_type as attention_type from t_user t,t_user_attention tu where  t.user_id=tu.user_id and tu.attention_user_id='"+user_id+"'";
		logger.info("fansesql"+sql);
		return jdbcRead.query(sql, BeanPropertyRowMapper.newInstance(FanseEntity.class));
	}

	@Override
	public Boolean savePrivateChat(String user_id, String other_user_id,
			String content, String content_time, String operation_type) {
		// TODO Auto-generated method stub
		String sql="insert into t_user_privatechat (user_id,other_user_id,content,content_time,operation_type) values (?,?,?,?,?)";
		logger.info("savePrivatechat "+sql);
		Object[] params=new Object[]{user_id,other_user_id,content,sdf.format(content_time),operation_type};
		int num=this.jdbcWrite.update(sql,params);
		if(num>0)
		{
			return true;
		}
		return false;
	}
	@Override
	public List<PrivateChatEntity> showPrivateChat(String user_id) {
		// TODO Auto-generated method stub
		String sql="select DISTINCT oth.user_id,oth.nickname,oth.user_pic,tpc.content_time,tpc.content,"
				+ "tpc.operation_type from (select t.nickname,t.user_pic,t.user_id from t_user t "
				+ "where t.user_id in (select DISTINCT tp.other_user_id from t_user_privatechat tp "
				+ "where tp.user_id='"+user_id+"')) oth ,t_user_privatechat tpcwhere oth.user_id=tpc.user_id"
				+ " order by tpc.content_time";
		logger.info("showpervatechat"+sql);
		return this.jdbcRead.query(sql, BeanPropertyRowMapper.newInstance(PrivateChatEntity.class));
	}

	@Override
	public List<PrivateChatUserEntity> showPrivateChatDetail(String user_id,
			String other_user_id) {
		// TODO Auto-generated method stub
		String sql="select tall.* from (select t.content,t.content_time,t.operation_type,tu.nickname,tu.user_id,tu.user_pic from "
				+ "(select * from t_user_privatechat where user_id='"+other_user_id+"' and other_user_id='"+user_id+"') t,"
				+ "(select c.user_id,c.nickname,c.user_pic from t_user c where c.user_id='"+other_user_id+"') "
				+ "tu where t.user_id=tu.user_id UNION ALL select tp.content,tp.content_time,tp.operation_type,"
				+ "tc.nickname,tc.user_id,tc.user_pic from "
				+ "(select * from t_user_privatechat where user_id='"+user_id+"' and other_user_id='"+other_user_id+"') tp,"
				+ "(select m.user_id,m.nickname,m.user_pic from t_user m where m.user_id='"+user_id+"') "
				+ "tc where tp.user_id=tc.user_id) tall ORDER BY tall.content_time";
		logger.info("showPrivateChatDetail:"+sql);
		return this.jdbcRead.query(sql, BeanPropertyRowMapper.newInstance(PrivateChatUserEntity.class));
	}

	@Override
	public Boolean saveAddScore(String user_id, String lable_id,String find_id,String addscore_id) {
		// TODO Auto-generated method stub
		Boolean saveflag=false;
		List<Object[]> objects=new ArrayList<Object[]>();
		List<Object[]> insertobjects=new ArrayList<Object[]>();
		List<Object[]> insershowobjects=new ArrayList<Object[]>();
		String[] lable_idstr=lable_id.split(";");
		for(int i=0;i<lable_idstr.length;i++)
		{
			Object[] obj=new Object[]{user_id,lable_idstr[i],user_id,lable_idstr[i]};
			Object[] insertobj=new Object[]{user_id,lable_idstr[i],find_id,addscore_id};
			Object[] insershowobject=new Object[]{user_id,addscore_id,'2','0',sdf.format(new Date())};
			objects.add(obj);
			insertobjects.add(insertobj);
			insershowobjects.add(insershowobject);
		}
		String insertsql="insert into t_user_lable_addscore (lable_id,find_id,addscore_id,user_id) values (?,?,?,?)";
		String insertshowsql="insert into t_user_news (rec_user_id,send_user_id,type,status,tine) values (?,?,?,?,?)";
		String sql="UPDATE t_user_lable t set t.lable_count=(select a.lable_count from"
				+ " (select tl.lable_count FROM t_user_lable tl where tl.user_id=? and tl.lable_name=?) a )+1"
				+ " where t.user_id=? and t.lable_name=?";
		logger.info("加分sql"+sql);
		int[] num=this.jdbcWrite.batchUpdate(sql, objects);
		if(num.length>0)
		{
			this.jdbcRead.batchUpdate(insertsql, insertobjects);
			this.jdbcRead.batchUpdate(sql,insershowobjects);
			saveflag=true;
		}
		return saveflag;
	}

	@Override
	public List<NoticeNewsEntity> getNoticeNews(String rec_user_id,
			String send_user_id, String type) {
		// TODO Auto-generated method stub
		String sql="select  t.user_id,t.nickname,t.user_pic,tn.type,tn.tine,tn.'status' "
				+ "from t_user t,t_user_news tn where  tn.send_user_id='"+send_user_id+"' "
				+ "and tn.type='2' and tn.send_user_id=t.user_id and tn.'status'='0'";
		return  this.jdbcRead.query(sql, BeanPropertyRowMapper.newInstance(NoticeNewsEntity.class)); 
	}

	@Override
	public List<LableUserEntity> getUserByLable(String lable_name) {
		// TODO Auto-generated method stub
		String sql="select t.user_id,t.user_name,t.nickname,t.user_pic,t.user_sex,t.introduction from "
				+ "t_user t,t_user_lable tl where t.user_id=tl.user_id and tl.lable_name='"+lable_name+"'";
		return this.jdbcRead.query(sql, BeanPropertyRowMapper.newInstance(LableUserEntity.class));
	}

	@Override
	public List<FashionPerEntity> showFashionPer(String user_id) {
		// TODO Auto-generated method stub
		String sql="select tt.*,ta.attention_type from  (select tl.lable_count,t.user_id,t.nickname,"
				+ "t.introduction,t.user_pic from t_user_lable tl,t_user t where"
				+ " tl.user_id=t.user_id and  tl.lable_name='时尚潮人' ORDER BY tl.lable_count desc) tt"
				+ " LEFT JOIN t_user_attention ta on tt.user_id='"+user_id+"'";
		return this.jdbcRead.query(sql, BeanPropertyRowMapper.newInstance(FashionPerEntity.class));
	}
	
}
