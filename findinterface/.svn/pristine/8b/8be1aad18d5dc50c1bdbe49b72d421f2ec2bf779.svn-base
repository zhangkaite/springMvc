package com.zkt.find.personal.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zkt.find.common.util.JsonUtil;
import com.zkt.find.personal.entity.FanseEntity;
import com.zkt.find.personal.entity.FashionPerEntity;
import com.zkt.find.personal.entity.LableUserEntity;
import com.zkt.find.personal.entity.NoticeNewsEntity;
import com.zkt.find.personal.entity.PersonalEntity;
import com.zkt.find.personal.entity.PersonalGoodsEntity;
import com.zkt.find.personal.entity.PrivateChatEntity;
import com.zkt.find.personal.entity.PrivateChatUserEntity;
import com.zkt.find.personal.service.IPersonalService;

@Controller
@RequestMapping("/person")
@SuppressWarnings({"rawtypes","unchecked"})
public class PersonalController {

	private static Logger logger=LogManager.getLogger(PersonalController.class);
	
	private IPersonalService service;
	/*
	 * 个人档案信息
	 * */
	//@RequestMapping("/personal")
	
	@RequestMapping(value ="/personal",method ={RequestMethod.GET, RequestMethod.POST})
	public void personalInfo(String data,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		String user_id=request.getParameter("user_id");
		Map resultmap=new HashMap();
		if((user_id ==null || user_id ==""))
		{
			resultmap.put("result_code", "9");
			resultmap.put("result_desc", "参数不能为空");
			JsonUtil.WriteJson("personal", resultmap, request, response);
		}
		else
		{
			PersonalEntity personentity=service.personalinfo(user_id);
			List<PersonalGoodsEntity> personalgoods=service.personalGoodsInfo(user_id);
			resultmap.put("result_code", "0");
			resultmap.put("result_desc", "查询成功");
			resultmap.put("user_name", personentity.getUser_name());
			resultmap.put("user_picture",personentity.getUser_pic());
			resultmap.put("nickname", personentity.getNickname());
			resultmap.put("user_sex", personentity.getUser_pic());
			resultmap.put("address", personentity.getUser_address());
			resultmap.put("Stringroduction", personentity.getIntroduction());
			resultmap.put("user_find_count", personentity.getUser_find_count());
			resultmap.put("user_attention_count", personentity.getUser_attention_count());
			resultmap.put("user_beattention_count", personentity.getUser_beattention_count());
			resultmap.put("user_lables", personentity.getUser_lableList());
			resultmap.put("goods", personalgoods);
			JsonUtil.WriteJson("personal", resultmap, request, response);
		}
	}
	//@RequestMapping("/feedback")
	@RequestMapping(value ="/feedback",method ={RequestMethod.GET, RequestMethod.POST})
	public void  feedback(String data ,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		String feed_info=request.getParameter("feed_info");
		String phone_number=request.getParameter("phone_number");
		String user_id=request.getParameter("user_id");
		String feed_time=request.getParameter("feed_time");
		Map resultmap=new HashMap();
		if((feed_info==null|| feed_info=="")||(phone_number==null|| phone_number=="")
				||(user_id==null|| user_id=="")||(feed_time==null|| feed_time==""))
		{
			resultmap.put("result_code", "9");
			resultmap.put("result_desc", "参数不能为空");
			JsonUtil.WriteJson("feedback", resultmap, request, response);
		}
		else
		{
			Boolean saveflag=service.saveFeedInfo(feed_info, phone_number, user_id, feed_time);
			if(saveflag)
			{
				resultmap.put("result_code", "1");
				resultmap.put("result_desc", "反馈成功");
				JsonUtil.WriteJson("feedback", resultmap, request, response);
			}
			else
			{
				resultmap.put("result_code", "2");
				resultmap.put("result_desc", "反馈失败");
				JsonUtil.WriteJson("feedback", resultmap, request, response);
			}
		}
	}
	//@RequestMapping("/fase")
	@RequestMapping(value ="/fase",method ={RequestMethod.GET, RequestMethod.POST})
	public void faselist(String data,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		String user_id=request.getParameter("user_id");
		Map resultmap=new HashMap();
		if(user_id==null || user_id=="")
		{
			resultmap.put("result_code", "9") ;
			resultmap.put("result_desc", "参数为空");
			JsonUtil.WriteJson("fase", resultmap, request, response);
		}
		else
		{
			List<FanseEntity> faseentitylist=service.fanselist(user_id);
			resultmap.put("result_code", "1");
			resultmap.put("result_desc", "查询成功");
			resultmap.put("faseList", faseentitylist);
			JsonUtil.WriteJson("fase", resultmap, request, response);
		}
	}
	//@RequestMapping("/privateChat")
	@RequestMapping(value ="/privateChat",method ={RequestMethod.GET, RequestMethod.POST})
	public void  privateChat(String data,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		String user_id=request.getParameter("user_id");
		String other_user_id=request.getParameter("other_user_id");
		String content=request.getParameter("content");
		String content_time=request.getParameter("content_time");
		String operation_type=request.getParameter("operation_type");
		Map resultmap=new HashMap();
		if((user_id ==null || user_id=="")||(other_user_id ==null || other_user_id=="")||
				(content ==null || content=="")||(content_time ==null || content_time=="")
				||(operation_type ==null || operation_type==""))
		{
			resultmap.put("result_code", "9");
			resultmap.put("result_desc", "参数为空");
			JsonUtil.WriteJson("privateChat", resultmap, request, response);
		}
		else
		{
			Boolean savepflag=service.savePrivateChat( user_id, other_user_id, content, content_time, operation_type);
			if(savepflag)
			{
				resultmap.put("result_code", "1");
				resultmap.put("result_desc", "添加成功");
				JsonUtil.WriteJson("privateChat", resultmap, request, response);
			}
			else
			{
				resultmap.put("result_code", "2");
				resultmap.put("result_desc", "添加失败");
				JsonUtil.WriteJson("privateChat", resultmap, request, response);
			}
		}
	}
	
