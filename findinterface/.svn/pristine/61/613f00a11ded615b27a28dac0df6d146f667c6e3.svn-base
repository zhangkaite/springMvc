package com.zkt.find.common.web;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.zkt.find.common.service.ICommonService;

/**
 * 
 *  工具类。
 *
 *  @author 凯特
 *  @version 1.0
 *
 *  <pre>
 *  使用范例：
 *  创建时间:2015-3-29 下午2:37:43
 *  修改历史：
 *     ver     修改日期     修改人  修改内容
 * ──────────────────────────────────
 *   V1.00  2015-3-29 下午2:37:43   初版
 *
 *  </pre>
 */
@Controller
@RequestMapping("/common")
public class CommonController {

	@Autowired
	private ICommonService service;
	/**
	 * 
	 *  获取总页数。
	 *  @author 凯特
	 */
	@RequestMapping("getTotalPage")
	public void getTotalPage(String everypage, String[] collections,String othercollections, String search, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		List<String> list = new ArrayList<String>();
		list = service.getTotalPage(everypage, collections,othercollections);
		JSONArray jsonArray = JSONArray.fromObject(list);
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().print(jsonArray);
	}
	
	/**
	 * 重写分页
	 */
	@RequestMapping("getTotalPages")
	public void getTotalPages(String everypage, String collections, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		List<String> list = new ArrayList<String>();
		//collections=URLDecoder.decode(collections,"UTF-8");
		list = service.getTotalPages(everypage, collections);
		JSONArray jsonArray = JSONArray.fromObject(list);
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().print(jsonArray);
	}
	
	
	/**
	 * 
	 *  抓取节目单。
	 *  @author 凯特
	 */
	@RequestMapping("grabEpg")
	public void grabEpg(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ResourceBundle bundle = ResourceBundle.getBundle("resources");
		String grabEpg_host_url = bundle.getString("grabEpg_host_url");
		URL url = new URL(grabEpg_host_url);
		HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
		urlConnection.setDoOutput(true);// URL 连接可用于输入和/或输出
		urlConnection.setDoInput(true);// URL 连接可用于输入和/或输出
		urlConnection.setUseCaches(false);
		OutputStreamWriter osw = new OutputStreamWriter(urlConnection.getOutputStream());
		// 设置包体
		osw.flush();
		osw.close();
		// 取得返回包体
		InputStream in = urlConnection.getInputStream();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in, "utf-8"));// 取得返回内容
		StringBuffer temp = new StringBuffer();
		String line = bufferedReader.readLine();
		while (line != null) {
			temp.append(line);
			line = bufferedReader.readLine();
		}
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().print(temp);
	}

	/**
	 * 
	 *  校验排序是否正确。
	 *  @author 凯特
	 */
	@RequestMapping("checkOrder")
	public void checkOrder(String[] idstr, String[] orderstr, String tablename, String ordercolumn, String idname,
			String[] collections, HttpServletRequest request, HttpServletResponse response) throws IOException {
		String returnValue = service.checkOrder(idstr, orderstr, tablename, ordercolumn, idname, collections);
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().print(returnValue);
	}
	
	
	 /**
     * 文件下载
     * 
     * @param fname 文件名称（含后缀）
     * @throws IOException
     */
    @RequestMapping("/down")
    public ResponseEntity<byte[]> downFile(@RequestParam(required = true) String fname,@RequestParam(required = true) String secpath)  {
        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String path = req.getSession().getServletContext().getRealPath("/WEB-INF".concat(secpath));
         try {
			fname=URLDecoder.decode(fname, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
        //默认文件名称
        /*
        try {
            downFileName = URLEncoder.encode(downFileName, "UTF-8");//转码解决IE下文件名乱码问题
        } catch (Exception e) {
            e.printStackTrace();
        }
         */
        //Http响应头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", fname);
 
        try {
            return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(new File(path + "/" + fname)),
                                              headers,
                                              HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            //日志
            //TODO
        }
         
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", "error.txt");
        return new ResponseEntity<byte[]>("文件不存在.".getBytes(), headers, HttpStatus.OK);
    }
	
	
	
}

