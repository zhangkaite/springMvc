package com.zkt.find.register.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
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
import com.zkt.find.register.service.IRegisterService;

@Controller
@RequestMapping("/t_user")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class RegisterController {
	@Autowired
	private IRegisterService service;

	private static Logger logger = LogManager.getLogger(RegisterController.class);
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@RequestMapping(value ="/register",method ={RequestMethod.GET, RequestMethod.POST})
	public void userRegister(String data, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String user_name = request.getParameter("user_name");
		logger.info("user_name:"+user_name);
		String user_address = request.getParameter("user_address");
		String user_pass = request.getParameter("user_pass");
		String user_sex = request.getParameter("user_sex");
		String nickname = request.getParameter("nickname");
		String lableList = request.getParameter("lableList");
		String user_type = request.getParameter("user_type");
		String machineID = request.getParameter("device_id");
		String introduction = request.getParameter("introduction");
		String user_pic = request.getParameter("user_pic");
		Map resultMap = new HashMap();
		if ((user_name == null || user_name == "") || (user_address == null || user_address == "")
				|| (user_pass == null || user_pass == "") || (user_sex == null || user_sex == "")
				|| (nickname == null || nickname == "") || (lableList == null || lableList == "")
				|| (user_type == null || user_type == "") ) {
			resultMap.put("result_code", "9");
			resultMap.put("result_desc", "传递的参数不能为空");
			resultMap.put("user_id", "0");
			resultMap.put("SystemTime", sdf.format(new Date()));
			JsonUtil.WriteJson("register", resultMap, request, response);
		} else if (service.usernameverify(user_name) != 0) {
			resultMap.put("result_code", "2");
			resultMap.put("result_desc", "用户已注册");
			resultMap.put("user_id", "0");
			resultMap.put("SystemTime", sdf.format(new Date()));
			JsonUtil.WriteJson("register", resultMap, request, response);
		} else {
			String saveresult = service.saveregisteruser(user_name, user_pass, user_sex, nickname, introduction, machineID,
					user_pic, user_address, user_type, lableList);
			if (saveresult != null) {
				resultMap.put("result_code", "0");
				resultMap.put("result_desc", "注册信息保存成功");
				resultMap.put("user_id", saveresult);
				resultMap.put("SystemTime", sdf.format(new Date()));
				JsonUtil.WriteJson("register", resultMap, request, response);
			} else {
				resultMap.put("result_code", "1");
				resultMap.put("result_desc", "保存失败");
				resultMap.put("user_id", "0");
				resultMap.put("SystemTime", sdf.format(new Date()));
				JsonUtil.WriteJson("register", resultMap, request, response);
			}
		}

	}
	
}