	//@RequestMapping("/showPrivateChatList")
	@RequestMapping(value ="/showPrivateChatList",method ={RequestMethod.GET, RequestMethod.POST})
	public void showPrivateChatList(String data, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		String user_id=request.getParameter("user_id");
		Map resultmap=new HashMap();
		if(user_id==null|| user_id=="")
		{
			resultmap.put("result_code", "9");
			resultmap.put("result_desc", "参数为空");
			JsonUtil.WriteJson("showPrivateChatList", resultmap, request, response);
		}
		else
		{
			List<PrivateChatEntity> privatechatusers=service.showPrivateChat(user_id);
			resultmap.put("result_code", "1");
			resultmap.put("result_desc", "查询成功");
			resultmap.put("userList", privatechatusers);
			JsonUtil.WriteJson("showPrivateChatList", resultmap, request, response);
		}
	}
	
	//@RequestMapping("/showPrivateChatDetail")
	@RequestMapping(value ="/showPrivateChatDetail",method ={RequestMethod.GET, RequestMethod.POST})
	public void showPrivateChatDetail(String data,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		String user_id=request.getParameter("user_id");
		String other_user_id=request.getParameter("other_user_id");
		Map resultmap=new HashMap();
		if((user_id ==null ||user_id =="")||(other_user_id==null || other_user_id==""))
		{
			resultmap.put("result_code", "9");
			resultmap.put("result_desc", "参数为空");
			JsonUtil.WriteJson("showPrivateChatDetail", resultmap, request, response);
		}
		else
		{
			List<PrivateChatUserEntity> privatechatuserentitys=service.showPrivateChatDetail(user_id,other_user_id);
			resultmap.put("result_code", "1");
			resultmap.put("result_desc", "查询成功");
			resultmap.put("userList", privatechatuserentitys);
			JsonUtil.WriteJson("showPrivateChatDetail", resultmap, request, response);
		}
	}
	//@RequestMapping("/addScore")
	@RequestMapping(value ="/addScore",method ={RequestMethod.GET, RequestMethod.POST})
	public void addScore(String data,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		 String user_id=request.getParameter("user_id");
		 String lable_id=request.getParameter("lable_id");
		 String find_id=request.getParameter("find_id");
		 String addscore_id=request.getParameter("addscore_id");
		 Map resultmap=new HashMap();
		 if((user_id==null||user_id =="")||(lable_id==null||lable_id=="")||(find_id==null||find_id=="")||(addscore_id==null||addscore_id==""))
		 {
			 resultmap.put("result_code", "9");
			 resultmap.put("result_desc", "参数为空");
			 JsonUtil.WriteJson("addScore", resultmap, request, response);
		 }
		 else
		 {
			 Boolean flag=service.saveAddScore(user_id, lable_id,find_id,addscore_id);
			 if(flag)
			 {
				 resultmap.put("result_code", "1");
				 resultmap.put("result_desc", "操作成功");
				 JsonUtil.WriteJson("addScore", resultmap, request, response);
			 }
			 else
			 {
				 resultmap.put("result_code", "2");
				 resultmap.put("result_desc", "操作失败");
				 JsonUtil.WriteJson("addScore", resultmap, request, response);
			 }
		 }
	}
	
