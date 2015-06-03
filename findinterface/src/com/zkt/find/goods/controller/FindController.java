package com.zkt.find.goods.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
import com.zkt.find.goods.entity.AdminRecommend;
import com.zkt.find.goods.entity.FindCommentsEntity;
import com.zkt.find.goods.entity.FindEntity;
import com.zkt.find.goods.entity.FindPicEntity;
import com.zkt.find.goods.entity.FindUserCollection;
import com.zkt.find.goods.entity.FindWantUserEntity;
import com.zkt.find.goods.entity.UserAttentionEntiy;
import com.zkt.find.goods.entity.UserLableEntity;
import com.zkt.find.goods.service.IFindService;

@Controller
@RequestMapping("/find")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class FindController {

	@Autowired
	private IFindService service;
	private static Logger logger = LogManager.getLogger(FindController.class);

	/** 发现信息发布 */
	// @RequestMapping("publish")
	@RequestMapping(value = "/publish", method = { RequestMethod.GET, RequestMethod.POST })
	public void PublishFind(String data, HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.info("发现信息发布,方法名称：PublishFind");
		Map resultMap = new HashMap();
		String user_id = request.getParameter("user_id");
		String find_desc = request.getParameter("find_desc");
		String find_attribute = request.getParameter("find_attribute");
		String find_lable = request.getParameter("find_lables");
		String find_pic = request.getParameter("find_pic");
		String user_lable = request.getParameter("user_lables");
		if ((null == find_lable || "".equals(find_lable)) || (null == user_id || "".equals(user_id))
				|| (null == find_desc || "".equals(find_desc)) || (null == find_attribute || "".equals(find_attribute))
				|| (null == find_pic || "".equals(find_pic)) || (null == user_lable || "".equals(user_lable))) {
			resultMap.put("result_code", "9");
			resultMap.put("result_desc", "传递的参数不能为空");
			JsonUtil.WriteJson("publish", resultMap, request, response);
			return;
		}
		String[] find_pics = find_pic.split(";");
		String[] user_lables = user_lable.split(";");
		boolean flag = service.savePublish(user_id, find_desc, find_attribute, find_lable, find_pics, user_lables);
		if (flag) {
			resultMap.put("result_code", "0");
			resultMap.put("result_desc", "发布的发现信息存储成功");
			JsonUtil.WriteJson("publish", resultMap, request, response);
		} else {
			resultMap.put("result_code", "9");
			resultMap.put("result_desc", "发布的发现信息存储失败");
			JsonUtil.WriteJson("publish", resultMap, request, response);
		}
	}

	/** 发布信息分页查询 */
	// @RequestMapping("search")
	@RequestMapping(value = "/search", method = { RequestMethod.GET, RequestMethod.POST })
	public void SearchFind(String data, HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.info("发布信息分页查询,方法名称：SearchFind");
		Map resultMap = new HashMap();
		String page_no = request.getParameter("page_no");
		String page_size = request.getParameter("page_size");
		if (null == page_no || null == page_size) {
			resultMap.put("result_code", "9");
			resultMap.put("result_desc", "传递的参数不能为空");
			JsonUtil.WriteJson("search", resultMap, request, response);
			return;
		}
		List<FindEntity> findList = service.getFindList(page_no, page_size);
		if (findList.size() == 0) {
			resultMap.put("result_code", "1");
			resultMap.put("result_desc", "获取达人发布的信息失败");
			JsonUtil.WriteJson("search", resultMap, request, response);
			return;
		}
		List<FindEntity> resultList = new ArrayList<FindEntity>();
		// 根据List 查询每条发现的想要用户、获取图片
		for (Iterator iterator = findList.iterator(); iterator.hasNext();) {
			FindEntity findEntity = (FindEntity) iterator.next();
			List<FindWantUserEntity> wantUserList = service.getWantUserInfo(findEntity);
			List<FindPicEntity> findPicList = service.getPicList(findEntity);
			findEntity.setWantUser(wantUserList);
			findEntity.setFindPic(findPicList);
			resultList.add(findEntity);
		}
		resultMap.put("result_code", "0");
		resultMap.put("result_desc", "获取达人发布的信息成功");
		resultMap.put("find_list", resultList);
		JsonUtil.WriteJson("search", resultMap, request, response);
	}

	/** 用户发现详情查询 */
	// @RequestMapping("detail")
	@RequestMapping(value = "/detail", method = { RequestMethod.GET, RequestMethod.POST })
	public void showUserFindDetail(String data, HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.info("用户发现详情查询,方法名称：showUserFindDetail");
		Map resultMap = new HashMap();
		String find_id = request.getParameter("find_id");
		// String user_id=request.getParameter("user_id");
		if (null == find_id) {
			resultMap.put("result_code", "9");
			resultMap.put("result_desc", "传递的参数不能为空");
			JsonUtil.WriteJson("detail", resultMap, request, response);
			return;
		}
		// 查询用户选择的达人标签
		List<UserLableEntity> userLableList = service.getUserLables(find_id);

		// 查询发现的用户评论
		List<FindCommentsEntity> findCommentsList = service.getComments(find_id);
		resultMap.put("result_code", "0");
		resultMap.put("result_desc", "数据获取成功");
		resultMap.put("userLableList", userLableList);
		resultMap.put("findCommentsList", findCommentsList);
		JsonUtil.WriteJson("detail", resultMap, request, response);
	}

	/** 用户关注 */
	// @RequestMapping("attentation")
	@RequestMapping(value = "/attentation", method = { RequestMethod.GET, RequestMethod.POST })
	public void userAttentation(String data, HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.info("用户关注,方法名称：userAttentation");
		Map resultMap = new HashMap();
		String user_id = request.getParameter("user_id");
		String attention_user_id = request.getParameter("attention_user_id");

		if (null == user_id || null == attention_user_id) {
			resultMap.put("result_code", "9");
			resultMap.put("result_desc", "传递的参数不能为空");
			JsonUtil.WriteJson("attentation", resultMap, request, response);
			return;
		}

		boolean flag = service.insertAttentionData(user_id, attention_user_id);
		if (flag) {
			resultMap.put("result_code", "0");
			resultMap.put("result_desc", "数据保存成功");
			JsonUtil.WriteJson("attentation", resultMap, request, response);
		} else {
			resultMap.put("result_code", "1");
			resultMap.put("result_desc", "数据保存失败");
			JsonUtil.WriteJson("attentation", resultMap, request, response);
		}
	}

	/** 用户想要发现 */
	// @RequestMapping("userWant")
	@RequestMapping(value = "/userWant", method = { RequestMethod.GET, RequestMethod.POST })
	public void UserWant(String data, HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.info("用户想要的发现,方法名称：UserWant");
		Map resultMap = new HashMap();
		String user_id = request.getParameter("user_id");
		String find_id = request.getParameter("find_id");

		if (null == user_id || null == find_id) {
			resultMap.put("result_code", "9");
			resultMap.put("result_desc", "传递的参数不能为空");
			JsonUtil.WriteJson("userWant", resultMap, request, response);
			return;
		}
		boolean flag = service.userWant(user_id, find_id);
		if (flag) {
			resultMap.put("result_code", "0");
			resultMap.put("result_desc", "数据保存成功");
			JsonUtil.WriteJson("userWant", resultMap, request, response);
		} else {
			resultMap.put("result_code", "1");
			resultMap.put("result_desc", "数据保存失败");
			JsonUtil.WriteJson("userWant", resultMap, request, response);
		}

	}

	/** 查看发现想要的用户 */
	// @RequestMapping("showUserWantList")
	@RequestMapping(value = "/showUserWantList", method = { RequestMethod.GET, RequestMethod.POST })
	public void ShowUserWantList(String data, HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.info("查看发现想要的用户,方法名称：ShowUserWantList");
		Map resultMap = new HashMap();
		String find_id = request.getParameter("find_id");
		String page_no = request.getParameter("page_no");
		String page_size = request.getParameter("page_size");
		if (null == find_id || null == page_no || null == page_size) {
			resultMap.put("result_code", "9");
			resultMap.put("result_desc", "传递的参数不能为空");
			JsonUtil.WriteJson("showUserWantList", resultMap, request, response);
			return;
		}
		List<FindWantUserEntity> ls = service.showUserWantList(find_id, page_no, page_size);
		resultMap.put("result_code", "0");
		resultMap.put("result_desc", "数据获取成功");
		resultMap.put("findWantUserList", ls);
		JsonUtil.WriteJson("showUserWantList", resultMap, request, response);

	}

	/** 用户评论发现 */
	// @RequestMapping("userComment")
	@RequestMapping(value = "/userComment", method = { RequestMethod.GET, RequestMethod.POST })
	public void UserComment(String data, HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.info("用户评论发现,方法名称：UserComment");
		Map resultMap = new HashMap();
		String find_id = request.getParameter("find_id");
		String user_id = request.getParameter("user_id");
		String comment_user_id = request.getParameter("comment_user_id");
		String comment_content = request.getParameter("comment_content");

		if (null == find_id || null == comment_user_id || null == comment_content || null == user_id) {
			resultMap.put("result_code", "9");
			resultMap.put("result_desc", "传递的参数不能为空");
			JsonUtil.WriteJson("userComment", resultMap, request, response);
			return;
		}
		boolean flag = service.UserComment(find_id, comment_user_id, comment_content, user_id);
		if (flag) {
			resultMap.put("result_code", "0");
			resultMap.put("result_desc", "数据保存成功");
			// resultMap.put("findWantUserList", ls);
			JsonUtil.WriteJson("userComment", resultMap, request, response);
		} else {
			resultMap.put("result_code", "1");
			resultMap.put("result_desc", "数据保存失败");
			// resultMap.put("findWantUserList", ls);
			JsonUtil.WriteJson("userComment", resultMap, request, response);
		}

	}

	/** 查看发现用户评论信息 */
	// @RequestMapping("showFindCommnets")
	@RequestMapping(value = "/showFindCommnets", method = { RequestMethod.GET, RequestMethod.POST })
	public void ShowFindCommnets(String data, HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.info("查看发现用户评论信息,方法名称：ShowFindCommnets");
		Map resultMap = new HashMap();
		String find_id = request.getParameter("find_id");
		String page_no = request.getParameter("page_no");
		String page_size = request.getParameter("page_size");
		if (null == find_id || null == page_no || null == page_size) {
			resultMap.put("result_code", "9");
			resultMap.put("result_desc", "传递的参数不能为空");
			JsonUtil.WriteJson("showFindCommnets", resultMap, request, response);
			return;
		}
		List<FindCommentsEntity> findCommentsList = service.ShowFindCommnets(find_id, page_no, page_size);
		if (findCommentsList.size() == 0) {
			resultMap.put("result_code", "1");
			resultMap.put("result_desc", "数据获取失败");
			JsonUtil.WriteJson("showFindCommnets", resultMap, request, response);
			return;
		}
		resultMap.put("result_code", "0");
		resultMap.put("result_desc", "数据获取成功");
		resultMap.put("findCommentsList", findCommentsList);
		JsonUtil.WriteJson("showFindCommnets", resultMap, request, response);

	}

	/** 用户收藏 */
	// @RequestMapping("userCollection")
	@RequestMapping(value = "/userCollection", method = { RequestMethod.GET, RequestMethod.POST })
	public void UserCollection(String data, HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.info("添加用户收藏,方法名称：UserCollection");
		Map resultMap = new HashMap();
		String find_id = request.getParameter("find_id");
		String user_id = request.getParameter("user_id");
		if (null == find_id || null == user_id) {
			resultMap.put("result_code", "9");
			resultMap.put("result_desc", "传递的参数不能为空");
			JsonUtil.WriteJson("userCollection", resultMap, request, response);
			return;
		}
		// 查看用户是否已收藏
		boolean isCollection = service.isCollection(find_id, user_id);
		if (isCollection) {
			resultMap.put("result_code", "2");
			resultMap.put("result_desc", "用户已收藏过该信息");
			// resultMap.put("findWantUserList", ls);
			JsonUtil.WriteJson("userCollection", resultMap, request, response);
			return;
		}

		boolean flag = service.UserCollection(find_id, user_id);
		if (flag) {
			resultMap.put("result_code", "0");
			resultMap.put("result_desc", "数据保存成功");
			// resultMap.put("findWantUserList", ls);
			JsonUtil.WriteJson("userCollection", resultMap, request, response);
		} else {
			resultMap.put("result_code", "1");
			resultMap.put("result_desc", "数据保存失败");
			// resultMap.put("findWantUserList", ls);
			JsonUtil.WriteJson("userCollection", resultMap, request, response);
		}
	}

	/** 删除用户收藏 */
	// @RequestMapping("delUserCollection")
	@RequestMapping(value = "/delUserCollection", method = { RequestMethod.GET, RequestMethod.POST })
	public void delUserCollection(String data, HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.info("删除用户收藏,方法名称：delUserCollection");
		Map resultMap = new HashMap();
		String find_id = request.getParameter("find_id");
		String user_id = request.getParameter("user_id");
		if (null == find_id || null == user_id) {
			resultMap.put("result_code", "9");
			resultMap.put("result_desc", "传递的参数不能为空");
			JsonUtil.WriteJson("delUserCollection", resultMap, request, response);
			return;
		}
		boolean flag = service.delUserCollection(find_id, user_id);
		if (flag) {
			resultMap.put("result_code", "0");
			resultMap.put("result_desc", "数据删除成功");
			// resultMap.put("findWantUserList", ls);
			JsonUtil.WriteJson("delUserCollection", resultMap, request, response);
		} else {
			resultMap.put("result_code", "1");
			resultMap.put("result_desc", "数据删除失败");
			// resultMap.put("findWantUserList", ls);
			JsonUtil.WriteJson("delUserCollection", resultMap, request, response);
		}

	}

	/** 查看用户收藏 */
	// @RequestMapping("showUserCollection")
	@RequestMapping(value = "/showUserCollection", method = { RequestMethod.GET, RequestMethod.POST })
	public void ShowUserCollection(String data, HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.info("查看查看用户收藏,方法名称：ShowUserCollection");
		Map resultMap = new HashMap();
		String user_id = request.getParameter("user_id");
		String page_no = request.getParameter("page_no");
		String page_size = request.getParameter("page_size");
		if (null == user_id || null == page_no || null == page_size) {
			resultMap.put("result_code", "9");
			resultMap.put("result_desc", "传递的参数不能为空");
			JsonUtil.WriteJson("showUserCollection", resultMap, request, response);
			return;
		}
		List<FindUserCollection> findUserCollectionsList = service.ShowUserCollection(user_id, page_no, page_size);
		List<FindUserCollection> userCollections = new ArrayList<FindUserCollection>();
		for (Iterator iterator = findUserCollectionsList.iterator(); iterator.hasNext();) {
			FindUserCollection findUserCollection = (FindUserCollection) iterator.next();
			String url = service.getPicUrl(findUserCollection.getFind_id());
			if (!"".equals(url)) {
				findUserCollection.setFindPicUrls(url);
				userCollections.add(findUserCollection);
			} else {
				continue;
			}
		}
		resultMap.put("result_code", "0");
		resultMap.put("result_desc", "数据获取成功");
		resultMap.put("findUserCollections", userCollections);
		JsonUtil.WriteJson("showUserCollection", resultMap, request, response);
	}

	/** 查看我的粉丝 */
	// @RequestMapping("showMyAttention")
	@RequestMapping(value = "/showMyAttention", method = { RequestMethod.GET, RequestMethod.POST })
	public void showMyAttention(String data, HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.info("查看我的粉丝,方法名称：showMyAttention");
		Map resultMap = new HashMap();
		String user_id = request.getParameter("user_id");
		String page_no = request.getParameter("page_no");
		String page_size = request.getParameter("page_size");
		if (null == user_id || null == page_no || null == page_size) {
			resultMap.put("result_code", "9");
			resultMap.put("result_desc", "传递的参数不能为空");
			JsonUtil.WriteJson("showMyAttention", resultMap, request, response);
			return;
		}
		List<UserAttentionEntiy> userAttentionList = service.showMyAttention(user_id, page_no, page_size);
		resultMap.put("result_code", "0");
		resultMap.put("result_desc", "数据获取成功");
		resultMap.put("userAttentionList", userAttentionList);
		JsonUtil.WriteJson("showMyAttention", resultMap, request, response);
	}

	/***
	 * 根据用户id和当前标签查看用户发现列表
	 */

	// @RequestMapping("showUserFindList")
	@RequestMapping(value = "/showUserFindList", method = { RequestMethod.GET, RequestMethod.POST })
	public void showUserFindList(String data, HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.info("根据用户id和当前标签查看用户发现列表,方法名称：showUserFindList");
		Map resultMap = new HashMap();
		String user_id = request.getParameter("user_id");
		String page_no = request.getParameter("page_no");
		String page_size = request.getParameter("page_size");
		String lable_id = request.getParameter("lable_id");
		if (null == user_id || null == page_no || null == page_size) {
			resultMap.put("result_code", "9");
			resultMap.put("result_desc", "传递的参数不能为空");
			JsonUtil.WriteJson("showMyAttention", resultMap, request, response);
			return;
		}
		List<FindEntity> findList = service.getUserFindList(user_id, lable_id, page_no, page_size);
		if (findList.size() == 0) {
			resultMap.put("result_code", "1");
			resultMap.put("result_desc", "获取达人发布的信息失败");
			JsonUtil.WriteJson("showUserFindList", resultMap, request, response);
			return;
		}
		List<FindEntity> resultList = new ArrayList<FindEntity>();
		// 根据List 查询每条发现的想要用户、获取图片
		for (Iterator iterator = findList.iterator(); iterator.hasNext();) {
			FindEntity findEntity = (FindEntity) iterator.next();
			List<FindWantUserEntity> wantUserList = service.getWantUserInfo(findEntity);
			List<FindPicEntity> findPicList = service.getPicList(findEntity);
			findEntity.setWantUser(wantUserList);
			findEntity.setFindPic(findPicList);
			resultList.add(findEntity);
		}
		resultMap.put("result_code", "0");
		resultMap.put("result_desc", "获取达人发布的信息成功");
		resultMap.put("find_list", resultList);
		JsonUtil.WriteJson("showUserFindList", resultMap, request, response);

	}

	/***
	 * 达人首页，后台管理员推荐
	 */
	// @RequestMapping("findHomePage")
	@RequestMapping(value = "/findHomePage", method = { RequestMethod.GET, RequestMethod.POST })
	public void findHomePage(String data, HttpServletRequest request, HttpServletResponse response) throws Exception {
		logger.info("达人首页，后台管理员推荐");
		Map resultMap = new HashMap();
		List<AdminRecommend> aRecommend = service.getAdminRecommend();
		if (aRecommend.size() == 0) {
			resultMap.put("result_code", "1");
			resultMap.put("result_desc", "达人首页信息获取失败");
			JsonUtil.WriteJson("findHomePage", resultMap, request, response);
			return;
		}
		resultMap.put("result_code", "0");
		resultMap.put("result_desc", "达人首页信息获取成功");
		resultMap.put("adminRecommend", aRecommend);
		JsonUtil.WriteJson("findHomePage", resultMap, request, response);
	}

}
