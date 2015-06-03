package com.zkt.find.login.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zkt.find.common.util.JsonUtil;
import com.zkt.find.common.util.MD5;
import com.zkt.find.common.util.SessionContext;
import com.zkt.find.login.service.ILoginService;
import com.zkt.find.register.entity.RegisterEntity;

/**
 * @author a
 * 用户登录接口
 *
 */
@Controller
@RequestMapping("/loginctl")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class LoginController {
	@Autowired
	private ILoginService service;
	MD5 md5=new MD5();
	private static Logger logger=LogManager.getLogger(LoginController.class);
	private SessionContext session = SessionContext.getInstance();
	//@RequestMapping("/login")
	@RequestMapping(value ="/login",method ={RequestMethod.GET, RequestMethod.POST})
	public void login(String data,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		String user_name=request.getParameter("user_name");
		String user_pass=request.getParameter("user_pass");
		String type=request.getParameter("type");
		Map resultmap=new HashMap();
		if((user_name==null || user_name =="")||(user_pass==null || user_pass =="")||(type==null || type ==""))
		{
			resultmap.put("result_code", "9");
			resultmap.put("result_desc", "参数不能为空");
			resultmap.put("user_id", "");
			JsonUtil.WriteJson("login", resultmap, request, response);
		}
		else{
			List<RegisterEntity> registerlist=service.userNoExit(user_name);
			if(registerlist.size()!=1)
			{
				resultmap.put("result_code", "2");
				resultmap.put("result_desc", "用户不存在");
				resultmap.put("user_id", "");
				JsonUtil.WriteJson("login", resultmap, request, response);
			}
			else
			{
				
				String md_user_pass=md5.getMD5ofStr(user_pass);
				RegisterEntity registerentity=registerlist.get(0);
				if(!md_user_pass.equals(registerentity.getUser_pass())) 
				{
					resultmap.put("result_code", "1");
					resultmap.put("result_desc", "用户名或密码错误");
					resultmap.put("user_id", "");
					JsonUtil.WriteJson("login", resultmap, request, response);
				}
				else
				{
					resultmap.put("result_code", "0");
					resultmap.put("result_desc", "登录成功");
					resultmap.put("user_id", registerentity.getUser_id());
					JsonUtil.WriteJson("login", resultmap, request, response);
					//写session
					 request.getSession(true).setAttribute("user_id", registerentity.getUser_id());
					 request.getSession(true).setAttribute("user_name", registerentity.getUser_name());
					 request.getSession(true).setAttribute("user_sex", registerentity.getUser_sex());
					 request.getSession(true).setAttribute("nickname", registerentity.getNickname());
					 request.getSession(true).setAttribute("introduction", registerentity.getIntroduction());
					 request.getSession(true).setAttribute("machineID", registerentity.getDevice_id());
					 request.getSession(true).setAttribute("user_address", registerentity.getUser_address());
					 request.getSession(true).setAttribute("user_type", registerentity.getUser_type());
					 request.getSession(true).setAttribute("lableList", registerentity.getUser_lableList());
					 request.getSession(true).setAttribute("user_createTime", registerentity.getUser_createTime());
					 session.AddSession(registerentity.getUser_id(), request.getSession(true));
				}
			}
			
		}
	}
	//@RequestMapping("/modifyPass")
	@RequestMapping(value ="/modifyPass",method ={RequestMethod.GET, RequestMethod.POST})
	public void modifyPass(String data,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		String user_id=request.getParameter("user_id");
		String old_pass=request.getParameter("old_pass");
		String new_pass=request.getParameter("new_pass");
		String user_name=request.getParameter("user_name");
		Map resultmap=new HashMap();
		if((user_id==null||user_id=="")||(old_pass==null||old_pass=="")||(new_pass==null||new_pass=="")||(user_name==null||user_name==""))
		{
			resultmap.put("result_code", "9");
			resultmap.put("result_desc", "接收参数为空");
			JsonUtil.WriteJson("modifyPass", resultmap, request, response);
		}
		else
		{
			String new_old_pass=md5.getMD5ofStr("old_pass");
			List<RegisterEntity> userlist=service.userNoExit(user_name);
			if(new_old_pass.equals(userlist.get(0).getUser_pass()))
			{
				resultmap.put("result_code", "2");
				resultmap.put("result_desc", "原始密码不正确");
				JsonUtil.WriteJson("modifyPass", resultmap, request, response);
			}
			else
			{
				String md_new_pass=md5.getMD5ofStr("new_pass");
				Boolean updateflag=service.updatePass(md_new_pass,user_id);
				if(updateflag)
				{
					resultmap.put("result_code", "0");
					resultmap.put("result_desc", "密码修改成功");
					JsonUtil.WriteJson("modifyPass", resultmap, request, response);
				}
				else
				{
					resultmap.put("result_code", "1");
					resultmap.put("result_desc", "密码修改失败");
					JsonUtil.WriteJson("modifyPass", resultmap, request, response);
				}
				
			}
		}
	}
}