	//@RequestMapping("/showNews")
	@RequestMapping(value ="/showNews",method ={RequestMethod.GET, RequestMethod.POST})
	public void showNews(String data,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
	 
	  String rec_user_id=request.getParameter("rec_user_id");
	  String send_user_id=request.getParameter("send_user_id");
	  String type=request.getParameter("type");
	  Map resultMap=new HashMap();
	  if((rec_user_id==null|rec_user_id=="")||(send_user_id==null||send_user_id=="")||(type==null||type==""))
	  {
		  resultMap.put("result_code", "9");
		  resultMap.put("result_desc", "参数为空");
		  JsonUtil.WriteJson("showNews", resultMap, request, response);
	  }
	  else
	  {
		  List<NoticeNewsEntity> noticelist=service.getNoticeNews( rec_user_id, send_user_id, type);
		  resultMap.put("result_code", "1");
		  resultMap.put("result_desc", "查询成功");
		  resultMap.put("result_list", noticelist);
		  JsonUtil.WriteJson("showNews", resultMap,	 request, response);
		  
	  }
	}
	//@RequestMapping("/lableUser")
	@RequestMapping(value ="/lableUser",method ={RequestMethod.GET, RequestMethod.POST})
	public void getUserByLable(String data ,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		
		String lable_name=request.getParameter("lable_name");
		Map resultMap=new HashMap();
		if(lable_name==null || lable_name== "")
		{
			resultMap.put("result_code", "9");
			resultMap.put("result_desc", "参数为空");
			JsonUtil.WriteJson("lableUser", resultMap, request, response);
		}
		else
		{
			List<LableUserEntity> lableuserlist=service.getUserByLable(lable_name);
			resultMap.put("result_code", "1");
			resultMap.put("result_desc", "查询成功");
			resultMap.put("user_list", lableuserlist);
			JsonUtil.WriteJson("lableUser", resultMap, request, response);
		}
	}
	//@RequestMapping("/showFashionPer")
	@RequestMapping(value ="/showFashionPer",method ={RequestMethod.GET, RequestMethod.POST})
	public void showFashionPer(String data,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		String user_id=request.getParameter("user_id");
		Map  resultMap=new HashMap();
		if(user_id==null||user_id=="")
		{
			resultMap.put("result_code", "9");
			resultMap.put("result_desc", "参数为空");
			JsonUtil.WriteJson("showFashionPer", resultMap, request, response);
		}
		else
		{
		List<FashionPerEntity> showFashionlist=service.showFashionPer(user_id);
		resultMap.put("result_code", "1");
		resultMap.put("result_desc", "查询成功");
		resultMap.put("user_list", showFashionlist);
		JsonUtil.WriteJson("showFashionPer", resultMap, request, response);
		}
	}
}
