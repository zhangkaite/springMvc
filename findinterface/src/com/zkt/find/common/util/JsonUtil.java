/**
 * Copyright (c) 2015 张凯特 corporation All Rights Reserved.
 */

package com.zkt.find.common.util;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * 此处进行功能描述。
 * 
 * @author Administrator
 * @version 1.0
 * 
 *          <pre>
 *  使用范例：
 *  创建时间:2015-4-1 下午8:25:50
 *  修改历史：
 *     ver     修改日期     修改人  修改内容
 * ──────────────────────────────────
 *   V1.00  2015-4-1 下午8:25:50   初版
 * 
 * </pre>
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class JsonUtil
{
    private static Logger logger = LogManager.getLogger(JsonUtil.class);
    /**
     * 将Object对象转换成Json格式的数据
     * 
     * @param obj
     * @return
     * @throws Exception
     */
    public static String getObjectToJson(Object obj) throws Exception
    {
        String resultJson = null;
        ObjectMapper jsonMapper = new ObjectMapper();
        resultJson = jsonMapper.writeValueAsString(obj);
        if (resultJson != null)
        {
            return resultJson;
        }
        return null;
    }

    /**
     * 将Json字符串转换成相应的
     * 
     * @param json
     * @return
     * @throws Exception
     */

    public static Object getObjectFromJson(String json, Class objClass) throws Exception
    {
        ObjectMapper jsonMapper = new ObjectMapper();
        Object obj = jsonMapper.readValue(json, objClass);
        if (obj != null)
        {
            return obj;
        }
        return null;
    }

    /***
     * 封装返回json串
     */
    public static void WriteJson(String methodName,Map resultMap, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        response.setContentType("text/html;charset=utf-8");
        String json = "";
        try
        {
            json = JsonUtil.getObjectToJson(resultMap);
            logger.info("调用"+methodName+"方法返回的json数据:"+json);
        }
        catch (Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        response.getWriter().print(json);
    }

}
